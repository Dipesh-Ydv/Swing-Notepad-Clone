package com.dipeshydv.notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea ta;
    StringBuilder text;
    Notepad() {
        setTitle("Notepad");

        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource(
                "com/dipeshydv/notepad/icons/notepad.png")
        );
        Image notepadImage = notepadIcon.getImage();
        // setting the icon for the app
        setIconImage(notepadImage);

        // creating menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);     // setting white color to the menu bar background

        // creating a menu option
        JMenu file = new JMenu("File");

        // creating menu items into the file menu option
        JMenuItem newDoc = new JMenuItem("New");
        newDoc.addActionListener(this);
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.META_MASK));
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.META_MASK));
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.META_MASK));
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.META_MASK));

        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        menuBar.add(file);

        JMenu edit = new JMenu("Edit");

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.META_MASK));
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.META_MASK));
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.META_MASK));
        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.META_MASK));

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);

        menuBar.add(edit);

        JMenu about = new JMenu("About");

        JMenuItem aboutNotepad = new JMenuItem("About Notepad");
        aboutNotepad.addActionListener(this);
        aboutNotepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.META_MASK));
        about.add(aboutNotepad);

        menuBar.add(about);

        // setting menu bar for the app
        setJMenuBar(menuBar);

        ta = new JTextArea();
        ta.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        // take the cursor to new line when the current line ends
        ta.setLineWrap(true);
        // take care that word will not split into subpart at the end of a line
        ta.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(ta);
        add(scrollPane);

        // This will get the frame size to maximum
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New" -> ta.setText("");
            case "Open" -> {
                JFileChooser chooser = new JFileChooser();
                // setting filter to not select every kind of file
                chooser.setAcceptAllFileFilterUsed(false);
                // Restricting to open only .txt files
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
                chooser.addChoosableFileFilter(restrict);
                int action = chooser.showOpenDialog(this);

                if (action != JFileChooser.APPROVE_OPTION) { return; }

                File file = chooser.getSelectedFile();

                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    ta.read(reader, null);
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            case "Save" -> {
                JFileChooser saveAs = new JFileChooser();
                saveAs.setApproveButtonText("Save");

                int action = saveAs.showSaveDialog(this);

                if (action != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                File fileName = new File(saveAs.getSelectedFile()+"");
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    ta.write(writer);
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            case "Print" -> {
                try {
                    ta.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
            case "Exit" -> System.exit(0);
            case "Copy" -> text = new StringBuilder(ta.getSelectedText());
            case "Paste" -> ta.insert(String.valueOf(text), ta.getCaretPosition());
            case "Cut" -> {
                text = new StringBuilder(ta.getSelectedText());
                ta.replaceRange("", ta.getSelectionStart(), ta.getSelectionEnd());
            }
            case "Select All" -> ta.selectAll();
            case "About Notepad" -> new About().setVisible(true);
        }
    }
}
