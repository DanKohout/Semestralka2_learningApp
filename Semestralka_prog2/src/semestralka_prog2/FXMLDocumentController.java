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
import java.io.FileNotFoundException;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 * UI controller
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
    @FXML
    private TextField AddTranslTextField;
    @FXML
    private TextField AddWordTextField;
    @FXML
    private Button addWordBtn;
//------------learningGUI-----------------
    @FXML
    private Rectangle card;
    @FXML
    private Button nextBtn;
    @FXML
    private AnchorPane learningGUI;
    @FXML
    private Label vocabLabel;

    BufferedWriter reader1;
    ArrayList<MyFile> files;
    ObservableList<String> comboboxesValues;

    @FXML
    void addFile(ActionEvent event) {

    }

    @FXML
    void exitLearning(ActionEvent event) {
        learningGUI.setVisible(false);
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
        for (int i = 0; i < files.size(); i++) {
            comboboxesValues.add(files.get(i).getName());
        }

    }

    /**
     * checking for files in folder data
     */
    public void checkForFiles() {

    }

    private int getFileIndex(String name) throws FileNotFoundException {
        int index = -1;
        for (MyFile file : files) {
            if (name.equals(file.getName())) {
                index = files.indexOf(file);
                break;
            }
        }
        if (index == -1) {
            throw new FileNotFoundException("file not found");
        }
        return index;
    }

    @FXML
    void start(ActionEvent event) {
        //System.out.println(files.size());
        //System.out.println(comboboxesValues.size());
        //System.out.println(files.get(0).getName());
        //if(chooseFileComboBox.getValue())
        try {
            GuiForLearning learn = new GuiForLearning(files.get(getFileIndex(chooseFileComboBox.getValue())), learningGUI, vocabLabel, card, nextBtn);
            //learn.createWindow(learningGUI, vocabLabel, card);
            learn.start();
            learningGUI.setVisible(true);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createFile() throws IOException {
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
        chooseFileComboBox.setItems(comboboxesValues);
        deleteFileComboBox.setItems(comboboxesValues);
        learningGUI.setVisible(false);
        files = new ArrayList<MyFile>();
        update();
        try {
            createFile();
        } catch (IOException e) {
        }

    }

}
