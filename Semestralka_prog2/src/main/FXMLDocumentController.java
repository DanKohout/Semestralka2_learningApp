/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package main;

import Utils.SortEnum;
import fileManagement.*;
import learning_process.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.util.regex.*;

/**
 * GUI controller, somewhat similar to main class, because it communicates with
 * graphic elements.
 *
 * @author daniel kohout
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ComboBox<String> chooseFileComboBox;
    @FXML
    private ComboBox<String> deleteFileComboBox;
    @FXML
    private ComboBox<String> changeComboBox1;
    @FXML
    private TextField textfieldIN;
    @FXML
    private Label msgLabel;
//---------------library------------------
    @FXML
    private AnchorPane AnchorLibrary;
    @FXML
    private ComboBox<String> chooseLibraryComboBox;
    @FXML
    private Label LibraryMessageLabel;

//---------------history------------------
    @FXML
    private AnchorPane AnchorHistory;
    @FXML
    private TextArea historyTextArea;
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
    private TextField RemoveWordTextField;
    @FXML
    private TextArea displayWordsArea;
    @FXML
    private Label msgLabelChange;
//------------learningGUI-----------------
    @FXML
    private Rectangle card;
    @FXML
    private Button nextBtn;
    @FXML
    private Button knowAlreadyButton;
    @FXML
    private Button exitLearningBtn;
    @FXML
    private AnchorPane learningGUI;
    @FXML
    private Label vocabLabel;

    BufferedWriter reader1;
    ArrayList<MyFile> files;
    ArrayList<Library> libraries;
    ObservableList<String> comboboxesValues;
    ObservableList<String> comboboxesValuesLibraries;

    /**
     * method for adding file to memory(Arraylist files)
     */
    @FXML
    public void addFile() {
        try {
            if (!textfieldIN.getText().equals("")) {
                Pattern pattern = Pattern.compile("[ěščřžýáíéóúůďťňĎŇŤŠČŘŽÝÁÍÉÚŮĚÓA-Za-z0-9/_]", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(textfieldIN.getText());
                if (matcher.find()) {
                    System.out.println(textfieldIN.getText());
                    String[] s = textfieldIN.getText().split("/");
                    String name = s[s.length - 1];
                    String folder = "";
                    for (int i = 0; i < s.length - 1; i++) {
                        folder += s[i] + "/";
                    }
                    MyFile f = new MyFile(name, folder);
                    f.createFile();
                    files.add(f);
                    update();
                    msgLabel.setText("");
                } else {
                    msgLabel.setText("Try different name, it \ndoes not match regex of\n this program");
                }
            } else {
                msgLabel.setText("You need to write path \nfirst");
            }
        } catch (IOException e) {
            msgLabel.setText("Adding of file failed\n(Input exception)");
        }
    }

    /**
     * method for removing file from memory (not physically removing file, but
     * removing it from ArrayList files)
     */
    @FXML
    public void removeFile() {
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

    /**
     * method for refreshing all comboboxes
     */
    private void update() {
        comboboxesValues.clear();
        comboboxesValuesLibraries.clear();
        for (int i = 0; i < files.size(); i++) {
            comboboxesValues.add(files.get(i).getName());
        }
        for (int i = 0; i < libraries.size(); i++) {
            comboboxesValuesLibraries.add(libraries.get(i).getName());
        }
    }

    /**
     * method for finding File index by its name
     *
     * @param name of the File
     * @return index of found file or -1 if file was not found
     */
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

    /**
     * method for starting learning process with chosen file
     */
    @FXML
    public void start() {
        try {
            try {
                GuiForLearning learn = new GuiForLearning(files.get(getFileIndex(chooseFileComboBox.getValue())), learningGUI, vocabLabel, card, nextBtn, knowAlreadyButton, exitLearningBtn);
                learn.start();
                msgLabel.setText("");
            } catch (NullPointerException e) {
                msgLabel.setText("File not chosen.");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /*----------------------------Library methods---------------------------*/
    /**
     * method for going into Library page
     */
    @FXML
    public void goToLibrary() {
        AnchorLibrary.setVisible(true);
    }

    /**
     * method for starting learning process with chosen library
     */
    @FXML
    public void startLibrary() {
        try {
            GuiForLearning learn = new GuiForLearning(libraries.get(getLibraryIndex(chooseLibraryComboBox.getValue())), learningGUI, vocabLabel, card, nextBtn, knowAlreadyButton, exitLearningBtn);
            learn.start();
            LibraryMessageLabel.setText("Welcome to libraries");
        } catch (NullPointerException e) {
            LibraryMessageLabel.setText("File not chosen.");
        } catch (FileNotFoundException e) {
            LibraryMessageLabel.setText("File not found.");
        }
    }

    /**
     * method for finding Library index by its name
     *
     * @param name of the library
     * @return index of found file or -1 if file was not found
     */
    private int getLibraryIndex(String name) throws FileNotFoundException {
        int index = -1;
        for (Library lib : libraries) {
            if (name.equals(lib.getName())) {
                index = libraries.indexOf(lib);
                break;
            }
        }
        if (index == -1) {
            throw new FileNotFoundException();
        }
        return index;
    }

    /*------------------------History methods----------------------*/
    /**
     * method for displaying History page
     */
    @FXML
    public void goToHistory() {
        AnchorHistory.setVisible(true);
        historyTextArea.setText(" datum   :  počet naučených slov    : soubor\n" + BinaryFilesManager.tooString());
    }

    /**
     * method for clearing history
     */
    @FXML
    public void clearHistory() {
        BinaryFilesManager.removeAllRecords();
        historyTextArea.setText(" datum   :  počet naučených slov   : soubor\n" + BinaryFilesManager.tooString());
    }

    /**
     * method for default adding data/data.txt
     */
    public void createFile() {
        try {
            MyFile f = new MyFile("data", "data/");
            f.createFile();
            files.add(f);
            update();
        } catch (IOException e) {
            msgLabel.setText("input failed (IOException)");
        }
    }

    /*----------------------------methods for change--------------------------*/
    /**
     * method for getting into Change page
     */
    @FXML
    public void changeFile() {
        try {
            if ((changeComboBox1.getValue() != null)) {
                AnchorChangeFile.setVisible(true);
                addWordLabelFile.setText("\"" + changeComboBox1.getValue() + "\"");
                changeLabelFile.setText("\"" + changeComboBox1.getValue() + "\"");
                refreshChange();
                msgLabel.setText("Hello");
            } else {
                msgLabel.setText("choose file please");
            }
        } catch (FileNotFoundException e) {
            msgLabel.setText("File not found");
        }
    }

    /**
     * method for calling for sorting words by word
     */
    @FXML
    public void sortFileByWord() {
        sortFile(SortEnum.word);
    }

    /**
     * method for calling for sorting words by translation
     */
    @FXML
    public void sortFileByTranslation() {
        sortFile(SortEnum.translation);
    }

    /**
     * private method for calling sorting of words
     */
    private void sortFile(SortEnum s) {
        try {
            files.get(getFileIndex(changeComboBox1.getValue())).sort(s);
            refreshChange();
            msgLabelChange.setText("File sorted");
        } catch (FileNotFoundException e) {
            msgLabelChange.setText("File not found");
        }
    }

    /**
     * method for refreshing changes done to words displayed
     */
    private void refreshChange() throws FileNotFoundException {

        if ((changeComboBox1.getValue() != null)) {
            String words = files.get(getFileIndex(changeComboBox1.getValue())).getAllWords();
            displayWordsArea.setText(words);
        } else {
            msgLabelChange.setText("choose file please");
        }
    }

    /**
     * method for adding Word into file memory
     */
    @FXML
    public void addWord() {
        try {
            boolean a = (!AddWordTextField.getText().equals("")) && (!AddTranslTextField.getText().equals(""));
            if (a) {
                files.get(getFileIndex(changeComboBox1.getValue())).addNewWord(AddWordTextField.getText(), AddTranslTextField.getText());
                refreshChange();
                msgLabelChange.setText("Word added");
            } else {
                msgLabelChange.setText("not enough inputs");
            }
        } catch (FileNotFoundException e) {
            msgLabelChange.setText("File not found");
        }
    }

    /**
     * method for removing Word from file memory
     */
    @FXML
    public void removeWord() {
        try {
            if ((!RemoveWordTextField.getText().equals(""))) {
                System.out.println("in remove word");
                files.get(getFileIndex(changeComboBox1.getValue())).removeWord(RemoveWordTextField.getText());
                refreshChange();
                msgLabelChange.setText("Word removed");
            } else {
                msgLabelChange.setText("not enough inputs");
            }
        } catch (FileNotFoundException e) {
            msgLabelChange.setText("File not found");
        }
    }

    /**
     * saves all changes in txt file (overwrites file)
     */
    @FXML
    public void saveFile() {
        try {
            if ((changeComboBox1.getValue() != null)) {
                files.get(getFileIndex(changeComboBox1.getValue())).overwriteFile();
                msgLabelChange.setText("file saved");
            } else {
                msgLabelChange.setText("choose file please");
            }
        } catch (FileNotFoundException e) {
            msgLabelChange.setText("File saving failed \n(FileNotFoundException)");
        } catch (IOException e) {
            msgLabelChange.setText("File saving failed \n(input exception)");
        }
    }

    /*-----------------------------exit methods------------------------------*/
    /**
     * method for exiting Library page
     */
    @FXML
    public void exitLibrary() {
        AnchorLibrary.setVisible(false);
    }

    /**
     * method for exiting History page
     */
    @FXML
    public void exitHistory() {
        AnchorHistory.setVisible(false);
    }

    /**
     * method for exiting Change page
     */
    @FXML
    public void exitChange() {
        AnchorChangeFile.setVisible(false);
        AddWordTextField.setText("");
        AddTranslTextField.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxesValues = FXCollections.observableArrayList();
        comboboxesValuesLibraries = FXCollections.observableArrayList();
        chooseFileComboBox.setItems(comboboxesValues);
        deleteFileComboBox.setItems(comboboxesValues);
        changeComboBox1.setItems(comboboxesValues);
        chooseLibraryComboBox.setItems(comboboxesValuesLibraries);
        learningGUI.setVisible(false);
        AnchorChangeFile.setVisible(false);
        AnchorLibrary.setVisible(false);
        AnchorHistory.setVisible(false);
        files = new ArrayList<>();
        libraries = new ArrayList<>();
        Libraries.DoLibraries();
        libraries.add(Libraries.getLibA());
        libraries.add(Libraries.getLibB());
        libraries.add(Libraries.getLibC());
        BinaryFilesManager.startup();
        update();
        vocabLabel.setWrapText(true);
        vocabLabel.setMaxWidth(520);
        createFile();

    }

}
