package com.monopoly_game_of_thrones.boardObjects;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessgaeBox {
    private static Label informationTextLabel = new Label();


    public static Label getInformationTextLabel() {
        informationTextLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        informationTextLabel.setFont(new Font("Papyrus",12));
        return informationTextLabel;
    }

    public static void setInformationTextLabel(String text){
        informationTextLabel.setText(text);
    }
}
