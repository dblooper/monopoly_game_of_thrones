package com.monopoly_game_of_thrones.boardObjects;

import com.monopoly_game_of_thrones.MonopolyGameOfThronesApplication;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import java.util.HashMap;
import java.util.Map;

public class User extends Piece {
    private String username;
    private boolean isHuman;
    private int money;
    private int userId;
    private Map<Integer, LandCard> userSetOfLandCards = new HashMap<>();
    private Map<Integer, SpecialCard> userSetOfSpecialCards = new HashMap<>();

    private FlowPane userFlowPaneCard = new FlowPane();

    private boolean isPlayable = true;
    private boolean computerEndOfTheTurn;
    private boolean takeRandomCardOnce = true;


    public User(String username, boolean isHuman, int userId, Image thePieceLabel) {
        super(thePieceLabel);
        this.username = username;
        this.isHuman = isHuman;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isHuman() {
        return isHuman;
    }


    public int getMoney() {
        return money;
    }
    public void addMoney(int money) {
        this.money += money;
    }
    public void substractMoney(int money) {
        this.money -= money;
    }

    public Map<Integer, LandCard> getUserSetOfLandCards() {
        return userSetOfLandCards;
    }
    public void addToUserSetOfLandCards(int position, LandCard landCardAdded) {
        userSetOfLandCards.put(position, landCardAdded);
    }

    public Map<Integer, SpecialCard> getUserSetOfSpecialCards() { return userSetOfSpecialCards;}
    public void addToUserSetOfSpecialCards(int position, SpecialCard specialCardAdded) {
        userSetOfSpecialCards.put(position,specialCardAdded);
        for(Map.Entry<Integer,SpecialCard> entry: userSetOfSpecialCards.entrySet()) {
            entry.getValue().setQuantityOfUserSpecialCards(userSetOfSpecialCards.size());
        }
    }

    public void placeTheFlowPaneCardStatus(){
        MonopolyGameOfThronesApplication.gridOfGame.add(userFlowPaneCard, 13, 6, 3, 3);
        GridPane.setValignment(userFlowPaneCard, VPos.TOP);
    }

    public void addToUserFlowPaneCardStatus(String cardName, int cardPrice){
        Label addCard = new Label(cardName + "; Price: " + cardPrice);
        addCard.maxWidth(5);
        addCard.maxHeight(2);
        userFlowPaneCard.getChildren().add(addCard);
    }
    public void userFlowPaneCardStatusReset(){
        userFlowPaneCard.getChildren().clear();
    }

    public void userFlowPaneCardActualization(){
        userFlowPaneCard.getChildren().clear();
        for (Map.Entry<Integer, LandCard> entryCardMap : userSetOfLandCards.entrySet()) {
            addToUserFlowPaneCardStatus(entryCardMap.getValue().getName(), entryCardMap.getValue().getPrice());
        }
        for (Map.Entry<Integer, SpecialCard> entryCardMap : userSetOfSpecialCards.entrySet()) {
            addToUserFlowPaneCardStatus(entryCardMap.getValue().getName(), entryCardMap.getValue().getPriceToPayForStaying());
        }
    }


    public boolean isUserEndOfTheTurn() {
        return computerEndOfTheTurn;
    }

    public void setUserEndOfTheTurn(boolean computerEndOfTheTurn) {
        this.computerEndOfTheTurn = computerEndOfTheTurn;
    }

    public boolean isTakeRandomCardOnce() {
        return takeRandomCardOnce;
    }

    public void setTakeRandomCardOnce(boolean takeRandomCardOnce) {
        this.takeRandomCardOnce = takeRandomCardOnce;
    }

    public boolean isPlayable() {
        if(money<0) {
            return isPlayable = false;
        }
        else{
            return isPlayable;
        }
    }

    public int getUserId(){
        return this.userId;
    }

}
