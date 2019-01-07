package com.monopoly_game_of_thrones;

import java.util.HashMap;
import java.util.Map;

public class SetOfLandCards {
    private Map<Integer, LandCard> cards;

    public SetOfLandCards() {
        this.cards = new HashMap<>();
//        Creating cards
        LandCard landCard1 = new LandCard("Crastler's Keep",60);
        LandCard landCard2 = new LandCard("Fist Of First Men",60);
        LandCard landCard3 = new LandCard("The Nightfort",100);
        LandCard landCard4 = new LandCard("Mole's Town",100);
        LandCard landCard5 = new LandCard("Crossroads",120);
        LandCard landCard6 = new LandCard("Vales Dothrak",140);
        LandCard landCard7 = new LandCard("Qarth",140);
        LandCard landCard8 = new LandCard("Pentos",160);
        LandCard landCard9 = new LandCard("The Eyrie",180);
        LandCard landCard10 = new LandCard("Dragonstone",180);
        LandCard landCard11 = new LandCard("Moat Cailin",200);
        LandCard landCard12 = new LandCard("Harrenhal",220);
        LandCard landCard13 = new LandCard("The Dreadfort",220);
        LandCard landCard14 = new LandCard("The Twins",240);
        LandCard landCard15 = new LandCard("Astapor",260);
        LandCard landCard16 = new LandCard("Tunkai",260);
        LandCard landCard17 = new LandCard("Meereen",280);
        LandCard landCard18 = new LandCard("Castle Black",300);
        LandCard landCard19 = new LandCard("Pyke",300);
        LandCard landCard20 = new LandCard("Winterfell",320);
        LandCard landCard21 = new LandCard("Braavos",350);
        LandCard landCard22 = new LandCard("King's Landing",400);
// Adding cards to HashMap to corelate them with board places -> Key: board place number, Value ->card
        cards.put(1, landCard1);
        cards.put(3, landCard2);
        cards.put(6, landCard3);
        cards.put(8, landCard4);
        cards.put(9, landCard5);
        cards.put(11, landCard6);
        cards.put(13, landCard7);
        cards.put(14, landCard8);
        cards.put(16, landCard9);
        cards.put(18, landCard10);
        cards.put(19, landCard11);
        cards.put(21, landCard12);
        cards.put(23, landCard13);
        cards.put(24, landCard14);
        cards.put(26, landCard15);
        cards.put(27, landCard16);
        cards.put(29, landCard17);
        cards.put(31, landCard18);
        cards.put(32, landCard19);
        cards.put(34, landCard20);
        cards.put(37, landCard21);
        cards.put(39, landCard22);
    }

    public Map<Integer, LandCard> returnMap(){
        return cards;
    }

    public void removeCardFromMap(int boardPlaceNumber, LandCard card){
        cards.remove(boardPlaceNumber,card);
    }

    public LandCard getCard(int boardPlaceNumber){
        return cards.get(boardPlaceNumber);
    }

    public void putLandCardToTheSet(int position, LandCard landCardAdded ) {
        this.cards.put(position,landCardAdded);
    }

}
