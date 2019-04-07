package com.monopolyGameOfThrones.setsOfGameData;

import com.monopolyGameOfThrones.boardObjects.SpecialCard;

import java.util.HashMap;
import java.util.Map;

public class SetOfSepcialCards {
    private Map<Integer, SpecialCard> specialCards;


    public SetOfSepcialCards() {
        SpecialCard specialCard1 = new SpecialCard("Lannister",200);
        SpecialCard specialCard2 = new SpecialCard("Baratheon",200);
        SpecialCard specialCard3 = new SpecialCard("Targaaryen",200);
        SpecialCard specialCard4 = new SpecialCard("Stark",200);
        this.specialCards = new HashMap<>();
        specialCards.put(5,specialCard1);
        specialCards.put(15,specialCard2);
        specialCards.put(25,specialCard3);
        specialCards.put(35,specialCard4);
    }

    public Map<Integer, SpecialCard> returnMap(){
        return specialCards;
    }

    public void putSpecialCardToTheSet(int position, SpecialCard specialCardAdded ) {
        this.specialCards.put(position,specialCardAdded);
    }

    public void removeCardFromMap(int boardPlaceNumber){
        specialCards.remove(boardPlaceNumber);
    }

    public SpecialCard getCard(int boardPlaceNumber){
        return specialCards.get(boardPlaceNumber);
    }
}
