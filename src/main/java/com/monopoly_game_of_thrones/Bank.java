package com.monopoly_game_of_thrones;

public class Bank {
    private int money;
    public Bank(int amountOfMoneyAtTheBeginning){
        this.money = amountOfMoneyAtTheBeginning;
    }

    public boolean takeMoneyFromTheBank(int amountOfMoney) {
        if (money>amountOfMoney) {
            this.money -= amountOfMoney;
            return true;
        }
        else {
            MessgaeBox.getInformationTextLabel().setText("No cash in the bank!");
            return false;
        }
    }

    public void giveMoneyToTheBank(int amountOfMoney) {
        if (amountOfMoney>0) {
            this.money += amountOfMoney;
        }
        else {
            System.out.println("Don't make a joke dude!");
        }
    }

    public int getMoneyInBank() {
        return money;
    }
}
