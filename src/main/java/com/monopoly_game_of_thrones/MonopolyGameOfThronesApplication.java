package com.monopoly_game_of_thrones;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MonopolyGameOfThronesApplication extends Application{
//    imported images
    private Image imageBackground = new Image("file:resources/MonopolyGameOfThrones-scene.jpg");
    private Image cardValarMorghulis = new Image("file:resources/cards/valarMorghulisCard.jpg");
    private Image cardTheIronThrone = new Image("file:resources/cards/theIronThrone.jpg");
    private Image dice = new Image("file:resources/DICE/diceVI.png");
    private Image village = new Image("file:resources/pieces/village.jpg");
    private Image castle = new Image("file:resources/pieces/castle.jpg");
    private Image userPiece = new Image("file:resources/pieces/dragonEgg.png");
    private Image compuetrPiece = new Image("file:resources/pieces/ironThrone.png");

//    private FlowPane cards = new FlowPane(Orientation.HORIZONTAL);

    final Integer[] values = new Integer[] {-1,0,20,30,50};
    final Label[] labels = new Label[values.length];
    final ProgressBar[] pbs = new ProgressBar[values.length];
    final ProgressIndicator[] pins = new ProgressIndicator[values.length];
    final HBox hbs[] = new HBox[values.length];



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(1000,1000,true,true,false,true);
        BackgroundImage backgroundImage = new BackgroundImage(imageBackground, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane gridOfGame = new GridPane();
        //--------------------------------------LAYOUT------------------------------------------------------

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
        colControl.setPercentWidth(10);

        ColumnConstraints colGap = new ColumnConstraints();
        colGap.setPercentWidth(5);

        gridOfGame.getColumnConstraints().addAll(colEdge, colHousesField, colMid,
                colMid, colMid, colMid, colMid, colMid, colMid, colMid, colMid, colHousesField, colEdge, colGap, colControl,colControl);
        gridOfGame.getRowConstraints().addAll(rowsEdge,rowsHousesField, rowsMid,
                rowsMid,rowsMid,rowsMid,rowsMid,rowsMid,rowsMid, rowsMid,rowsMid,rowsHousesField, rowsEdge);

//Creating a backgroudn of the game
        BorderPane image = new BorderPane();
        gridOfGame.add(image,0,0,13,13);
        image.setBackground(background);

        BorderPane.setAlignment(image, Pos.CENTER);

//        gridOfGame.setAlignment(Pos.TOP_LEFT);
        gridOfGame.setPadding(new Insets(15));

        gridOfGame.setGridLinesVisible(true);


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

//Creating buttons
        Button valarMorgulisCardButton = new Button("Click to get the card",valarMorghulisCard);
//        valarMorgulisCardButton.setMaxSize(100, Control.USE_PREF_SIZE);
//        valarMorgulisCardButton.setMinSize(100, Control.USE_PREF_SIZE);
        valarMorgulisCardButton.setAlignment(Pos.CENTER);
        valarMorgulisCardButton.setContentDisplay(ContentDisplay.TOP);

        Button theIronThroneCardButton = new Button("Click to get the card",theIronThroneCard);
//        theIronThroneCardButton.setMaxSize(100, Control.USE_PREF_SIZE);
//        theIronThroneCardButton.setMinSize(100, Control.USE_PREF_SIZE);
        theIronThroneCardButton.setAlignment(Pos.CENTER);
        theIronThroneCardButton.setContentDisplay(ContentDisplay.TOP);

        Button throwDice = new Button("#THROW \nTHE DICE#",diceView);
        throwDice.setAlignment(Pos.CENTER);
//        throwDice.setMaxSize(120, Control.USE_PREF_SIZE);
//        throwDice.setMinSize(120, Control.USE_PREF_SIZE);
        throwDice.setContentDisplay(ContentDisplay.TOP);

        Button buyALand = new Button("$$Buy \na land$$");
        Button buyAVillage = new Button("$$Buy \na Village",villagePin);
        buyAVillage.setContentDisplay(ContentDisplay.TOP);
        Button buyACastle = new Button("$$Buy \na Castle$$",castlePin);
        buyACastle.setContentDisplay(ContentDisplay.TOP);
        Button sellALand = new Button("$$Sell \na land$$");





//Adding buttons to grid
        gridOfGame.add(theIronThroneCardButton,8,8,2,2);
        gridOfGame.add(valarMorgulisCardButton,3,3,2,2);
        gridOfGame.add(throwDice,3,8,2,2);
        gridOfGame.add(buyALand,15,12,1,1);
        gridOfGame.add(sellALand,14,12,1,1);
        gridOfGame.add(buyAVillage,14,10,1,1);
        gridOfGame.add(buyACastle,15,10,1,1);

//Adding status of the game:
        Label statusOfTheGame = new Label("Status of the game: ");
        statusOfTheGame.setFont(new Font("Arial", 15));
        gridOfGame.add(statusOfTheGame,14,5,2,1);
        Label quantityOfThrows = new Label("Quantity of turns: ");
        gridOfGame.add(quantityOfThrows,14,7,2,1);

//        Adding money progress button
        final Label label = labels[2] = new Label();
        label.setText("Money: " + values[2]);
        ProgressBar moneyProgressBar = pbs[2]=new ProgressBar();
        moneyProgressBar.setProgress(values[2]);

        final HBox hbs = new HBox();
        hbs.setAlignment(Pos.CENTER);
        hbs.getChildren().addAll(label,moneyProgressBar);
        gridOfGame.add(hbs,14,6,2,1);


//Adding status of dice after throw
        Label dice1Draw = new Label("Your 1 dice \nthrow: ");
        Label dice2Draw = new Label("Your 2 dice \nthrow: ");
        gridOfGame.add(dice1Draw,15,0,1,1);
        gridOfGame.add(dice2Draw,14,0,1,1);

 //Creaing a scene and primary stage
        Scene scene = new Scene(gridOfGame,1250,1100,Color.WHITE);

        primaryStage.setTitle("Monopoly - Game Of Thrones");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();




    }
}
