package controller;

import controller.exceptions.FileReadError;
import controller.exceptions.FileWriteError;
import modell.FileReader;
import modell.FileWriter;

import java.io.IOException;

public final class TextController {
    private TextController(){}
    public  static String addStringToText() throws FileReadError,IOException{

        return FileReader.getInstance().readFile();
    }
    public static void save(String fileText, String buttonText) throws FileWriteError, IOException {
        FileWriter.getInstance().writeFile(fileText,buttonText);

    }
    public static void setNullFilePath(){
        FileReader.getInstance().setNullFilePath();
    }
    public static String getFilepath(){
       return FileReader.getInstance().getFilePath();
    }
}

