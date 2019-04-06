package view;

import controller.TextController;
import controller.enums.ButtonText;
import controller.exceptions.FileReadError;
import controller.exceptions.FileWriteError;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public final class STEJframe extends JFrame {

    private final JLabel CHARSIZEJLB = new JLabel("Betüméret:");
    private CharSizeCB charSizeCB;
    private OpenButton openButton;
    private SaveAsJbutton saveAsJbutton;
    private SaveButton saveButton;
    private NewJButton newJButton;
    private ScrollPaneWithTextArea textArea;


    public  STEJframe() {

        super("MinimalTextEditor");

        setLayout(null);
        setSize(new java.awt.Dimension(1000, 650));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.BLACK);
        CHARSIZEJLB.setBounds(240, 20, 90, 15);
        CHARSIZEJLB.setForeground(Color.white);
        charSizeCB = new CharSizeCB();

        textArea = new ScrollPaneWithTextArea();
        openButton = new OpenButton();
        saveButton = new SaveButton();
        saveAsJbutton = new SaveAsJbutton();
        newJButton = new NewJButton();
        contentPane.add(CHARSIZEJLB);
        contentPane.add(charSizeCB);
        contentPane.add(openButton);
        contentPane.add(saveAsJbutton);
        contentPane.add(saveButton);
        contentPane.add(newJButton);
        contentPane.add(textArea);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                if (textArea.fileText.getText().equals(""))
                    System.exit(0);
                if (JOptionPane.showConfirmDialog(null, "Dokumentum mentése?", "", JOptionPane.YES_NO_OPTION) == 1)
                    System.exit(0);
                try {
                    TextController.save(textArea.fileText.getText(), null);
                    System.exit(0);
                } catch (IOException | FileWriteError e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }


            }
        });

        setVisible(true);


    }


    class NewJButton extends JButton {
        NewJButton() {
            super("New");
            this.setBounds(900, 90, 68, 25);
            this.addActionListener(action -> {
                if (!textArea.fileText.getText().equals("")) {
                    try {
                        TextController.save(textArea.fileText.getText(), null);
                        textArea.fileText.setText("");
                        TextController.setNullFilePath();
                        Run.INSTANCE.setSteJframeTitle(TextController.getFilepath());

                    } catch (FileWriteError | IOException fileWriteError) {
                        JOptionPane.showMessageDialog(null, fileWriteError);
                    }

                }

            });

        }
    }

    class OpenButton extends JButton {

        OpenButton() {
            super("Open");
            this.setBounds(790, 90, 85, 25);
            this.addActionListener(action -> {
                try {
                    if (!textArea.fileText.getText().equals(""))
                        TextController.save(textArea.fileText.getText(), null);
                    String stringToText = TextController.addStringToText();
                    textArea.fileText.setText(stringToText);
                    textArea.revalidate();
                    textArea.repaint();
                    Run.INSTANCE.setSteJframeTitle(TextController.getFilepath());
                } catch (FileReadError | IOException | FileWriteError fe) {
                    JOptionPane.showMessageDialog(null, fe.toString());
                }


            });


        }
    }

    class ScrollPaneWithTextArea extends JScrollPane {

        JTextArea fileText;
        Font font;
        final String FONTNAME = null;
        final int FONTSTYLE = Font.BOLD;

        ScrollPaneWithTextArea() {


            super();
            font = new Font(FONTNAME, FONTSTYLE, (int) charSizeCB.getSelectedItem());
            fileText = new JTextArea();
            fileText.setText("");
            fileText.setColumns(20);
            fileText.setRows(5);
            fileText.setFont(font);

            this.setBounds(20, 50, 750, 510);
            setViewportView(fileText);
            setBackground(Color.white);
        }

    }

    class SaveAsJbutton extends JButton {
        
        private String buttonText;


        SaveAsJbutton() {
            super();
            buttonText = ButtonText.SAVEASTEXT.getBUTTONTEXT();
            setText(buttonText);


           setBounds(790, 150, 95, 25);
            this.addActionListener(action -> {

                try {

                    TextController.save(textArea.fileText.getText(), buttonText);
                } catch (FileWriteError | IOException fileWriteError) {
                    JOptionPane.showMessageDialog(null, fileWriteError);
                }


            });

        }
    }

    class SaveButton extends JButton {
        SaveButton() {
            super("Save");
            setBounds(790, 210, 68, 25);
            addActionListener(action -> {
                try {
                    TextController.save(textArea.fileText.getText(), null);
                } catch (FileWriteError | IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            });


        }
    }

    class CharSizeCB extends JComboBox {
        CharSizeCB() {

            super();

            Integer[] item = new Integer[]{13, 15, 17, 19, 21, 23, 25, 27};
            setModel(new DefaultComboBoxModel<>(item));
            setBounds(330, 10, 73, 24);
            addActionListener(action -> {

                if (action.getSource().equals(this)) {
                    textArea.fileText.setFont(new Font(textArea.FONTNAME, textArea.FONTSTYLE, (int) charSizeCB.getSelectedItem()));
                }
            });
        }
    }
}
