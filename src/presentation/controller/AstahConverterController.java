package presentation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import presentation.viewmodel.AstahConverterViewModel;

public class AstahConverterController
{
    @FXML
    private CheckBox privateMethodsCheckBox;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private Label infoLbl;
    @FXML
    private Label classNameLbl;

    private AstahConverterViewModel viewModel;

    public void initialize() {
        this.viewModel = new AstahConverterViewModel();
    }

    @FXML
    public void convertPressed()
    {
        viewModel.handleConversion(inputTextArea.getText());

        classNameLbl.setText(viewModel.getClassName());
        infoLbl.setText("Converted successfully");
    }

    @FXML
    public void copyAttributesPressed()
    {
        viewModel.handleCopyAttributes();
        infoLbl.setText("Attributes copied");
    }

    @FXML
    public void copyOperationsPressed()
    {
        boolean hidePrivateMethods = !privateMethodsCheckBox.isSelected();

        viewModel.handleCopyOperations(hidePrivateMethods);
        infoLbl.setText("Operations copied");
    }

    public void copyClassNamePressed()
    {
        viewModel.handleCopyClassName();
    }
}
