package com.monopoly_game_of_thrones;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import java.util.HashMap;
import java.util.Map;

public class CollectionOfProperties {
    private Image castle = new Image("file:resources/pieces/castle.png");
    private Image village = new Image("file:resources/pieces/village.png");
    private Image dot = new Image("file:resources/pieces/dot.png");
    private Map<Integer, FlowPane> setOfProperties = new HashMap();
    private boolean noChangeRedToVillage;

    public void setIsBought(int boardPlaceNumber) {
        setOfProperties.get(boardPlaceNumber).getChildren().add(new ImageView(dot));
        noChangeRedToVillage = false;
    }

    public void setIsSold(int boardPlaceNumber) {
        setOfProperties.get(boardPlaceNumber).getChildren().clear();
        noChangeRedToVillage = false;
    }


    public void setVillageOnThePane(int boardPlaceNumber) {

        if(setOfProperties.get(boardPlaceNumber).getChildren().isEmpty()) {
            noChangeRedToVillage = false;
        }
        if(setOfProperties.get(boardPlaceNumber).getChildren().size()>1) {
            noChangeRedToVillage = false;
        }

        if(setOfProperties.get(boardPlaceNumber).getChildren().size() == 1 && !noChangeRedToVillage) {
            setOfProperties.get(boardPlaceNumber).getChildren().clear();
            noChangeRedToVillage = true;
        }
        setOfProperties.get(boardPlaceNumber).getChildren().add(new ImageView(village));
    }
    public void setCastleOnThePane(int boardPlaceNumber) {
        setOfProperties.get(boardPlaceNumber).getChildren().clear();
        setOfProperties.get(boardPlaceNumber).getChildren().add(new ImageView(castle));

    }

    public Map<Integer, FlowPane> getSetOfProperties() {
        return setOfProperties;
    }

    public void removieVIllageFromThePane(int boardPlaceNumber) {

        ObservableList<Node> children = setOfProperties.get(boardPlaceNumber).getChildren();
        if(children.size() >0) children.remove(children.size()-1);

        if(setOfProperties.get(boardPlaceNumber).getChildren().isEmpty()) {
            setOfProperties.get(boardPlaceNumber).getChildren().add(new ImageView(dot));
            noChangeRedToVillage = false;

        }

    }

    public void addPropertyFlowPanesTOTheGrid(){


        FlowPane propertyOfLand1 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip1 = new Tooltip("Land: Craster's Keep" +
                "\n Cost for staying:" +
                "\n-no property : 60$" +
                "\n-1 village: 160$" +
                "\n-2 villages: 260" +
                "\n-3 villages: 360$" +
                "\n-4 villages: 460$" +
                "\n-1 castle: 1060$");
        Tooltip.install(propertyOfLand1,tooltip1);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand1,1,10);

        FlowPane propertyOfLand2 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip2 = new Tooltip("Land: The Fist Of The First Men" +
                "\n Cost for staying:" +
                "\n-no property : 60$" +
                "\n-1 village: 160$" +
                "\n-2 villages: 260" +
                "\n-3 villages: 360$" +
                "\n-4 villages: 460$" +
                "\n-1 castle: 1060$");
        Tooltip.install(propertyOfLand2,tooltip2);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand2,1,8);

        FlowPane propertyOfLand3 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip3 = new Tooltip("Land: The Nightfort" +
                "\n Cost for staying:" +
                "\n-no property : 100$" +
                "\n-1 village: 200$" +
                "\n-2 villages: 300" +
                "\n-3 villages: 400$" +
                "\n-4 villages: 500$" +
                "\n-1 castle: 1100$");
        Tooltip.install(propertyOfLand3,tooltip3);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand3,1,5);

        FlowPane propertyOfLand4 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip4 = new Tooltip("Mole's Town" +
                "\n Cost for staying:" +
                "\n-no property : 100$" +
                "\n-1 village: 200$" +
                "\n-2 villages: 300" +
                "\n-3 villages: 400$" +
                "\n-4 villages: 500$" +
                "\n-1 castle: 1100$");
        Tooltip.install(propertyOfLand4,tooltip4);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand4,1,3);

        FlowPane propertyOfLand5 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip5 = new Tooltip("The Inn At The Crossroads" +
                "\n Cost for staying:" +
                "\n-no property : 120$" +
                "\n-1 village: 220$" +
                "\n-2 villages: 320" +
                "\n-3 villages: 420$" +
                "\n-4 villages: 520$" +
                "\n-1 castle: 1120$");
        Tooltip.install(propertyOfLand5,tooltip5);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand5,1,2);

        FlowPane propertyOfLand6 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand6.setAlignment(Pos.CENTER);
        Tooltip tooltip6 = new Tooltip("Land: Vales Dothrak" +
                "\n Cost for staying:" +
                "\n-no property : 140$" +
                "\n-1 village: 240$" +
                "\n-2 villages: 340" +
                "\n-3 villages: 440$" +
                "\n-4 villages: 540$" +
                "\n-1 castle: 1140$");
        Tooltip.install(propertyOfLand6,tooltip6);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand6,2,1);

        FlowPane propertyOfLand7 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand7.setAlignment(Pos.CENTER);
        Tooltip tooltip7 = new Tooltip("Land: Quarth" +
                "\n Cost for staying:" +
                "\n-no property : 140$" +
                "\n-1 village: 240$" +
                "\n-2 villages: 340" +
                "\n-3 villages: 440$" +
                "\n-4 villages: 540$" +
                "\n-1 castle: 1140$");
        Tooltip.install(propertyOfLand7,tooltip7);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand7,4,1);

        FlowPane propertyOfLand8 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand8.setAlignment(Pos.CENTER);
        Tooltip tooltip8 = new Tooltip("Land: Pentos" +
                "\n Cost for staying:" +
                "\n-no property : 160$" +
                "\n-1 village: 260$" +
                "\n-2 villages: 360" +
                "\n-3 villages: 460$" +
                "\n-4 villages: 560$" +
                "\n-1 castle: 1160$");
        Tooltip.install(propertyOfLand8,tooltip8);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand8,5,1);

        FlowPane propertyOfLand9 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand9.setAlignment(Pos.CENTER);
        Tooltip tooltip9 = new Tooltip("Land: The Eyrie" +
                "\n Cost for staying:" +
                "\n-no property : 180$" +
                "\n-1 village: 280$" +
                "\n-2 villages: 380" +
                "\n-3 villages: 480$" +
                "\n-4 villages: 580$" +
                "\n-1 castle: 1160$");
        Tooltip.install(propertyOfLand9,tooltip9);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand9,7,1);

        FlowPane propertyOfLand10 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand10.setAlignment(Pos.CENTER);
        Tooltip tooltip10 = new Tooltip("Land: Dragonstone" +
                "\n Cost for staying:" +
                "\n-no property : 180$" +
                "\n-1 village: 280$" +
                "\n-2 villages: 380" +
                "\n-3 villages: 480$" +
                "\n-4 villages: 580$" +
                "\n-1 castle: 1180$");
        Tooltip.install(propertyOfLand10,tooltip10);

        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand10,9,1);

        FlowPane propertyOfLand11 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand11.setAlignment(Pos.CENTER);
        Tooltip tooltip11 = new Tooltip("Land: Moat Cailin" +
                "\n Cost for staying:" +
                "\n-no property : 200$" +
                "\n-1 village: 300$" +
                "\n-2 villages: 400" +
                "\n-3 villages: 500$" +
                "\n-4 villages: 600$" +
                "\n-1 castle: 1200$");
        Tooltip.install(propertyOfLand11,tooltip11);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand11,10,1);

        FlowPane propertyOfLand12 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip12 = new Tooltip("Land: Harrenhal" +
                "\n Cost for staying:" +
                "\n-no property : 220$" +
                "\n-1 village: 320$" +
                "\n-2 villages: 420" +
                "\n-3 villages: 520$" +
                "\n-4 villages: 620$" +
                "\n-1 castle: 1220$");
        Tooltip.install(propertyOfLand12,tooltip12);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand12,11,2);

        FlowPane propertyOfLand13 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip13 = new Tooltip("Land: The Dreadfort" +
                "\n Cost for staying:" +
                "\n-no property : 220$" +
                "\n-1 village: 320$" +
                "\n-2 villages: 420" +
                "\n-3 villages: 520$" +
                "\n-4 villages: 620$" +
                "\n-1 castle: 1220$");
        Tooltip.install(propertyOfLand13,tooltip13);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand13,11,4);

        FlowPane propertyOfLand14 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip14 = new Tooltip("Land: The Twins" +
                "\n Cost for staying:" +
                "\n-no property : 240$" +
                "\n-1 village: 340$" +
                "\n-2 villages: 440" +
                "\n-3 villages: 540$" +
                "\n-4 villages: 640$" +
                "\n-1 castle: 1240$");
        Tooltip.install(propertyOfLand14,tooltip14);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand14,11,5);

        FlowPane propertyOfLand15 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip15 = new Tooltip("Land: Astapor" +
                "\n Cost for staying:" +
                "\n-no property : 260$" +
                "\n-1 village: 360$" +
                "\n-2 villages: 460" +
                "\n-3 villages: 560$" +
                "\n-4 villages: 660$" +
                "\n-1 castle: 1260$");
        Tooltip.install(propertyOfLand15,tooltip15);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand15,11,7);

        FlowPane propertyOfLand16 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip16 = new Tooltip("Land: Yunkai" +
                "\n Cost for staying:" +
                "\n-no property : 260$" +
                "\n-1 village: 360$" +
                "\n-2 villages: 460" +
                "\n-3 villages: 560$" +
                "\n-4 villages: 660$" +
                "\n-1 castle: 1260$");
        Tooltip.install(propertyOfLand16,tooltip16);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand16,11,8);

        FlowPane propertyOfLand17 = new FlowPane(Orientation.HORIZONTAL);
        Tooltip tooltip17 = new Tooltip("Land: Meereen" +
                "\n Cost for staying:" +
                "\n-no property : 280$" +
                "\n-1 village: 380$" +
                "\n-2 villages: 480" +
                "\n-3 villages: 580$" +
                "\n-4 villages: 680$" +
                "\n-1 castle: 1280$");
        Tooltip.install(propertyOfLand17,tooltip17);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand17,11,10);

        FlowPane propertyOfLand18 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand18.setAlignment(Pos.CENTER);
        Tooltip tooltip18 = new Tooltip("Land: Castle Black" +
                "\n Cost for staying:" +
                "\n-no property : 300$" +
                "\n-1 village: 400$" +
                "\n-2 villages: 500" +
                "\n-3 villages: 600$" +
                "\n-4 villages: 700$" +
                "\n-1 castle: 1300$");
        Tooltip.install(propertyOfLand18,tooltip18);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand18,10,11);

        FlowPane propertyOfLand19 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand19.setAlignment(Pos.CENTER);
        Tooltip tooltip19 = new Tooltip("Land: Pyke" +
                "\n Cost for staying:" +
                "\n-no property : 300$" +
                "\n-1 village: 400$" +
                "\n-2 villages: 500" +
                "\n-3 villages: 600$" +
                "\n-4 villages: 700$" +
                "\n-1 castle: 1300$");
        Tooltip.install(propertyOfLand19,tooltip19);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand19,9,11);

        FlowPane propertyOfLand20 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand20.setAlignment(Pos.CENTER);
        Tooltip tooltip20 = new Tooltip("Land: Winterfell" +
                "\n Cost for staying:" +
                "\n-no property : 320$" +
                "\n-1 village: 420$" +
                "\n-2 villages: 520" +
                "\n-3 villages: 620$" +
                "\n-4 villages: 720$" +
                "\n-1 castle: 1320$");
        Tooltip.install(propertyOfLand20,tooltip20);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand20,7,11);

        FlowPane propertyOfLand21 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand21.setAlignment(Pos.CENTER);
        Tooltip tooltip21 = new Tooltip("Land: Braavos" +
                "\n Cost for staying:" +
                "\n-no property : 350$" +
                "\n-1 village: 450$" +
                "\n-2 villages: 550" +
                "\n-3 villages: 650$" +
                "\n-4 villages: 750$" +
                "\n-1 castle: 1350$");
        Tooltip.install(propertyOfLand21,tooltip21);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand21,4,11);

        FlowPane propertyOfLand22 = new FlowPane(Orientation.VERTICAL);
        propertyOfLand22.setAlignment(Pos.CENTER);
        Tooltip tooltip22 = new Tooltip("Land: King's Landing" +
                "\n Cost for staying:" +
                "\n-no property : 400$" +
                "\n-1 village: 500$" +
                "\n-2 villages: 600" +
                "\n-3 villages: 700$" +
                "\n-4 villages: 800$" +
                "\n-1 castle: 1400$");
        Tooltip.install(propertyOfLand22,tooltip22);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfLand22,2,11);

        FlowPane propertyOfSpecialLand1 = new FlowPane(Orientation.VERTICAL);
        Tooltip tooltipS5 = new Tooltip("House: Lannister" +
                "\n Cost for staying:" +
                "\n-1 house : 200$" +
                "\n-2 houses : 350$" +
                "\n-3 houses : 600" +
                "\n-4 houses : 800$");
        Tooltip.install(propertyOfSpecialLand1,tooltipS5);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfSpecialLand1,1,6);

        FlowPane propertyOfSpecialLand2 = new FlowPane(Orientation.VERTICAL);
        Tooltip tooltipS15 = new Tooltip("House: Baratheon" +
                "\n Cost for staying:" +
                "\n-1 house : 200$" +
                "\n-2 houses : 350$" +
                "\n-3 houses : 600" +
                "\n-4 houses : 800$");
        Tooltip.install(propertyOfSpecialLand2,tooltipS15);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfSpecialLand2,6,1);

        FlowPane propertyOfSpecialLand3 = new FlowPane(Orientation.VERTICAL);
        Tooltip tooltipS25 = new Tooltip("House: Targaryen" +
                "\n Cost for staying:" +
                "\n-1 house : 200$" +
                "\n-2 houses : 350$" +
                "\n-3 houses : 600" +
                "\n-4 houses : 800$");
        Tooltip.install(propertyOfSpecialLand3,tooltipS25);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfSpecialLand3,11,6);

        FlowPane propertyOfSpecialLand4 = new FlowPane(Orientation.VERTICAL);
        Tooltip tooltipS35 = new Tooltip("Land: Stark" +
                "\n Cost for staying:" +
                "\n-1 house : 200$" +
                "\n-2 houses : 350$" +
                "\n-3 houses : 600" +
                "\n-4 houses : 800$");
        Tooltip.install(propertyOfSpecialLand4,tooltipS35);
        MonopolyGameOfThronesApplication.gridOfGame.add(propertyOfSpecialLand4,6,11);


        setOfProperties.put(1, propertyOfLand1);
        setOfProperties.put(3, propertyOfLand2);
        setOfProperties.put(6, propertyOfLand3);
        setOfProperties.put(5,propertyOfSpecialLand1);
        setOfProperties.put(8, propertyOfLand4);
        setOfProperties.put(9, propertyOfLand5);
        setOfProperties.put(11, propertyOfLand6);
        setOfProperties.put(13, propertyOfLand7);
        setOfProperties.put(14, propertyOfLand8);
        setOfProperties.put(15, propertyOfSpecialLand2);
        setOfProperties.put(16, propertyOfLand9);
        setOfProperties.put(18, propertyOfLand10);
        setOfProperties.put(19, propertyOfLand11);
        setOfProperties.put(21, propertyOfLand12);
        setOfProperties.put(23, propertyOfLand13);
        setOfProperties.put(24, propertyOfLand14);
        setOfProperties.put(25, propertyOfSpecialLand3);
        setOfProperties.put(26, propertyOfLand15);
        setOfProperties.put(27, propertyOfLand16);
        setOfProperties.put(29, propertyOfLand17);
        setOfProperties.put(31, propertyOfLand18);
        setOfProperties.put(32, propertyOfLand19);
        setOfProperties.put(34, propertyOfLand20);
        setOfProperties.put(35, propertyOfSpecialLand4);
        setOfProperties.put(37, propertyOfLand21);
        setOfProperties.put(39, propertyOfLand22);
        for(Map.Entry<Integer, FlowPane> propertySettings: setOfProperties.entrySet()) {
            propertySettings.getValue().setMaxHeight(2);
        }

    }
}
