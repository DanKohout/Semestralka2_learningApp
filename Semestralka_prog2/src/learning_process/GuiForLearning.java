/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package learning_process;

import fileManagement.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author daniel kohout
 */
public class GuiForLearning {

    private MyFile file;
    private AnchorPane learningGUI;
    private Label vocabLabel;
    private Rectangle card;
    private Button nextBtn;
    private Button knowAlreadyB;
    private boolean transl = false;
    private int index = 0;

    /**
     * constructor for class
     *
     * @param file - file from which we will get the words
     * @param learningGUI - for making learning gui visible
     * @param vocabLabel - label for the word we want to project
     * @param card - rectangle which is "card" on which are the words projected
     * @param nextBtn - for going to next word
     */
    public GuiForLearning(MyFile file, AnchorPane learningGUI, Label vocabLabel, Rectangle card, Button nextBtn, Button knowAlreadyB) {
        this.file = file;
        this.learningGUI = learningGUI;
        this.vocabLabel = vocabLabel;
        this.card = card;
        this.nextBtn = nextBtn;
        this.knowAlreadyB = knowAlreadyB;
    }

    /**
     * "creates" another window meant for learning (creates it on top of the
     * menu)
     */
    public void start() {
        learningGUI.setVisible(true);
        setLabel();
        getNext();
        setKnowAlreadyB();
    }

    /**
     * for getting to next word(vocable)
     */
    private void getNext() {
        nextBtn.setOnAction(v -> {
            //System.out.println("in");
            if (file.getNumOfWords() != 0) {
                index++;
                transl = false;
                if (index >= file.getNumOfWords()) {
                    index = 0;
                }
                vocabLabel.setText(file.getWord(index).getWord());
            } else {
                vocabLabel.setText("file is\n empty");
            }
        });

    }

    /**
     * Method for managment of changing word to translation. It creates event
     * for clicking the "card" on which is the word projected
     */
    private void setLabel() {
        if (file.getNumOfWords() != 0) {
            vocabLabel.setText(file.getWord(index).getWord());
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

}
