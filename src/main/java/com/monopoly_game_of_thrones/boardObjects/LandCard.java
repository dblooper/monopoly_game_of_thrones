package com.monopoly_game_of_thrones.boardObjects;

public class LandCard {
    private String name;
    private int price;
    private int originPrice;
    private int quantityOfVillages = 0;
    private int quantityOfCastles = 0;

    public LandCard(String cardName, int cardPrice) {
        this.name = cardName;
        this.price = cardPrice;
        this.originPrice = cardPrice;
    }

    public void setTheCostOfTheLand() {
        if(quantityOfVillages == 4 && quantityOfCastles == 0) {
            price = originPrice + 400;
        }
        else if(quantityOfVillages == 3 && quantityOfCastles == 0) {
            price = originPrice + 300;
        }
        else if(quantityOfVillages == 2 && quantityOfCastles == 0) {
            price = originPrice + 200;
        }
        else if(quantityOfVillages == 1 && quantityOfCastles == 0) {
            price = originPrice + 100;
        }
        else if(quantityOfVillages == 0 && quantityOfCastles == 1) {
            price = originPrice + 800;
        }
        else{
            price = originPrice;
        }
    }

    public void setNewVillageOnTheLand() {
            this.quantityOfVillages += 1;
            setTheCostOfTheLand();
        }
        public void removeVillageFromTheLand() {
            this.quantityOfVillages -= 1;
            setTheCostOfTheLand();
        }
    public void removeCastleFromTheLand() {
        this.quantityOfCastles -= 1;
        this.price = originPrice;
    }

    public void setNewCastleOnTheLand() {
            this.quantityOfVillages = 0;
            this.quantityOfCastles += 1;
            price = originPrice + 1000;
    }

    public String getName() {
        return name;
    }

    public int getQuantityOfVillages() {
        return quantityOfVillages;
    }

    public int getQuantityOfCastles() {
        return quantityOfCastles;
    }

    public int getPrice() {
        return  price;
    }

    public void setOriginPriceAndProperties() {
        this.price = originPrice;
        this.quantityOfCastles = 0;
        this.quantityOfVillages = 0;
    }

}
