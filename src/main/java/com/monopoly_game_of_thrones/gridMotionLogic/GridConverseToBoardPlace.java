package com.monopoly_game_of_thrones.gridMotionLogic;

public class GridConverseToBoardPlace {

    public static int converse1dTo2dGridYCoordinate(int positionOfPiece){

        if(positionOfPiece == 10) {
            return 0;
        }
        else if(positionOfPiece == 0) {
            return 12;
        }
        else if (positionOfPiece>0 && positionOfPiece < 11) {
            return 12 - (positionOfPiece + 1);
        }
        else if(positionOfPiece < 21) {
            return 0;
        }
        else if(positionOfPiece == 21) {
            return 2;
        }
        else if(positionOfPiece<30) {
            return positionOfPiece - 19;
        }
        else {
            return 12;
        }
    }

    public static int converse1dTo2dGridXCoordinate(int positionOfPiece) {
        if (positionOfPiece < 11) {
            return 0;
        }
        else if(positionOfPiece == 11) {
            return 2;
        }
        else if(positionOfPiece < 20) {
            return positionOfPiece - 9;
        }
        else if(positionOfPiece == 20) {
            return 12;
        }
        else if(positionOfPiece < 31) {
            return 12;
        }
        else if(positionOfPiece == 31) {
            return 10;
        }
        else if(positionOfPiece == 40) {
            return 0;
        }
        else{
            return 12 - (positionOfPiece-29);
        }

    }

}
