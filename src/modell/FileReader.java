package modell;

import controller.exceptions.FileReadError;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public final class FileReader {
    private String filePath;
    private static FileReader ourInstance = new FileReader();

    public static FileReader getInstance() {return ourInstance;
    }

    private FileReader() {
    }
    public final String readFile() throws FileReadError {

        JFileChooser openFile = new JFileChooser();
        if(openFile.showOpenDialog(null)==0){
            File file= openFile.getSelectedFile();
            filePath = file.toString();
        }
        try {
        return     readFromFile(filePath);
        }catch (FileReadError | IOException ex){
            throw new FileReadError();
        }



    }

    public final String getFilePath() {
        return filePath;
    }
/*
*A metodus az new gomb megnyomásakor kell, visszaállítani a fájl elérési
* útját null-ra.
* */
    public final void setNullFilePath() {
        this.filePath=null;
    }

    private String readFromFile(String filePath) throws FileReadError, IOException {
        BufferedReader reader=null;

        StringBuilder sb;
        try {
             reader=new BufferedReader(new java.io.FileReader(filePath));
            String line=null;

             sb=new StringBuilder();
            while ((line=reader.readLine())!=null){


                sb.append(line);
                sb.append(System.lineSeparator());
            }
        }catch (Exception e){
            throw new FileReadError();
        }finally {
            if(reader!=null)
            reader.close();
        }

       return sb.toString();
    }
}

