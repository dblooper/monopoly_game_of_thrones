package com.monopoly_game_of_thrones;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SetOfRandomChosenCards {
    private Map<Integer, RandomChosenCard> randomChosenCardMap = new HashMap<>();

    public SetOfRandomChosenCards() {
        RandomChosenCard goToJail1 = new RandomChosenCard("Lannisters always pay his debts. You are going to jail.",0,false,true);
        RandomChosenCard goToJail2 = new RandomChosenCard("You are suspected. King arrested you. You are going to jail.",0,false,true);
        RandomChosenCard payToTheBank1 = new RandomChosenCard("Pay 300$ to the box for your threats!",300,false,false);
        RandomChosenCard payToTheBank2 = new RandomChosenCard("Pay 200$ to the box for your new army!",200,false,false);
        RandomChosenCard payToTheBank3 = new RandomChosenCard("Pay 100$ to the box for food!",100,false,false);
        RandomChosenCard getFromTheBank1 = new RandomChosenCard("Old Gods bless you! You earned  200$!",200,true,false);
        RandomChosenCard getFromTheBank2 = new RandomChosenCard("Follow the night watch! You earned  50$!",50,true,false);
        RandomChosenCard getFromTheBank3 = new RandomChosenCard("The Mereen sends regards! You earned  150$!",150,true,false);
        randomChosenCardMap.put(1,goToJail1);
        randomChosenCardMap.put(2,goToJail2);
        randomChosenCardMap.put(3,payToTheBank1);
        randomChosenCardMap.put(4,payToTheBank2);
        randomChosenCardMap.put(5,payToTheBank3);
        randomChosenCardMap.put(6,getFromTheBank1);
        randomChosenCardMap.put(7,getFromTheBank2);
        randomChosenCardMap.put(8,getFromTheBank3);
    }

    public RandomChosenCard returnedCard() {
        Random random = new Random();
        int randomKeyValue = 1 + random.nextInt(8);
        return randomChosenCardMap.get(randomKeyValue);
    }

    public RandomChosenCard returnedCardHandling(User user, Bank bank, MoneyBox moneyBox, Label moneyBoxStatusLabel) {
        RandomChosenCard randomChosenCard = returnedCard();
        if(randomChosenCard.isGoToJailDecision()) {
            user.setPositionOfThePiece(10);
            user.setInJail(true);
            GridPane.setColumnIndex(user.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridXCoordinate(user.getPositionOfThePiece()));
            GridPane.setRowIndex(user.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridYCoordinate(user.getPositionOfThePiece()));
        }
        else if(randomChosenCard.getPrice()>0 && randomChosenCard.isSalary()) {
            user.addMoney(randomChosenCard.getPrice());
            bank.takeMoneyFromTheBank(randomChosenCard.getPrice());
        }
        else{
            user.substractMoney(randomChosenCard.getPrice());
            moneyBox.setMoneyCollected(randomChosenCard.getPrice());
            moneyBoxStatusLabel.setText(String.valueOf(moneyBox.getMoneyCollected()));
        }
        return randomChosenCard;
    }
}
