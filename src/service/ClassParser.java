package service;

import entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassParser
{
    private static final String IGNORED_MODIFIERS = "(?:(?:static|final|volatile)\\s+)*";

    public ClassModel stringToClassModel(String classInStringFormat)
    {
        String source = removeComments(classInStringFormat);

        String className = extractClassName(source);

        List<FieldVariable> fields = extractFields(source);
        List<Constructor> constructors = extractConstructors(source, className);
        List<Method> methods = extractMethods(source, className);

        return new ClassModel(className, fields, constructors, methods);
    }

    private String extractClassName(String source)
    {
        Pattern pattern = Pattern.compile("\\bclass\\s+(\\w+)");
        Matcher matcher = pattern.matcher(source);

        if (matcher.find())
        {
            return matcher.group(1);
        }

        throw new IllegalArgumentException("No class name found.");
    }

    private List<FieldVariable> extractFields(String source)
    {
        List<FieldVariable> fields = new ArrayList<>();

        Pattern pattern = Pattern.compile(
                "\\b(public|private|protected)\\s+" +
                        IGNORED_MODIFIERS +
                        "([\\w<>\\[\\], ?]+)\\s+" +
                        "(\\w+)\\s*" +
                        "(?:=.*?)?;"
        );

        Matcher matcher = pattern.matcher(source);

        while (matcher.find())
        {
            String visibility = toUmlVisibility(matcher.group(1));
            String type = matcher.group(2).trim();
            String name = matcher.group(3).trim();

            fields.add(new FieldVariable(visibility, type, name));
        }

        return fields;
    }

    private List<Constructor> extractConstructors(String source, String className)
    {
        List<Constructor> constructors = new ArrayList<>();

        Pattern pattern = Pattern.compile(
                "\\b(public|private|protected)\\s+" +
                        className +
                        "\\s*\\(([^)]*)\\)"
        );

        Matcher matcher = pattern.matcher(source);

        while (matcher.find())
        {
            String argumentString = matcher.group(2).trim();
            List<Argument> arguments = extractArguments(argumentString);

            constructors.add(new Constructor(className, arguments));
        }

        return constructors;
    }

    private List<Method> extractMethods(String source, String className)
    {
        List<Method> methods = new ArrayList<>();

        Pattern pattern = Pattern.compile(
                "\\b(public|private|protected)\\s+" +
                        IGNORED_MODIFIERS +
                        "(?!class\\b)" +
                        "([\\w<>\\[\\], ?]+)\\s+" +
                        "(\\w+)\\s*" +
                        "\\(([^)]*)\\)"
        );

        Matcher matcher = pattern.matcher(source);

        while (matcher.find())
        {
            String visibility = toUmlVisibility(matcher.group(1));
            String returnType = matcher.group(2).trim();
            String name = matcher.group(3).trim();
            String argumentString = matcher.group(4).trim();

            if (name.equals(className))
            {
                continue;
            }

            List<Argument> arguments = extractArguments(argumentString);

            methods.add(new Method(visibility, returnType, name, arguments));
        }

        return methods;
    }

    private List<Argument> extractArguments(String argumentString)
    {
        List<Argument> arguments = new ArrayList<>();

        if (argumentString == null || argumentString.isBlank())
        {
            return arguments;
        }

        String[] argumentParts = argumentString.split(",");

        for (String argumentPart : argumentParts)
        {
            String cleanedArgument = argumentPart.trim();

            String[] words = cleanedArgument.split("\\s+");

            if (words.length >= 2)
            {
                String name = words[words.length - 1];
                String type = cleanedArgument.substring(0, cleanedArgument.length() - name.length()).trim();

                arguments.add(new Argument(type, name));
            }
        }

        return arguments;
    }

    private String toUmlVisibility(String javaVisibility)
    {
        return switch (javaVisibility)
        {
            case "public" -> "+";
            case "private" -> "-";
            case "protected" -> "#";
            default -> "~";
        };
    }

    private String removeComments(String source)
    {
        return source
                .replaceAll("//.*", "")
                .replaceAll("(?s)/\\*.*?\\*/", "");
    }
}