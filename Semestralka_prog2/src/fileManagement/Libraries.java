/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagement;

import java.io.File;

/**
 *
 * @author daniel kohout
 */
public class Libraries {

    private static Library libA = new Library("A", "data/");
    private static Library libB = new Library("B", "data/");
    private static Library libC = new Library("C", "data/");

    public static void DoLibraries() {
        doLevelA();
        doLevelB();
        doLevelC();
    }

    public static Library getLibA() {
        return libA;
    }

    public static Library getLibB() {
        return libB;
    }

    public static Library getLibC() {
        return libC;
    }

    private static void doLevelA() {
        if (libA.getNumOfWords() == 0) {
            libA.addNewWord("já", "I");
            libA.addNewWord("skrz", "through");
            libA.addNewWord("jak,jako", "like");
            libA.addNewWord("málo", "few");
            libA.addNewWord("mezi", "between");
            libA.addNewWord("jiný,další", "other");
            libA.addNewWord("do", "into");
            libA.addNewWord("každý", "each");
            libA.addNewWord("kvůli", "because of");
            libA.addNewWord("vlastnní", "own");
            libA.addNewWord("část,díl", "part");
            libA.addNewWord("jiný,další", "another");
            libA.addNewWord("před", "before");
            libA.addNewWord("obor", "field");
            libA.addNewWord("hledat", "look for");
            libA.overwriteFile();
        }
    }

    private static void doLevelB() {
        if (libB.getNumOfWords() == 0) {
            libB.addNewWord("lepší", "better");
            libB.addNewWord("mít \npravdu", "be right");
            libB.addNewWord("jak,jako", "like");
            libB.addNewWord("málo", "few");
            libB.addNewWord("mezi", "between");
            libB.addNewWord("jiný,další", "other");
            libB.addNewWord("do", "into");
            libB.addNewWord("každý", "each");
            libB.addNewWord("kvůli", "because of");
            libB.addNewWord("vlastnní", "own");
            libB.addNewWord("část,díl", "part");
            libB.addNewWord("jiný,další", "another");
            libB.addNewWord("před", "before");
            libB.addNewWord("obor", "field");
            libB.addNewWord("hledat", "look for");
            libB.overwriteFile();
        }
    }

    private static void doLevelC() {
        if (libC.getNumOfWords() == 0) {
            libC.addNewWord("hodně opatrný", "catious");
            libC.addNewWord("skrz", "through");
            libC.addNewWord("jak,jako", "like");
            libC.addNewWord("málo", "few");
            libC.addNewWord("mezi", "between");
            libC.addNewWord("jiný,další", "other");
            libC.addNewWord("do", "into");
            libC.addNewWord("každý", "each");
            libC.addNewWord("kvůli", "because of");
            libC.addNewWord("vlastnní", "own");
            libC.addNewWord("část,díl", "part");
            libC.addNewWord("jiný,další", "another");
            libC.addNewWord("před", "before");
            libC.addNewWord("obor", "field");
            libC.addNewWord("hledat", "look for");
            libC.overwriteFile();
        }
    }
}
