package presentation.viewmodel;

import entity.ClassModel;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import service.AstahTextGenerator;
import service.ClassParser;

public class AstahConverterViewModel
{
    private final ClassParser classParser;
    private final AstahTextGenerator astahTextGenerator;

    private ClassModel classModel;

    public AstahConverterViewModel()
    {
        this.classParser = new ClassParser();
        this.astahTextGenerator = new AstahTextGenerator();
    }

    public void handleConversion(String javaClass)
    {
        if (javaClass == null || javaClass.isBlank())
        {
            throw new IllegalArgumentException("Paste a Java class before converting.");
        }

        classModel = classParser.stringToClassModel(javaClass);
    }

    public void reset()
    {
        classModel = null;
    }

    public boolean hasClassName()
    {
        return !getClassName().isBlank();
    }

    public boolean hasAttributes()
    {
        return !getAttributesText().isBlank();
    }

    public boolean hasOperations(boolean hidePrivateMethods)
    {
        return !getOperationsText(hidePrivateMethods).isBlank();
    }

    public String handleCopyAttributes()
    {
        String attributes = getAttributesText();

        if (attributes.isBlank())
        {
            return "No attributes to copy.";
        }

        copyToClipboard(attributes);
        return "Attributes copied.";
    }

    public String handleCopyOperations(boolean hidePrivateMethods)
    {
        String operations = getOperationsText(hidePrivateMethods);

        if (operations.isBlank())
        {
            return "No operations to copy.";
        }

        copyToClipboard(operations);
        return "Operations copied.";
    }

    public String handleCopyClassName()
    {
        String className = getClassName();

        if (className.isBlank())
        {
            return "No class name to copy.";
        }

        copyToClipboard(className);
        return "Class name copied.";
    }

    public String getClassName()
    {
        if (classModel == null)
        {
            return "";
        }

        return classModel.getClassName();
    }

    private String getAttributesText()
    {
        if (classModel == null)
        {
            return "";
        }

        return astahTextGenerator.classModelToAstahFields(classModel);
    }

    private String getOperationsText(boolean hidePrivateMethods)
    {
        if (classModel == null)
        {
            return "";
        }

        return astahTextGenerator.classModelToAstahMethods(classModel, hidePrivateMethods);
    }

    private void copyToClipboard(String text)
    {
        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        Clipboard.getSystemClipboard().setContent(content);
    }
}