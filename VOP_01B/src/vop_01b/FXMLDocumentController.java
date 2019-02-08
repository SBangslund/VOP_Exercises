package vop_01b;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import polymorphism.ShapeFacade;

public class FXMLDocumentController implements Initializable {

    private final ToggleGroup radioGroup = new ToggleGroup();

    @FXML
    private TextField parameter01;
    @FXML
    private TextField parameter02;
    @FXML
    private RadioButton radioEllipse;
    @FXML
    private RadioButton radioRectangle;
    @FXML
    private RadioButton radioCircle;
    @FXML
    private RadioButton radioSquare;
    @FXML
    private TextArea resultBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioCircle.setToggleGroup(radioGroup);
        radioCircle.setUserData(ShapeFacade.SHAPES.CIRCLE);

        radioEllipse.setToggleGroup(radioGroup);
        radioEllipse.setUserData(ShapeFacade.SHAPES.ELLIPSE);

        radioRectangle.setToggleGroup(radioGroup);
        radioRectangle.setUserData(ShapeFacade.SHAPES.RECTANGLE);

        radioSquare.setToggleGroup(radioGroup);
        radioSquare.setUserData(ShapeFacade.SHAPES.SQUARE);

        radioEllipse.setSelected(true);
    }

    @FXML
    private void handleGetInfo(ActionEvent event) {
        ShapeFacade.SHAPES shape = (ShapeFacade.SHAPES) radioGroup.getSelectedToggle().getUserData();
        ShapeFacade facade = ShapeFacade.getInstance();
        String info;
        if (!parameter02.isVisible()) {
            info = facade.getShapeInfo(shape, Double.valueOf(parameter01.getText()));
        } else {
            info = facade.getShapeInfo(shape, new double[]{
                Double.valueOf(parameter01.getText()),
                Double.valueOf(parameter02.getText())
            });
        }
        resultBox.setText(info);
    }

    @FXML
    private void onClicked(MouseEvent event) {
        if (radioCircle.isSelected() || radioSquare.isSelected()) {
            parameter02.setVisible(false);
        } else {
            parameter02.setVisible(true);
        }
    }
}
