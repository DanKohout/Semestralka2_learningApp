/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package learning_process;

import fileManagement.*;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 * This class is extension of FXMLDocumentController
 *
 * @author daniel kohout
 */
public class GuiForLearning {

    private Files file;
    private AnchorPane learningGUI;
    private Label vocabLabel;
    private Rectangle card;
    private Button nextBtn;
    private Button knowAlreadyB;
    private Button exit;
    private boolean transl = false;
    private int index = 0;

    /**
     * constructor for class GuiForLearning
     *
     * @param file - file from which we will get the words
     * @param learningGUI - for making learning gui visible
     * @param vocabLabel - label for the word we want to project
     * @param card - rectangle which is "card" on which are the words projected
     * @param nextBtn - for going to next word
     * @param knowAlreadyB -
     * @param exit - exiting Learning page
     */
    public GuiForLearning(Files file, AnchorPane learningGUI, Label vocabLabel, Rectangle card, Button nextBtn, Button knowAlreadyB, Button exit) {
        this.file = file;
        this.learningGUI = learningGUI;
        this.vocabLabel = vocabLabel;
        this.card = card;
        this.nextBtn = nextBtn;
        this.knowAlreadyB = knowAlreadyB;
        this.exit = exit;
    }

    /**
     * "creates" another page/window meant for learning
     */
    public void start() {
        learningGUI.setVisible(true);
        setLabel();
        getNext();
        setKnowAlreadyB();
        setExit();
    }

    /**
     * method for getting to next word(vocable)
     */
    private void getNext() {
        nextBtn.setOnAction(v -> {
            next();
        });

    }

    /**
     * changes to the next word in order
     */
    private void next() {
        if (file.getNumOfWords() != 0 && !file.knowAllWords()) {
            index++;
            transl = false;
            if (index >= file.getNumOfWords()) {
                index = 0;
            }
            if (file.getWord(index).getKnow()) {
                next();
            } else {
                vocabLabel.setText(file.getWord(index).getWord());
            }
        } else {
            vocabLabel.setText(" file is\n empty");
        }
    }

    /**
     * Method for managment of changing word to translation. It creates event
     * for clicking the "card" on which is the word projected
     */
    private void setLabel() {
        if (file.getNumOfWords() != 0) {
            if (file.getWord(index).getKnow()) {
                next();
            } else {
                vocabLabel.setText(file.getWord(index).getWord());
            }
        }
        transl = false;
        card.setOnMouseClicked(v -> {
            transl = !transl;
            if (file.getNumOfWords() != 0) {
                if (transl) {
                    vocabLabel.setText(file.getWord(index).getTransl());
                } else {
                    vocabLabel.setText(file.getWord(index).getWord());
                }
            } else {
                vocabLabel.setText(" file is\n empty");
            }
        });

    }

    private void setKnowAlreadyB() {
        knowAlreadyB.setOnAction(v -> {
            if (file.getNumOfWords() != 0) {
                file.getWord(index).know();
            } else {
                vocabLabel.setText("no words\nfound");
            }
        });
    }

    private void setExit() {
        exit.setOnAction(v -> {
            learningGUI.setVisible(false);
            BinaryFilesManager.addRecord(LocalDate.now(), file.getName(), file.nLearnedWords());
        });
    }

}
