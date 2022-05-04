/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package semestralka_prog2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * file for
 *
 * @author daniel kohout
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button addTXTb;
    @FXML
    private ComboBox<?> chooseFileComboBox;
    @FXML
    private ComboBox<?> deleteFileComboBox;
    @FXML
    private Button removeFileTXT;
    @FXML
    private TextField textfieldIN;

    @FXML
    void addFile(ActionEvent event) {

    }

    @FXML
    void removeFile(ActionEvent event) {

    }

    @FXML
    void start(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
