package vop_02f;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private TextField replaceField;
    @FXML
    private TextArea textField;
    @FXML
    private AnchorPane pane;

    private FileChooser fileChooser = null;
    private File file;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
    }

    @FXML
    private void handleReplaceAll(ActionEvent event) {
        replaceWords(textField.getText(), searchField.getText(), replaceField.getText());
    }

    @FXML
    private void handleOpenFile(ActionEvent event) {
        file = fileChooser.showOpenDialog(pane.getScene().getWindow());
        List<String> data = readFromTextFile(file);
        String result = "";

        for (String line : data) {
            result += line + "\n";
        }
        textField.setText(result);
    }

    @FXML
    private void handleSaveAs(ActionEvent event) {
        File newFile = fileChooser.showOpenDialog(pane.getScene().getWindow());
        writeToTextFile(textField.getText(), newFile);
    }

    private void replaceWords(String words, String search, String replace) {
        StringBuilder sb = new StringBuilder();

        Scanner lines = new Scanner(words);
        while (lines.hasNextLine()) {
            Scanner lineWords = new Scanner(lines.nextLine());
            while (lineWords.hasNext()) {
                String thisWord = lineWords.next();
                sb.append(thisWord.equals(search) ? replace + " " : thisWord + " ");
            }
            lineWords.close();
            sb.append("\n");
        }
        lines.close();
        textField.setText(sb.toString());
    }

    private void writeToTextFile(String data, File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                writer.append(scanner.nextLine() + "\n");
            }
            writer.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Could not find file: " + file.getAbsolutePath());
        }
    }

    private List<String> readFromTextFile(File file) {
        List<String> data = new ArrayList<>();
        String line = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                data.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Something went wrong at: " + line);
        }
        return data;
    }

}
