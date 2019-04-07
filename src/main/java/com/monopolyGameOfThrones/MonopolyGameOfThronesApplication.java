package com.monopolyGameOfThrones;

import com.monopolyGameOfThrones.boardObjects.*;
import com.monopolyGameOfThrones.gridMotionLogic.GridConverseToBoardPlace;
import com.monopolyGameOfThrones.setsOfGameData.CollectionOfProperties;
import com.monopolyGameOfThrones.setsOfGameData.SetOfLandCards;
import com.monopolyGameOfThrones.setsOfGameData.SetOfRandomChosenCards;
import com.monopolyGameOfThrones.setsOfGameData.SetOfSepcialCards;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class MonopolyGameOfThronesApplication extends Application {

    public static GridPane gridOfGame = new GridPane();
//    imported images
    private Image imageBackground = new Image("file:resources/MonopolyGameOfThrones-scene.jpg");
    private Image manualImage = new Image("file:resources/MANUAL.png");
    private Image cardValarMorghulis = new Image("file:resources/cards/valarMorghulisCard.jpg");
    private Image cardTheIronThrone = new Image("file:resources/cards/theIronThrone.jpg");
    private Image dice = new Image("file:resources/DICE/diceVI.png");
    private Image village = new Image("file:resources/village.jpg");
    private Image castle = new Image("file:resources/castle.jpg");
    private Image userPiece = new Image("file:resources/pieces/crown.png");
    private Image computerPiece1 = new Image("file:resources/pieces/ironThrone.png");
    private Image computerPiece2 = new Image("file:resources/pieces/raven.png");
    private Image computerPiece3 = new Image("file:resources/pieces/kingOfIce.png");
    private BorderPane image = new BorderPane();
//    CASH DATA
    private int amountOfMoneyInTheGame = 15000;
    private int startingMoney = 1000;
    private int beginPlaceOfThePiece = 0;
    private int costOfTheVillage = 150;
    private int costOfTheCastle = 300;

    private AppendableTextComputerStatusPane appendTextComputerStatus = new AppendableTextComputerStatusPane();
    private Label dice1Indication = new Label();
    private Label dice2Indication = new Label();
    private Label dice1Draw = new Label("Your 1 dice: ");
    private Label dice2Draw = new Label("Your 2 dice: ");
    private Label moneyBoxLabel = new Label("Money Box: ");
    private Label moneyBoxStatus = new Label();
    private Label statusOfTheGame = new Label("Status of the game: ");
    private Label bankStatusTextLabel = new Label("Money in the bank: ");
    private Label bankMoneyQuantityLabel = new Label();
    private Label quantityOfCardsTextLabel = new Label("Cards q-ty land/house: ");
    private Label quantityOfCardsValueLabel = new Label();
    private Label userCardListLabel = new Label("Your cards Name; Price: ");
    private Label userInfoOfTheGameTextLabel = new Label("User information:");
    private Label usersOnBoardLabel = new Label("Users in the game:");
    private Label userMoneyQuantityLabel = new Label();
    private Label scrollPaneLabel = new Label("Computer turns information:");

    private Map<Integer, Label> computerUsersMap = new HashMap<>();
    private FlowPane computerUsersFlowPane = new FlowPane();
    private VBox vbs = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private ProgressBar moneyProgressBar = new ProgressBar();
    private Bank bank = new Bank(amountOfMoneyInTheGame);
    private MoneyBox moneyBox = new MoneyBox();

    private SetOfLandCards setOfLandCards = new SetOfLandCards();
    private SetOfSepcialCards setOfSepcialCards = new SetOfSepcialCards();
    public CollectionOfProperties collectionOfProperties = new CollectionOfProperties();
    private SetOfRandomChosenCards setOfRandomChosenCards = new SetOfRandomChosenCards();


//------------------------------------------------methods---------------------------------------------------------------------------------------------------------------------

    public void computerTurn(User user) {
        Random random = new Random();
        int los1 = 1 + random.nextInt(100);
        int los2 = 1 + random.nextInt(100);
        int los3 = random.nextInt(5);
        int los4 = 1 + random.nextInt(100);

        if(los1<95 && user.getMoney() > 200) {
            buyALand(user);
        }

        if(los2<70) {
            while (los3 > 0) {
                if (user.getMoney() > costOfTheVillage+150) {
                    buyAProperty(user);
                }
                los3 -= 1;
            }
        }

        if(user.getMoney() < 400) {
            sellAProperty(user);
        }

        if(user.getMoney() < 150) {
            sellALand(user);
        }

        if(los4<75) {
            takeTheRandomCard(user);
        }

    }

    public void removeFromUsersMapAndPane(User user) {
        if(user != null && computerUsersFlowPane.getChildren().size() > 0){
            computerUsersMap.remove(user.getUserId());
            computerUsersFlowPane.getChildren().clear();
            for (Map.Entry<Integer, Label> entryCardMap : computerUsersMap.entrySet()) {
                computerUsersFlowPane.getChildren().add(entryCardMap.getValue());
            }
        }
    }

    public void addToComputerUsersMapAndPane(User user, Color color) {
        Label userName = new Label("-> " + user.getUsername());
        userName.setTextFill(color);
        userName.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        userName.setFont(Font.font("Papyrus",12));
        computerUsersMap.put(user.getUserId(),userName);
        computerUsersFlowPane.getChildren().add(userName);
    }

    public void takeTheRandomCard(User user) {
        if ((user.getPositionOfThePiece() == 2
                || user.getPositionOfThePiece() == 17
                || user.getPositionOfThePiece() == 33
                || user.getPositionOfThePiece() == 7
                || user.getPositionOfThePiece() == 22
                || user.getPositionOfThePiece() == 36)&&user.isTakeRandomCardOnce()){

                    RandomChosenCard returnedRandomCardWithHandling = setOfRandomChosenCards.returnedCardHandling(user, bank, moneyBox, moneyBoxStatus);
                    user.setTakeRandomCardOnce(false);

                    if(user.isHuman()){
                        MessgaeBox.getInformationTextLabel().setText(returnedRandomCardWithHandling.getName());
                        actualizeUserLabels(user);
                    }
                    if(!user.isHuman()){
                        appendTextComputerStatus.appendText(user.getUsername() +" Taken random card: " + returnedRandomCardWithHandling.getName());
                        actualizeComputerBankCardsLabels();
                    }

        } else if(user.isHuman()) {
            MessgaeBox.getInformationTextLabel().setText("You cannot take a card!");
        }

    }

    public void buyAProperty(User user) {

        if (user.getUserSetOfLandCards().containsKey(user.getPositionOfThePiece())
                && user.getMoney() >= costOfTheVillage
                && user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getQuantityOfVillages() < 4
                && user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getQuantityOfCastles() == 0) {

                    user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).setNewVillageOnTheLand();
                    collectionOfProperties.setVillageOnThePane(user.getPositionOfThePiece());
                    user.substractMoney(costOfTheVillage);
                    bank.giveMoneyToTheBank(costOfTheVillage);
                    if(user.isHuman()&&user.getUserSetOfLandCards().containsKey(user.getPositionOfThePiece())){
                        MessgaeBox.setInformationTextLabel("Bought a village. Land " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getName() + " now costs: " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                        actualizeUserLabels(user);
                        user.userFlowPaneCardActualization();

                    }
                    if(!user.isHuman()){
                        appendTextComputerStatus.appendText(user.getUsername()+": Bought a village. . Land " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getName() + " now costs: " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                        actualizeComputerBankCardsLabels();
                    }

        } else if (user.getUserSetOfLandCards().containsKey(user.getPositionOfThePiece())
                && user.getMoney() > costOfTheCastle
                && user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getQuantityOfVillages() >= 4) {

                    user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).setNewCastleOnTheLand();
                    collectionOfProperties.setCastleOnThePane(user.getPositionOfThePiece());
                    user.substractMoney(costOfTheCastle);
                    bank.giveMoneyToTheBank(costOfTheCastle);
                    if(user.isHuman()&&user.getUserSetOfLandCards().containsKey(user.getPositionOfThePiece())){
                        MessgaeBox.setInformationTextLabel("Bought a castle. Land " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getName() + " now costs: " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                        actualizeUserLabels(user);
                        user.userFlowPaneCardActualization();

                    }
                    if(!user.isHuman()){
                        appendTextComputerStatus.appendText(user.getUsername()+": Bought a castle. Land " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getName() + " now costs: " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                        actualizeComputerBankCardsLabels();
                    }

        } else {
            if(user.isHuman()) {
                MessgaeBox.getInformationTextLabel().setText("You cannot buy a property!");
            }
        }
   }

   public void sellAProperty(User user) {
        if(user.getUserSetOfLandCards().containsKey(user.getPositionOfThePiece())
        && user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getQuantityOfVillages()>0
        && bank.getMoneyInBank()>150){
                user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).removeVillageFromTheLand();
                bank.takeMoneyFromTheBank(150);
                user.addMoney(150);
                collectionOfProperties.removieVIllageFromThePane(user.getPositionOfThePiece());
                     if (user.isHuman()) {
                         MessgaeBox.setInformationTextLabel("Sold a village. Land now costs: " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                         user.userFlowPaneCardActualization();
                         actualizeUserLabels(user);
                     }
                    if(!user.isHuman()) {
                        appendTextComputerStatus.appendText(user.getUsername() + ": Sold a village. Land now costs: "+ user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                        actualizeComputerBankCardsLabels();
                    }
        }
        else if(user.getUserSetOfLandCards().containsKey(user.getPositionOfThePiece())
                &&user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getQuantityOfCastles() >= 1
                && bank.getMoneyInBank()>800){
                    user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).removeCastleFromTheLand();
                    bank.takeMoneyFromTheBank(800);
                    user.addMoney(800);
                    collectionOfProperties.removieVIllageFromThePane(user.getPositionOfThePiece());
                        if (user.isHuman()) {
                            MessgaeBox.setInformationTextLabel("Sold a castle. Land now costs: " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                            user.userFlowPaneCardActualization();
                            actualizeUserLabels(user);
                        }
                        if(!user.isHuman()) {
                            appendTextComputerStatus.appendText(user.getUsername() + ": Sold a castle. Land now costs: "+ user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                            actualizeComputerBankCardsLabels();
                        }
        }
        else{
            if(user.isHuman()) {
                MessgaeBox.setInformationTextLabel("You cannot sell the property!");
            }
        }

   }

    public void buyALand(User user) {
        if (setOfLandCards.returnMap().containsKey(user.getPositionOfThePiece())
                && user.getMoney() > setOfLandCards.getCard(user.getPositionOfThePiece()).getPrice()
                && !(user.getUserSetOfLandCards().containsKey(user.getPositionOfThePiece()))) {
            user.addToUserSetOfLandCards(user.getPositionOfThePiece(), setOfLandCards.getCard(user.getPositionOfThePiece()));
            user.substractMoney(setOfLandCards.getCard(user.getPositionOfThePiece()).getPrice());
            bank.giveMoneyToTheBank(setOfLandCards.getCard(user.getPositionOfThePiece()).getPrice());
            setOfLandCards.removeCardFromMap(user.getPositionOfThePiece(), user.getUserSetOfLandCards().get(user.getPositionOfThePiece()));
            collectionOfProperties.setIsBought(user.getPositionOfThePiece());
                if(user.isHuman()) {
//                    System.out.println(user.getUserSetOfLandCards().size());
                    actualizeUserLabels(user);
                    MessgaeBox.getInformationTextLabel().setText("Bought: " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getName());
                    user.addToUserFlowPaneCardStatus(user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getName(), user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                    user.userFlowPaneCardActualization();
                }
                if(!user.isHuman()) {
                    appendTextComputerStatus.appendText(user.getUsername() + ": Bought: " + user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getName()+ " Land costs: " +user.getUserSetOfLandCards().get(user.getPositionOfThePiece()).getPrice());
                    actualizeComputerBankCardsLabels();
            }

        } else if (setOfSepcialCards.returnMap().containsKey(user.getPositionOfThePiece())
                && user.getMoney() > setOfSepcialCards.getCard(user.getPositionOfThePiece()).getPrice()
                && !(user.getUserSetOfSpecialCards().containsKey(user.getPositionOfThePiece()))) {
            user.addToUserSetOfSpecialCards(user.getPositionOfThePiece(), setOfSepcialCards.getCard(user.getPositionOfThePiece()));
            user.substractMoney(setOfSepcialCards.getCard(user.getPositionOfThePiece()).getPrice());
            bank.giveMoneyToTheBank(setOfSepcialCards.getCard(user.getPositionOfThePiece()).getPrice());
            setOfSepcialCards.removeCardFromMap(user.getPositionOfThePiece());
            collectionOfProperties.setIsBought(user.getPositionOfThePiece());
                if(user.isHuman()) {
                    actualizeUserLabels(user);
                    MessgaeBox.getInformationTextLabel().setText("Bought: " + user.getUserSetOfSpecialCards().get(user.getPositionOfThePiece()).getName());
                    user.addToUserFlowPaneCardStatus(user.getUserSetOfSpecialCards().get(user.getPositionOfThePiece()).getName(), user.getUserSetOfSpecialCards().get(user.getPositionOfThePiece()).getPrice());
                    user.userFlowPaneCardActualization();
                }
                if(!user.isHuman()) {
                    appendTextComputerStatus.appendText(user.getUsername() + ": Bought: " + user.getUserSetOfSpecialCards().get(user.getPositionOfThePiece()).getName()+ " Land costs: " +user.getUserSetOfSpecialCards().get(user.getPositionOfThePiece()).getPrice() );
                    actualizeComputerBankCardsLabels();
                }
        }else {
            if (user.isHuman() && (!setOfSepcialCards.returnMap().containsKey(user.getPositionOfThePiece()) || !setOfSepcialCards.returnMap().containsKey(user.getPositionOfThePiece()))) {
                MessgaeBox.getInformationTextLabel().setText("No possibility to buy card! Not enough money or card is not available!");
            }
        }
    }

    public void sellALand(User user) {
        if(user.getUserSetOfLandCards().containsKey(user.getPositionOfThePiece())){
            LandCard landToDelete = user.getUserSetOfLandCards().get(user.getPositionOfThePiece()) ;
            bank.takeMoneyFromTheBank(landToDelete.getPrice());
            user.addMoney(landToDelete.getPrice());
            landToDelete.setOriginPriceAndProperties();
            setOfLandCards.putLandCardToTheSet(user.getPositionOfThePiece(),landToDelete);

            collectionOfProperties.setIsSold(user.getPositionOfThePiece());

            user.getUserSetOfLandCards().remove(user.getPositionOfThePiece(),user.getUserSetOfLandCards().get(user.getPositionOfThePiece()));

            messageBoxUserCompCardsLabelsAztualization(user, landToDelete.getName());

        }else{
            if(user.isHuman()) {
                MessgaeBox.setInformationTextLabel("You cannot sell the card!");
            }
        }

        if(user.getUserSetOfSpecialCards().containsKey(user.getPositionOfThePiece())) {
            SpecialCard specialCardToDelete = user.getUserSetOfSpecialCards().get(user.getPositionOfThePiece()) ;

            setOfSepcialCards.putSpecialCardToTheSet(user.getPositionOfThePiece(),specialCardToDelete);
            bank.takeMoneyFromTheBank(specialCardToDelete.getPrice());
            user.addMoney(specialCardToDelete.getPrice());

            user.getUserSetOfSpecialCards().remove(user.getPositionOfThePiece(),user.getUserSetOfSpecialCards().get(user.getPositionOfThePiece()));
            collectionOfProperties.setIsSold(user.getPositionOfThePiece());

            messageBoxUserCompCardsLabelsAztualization(user, specialCardToDelete.getName());

            if(user.isHuman()) {
                user.userFlowPaneCardActualization();
                actualizeUserLabels(user);
            }

        }else{
            if(user.isHuman()&&user.getUserSetOfSpecialCards().containsKey(user.getPositionOfThePiece())) {
                MessgaeBox.getInformationTextLabel().setText("You cannot sell the card!");
            }
        }

    }

    public void userDelete(User user) {
        if(!user.isPlayable()) {
            if (!(user.getUserSetOfLandCards().isEmpty())) {
                Iterator<Map.Entry<Integer, LandCard>> itr = user.getUserSetOfLandCards().entrySet().iterator();
                while (itr.hasNext()) {
                    Map.Entry<Integer, LandCard> entry = itr.next();
                    setOfLandCards.putLandCardToTheSet(entry.getKey(), entry.getValue());

                    if (collectionOfProperties.getSetOfProperties().containsKey(entry.getKey())) {
                        collectionOfProperties.getSetOfProperties().get(entry.getKey()).getChildren().clear();
                    }
                    itr.remove();

                }
                user.getUserSetOfLandCards().clear();
                user.substractMoney(user.getMoney());
            }

            if (!(user.getUserSetOfSpecialCards().isEmpty())) {
                Iterator<Map.Entry<Integer, SpecialCard>> itr = user.getUserSetOfSpecialCards().entrySet().iterator();
                while (itr.hasNext()) {
                    Map.Entry<Integer, SpecialCard> entry = itr.next();
                    setOfSepcialCards.putSpecialCardToTheSet(entry.getKey(), entry.getValue());

                    if (collectionOfProperties.getSetOfProperties().containsKey(entry.getKey())) {
                        collectionOfProperties.getSetOfProperties().get(entry.getKey()).getChildren().clear();
                    }
                    itr.remove();
                }
                user.getUserSetOfSpecialCards().clear();

            }

            if (user.isHuman()) {
                user.userFlowPaneCardStatusReset();
                MessgaeBox.getInformationTextLabel().setText("You have Lost! END OF THE GAME");
            }
            if (!user.isHuman()) {
                appendTextComputerStatus.appendText("User " + user.getUsername() + " lost. His watch is ended.");
            }


            removeFromUsersMapAndPane(user);
            gridOfGame.getChildren().remove(user.getThePieceAsNode());
        }
    }

//    // turning
    public void paymentExecution(User user, int money) {
        user.substractMoney(money);
        bank.giveMoneyToTheBank(money);
    }
    public void actualizeUserLabels(User user) {
        userMoneyQuantityLabel.setText("Your cash: " + user.getMoney());
        bankMoneyQuantityLabel.setText(String.valueOf(bank.getMoneyInBank()));
        quantityOfCardsValueLabel.setText(user.getUserSetOfLandCards().size() + "/" + user.getUserSetOfSpecialCards().size());
//        System.out.println(user.getUserSetOfLandCards().size());
        float percentageOfMoney = (float)user.getMoney()/this.amountOfMoneyInTheGame;
        moneyProgressBar.setProgress(percentageOfMoney);
    }

    public void actualizeComputerBankCardsLabels() {
        bankMoneyQuantityLabel.setText(String.valueOf(bank.getMoneyInBank()));
    }


    public void user1PayToUser2Execution(User payingUser, User landlord) {
        if(landlord.getUserSetOfLandCards().containsKey(payingUser.getPositionOfThePiece())){
            payingUser.substractMoney(landlord.getUserSetOfLandCards().get(payingUser.getPositionOfThePiece()).getPrice());
            landlord.addMoney(landlord.getUserSetOfLandCards().get(payingUser.getPositionOfThePiece()).getPrice());
        }
        if (landlord.getUserSetOfSpecialCards().containsKey(payingUser.getPositionOfThePiece())) {
            payingUser.substractMoney(landlord.getUserSetOfSpecialCards().get(payingUser.getPositionOfThePiece()).getPriceToPayForStaying());
            landlord.addMoney(landlord.getUserSetOfSpecialCards().get(payingUser.getPositionOfThePiece()).getPriceToPayForStaying());
        }
    }

    public void payingDebtsToTheLandlords(User payingUser,User user2,User user3,User user4) {
        if(payingUser.isPlayable()) {
            user1PayToUser2Execution(payingUser,user2);
            user1PayToUser2Execution(payingUser,user3);
            user1PayToUser2Execution(payingUser,user4);

            if(payingUser.isHuman()){
                actualizeUserLabels(payingUser);
            }
        }
        userDelete(payingUser);
    }

 public void eventsDuringDiceThrow(User user) {
     int dice1Value = Dice.diceThrow();
     int dice2Value = Dice.diceThrow();
     int diceSumValueOfPieceTranslation = dice1Value + dice2Value;
     int valueOfTranslationOfThePiece = user.getPositionOfThePiece() + diceSumValueOfPieceTranslation;
     int bonusAfterPassTheStart = 200;
     int paymentToTheBank1 = 200;
     int paymentToTheUser = 150;
     int paymentToTheBank2 = 100;

     if(user.isPlayable()) {
         if (!(valueOfTranslationOfThePiece <= 40)) {
             if (bank.takeMoneyFromTheBank(bonusAfterPassTheStart)) {
                 valueOfTranslationOfThePiece = valueOfTranslationOfThePiece - 40;
                 user.addMoney(bonusAfterPassTheStart);
                 bank.takeMoneyFromTheBank(bonusAfterPassTheStart);
                 if (user.isHuman()) {
                     actualizeUserLabels(user);
                 }
             } else {
                 valueOfTranslationOfThePiece = valueOfTranslationOfThePiece - 40;

                 if (user.isHuman()) {
                     actualizeUserLabels(user);
                 }
             }
         } else if (valueOfTranslationOfThePiece == 4) {
             paymentExecution(user, paymentToTheBank1);
             if (user.isHuman()) {
                 actualizeUserLabels(user);
             }
         } else if (valueOfTranslationOfThePiece == 38) {
             paymentExecution(user, paymentToTheBank2);
             if (user.isHuman()) {
                 actualizeUserLabels(user);
             }
         } else if (valueOfTranslationOfThePiece == 12 ||
                 valueOfTranslationOfThePiece == 28) {
             user.addMoney(paymentToTheUser);
             bank.takeMoneyFromTheBank(paymentToTheUser);
             if (user.isHuman()) {
                 actualizeUserLabels(user);
                 MessgaeBox.setInformationTextLabel("The Saint Land. You earned: " + paymentToTheUser + "$");
             }
             if (!user.isHuman()) {
                 appendTextComputerStatus.appendText(user.getUsername() + ": has earned " + paymentToTheUser + "$ on the Saint Land");
             }
         } else if (valueOfTranslationOfThePiece == 20) {
             if (moneyBox.getMoneyCollected() > 0) {
                 user.addMoney(moneyBox.getMoneyCollected());
                 moneyBox.clearMoneyBox();
                 moneyBoxStatus.setText("0");
             }
         }
         //        handling the jail
         if (valueOfTranslationOfThePiece == 30) {
             valueOfTranslationOfThePiece = 10;
             GridPane.setColumnIndex(user.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridXCoordinate(valueOfTranslationOfThePiece));
             GridPane.setRowIndex(user.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridYCoordinate(valueOfTranslationOfThePiece));
             user.setPositionOfThePiece(valueOfTranslationOfThePiece);
             if (user.isHuman()) {
                 MessgaeBox.getInformationTextLabel().setText("The jail is waiting my lord. You have lost one throw");
             } else {
                 appendTextComputerStatus.appendText(user.getUsername() + ": Is going to the jail - lost one throw");
             }

             user.setInJail(true);

         } else {
             user.setPositionOfThePiece(valueOfTranslationOfThePiece);
             user.addJailQuantityOfTurns();
             GridPane.setColumnIndex(user.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridXCoordinate(user.getPositionOfThePiece()));
             GridPane.setRowIndex(user.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridYCoordinate(user.getPositionOfThePiece()));

         }
         if (user.isHuman()) {
             dice1Indication.setText(String.valueOf(dice1Value));
             dice2Indication.setText(String.valueOf(dice2Value));
         }
     }

     if (computerUsersFlowPane.getChildren().size() == 1
             && user.isPlayable()) {
         MessgaeBox.setInformationTextLabel("THE WINNER IS: " + user.getUsername());
     }

 }
    public void messageBoxUserCompCardsLabelsAztualization(User user, String CardToDeleteName){

        if(user.isHuman()) {
            MessgaeBox.setInformationTextLabel("Sold: "+ CardToDeleteName);
            user.userFlowPaneCardActualization();
            actualizeUserLabels(user);
        }
        if(!user.isHuman()) {
            appendTextComputerStatus.appendText(user.getUsername() + ": Sold: " + CardToDeleteName);
            actualizeComputerBankCardsLabels();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//-----------------------------------------------------GameLayout---------------------------------------------------------------------------------------

        BackgroundSize backgroundSize = new BackgroundSize(800,800,true,true,false,true);
        BackgroundImage backgroundImage = new BackgroundImage(imageBackground, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);
        Background background = new Background(backgroundImage);


        //GRID OF THE GAME
        RowConstraints rowsEdge = new RowConstraints();
        rowsEdge.setPercentHeight(12.75);

        RowConstraints rowsMid = new RowConstraints();
        rowsMid.setPercentHeight(9);

        ColumnConstraints colEdge = new ColumnConstraints();
        colEdge.setPercentWidth(12.75);

        ColumnConstraints colMid = new ColumnConstraints();
        colMid.setPercentWidth(9.35);

        ColumnConstraints colHousesField = new ColumnConstraints();
        colHousesField.setPercentWidth(3);

        RowConstraints rowsHousesField = new RowConstraints();
        rowsHousesField.setPercentHeight(3);

        ColumnConstraints colControl = new ColumnConstraints();
        colControl.setPercentWidth(12);

        ColumnConstraints colGap = new ColumnConstraints();
        colGap.setPercentWidth(1);

        gridOfGame.getColumnConstraints().addAll(colEdge, colHousesField, colMid,
                colMid, colMid, colMid, colMid, colMid, colMid, colMid, colMid, colHousesField, colEdge, colGap, colControl,colControl);
        gridOfGame.getRowConstraints().addAll(rowsEdge,rowsHousesField, rowsMid,
                rowsMid,rowsMid,rowsMid,rowsMid,rowsMid,rowsMid, rowsMid,rowsMid,rowsHousesField, rowsEdge);

//Creating a backgroudnd of the game
        gridOfGame.add(image,0,0,13,13);
        image.setBackground(background);
        BorderPane.setAlignment(image, Pos.CENTER);
        gridOfGame.setPadding(new Insets(15));
//        gridOfGame.setGridLinesVisible(true);

//Including images and their sizes
        ImageView valarMorghulisCard = new ImageView(cardValarMorghulis);
        valarMorghulisCard.setFitHeight(100);
        valarMorghulisCard.setPreserveRatio(true);

        ImageView theIronThroneCard = new ImageView(cardTheIronThrone);
        theIronThroneCard.setFitHeight(100);
        theIronThroneCard.setPreserveRatio(true);

        ImageView diceView = new ImageView(dice);
        diceView.setFitHeight(50);
        diceView.setPreserveRatio(true);

        ImageView villagePin = new ImageView(village);
        villagePin.setFitHeight(30);
        villagePin.setPreserveRatio(true);

        ImageView castlePin = new ImageView(castle);
        castlePin.setFitHeight(30);
        castlePin.setPreserveRatio(true);

//---------------------------------------------Creating buttons-----------------------------------------------------------
        Button valarMorgulisCardButton = new Button("GET THE CARD",valarMorghulisCard);
        valarMorgulisCardButton.setAlignment(Pos.BOTTOM_RIGHT);
        valarMorgulisCardButton.setContentDisplay(ContentDisplay.TOP);


        Button theIronThroneCardButton = new Button("GET THE CARD",theIronThroneCard);
        theIronThroneCardButton.setAlignment(Pos.TOP_LEFT);
        theIronThroneCardButton.setContentDisplay(ContentDisplay.TOP);

        Button throwDiceButton = new Button("#THROW \nDICES#",diceView);
        throwDiceButton.setAlignment(Pos.CENTER);
        throwDiceButton.setContentDisplay(ContentDisplay.TOP);

        Button buyALandButton = new Button("$$Buy \na land$$");

        Button buyAPropertyButton = new Button("$$Buy \na Property$$",villagePin);
        buyAPropertyButton.setContentDisplay(ContentDisplay.LEFT);
        buyAPropertyButton.setTooltip(new Tooltip("COST IF BOUGHT:\n" +
                "VILLAGE – 150$\n" +
                "CASTLE – 300$(available after \n 4 villages purchasing \n on one field)\n"));

        Button sellAProperyButton = new Button("$$Sell a Property$$");
        sellAProperyButton.setContentDisplay(ContentDisplay.LEFT);
        sellAProperyButton.setTooltip(new Tooltip("COST IF SOLD:\n" +
                "VILLAGE – 150$\n" +
                "CASTLE – 800$\n"));

        Button sellALandButton = new Button("$$Sell \na land$$");

        Button letGameGoingOnButton = new Button("Let the Game \nBegin!");
        letGameGoingOnButton.setContentDisplay(ContentDisplay.TOP);

//Adding buttons to grid
        gridOfGame.add(theIronThroneCardButton,8,8,2,2);
        gridOfGame.add(valarMorgulisCardButton,3,3,2,2);
        gridOfGame.add(throwDiceButton,3,8,2,2);
        gridOfGame.add(buyALandButton,15,12,1,2);
        gridOfGame.add(sellALandButton,14,12,1,2);
        gridOfGame.add(buyAPropertyButton,14,10,2,1);
        gridOfGame.add(sellAProperyButton,14,11,2,1);
        gridOfGame.add(letGameGoingOnButton,4,8,2,1);

        GridPane.setHalignment(theIronThroneCardButton, HPos.CENTER);

        GridPane.setHalignment(valarMorgulisCardButton, HPos.CENTER);
        GridPane.setHalignment(throwDiceButton, HPos.LEFT);
        GridPane.setHalignment(buyALandButton, HPos.CENTER);
        GridPane.setHalignment(sellALandButton, HPos.CENTER);
        GridPane.setHalignment(buyAPropertyButton, HPos.LEFT);
        GridPane.setHalignment(sellAProperyButton, HPos.LEFT);
        GridPane.setHalignment(letGameGoingOnButton, HPos.RIGHT);

//Adding settings to the label
        gridOfGame.add(moneyBoxLabel,6,5,2,1);
        GridPane.setValignment(moneyBoxLabel, VPos.TOP);
        moneyBoxLabel.setFont(Font.font("Papyrus",12));
        moneyBoxLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));

        gridOfGame.add(moneyBoxStatus,6,5);
        GridPane.setHalignment(moneyBoxStatus,HPos.CENTER);
        GridPane.setValignment(moneyBoxStatus,VPos.BOTTOM);
        moneyBoxStatus.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        moneyBoxStatus.setFont(Font.font("Papyrus",15));


        gridOfGame.add(statusOfTheGame,14,2,2,1);
        statusOfTheGame.setFont(new Font("Papyrus",15));
        GridPane.setValignment(statusOfTheGame,VPos.BOTTOM);
        gridOfGame.add(bankStatusTextLabel,14,3,2,1);
        bankStatusTextLabel.setFont(new Font("Papyrus",12));

        gridOfGame.add(bankMoneyQuantityLabel,15,3,1,1);
        GridPane.setHalignment(bankMoneyQuantityLabel,HPos.RIGHT);
        bankMoneyQuantityLabel.setFont(new Font("Papyrus",12));

        gridOfGame.add(quantityOfCardsTextLabel,14,5,2,1);
        quantityOfCardsTextLabel.setFont(new Font("Papyrus",12));

        gridOfGame.add(quantityOfCardsValueLabel,15,5,2,1);
        GridPane.setHalignment(quantityOfCardsValueLabel,HPos.RIGHT);
        quantityOfCardsValueLabel.setFont(new Font("Papyrus",12));

        gridOfGame.add(userCardListLabel,14,5,2,1);
        GridPane.setValignment(userCardListLabel,VPos.BOTTOM);
        userCardListLabel.setFont(new Font("Papyrus",12));

        gridOfGame.add(userInfoOfTheGameTextLabel,3,10,8,1);
        userInfoOfTheGameTextLabel.setFont(new Font("Papyrus",12));
        userInfoOfTheGameTextLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        GridPane.setValignment(userInfoOfTheGameTextLabel,VPos.TOP);

        gridOfGame.add(MessgaeBox.getInformationTextLabel(),3,10,8,1);
        GridPane.setValignment(MessgaeBox.getInformationTextLabel(),VPos.BOTTOM);

//Adding progress button
        GridPane.setHalignment(userMoneyQuantityLabel,HPos.CENTER);
        userMoneyQuantityLabel.setFont(new Font("Papyrus",12));

        vbs.setAlignment(Pos.CENTER);
        vbs.getChildren().addAll(userMoneyQuantityLabel,moneyProgressBar);

        gridOfGame.add(vbs,14,4,2,1);

// Adding scroll pane

        scrollPane.setContent(appendTextComputerStatus);
        gridOfGame.add(scrollPane,2,6,6,2);
        scrollPane.setFitToWidth(true);

        scrollPaneLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        gridOfGame.add(scrollPaneLabel,2,5,4,1);
        GridPane.setValignment(scrollPaneLabel,VPos.BOTTOM);
        scrollPaneLabel.setFont(new Font("Papyrus",12));

        //Adding status of dice after throw

        GridPane.setHalignment(dice1Draw,HPos.CENTER);
        GridPane.setValignment(dice1Draw, VPos.TOP);
        dice1Draw.setFont(Font.font("Papyrus",12));
        dice2Draw.setFont(Font.font("Papyrus",12));
        GridPane.setHalignment(dice2Draw,HPos.CENTER);
        GridPane.setValignment(dice2Draw, VPos.TOP);
        gridOfGame.add(dice1Draw,14,0,1,1);
        gridOfGame.add(dice2Draw,15,0,1,1);
        gridOfGame.add(dice1Indication,14,0,1,1);
        GridPane.setHalignment(dice1Indication, HPos.CENTER);
        GridPane.setValignment(dice1Indication, VPos.BOTTOM);
        dice1Indication.setFont(Font.font("Papyrus",30));
        GridPane.setHalignment(dice2Indication, HPos.CENTER);
        GridPane.setValignment(dice2Indication, VPos.BOTTOM);
        dice2Indication.setFont(Font.font("Papyrus",30));
        gridOfGame.add(dice2Indication,15,0,1,1);

        collectionOfProperties.addPropertyFlowPanesToTheGrid();

//------------------------------------endGameLayout---------------------------------------------------------------------------------------

//------------------------------------statusOfTheGameSetting-----------------------------------------------------------------------------------

        User humanUser = new User("",true,1,userPiece);
        humanUser.placeTheFlowPaneCardStatus();
        humanUser.setUserEndOfTheTurn(false);

        User compUser1 = new User("Computer1",false,2, computerPiece1);
        User compUser2 = new User("Computer2",false, 3, computerPiece2);
        User compUser3 = new User("Computer3",false, 4, computerPiece3);
        compUser3.setUserEndOfTheTurn(true);

        gridOfGame.add(humanUser.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridXCoordinate(beginPlaceOfThePiece), GridConverseToBoardPlace.converse1dTo2dGridYCoordinate(beginPlaceOfThePiece));
        gridOfGame.add(compUser1.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridXCoordinate(beginPlaceOfThePiece), GridConverseToBoardPlace.converse1dTo2dGridYCoordinate(beginPlaceOfThePiece));
        gridOfGame.add(compUser2.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridXCoordinate(beginPlaceOfThePiece), GridConverseToBoardPlace.converse1dTo2dGridYCoordinate(beginPlaceOfThePiece));
        gridOfGame.add(compUser3.getThePieceAsNode(), GridConverseToBoardPlace.converse1dTo2dGridXCoordinate(beginPlaceOfThePiece), GridConverseToBoardPlace.converse1dTo2dGridYCoordinate(beginPlaceOfThePiece));


        gridOfGame.add(usersOnBoardLabel,8,2,2,1);
        GridPane.setValignment(usersOnBoardLabel,VPos.CENTER);
        usersOnBoardLabel.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        usersOnBoardLabel.setFont(Font.font("Papyrus",12));

        gridOfGame.add(computerUsersFlowPane,8,3);

        GridPane.setHalignment(humanUser.getThePieceAsNode(), HPos.RIGHT);
        GridPane.setValignment(humanUser.getThePieceAsNode(), VPos.TOP);

        GridPane.setHalignment(compUser1.getThePieceAsNode(), HPos.LEFT);
        GridPane.setValignment(compUser1.getThePieceAsNode(), VPos.TOP);

        GridPane.setHalignment(compUser2.getThePieceAsNode(), HPos.RIGHT);
        GridPane.setValignment(compUser2.getThePieceAsNode(), VPos.BOTTOM);

        GridPane.setHalignment(compUser3.getThePieceAsNode(), HPos.LEFT);
        GridPane.setValignment(compUser3.getThePieceAsNode(), VPos.BOTTOM);

        //CASH DATA
        moneyBoxStatus.setText("0");

        bank.takeMoneyFromTheBank(startingMoney);
        bank.takeMoneyFromTheBank(startingMoney);
        bank.takeMoneyFromTheBank(startingMoney);
        bank.takeMoneyFromTheBank(startingMoney);

        humanUser.addMoney(startingMoney);
        compUser1.addMoney(startingMoney);
        compUser2.addMoney(startingMoney);
        compUser3.addMoney(startingMoney);

//      STATUS OF THE GAME INFORMATION
        actualizeUserLabels(humanUser);
        MessgaeBox.setInformationTextLabel("WELCOME TO MONOPOLY! Please throw the dices to start! Good luck!");
//------------------------------------EndStatus---------------------------------------------------------------------------------------
//------------------------------------gamePerformance---------------------------------------------------------------------------------------
            throwDiceButton.setOnAction(event -> {

                    if(!(humanUser.isUserEndOfTheTurn()) && compUser3.isUserEndOfTheTurn()) {
                        MessgaeBox.setInformationTextLabel("");
                        eventsDuringDiceThrow(humanUser);
                        payingDebtsToTheLandlords(humanUser,compUser1,compUser2,compUser3);
                        humanUser.setTakeRandomCardOnce(true);
                    }

                humanUser.setUserEndOfTheTurn(true);
                compUser3.setUserEndOfTheTurn(false);

            });

            buyALandButton.setOnAction(event -> {
                buyALand(humanUser);

            });
            sellALandButton.setOnAction(event ->{
                sellALand(humanUser);
            });

            buyAPropertyButton.setOnAction(event -> {
                buyAProperty(humanUser);
            });
            sellAProperyButton.setOnAction(event ->{
                sellAProperty(humanUser);
            });

            theIronThroneCardButton.setOnAction(event -> {
                if (humanUser.getPositionOfThePiece() == 2
                        || humanUser.getPositionOfThePiece() == 17
                        || humanUser.getPositionOfThePiece() == 33) {
                    takeTheRandomCard(humanUser);
                }
                else {
                    MessgaeBox.getInformationTextLabel().setText("You cannot take a card!");
                }
            });

            valarMorgulisCardButton.setOnAction(event -> {
                if (humanUser.getPositionOfThePiece() == 7
                        || humanUser.getPositionOfThePiece() == 22
                        || humanUser.getPositionOfThePiece() == 36) {
                    takeTheRandomCard(humanUser);
                }
                else {
                    MessgaeBox.getInformationTextLabel().setText("You cannot take a card!");
                }

            });

            letGameGoingOnButton.setOnAction(event -> {
                while(!(compUser3.isUserEndOfTheTurn())){
                    userDelete(humanUser);
                    eventsDuringDiceThrow(compUser1);
                    payingDebtsToTheLandlords(compUser1,humanUser,compUser2,compUser3);
                    computerTurn(compUser1);

                    eventsDuringDiceThrow(compUser2);
                    payingDebtsToTheLandlords(compUser2,humanUser,compUser1,compUser3);
                    computerTurn(compUser2);

                    eventsDuringDiceThrow(compUser3);
                    payingDebtsToTheLandlords(compUser3,humanUser,compUser2,compUser1);
                    computerTurn(compUser3);

                    compUser3.setUserEndOfTheTurn(true);
                    actualizeUserLabels(humanUser);

                }

                humanUser.setUserEndOfTheTurn(false);
                appendTextComputerStatus.appendText("END OF COMPUTERS TURNS. YOUR TURN");
            });

//--------------------------------Creaing a scene and primary stage---------------------------------------------------------------------
//--------------------------------------creatingTheUserNameTyping)----------------------------------------------------------------------

        final TextField textField = new TextField();
        textField.setPromptText("Type your name");
        textField.setPrefColumnCount(10);

        Text titleText = new Text("Welcome to Monopoly! Find your destiny!");
        titleText.setFont(Font.font("Papyrus",26));

        Text information = new Text("Please type your name to start the game.(Min 4 max 15 characters)");
        information.setFont(Font.font("Papyrus",12));

        VBox vBox = new VBox();
        vBox.getChildren().add(titleText);
        vBox.getChildren().add(information);

        Text usernameText = new Text("Username: ");
        usernameText.setFont(Font.font("Papyrus",20));

        Button startTheGame = new Button("Start the game");
        GridPane startGrid = new GridPane();

        startGrid.setMinSize(300,50);
        HBox hBox = new HBox(10);

        hBox.getChildren().add(usernameText);
        hBox.getChildren().add(textField);
        hBox.getChildren().add(startTheGame);

//        startGrid.setGridLinesVisible(true);
        startGrid.add(vBox,0,0,3,1);
        startGrid.add(hBox,0,1);

        VBox layout = new VBox(20);

        Button goBackToTheGameButton = new Button("Go back to the game");

        BackgroundSize manualSize = new BackgroundSize(1250,700,true,true,false,true);
        BackgroundImage manualImageBackground = new BackgroundImage(manualImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,manualSize);
        Background manual = new Background(manualImageBackground);
        layout.setBackground(manual);
        layout.getChildren().add(goBackToTheGameButton);
        layout.setAlignment(Pos.BOTTOM_LEFT);

        Button sceneShow = new Button("Show the manual");
        gridOfGame.add(sceneShow,14,9,2,1);
        GridPane.setValignment(sceneShow,VPos.BOTTOM);

        Scene sceneBefor = new Scene(startGrid);
        Scene scene2 = new Scene(layout,1250,700);
        Scene scene = new Scene(gridOfGame, 850, 700, Color.WHITE);

        primaryStage.setScene(sceneBefor);
        primaryStage.setTitle("Monopoly - Game Of Thrones");
        primaryStage.setResizable(false);
        primaryStage.show();

        sceneShow.setOnAction(e-> {
            primaryStage.setScene(scene2);
            primaryStage.centerOnScreen();
        });
        goBackToTheGameButton.setOnAction(e-> {
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        });
        startTheGame.setOnAction(e->{
            if(textField.getCharacters().length() > 3 && textField.getCharacters().length() < 16) {
                humanUser.setUsername(textField.getCharacters().toString());
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();
                addToComputerUsersMapAndPane(humanUser,Color.BLUE);
                addToComputerUsersMapAndPane(compUser1,Color.RED);
                addToComputerUsersMapAndPane(compUser2,Color.GREEN);
                addToComputerUsersMapAndPane(compUser3,Color.PURPLE);
            }
        });
        }

    public static void main(String[] args) {
        launch(args);
    }
}
