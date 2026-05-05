package presentation.viewmodel;

import entity.ClassModel;
import service.AstahTextGenerator;
import service.ClassParser;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

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
        classModel = classParser.stringToClassModel(javaClass);
    }

    public void handleCopyAttributes()
    {
        copyToClipboard(getAttributesText());
    }

    public void handleCopyOperations(boolean hidePrivateMethods)
    {
        copyToClipboard(getOperationsText(hidePrivateMethods));
    }

    public void handleCopyClassName()
    {
        copyToClipboard(getClassName());
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