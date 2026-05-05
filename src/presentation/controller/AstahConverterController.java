package presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import presentation.viewmodel.AstahConverterViewModel;

public class AstahConverterController
{
    @FXML
    private Button copyClassNameBtn;
    @FXML
    private Button copyAttributesBtn;
    @FXML
    private Button copyOperationsBtn;
    @FXML
    private CheckBox privateMethodsCheckBox;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private Label infoLbl;

    @FXML
    private Label classNameLbl;

    private AstahConverterViewModel viewModel;

    @FXML
    public void initialize()
    {
        this.viewModel = new AstahConverterViewModel();

        resetConvertedState("Paste a Java class and press Convert.");

        inputTextArea.textProperty().addListener((observable, oldValue, newValue) ->
        {
            resetConvertedState("Input changed. Press Convert again.");
        });

        privateMethodsCheckBox.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            updateCopyButtons();
        });
    }

    @FXML
    public void convertPressed()
    {
        try
        {
            viewModel.handleConversion(inputTextArea.getText());

            classNameLbl.setText(viewModel.getClassName());
            updateCopyButtons();

            infoLbl.setText("Converted successfully.");
        }
        catch (IllegalArgumentException exception)
        {
            resetConvertedState(exception.getMessage());
        }
        catch (Exception exception)
        {
            resetConvertedState("Something went wrong while converting.");
        }
    }

    @FXML
    public void copyAttributesPressed()
    {
        infoLbl.setText(viewModel.handleCopyAttributes());
    }

    @FXML
    public void copyOperationsPressed()
    {
        boolean hidePrivateMethods = !privateMethodsCheckBox.isSelected();

        infoLbl.setText(viewModel.handleCopyOperations(hidePrivateMethods));
    }

    @FXML
    public void handleCheckBoxPressed()
    {
        updateCopyButtons();
        infoLbl.setText("Private method setting updated.");
    }

    @FXML
    public void copyClassNamePressed()
    {
        infoLbl.setText(viewModel.handleCopyClassName());
    }

    private void resetConvertedState(String message)
    {
        viewModel.reset();

        classNameLbl.setText("");
        infoLbl.setText(message);

        copyClassNameBtn.setDisable(true);
        copyAttributesBtn.setDisable(true);
        copyOperationsBtn.setDisable(true);
    }

    private void updateCopyButtons()
    {
        boolean hidePrivateMethods = !privateMethodsCheckBox.isSelected();

        copyClassNameBtn.setDisable(!viewModel.hasClassName());
        copyAttributesBtn.setDisable(!viewModel.hasAttributes());
        copyOperationsBtn.setDisable(!viewModel.hasOperations(hidePrivateMethods));
    }
}