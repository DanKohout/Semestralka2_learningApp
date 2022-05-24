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
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.shape.Rectangle;

/**
 * GUI controller, somewhat similar to main class, because it communicates with
 * graphic elements.
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
    private ComboBox<String> changeComboBox1;
    @FXML
    private Button removeFileTXT;
    @FXML
    private TextField textfieldIN;
    @FXML
    private Label msgLabel;

//-------------change file----------------
    @FXML
    private AnchorPane AnchorChangeFile;
    @FXML
    private Label addWordLabelFile;
    @FXML
    private Label changeLabelFile;
    @FXML
    private TextField AddTranslTextField;
    @FXML
    private TextField AddWordTextField;
    @FXML
    private TextArea displayWordsArea;
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
    public void addFile(ActionEvent event) throws IOException {
        if (!textfieldIN.getText().equals("")) {
            //System.out.println("in addfile");
            System.out.println(textfieldIN.getText());
            String[] s = textfieldIN.getText().split("/");
            String name = s[s.length - 1];
            String folder = "";
            for (int i = 0; i < s.length - 1; i++) {
                folder += s[i] + "/";
            }
            files.add(new MyFile(name, folder));
            update();
            msgLabel.setText("");
        } else {
            msgLabel.setText("You need to write path first");
        }
    }

    @FXML
    public void exitLearning(ActionEvent event) {
        learningGUI.setVisible(false);
    }

    @FXML
    public void removeFile(ActionEvent event) {
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
            update();
            msgLabel.setText("File removed from \napp memory successfully.");
        } else {
            msgLabel.setText("File not found.");
        }
    }

    private void update() {
        comboboxesValues.clear();
        for (int i = 0; i < files.size(); i++) {
            comboboxesValues.add(files.get(i).getName());
        }
    }

    /**
     * checking for predefined files in specific folder
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
            //learningGUI.setVisible(true);
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

    @FXML
    public void changeFile() throws FileNotFoundException {
        if ((changeComboBox1.getValue() != null)) {
            AnchorChangeFile.setVisible(true);
            addWordLabelFile.setText("\"" + changeComboBox1.getValue() + "\"");
            changeLabelFile.setText("\"" + changeComboBox1.getValue() + "\"");
            refreshChange();
        } else {
            msgLabel.setText("choose file please");
        }
    }

    private void refreshChange() throws FileNotFoundException {
        if ((changeComboBox1.getValue() != null)) {
            String words = files.get(getFileIndex(changeComboBox1.getValue())).getAllWords();
            displayWordsArea.setText(words);
        } else {
            msgLabel.setText("choose file please");
        }
    }

    @FXML
    public void addWord() {
        try {
            boolean a =/* (addWordComboBox1.getValue() != null) &&*/ (!AddWordTextField.getText().equals("")) && (!AddTranslTextField.getText().equals(""));
            if (a) {
                System.out.println("in add word");
                files.get(getFileIndex(changeComboBox1.getValue())).addNewWord(AddWordTextField.getText(), AddTranslTextField.getText());
                refreshChange();
            } else {
                msgLabel.setText("not enough inputs");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void exitChange(ActionEvent event) {
        AnchorChangeFile.setVisible(false);
        AddWordTextField.setText("");
        AddTranslTextField.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxesValues = FXCollections.observableArrayList();
        chooseFileComboBox.setItems(comboboxesValues);
        deleteFileComboBox.setItems(comboboxesValues);
        changeComboBox1.setItems(comboboxesValues);
        learningGUI.setVisible(false);
        AnchorChangeFile.setVisible(false);
        files = new ArrayList<MyFile>();
        update();
        try {
            createFile();
        } catch (IOException e) {
            System.out.println("Exception" + e.getMessage());
        }

    }

}
