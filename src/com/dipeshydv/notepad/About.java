package com.dipeshydv.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame {
    About() {
        super("About Notepad");
        setBounds(400, 100, 600, 500);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/dipeshydv/notepad/icons/windows.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 60, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel windowImage = new JLabel(i3);
        windowImage.setBounds(70, 40, 400, 80);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("com/dipeshydv/notepad/icons/notepad.png"));
        Image i5 = i4.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel icon = new JLabel(i6);
        icon.setBounds(50, 180, 70, 70);

        JLabel text = new JLabel("<html>NOTEPAD<br>Owner: Dipesh Yadav<br>Version 1.0.0 (OS BUILD JAVA)<br>Yadav Corporation All Rights Reserved.</html>");
        text.setBounds(150, 115, 500, 200);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));

        JButton okBtn = new JButton("OK");
        okBtn.setBounds(150, 350, 120, 25);
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        add(okBtn);
        add(text);
        add(icon);
        add(windowImage);

//        setVisible(fa);
    }

}
