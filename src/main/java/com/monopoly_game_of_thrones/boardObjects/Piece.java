package com.monopoly_game_of_thrones.boardObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Piece {
    private final Image thePieceLabel;
    private int positionOfThePiece;
    private ImageView thePieceLabelAsNode;
    private int turnCounterInTheJail;
    private boolean isInJail;

    public Piece(Image thePieceLabel) {
        this.thePieceLabel = thePieceLabel;
        thePieceLabelAsNode = new ImageView(thePieceLabel);
        thePieceLabelAsNode.setFitHeight(30);
        thePieceLabelAsNode.setPreserveRatio(true);

    }

    public void addJailQuantityOfTurns() {
        if(isInJail && turnCounterInTheJail < 1) {
            this.positionOfThePiece = 10;
            this.turnCounterInTheJail += 1;
        }
        else{
            isInJail = false;
            turnCounterInTheJail = 0;
        }
    }

    public boolean isInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        this.isInJail = inJail;
    }

    public final ImageView getThePieceAsNode() {
        return thePieceLabelAsNode;
    }

    public int getPositionOfThePiece() {
        return positionOfThePiece;
    }

    public void setPositionOfThePiece(int positionOfThePiece) {
            this.positionOfThePiece = positionOfThePiece;
    }

}

