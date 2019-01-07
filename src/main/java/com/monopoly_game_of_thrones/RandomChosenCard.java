package com.monopoly_game_of_thrones;

public class RandomChosenCard {
    private String name;
    private int price;
    private boolean goToJailDecision;
    private boolean isSalary;

    public RandomChosenCard(String name, int price, boolean isSalary, boolean goToJailDecision) {
        this.name = name;
        this.price = price;
        this.isSalary = isSalary;
        this.goToJailDecision = goToJailDecision;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSalary() {
        return isSalary;
    }

    public boolean isGoToJailDecision() {
        return goToJailDecision;
    }
}
