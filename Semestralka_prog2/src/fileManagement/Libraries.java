/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fileManagement;

/**
 * class for creating libraries A,B and C
 *
 * @author daniel kohout
 */
public class Libraries {

    private static Library libA = new Library("A", "data/");
    private static Library libB = new Library("B", "data/");
    private static Library libC = new Library("C", "data/");

    public static void DoLibraries() {
        libA.createFile();
        libB.createFile();
        libC.createFile();
        doLevelA();
        doLevelB();
        doLevelC();
    }

    /**
     * getter for Library libA
     *
     * @return Library libA
     */
    public static Library getLibA() {
        return libA;
    }

    /**
     * getter for Library libC
     *
     * @return Library libC
     */
    public static Library getLibB() {
        return libB;
    }

    /**
     * getter for Library libC
     *
     * @return Library libC
     */
    public static Library getLibC() {
        return libC;
    }

    /**
     * method for filling up Library A if the library is empty
     */
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
            libA.addNewWord("oko", "eye");
            libA.addNewWord("ruka", "hand");
            libA.addNewWord("záda", "back");
            libA.addNewWord("nos", "nose");
            libA.addNewWord("pusa", "mouth");
            libA.addNewWord("ucho", "ear");
            libA.addNewWord("noha", "leg");
            libA.addNewWord("prst", "finger");
            libA.addNewWord("koleno", "knee");
            libA.addNewWord("zuby", "teeth");
            libA.overwriteFile();
        }
    }

    /**
     * method for filling up Library B if the library is empty
     */
    private static void doLevelB() {
        if (libB.getNumOfWords() == 0) {
            libB.addNewWord("rozdílnost", "variety");
            libB.addNewWord("vyděšený", "scared");
            libB.addNewWord("udržovat", "maintain");
            libB.addNewWord("hodnota", "worth");
            libB.addNewWord("běžný", "common");
            libB.addNewWord("až do", "until");
            libB.addNewWord("mysl", "mind");
            libB.addNewWord("docela, \nspíše", "rather");
            libB.addNewWord("údaj, číslo", "figure");
            libB.addNewWord("obchod,\ndohoda", "deal");
            libB.addNewWord("přidat", "add");
            libB.addNewWord("případ", "cause");
            libB.addNewWord("zdroj", "source");
            libB.addNewWord("k dispozici", "availible");
            libB.addNewWord("ačkoli", "although");
            libB.addNewWord("soustředit se", "focus");
            libB.addNewWord("akcie,\nzásoba", "stock");
            libB.addNewWord("dokud", "until");
            libB.addNewWord("považovat,\nzvážit", "consider");
            libB.addNewWord("nicméně,\navšak", "however");
            libB.addNewWord("zkušenost,\nzážitek", "experience");
            libB.addNewWord("přistoupit k", "access");
            libB.addNewWord("obsahovat", "include");
            libB.addNewWord("obsažený,\nzahrnutý", "involved");
            libB.addNewWord("určit,\nstanovit", "determine");
            libB.addNewWord("reklama", "commercial");
            libB.addNewWord("rys,\nvlastnost", "feature");
            libB.addNewWord("jestli", "whether");
            libB.addNewWord("lidský", "human");
            libB.addNewWord("stát za", "worth");
            libB.addNewWord("částečný,\nkonkrétní", "particular");
            libB.addNewWord("příklad", "instance");
            libB.addNewWord("nakoupit", "purchase");
            libB.addNewWord("poskytnout", "provide");
            libB.addNewWord("možná", "might");
            libB.addNewWord("rada", "board");
            libB.addNewWord("jídlo v\nhotelu\n/penze", "board");
            libB.addNewWord("jednou", "once");
            libB.addNewWord("nastavit", "set up");
            libB.addNewWord("interval", "range");
            libB.addNewWord("současný", "current");
            libB.addNewWord("půda, zem", "soil");
            libB.addNewWord("bytost", "being");
            libB.addNewWord("přístup", "access");
            libB.addNewWord("dodatečný", "additional");
            libB.addNewWord("tvůrčí", "creative");
            libB.addNewWord("náklad", "cost");
            libB.overwriteFile();
        }
    }

    /**
     * method for filling up Library C if the library is empty
     */
    private static void doLevelC() {
        if (libC.getNumOfWords() == 0) {
            libC.addNewWord("domnívat se", "assume");
            libC.addNewWord("mást,\nznemožnit", "baffle");
            libC.addNewWord("zkreslený", "biased");
            libC.addNewWord("soustředit", "concentrate");
            libC.addNewWord("posoudit,\nbrát ohled", "consider");
            libC.addNewWord("zvažovat,\nrozjímat", "contemplate");
            libC.addNewWord("cynický", "cynical");
            libC.addNewWord("odvodit,\ndedukovat", "deduce");
            libC.addNewWord("důkladný,\rozvážný", "deliberate");
            libC.addNewWord("dilema", "dilemma");
            libC.addNewWord("diskriminovat,\nrozlišovat", "discriminate");
            libC.addNewWord("nejistý,\npochybný", "dubious");
            libC.addNewWord("odhad", "estimate");
            libC.addNewWord("víra", "faith");
            libC.addNewWord("shromáždit,\nsebrat", "gather");
            libC.addNewWord("génius,\ngenialita", "genius");
            libC.addNewWord("úchop,\nsevřít", "grasp");
            libC.addNewWord("hádání,\ndohad", "quesswork");
            libC.addNewWord("předtucha,\ntušení", "hunch");
            libC.addNewWord("ideologie", "ideology");
            libC.addNewWord("důmyslný", "ingenious");
            libC.addNewWord("inspirace", "inspiration");
            libC.addNewWord("intuice", "intuition");
            libC.addNewWord("ospravedlnit,\nomlouvat", "justify");
            libC.addNewWord("naivní", "naive");
            libC.addNewWord("potucha,\nponětí", "notion");
            libC.addNewWord("optimistický", "optimistic");
            libC.overwriteFile();
        }
    }
}
