/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package semestralka_prog2;

import fileManagement.*;
import learning_process.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;

/**
 * file for
 *
 * @author daniel kohout
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button addTXTb;
    @FXML
    private ComboBox<String> chooseFileComboBox;
    @FXML
    private ComboBox<String> deleteFileComboBox;
    @FXML
    private Button removeFileTXT;
    @FXML
    private TextField textfieldIN;

    BufferedWriter reader1;
    ArrayList<MyFile> files;
    ObservableList<String> comboboxesValues;

    @FXML
    void addFile(ActionEvent event) {

    }

    @FXML
    void removeFile(ActionEvent event) {
        boolean removed = false;
        String name = deleteFileComboBox.getValue();
        Iterator itr = files.iterator();
        while (itr.hasNext()) {
            MyFile f = (MyFile) itr.next();
            if (f.getName().equals(name)) {
                itr.remove();
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("File removed from app memory successfully.");
        } else {
            System.out.println("File not found.");
        }
    }

    public void update() {
        comboboxesValues.clear();
        //deleteFileComboBox.getItems().clear();
        for (int i = 0; i < files.size() - 1; i++) {
            comboboxesValues.add(files.get(i).getName());
        }

    }

    @FXML
    void start(ActionEvent event) {

    }

    public void createFile() {
        files.add(new MyFile("data", "data/"));
        update();
    }

    public void readFile() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("a.txt"))) {

        } catch (IOException e) {
            System.out.println("" + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxesValues = FXCollections.observableArrayList();
        files = new ArrayList<MyFile>();
        //update();
        createFile();

    }

}
