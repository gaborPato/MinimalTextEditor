package modell;

import controller.enums.ButtonText;
import controller.exceptions.FileWriteError;
import kotlin.jvm.Throws;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class FileWriter {

    private static FileWriter ourInstance = new FileWriter();

    public static FileWriter getInstance() {
        return ourInstance;
    }

    private FileWriter() {
    }

    public final void writeFile(String fileText, String buttonText) throws FileWriteError, IOException {
        FileReader fr = FileReader.getInstance();
        String filePath = fr.getFilePath();
        java.io.FileWriter fileWriter = null;
        File newFile = null;


        if (filePath == null || ButtonText.SAVEASTEXT.getBUTTONTEXT().equals(buttonText)) {
            JFileChooser saveFile = new JFileChooser();
            if (saveFile.showSaveDialog(null) == 0) {
                File selectedFile = saveFile.getSelectedFile();
                filePath = selectedFile.toString();
                newFile = new File(filePath);


            }
        } else {
            newFile = new File(filePath);

        }
        try {
            fileWriter = new java.io.FileWriter(newFile);
            fileWriter.write(fileText);
            System.out.println("save file");

        } catch (Exception e) {
            throw new FileWriteError();
        } finally {
            if (fileWriter != null)
                fileWriter.close();
        }

    }

}

