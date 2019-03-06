package vop_04a;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField inputText;
    @FXML
    private Label infoText;
    @FXML
    private TextArea cardsInfo;
    @FXML
    private RadioButton radio50;
    @FXML
    private RadioButton radio100;
    @FXML
    private RadioButton radio250;
    
    private NumberPlates numberPlates;
    private DeckOfCards deckOfCards;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numberPlates = new NumberPlates();
        deckOfCards = new DeckOfCards();
        cardsInfo.setText(deckOfCards.toString());
        
        radio50.setSelected(true);
        
        ToggleGroup group = new ToggleGroup();
        radio50.setToggleGroup(group);
        radio100.setToggleGroup(group);
        radio250.setToggleGroup(group);
    }    
    
    @FXML
    private void handleOnCheck(ActionEvent event) {
        infoText.setText(numberPlates.validate(inputText.getText()));
    }
    
    @FXML
    private void handleOnShuffle(ActionEvent event) {
        deckOfCards.shuffle(radio50.isSelected() ? 50 : radio100.isSelected() ? 100 : 250);
        cardsInfo.setText(deckOfCards.toString());
    }
}
