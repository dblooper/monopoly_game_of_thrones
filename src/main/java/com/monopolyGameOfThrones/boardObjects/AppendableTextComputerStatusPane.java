package com.monopolyGameOfThrones.boardObjects;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AppendableTextComputerStatusPane extends Text {
    public void appendText(String text) {
        setFont(new Font("Papyrus",10));
        setWrappingWidth(300);
        if (!(getText().equals(""))) setText(text + "\n" + getText());
        else setText(text);
    }
}
