package com.monopolyGameOfThrones.boardObjects;

public class MoneyBox {
    private int moneyCollected;

    public int getMoneyCollected() {
        return moneyCollected;
    }

    public void setMoneyCollected(int moneyCollected) {
        this.moneyCollected += moneyCollected;
    }
    public void clearMoneyBox(){
        this.moneyCollected = 0;
    }
}
