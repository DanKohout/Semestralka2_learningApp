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
    private boolean transl = false;
    private int index = 0;

    public GuiForLearning(MyFile file, AnchorPane learningGUI, Label vocabLabel, Rectangle card, Button nextBtn) {
        this.file = file;
        this.learningGUI = learningGUI;
        this.vocabLabel = vocabLabel;
        this.card = card;
        this.nextBtn = nextBtn;
    }

    /**
     * "creates" another window meant for learning (creates it on top of the
     * menu)
     */
    public void start() {
        setLabel();
        getNext();
    }

    private void getNext() {
        nextBtn.setOnAction(v -> {
            //System.out.println("in");
            index++;
            transl = false;
            if (index >= file.getNumOfWords()) {
                index = 0;
            }
            vocabLabel.setText(file.getWord(index).getWord());
        });

    }

    private void setLabel() {
        vocabLabel.setText(file.getWord(index).getWord());
        transl = false;
        card.setOnMouseClicked(v -> {
            transl = !transl;
            if (transl) {
                vocabLabel.setText(file.getWord(index).getTransl());
            } else {
                vocabLabel.setText(file.getWord(index).getWord());
            }
        });

    }

}
