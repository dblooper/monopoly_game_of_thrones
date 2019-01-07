package com.monopoly_game_of_thrones;

public class SpecialCard {
    private String name;
    protected int price;
    private int quantityOfSpecialCards;

    public SpecialCard(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void setQuantityOfUserSpecialCards(int quantityOfSpecialCards){
        this.quantityOfSpecialCards = quantityOfSpecialCards;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }

    public int getPriceToPayForStaying(){
        switch(quantityOfSpecialCards){
            case 2:
                return price = 350;
            case 3:
                return price = 600;
            case 4:
                return price = 800;
            default:
                return price;
        }
    }
}
