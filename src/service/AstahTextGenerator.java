package service;

import entity.*;

import java.util.ArrayList;
import java.util.List;

public class AstahTextGenerator
{
    public String classModelToAstahFields(ClassModel classModel)
    {
        StringBuilder builder = new StringBuilder();

        for (FieldVariable field : classModel.getFieldVariables())
        {
            builder.append(field.visibility())
                    .append(" ")
                    .append(field.name())
                    .append(": ")
                    .append(field.type())
                    .append(System.lineSeparator());
        }

        return builder.toString();
    }

    public String classModelToAstahMethods(ClassModel classModel)
    {
        StringBuilder builder = new StringBuilder();

        for (Constructor constructor : classModel.getConstructors())
        {
            builder.append("+ ")
                    .append(constructor.name())
                    .append("(")
                    .append(argumentsToString(constructor.arguments()))
                    .append(")")
                    .append(System.lineSeparator());
        }

        for (Method method : classModel.getMethods())
        {
            builder.append(method.visibility())
                    .append(" ")
                    .append(method.name())
                    .append("(")
                    .append(argumentsToString(method.arguments()))
                    .append(")")
                    .append(": ")
                    .append(method.returnType())
                    .append(System.lineSeparator());
        }

        return builder.toString();
    }

    private String argumentsToString(List<Argument> arguments)
    {
        List<String> result = new ArrayList<>();

        for (Argument argument : arguments)
        {
            result.add(argument.name() + ": " + argument.type());
        }

        return String.join(", ", result);
    }
}
