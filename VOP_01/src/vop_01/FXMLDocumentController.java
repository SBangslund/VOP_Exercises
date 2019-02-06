package vop_01;

import ancient_encryption.AtbashCipher;
import ancient_encryption.CeasarChiper;
import ancient_encryption.CipherInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField messageText;
    @FXML
    private RadioButton atbashRadio;
    @FXML
    private RadioButton ceasarRadio;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private TextField krypterText;
    @FXML
    private TextField dekrypterText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, CipherInterface.ALPHABETH.length - 1,
                CipherInterface.ALPHABETH.length / 2));

        atbashRadio.setSelected(true);

        ToggleGroup group = new ToggleGroup();
        atbashRadio.setToggleGroup(group);
        ceasarRadio.setToggleGroup(group);
    }

    @FXML
    private void handleKrypterOnAction(ActionEvent event) {
        CipherInterface cip = atbashRadio.isSelected() ? new AtbashCipher() : new CeasarChiper(spinner.getValue());
        krypterText.setText(cip.encrypt(messageText.getText()));
    }

    @FXML
    private void handleDekrypterButton(ActionEvent event) {
        CipherInterface cip = atbashRadio.isSelected() ? new AtbashCipher() : new CeasarChiper(spinner.getValue());
        dekrypterText.setText(cip.decrypt(krypterText.getText()));
    }
}
