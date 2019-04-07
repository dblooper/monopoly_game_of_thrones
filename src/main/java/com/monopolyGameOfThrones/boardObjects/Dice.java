package com.monopolyGameOfThrones.boardObjects;

import java.util.Random;

public class Dice {
    public static int diceThrow() {
        Random randomDicePipes = new Random();
        return randomDicePipes.nextInt(6) + 1;
    }

}
