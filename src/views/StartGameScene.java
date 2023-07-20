package views;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.characters.Character;
import model.characters.*;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;

import java.io.IOException;

import static engine.Game.*;

public class StartGameScene extends Main {
    private static Hero selectedHero;
    private final Scene scene;
    private final Image[] fighter1 = new Image[5];
    private final Image[] fighter2 = new Image[5];
    private final Image[] explorer1 = new Image[5];
    private final Image[] explorer2 = new Image[5];
    private final Image[] explorer3 = new Image[5];
    private final Image[] medic1 = new Image[5];
    private final Image[] medic3 = new Image[5];
    private final Image[] medic2 = new Image[5];
    private final Image[] zombie = new Image[4];
    private final Hero firstHero;
    private Button[][] buttons;
    private Image background;
    private Image cellImage;
    private Image cellImageUnlocked;
    private Image vaccineImage;
    private Image supplyImage;
    private boolean finished = false;
    private boolean isAvailableHeroPlaced[] = {false, false, false, false, false, false, false, false};
    private boolean isHeroPlaced[] = {false, false, false, false, false, false, false, false};
    private boolean isLabelPlaced;
    public StartGameScene(Stage primaryStage, Hero hero) throws IOException {
        firstHero = hero;
        loadImages();
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        Game.startGame(hero);
        selectedHero = hero;
        BorderPane borderPane = new BorderPane();

        GridPane gridPane = new GridPane();
        ImageView backgroundView = new ImageView(background);

        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(borderPane.widthProperty());
        backgroundView.fitHeightProperty().bind(borderPane.heightProperty());
        borderPane.getChildren().add(backgroundView);

        gridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridPane);

        VBox heroContainer = new VBox();
        heroContainer.setAlignment(Pos.CENTER_LEFT);
        heroContainer.setBackground(createBackground("views/resources/startGame/buttonContainer.png", 0.44, 0.7));
        borderPane.setLeft(heroContainer);


        VBox buttonContainer = new VBox();
        buttonContainer.setBackground(createBackground("views/resources/startGame/buttonContainer.png", 0.44, 0.7));
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        borderPane.setRight(buttonContainer);
        Label label = new Label("                                                                                                                  ");


        Button attackButton = createButton("views/resources/startGame/attackButton/attackButtonNormal.png", "views/resources/startGame/attackButton/attackButtonSelected.png", 0.3, 0.15);
        Button useSpecialButton = createButton("views/resources/startGame/useSpecialButton/useSpecialButtonNormal.png", "views/resources/startGame/useSpecialButton/useSpecialButtonSelected.png", 0.3, 0.15);
        Button cureButton = createButton("views/resources/startGame/cureButton/cureButtonNormal.png", "views/resources/startGame/cureButton/cureButtonSelected.png", 0.3, 0.15);
        Button endTurnButton = createButton("views/resources/startGame/endTurnButton/endTurnButtonNormal.png", "views/resources/startGame/endTurnButton/endTurnButtonSelected.png", 0.3, 0.15);
        Label message = new Label("                                                                                                       ");

        attachAnimation(1500, 0, 2, attackButton);
        attachAnimation(1500, 0, 2.1, useSpecialButton);
        attachAnimation(1500, 0, 2.2, cureButton);
        attachAnimation(1500, 0, 2.3, endTurnButton);

        message.setPadding(new Insets(10));

        attackButton.setOnAction(actionEvent -> {
            try {
                if (selectedHero != null && !finished) {
                    selectedHero.attack();
                    checkDead();
                    updateHeroContainer(heroContainer, selectedHero);
                    updateMap(heroContainer, buttonContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                    updateButtonContainer(label, message, buttonContainer, heroContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                    if (Game.checkGameOver() && !Game.checkWin()) {
                        loseScreen(firstHero, root, primaryStage);
                        finished = true;
                    }
                    if (Game.checkWin()) {
                        winScreen(root, primaryStage);
                        finished = true;
                    }
                }
            } catch (NotEnoughActionsException | InvalidTargetException e) {
                createNotification(e.getMessage(), Color.RED, root);
            }
        });
        attackButton.setPadding(new Insets(10));

        useSpecialButton.setOnAction(actionEvent -> {
            try {
                if (selectedHero != null && !finished) {
                    selectedHero.useSpecial();
                    checkDead();
                    updateMap(heroContainer, buttonContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                    updateHeroContainer(heroContainer, selectedHero);
                    updateButtonContainer(label, message, buttonContainer, heroContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                    if (Game.checkGameOver() && !Game.checkWin()) {
                        loseScreen(firstHero, root, primaryStage);
                        finished = true;
                    }
                    if (Game.checkWin()) {
                        winScreen(root, primaryStage);
                        finished = true;
                    }
                }
            } catch (NoAvailableResourcesException | InvalidTargetException e) {
                createNotification(e.getMessage(), Color.RED, root);
            }
        });
        useSpecialButton.setPadding(new Insets(10));

        cureButton.setOnAction(actionEvent -> {
            try {
                if (selectedHero != null && !finished) {
                    selectedHero.cure();
                    checkDead();
                    updateHeroContainer(heroContainer, selectedHero);
                    updateMap(heroContainer, buttonContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                    updateButtonContainer(label, message, buttonContainer, heroContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                    if (Game.checkGameOver() && !Game.checkWin()) {
                        loseScreen(firstHero, root, primaryStage);
                        finished = true;
                    }
                    if (Game.checkWin()) {
                        winScreen(root, primaryStage);
                        finished = true;
                    }
                }
            } catch (NoAvailableResourcesException | InvalidTargetException | NotEnoughActionsException e) {
                createNotification(e.getMessage(), Color.RED, root);
            }
        });
        cureButton.setPadding(new Insets(10));

        endTurnButton.setOnAction(actionEvent -> {
            try {
                if (selectedHero != null && !finished) {
                    int previousHp = hero.getCurrentHp();
                    endTurn();
                    int afterHp = hero.getCurrentHp();
                    if (previousHp != afterHp)
                        createNotification("You've been attacked by zombies!", Color.RED, root);
                    checkDead();
                    updateHeroContainer(heroContainer, selectedHero);
                    updateMap(heroContainer, buttonContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                    updateButtonContainer(label, message, buttonContainer, heroContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                }
                if (Game.checkGameOver() && !Game.checkWin()) {
                    loseScreen(firstHero, root, primaryStage);
                    finished = true;
                }
                if (Game.checkWin()) {
                    winScreen(root, primaryStage);
                    finished = true;
                }
            } catch (NotEnoughActionsException | InvalidTargetException e) {
                createNotification(e.getMessage(), Color.RED, root);
            }
        });
        endTurnButton.setPadding(new Insets(10));

        buttonContainer.getChildren().addAll(label, message, attackButton, useSpecialButton, cureButton, endTurnButton);
        createMap(gridPane, borderPane, heroContainer, buttonContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
        updateButtonContainer(label, message, buttonContainer, heroContainer, attackButton, useSpecialButton, cureButton, endTurnButton);

        buttonContainer.setAlignment(Pos.CENTER);
        attackButton.setAlignment(Pos.CENTER);
        useSpecialButton.setAlignment(Pos.CENTER);
        cureButton.setAlignment(Pos.CENTER);
        label.setAlignment(Pos.CENTER);


        updateHeroContainer(heroContainer, hero);
        updateMap(heroContainer, buttonContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
        root.getChildren().add(borderPane);
        scene = new Scene(root, 1920, 1080);

        scene.setOnKeyPressed(event -> {
            try {
                if (selectedHero != null && !finished) {

                    handleKeyPress(event, selectedHero, heroContainer, root);
                    checkDead();
                    updateMap(heroContainer, buttonContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                    updateHeroContainer(heroContainer, selectedHero);
                    updateButtonContainer(label, message, buttonContainer, heroContainer, attackButton, useSpecialButton, cureButton, endTurnButton);
                    if (Game.checkGameOver() && !Game.checkWin()) {
                        loseScreen(firstHero, root, primaryStage);
                        finished = true;
                    }
                    if (Game.checkWin()) {
                        winScreen(root, primaryStage);
                        finished = true;
                    }
                }
            } catch (MovementException | NotEnoughActionsException e) {
                createNotification(e.getMessage(), Color.RED, root);
            }
        });
    }
//scene.setOnKeyPressed(event ->handleKeyPress(event, selectedHero, heroContainer, root);))
    private void updateButtonContainer(Label label3, Label message, VBox buttonContainer, VBox heroContainer, Button attackButton, Button useSpecialButton, Button cureButton, Button endTurnButton) {
        buttonContainer.getChildren().clear();

        Font customFont = Font.loadFont(StartGameScene.class.getResourceAsStream("/views/resources/fonts/font.ttf"), 24);
        Label label = new Label("Available Heroes");
        label.setFont(customFont);
        label.setTextFill(Color.LIGHTGOLDENRODYELLOW);

        HBox availableHeroesBox = new HBox();
        availableHeroesBox.setAlignment(Pos.CENTER);
        availableHeroesBox.setSpacing(10);
        availableHeroesBox.setPadding(new Insets(10));
        availableHeroesBox.getChildren().add(label);
        buttonContainer.getChildren().add(availableHeroesBox);
        availableHeroesBox = new HBox();
        availableHeroesBox.setAlignment(Pos.CENTER);
        availableHeroesBox.setSpacing(10);

        for (int i = 0; i < availableHeroes.size(); i++) {
            Hero hero = availableHeroes.get(i);
            Image image = switch (hero.getName()) {
                case "Joel Miller" -> new Image("views/resources/startGame/heroes/fighter1.png");
                case "David" -> new Image("views/resources/startGame/heroes/fighter2.png");
                case "Riley Abel" -> new Image("views/resources/startGame/heroes/explorer1.png");
                case "Tess" -> new Image("views/resources/startGame/heroes/explorer2.png");
                case "Tommy Miller" -> new Image("views/resources/startGame/heroes/explorer3.png");
                case "Ellie Williams" -> new Image("views/resources/startGame/heroes/medic1.png");
                case "Bill" -> new Image("views/resources/startGame/heroes/medic2.png");
                case "Henry Burell" -> new Image("views/resources/startGame/heroes/medic3.png");
                default -> null;
            };
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            availableHeroesBox.getChildren().add(imageView);
            if (!isAvailableHeroPlaced[i]) {
                TranslateTransition tt = new TranslateTransition(Duration.seconds(2), imageView);
                tt.setFromX(1000);
                tt.setToX(0);
                tt.play();
                TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1.9), label);
                tt2.setFromX(1000);
                tt2.setToX(0);
                tt2.play();
                isAvailableHeroPlaced[i] = true;
            }
            if ((i + 1) % 2 == 0 || i == availableHeroes.size() - 1) {
                buttonContainer.getChildren().add(availableHeroesBox);
                availableHeroesBox = new HBox();
                availableHeroesBox.setAlignment(Pos.CENTER);
                availableHeroesBox.setSpacing(10);
            }
        }

        Label label2 = new Label("Heroes");
        label2.setFont(customFont);
        label2.setTextFill(Color.LIGHTGOLDENRODYELLOW);

        HBox heroesBox = new HBox();
        heroesBox.setAlignment(Pos.CENTER);
        heroesBox.setSpacing(10);
        heroesBox.setPadding(new Insets(10));
        heroesBox.getChildren().add(label2);
        buttonContainer.getChildren().add(heroesBox);

        heroesBox = new HBox();
        heroesBox.setAlignment(Pos.CENTER);
        heroesBox.setSpacing(10);

        for (int i = 0; i < heroes.size(); i++) {
            Hero hero = heroes.get(i);
            Image image = switch (hero.getName()) {
                case "Joel Miller" -> new Image("views/resources/startGame/heroes/fighter1.png");
                case "David" -> new Image("views/resources/startGame/heroes/fighter2.png");
                case "Riley Abel" -> new Image("views/resources/startGame/heroes/explorer1.png");
                case "Tess" -> new Image("views/resources/startGame/heroes/explorer2.png");
                case "Tommy Miller" -> new Image("views/resources/startGame/heroes/explorer3.png");
                case "Ellie Williams" -> new Image("views/resources/startGame/heroes/medic1.png");
                case "Bill" -> new Image("views/resources/startGame/heroes/medic2.png");
                case "Henry Burell" -> new Image("views/resources/startGame/heroes/medic3.png");
                default -> null;
            };

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            imageView.setOnMouseClicked(event -> {
                selectedHero = hero;
                updateHeroContainer(heroContainer, selectedHero);


            });
            if (!isHeroPlaced[i]) {
                TranslateTransition tt = new TranslateTransition(Duration.seconds(1.5), imageView);
                tt.setFromX(500);
                tt.setToX(0);
                tt.play();
                isHeroPlaced[i] = true;
            }
            if (!isLabelPlaced) {
                TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1.5), label2);
                tt2.setFromX(1000);
                tt2.setToX(0);
                tt2.play();
                isLabelPlaced = true;
            }
            heroesBox.getChildren().add(imageView);
            if ((i + 1) % 2 == 0 || i == heroes.size() - 1) {
                buttonContainer.getChildren().add(heroesBox);
                heroesBox = new HBox();
                heroesBox.setAlignment(Pos.CENTER);
                heroesBox.setSpacing(10);
            }
        }
        buttonContainer.getChildren().addAll(label3, message, attackButton, useSpecialButton, cureButton, endTurnButton);
    }

    private void loadImages() {
        background = new Image("views/resources/startGame/Background.jpg");
        cellImage = new Image("views/resources/startGame/Background.png");
        cellImageUnlocked = new Image("views/resources/startGame/cellImage.png");
        vaccineImage = new Image("views/resources/startGame/vaccine.png");
        supplyImage = new Image("views/resources/startGame/supply.png");
        String[] heroNames = {"fighter1", "fighter2", "explorer1", "explorer2", "explorer3", "medic1", "medic2", "medic3"};
        String[] stateNames = {"steady", "move", "attack", "dead"};

        for (int i = 0; i < heroNames.length; i++) {
            for (int j = 0; j < stateNames.length; j++) {
                String imagePath = "views/resources/Assets/" + heroNames[i] + "/" + stateNames[j] + ".gif";
                Image image = new Image(imagePath);

                switch (i) {
                    case 0 -> fighter1[j] = image;
                    case 1 -> fighter2[j] = image;
                    case 2 -> explorer1[j] = image;
                    case 3 -> explorer2[j] = image;
                    case 4 -> explorer3[j] = image;
                    case 5 -> medic1[j] = image;
                    case 6 -> medic2[j] = image;
                    case 7 -> medic3[j] = image;
                }
            }

            String deadImagePath = "views/resources/Assets/" + heroNames[i] + "/dead.png";
            Image deadImage = new Image(deadImagePath);
            switch (i) {
                case 0 -> fighter1[4] = deadImage;
                case 1 -> fighter2[4] = deadImage;
                case 2 -> explorer1[4] = deadImage;
                case 3 -> explorer2[4] = deadImage;
                case 4 -> explorer3[4] = deadImage;
                case 5 -> medic1[4] = deadImage;
                case 6 -> medic2[4] = deadImage;
                case 7 -> medic3[4] = deadImage;
            }
        }

        zombie[0] = new Image("views/resources/Assets/zombie/steady.gif");
        zombie[1] = new Image("views/resources/Assets/zombie/attack.gif");
        zombie[2] = new Image("views/resources/Assets/zombie/dead.gif");
        zombie[3] = new Image("views/resources/Assets/zombie/dead.png");
    }

    private void winScreen(StackPane root, Stage primaryStage) {
        availableHeroes.clear();
        heroes.clear();
        StackPane winScreen = new StackPane();
        winScreen.setAlignment(Pos.CENTER);

        winScreen.prefWidthProperty().bind(primaryStage.widthProperty());
        winScreen.prefHeightProperty().bind(primaryStage.heightProperty());

        Rectangle overlay = new Rectangle();
        overlay.setWidth(primaryStage.getWidth());
        overlay.setHeight(primaryStage.getHeight());
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0);
        root.getChildren().add(overlay);

        Label label = new Label("YOU WON!");
        Font customFont = Font.loadFont(StartGameScene.class.getResourceAsStream("/views/resources/fonts/font.ttf"), 64);
        label.setFont(customFont);
        label.setTextFill(Color.LIGHTGREEN);
        label.setTranslateY(-200);
        Timeline labelFadeTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(label.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(label.opacityProperty(), 0.5)),
                new KeyFrame(Duration.seconds(1), new KeyValue(label.opacityProperty(), 1))
        );
        labelFadeTimeline.setCycleCount(Timeline.INDEFINITE);
        labelFadeTimeline.play();

        Image imageZombie = zombie[2];
        Image deadImage = zombie[3];

        ImageView imageZombieView = new ImageView(imageZombie);
        ImageView imageDeadView = new ImageView(deadImage);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), overlay);
        fadeTransition.setToValue(0.65);
        fadeTransition.play();

        Button mainMenuButton = createButton("views/resources/startGame/mainMenuButton/mainMenuButtonNormal.png", "views/resources/startGame/mainMenuButton/mainMenuButtonSelected.png", 0.4, 0.2);
        Button playAgainButton = createButton("views/resources/startGame/playAgainButton/playAgainButtonNormal.png", "views/resources/startGame/playAgainButton/playAgainButtonSelected.png", 0.4, 0.2);
        Button exitButton = createButton("views/resources/startGame/exitButton/exitButtonNormal.png", "views/resources/startGame/exitButton/exitButtonSelected.png", 0.4, 0.2);

        fadeTransition.setOnFinished(actionEvent -> {
            root.getChildren().addAll(label, imageZombieView);

            winScreen.getChildren().add(mainMenuButton);
            mainMenuButton.setTranslateX(-500);
            mainMenuButton.setTranslateY(300);
            mainMenuButton.setOnMouseClicked(event -> {
                Main main = new Main();
                main.start(primaryStage);
                primaryStage.setFullScreen(true);
            });

            winScreen.getChildren().add(playAgainButton);
            playAgainButton.setTranslateY(300);
            playAgainButton.setOnMouseClicked(event -> {
                try {
                    ChampionSelect championSelect = new ChampionSelect(primaryStage);
                    primaryStage.setScene(championSelect.getScene());
                    primaryStage.setFullScreen(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            winScreen.getChildren().add(exitButton);
            exitButton.setTranslateX(500);
            exitButton.setTranslateY(300);
            exitButton.setOnMouseClicked(event -> Platform.runLater(() -> {
                primaryStage.close();
                Platform.exit();
            }));
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.8));
            pauseTransition.setOnFinished(event -> {
                root.getChildren().remove(imageZombieView);
                root.getChildren().add(imageDeadView);
            });
            pauseTransition.play();
        });

        root.getChildren().add(winScreen);
    }

    private void loseScreen(Hero hero, StackPane root, Stage primaryStage) {
        availableHeroes.clear();
        heroes.clear();
        StackPane loseScreen = new StackPane();
        loseScreen.setAlignment(Pos.CENTER);

        loseScreen.prefWidthProperty().bind(primaryStage.widthProperty());
        loseScreen.prefHeightProperty().bind(primaryStage.heightProperty());

        Rectangle overlay = new Rectangle();
        overlay.setWidth(primaryStage.getWidth());
        overlay.setHeight(primaryStage.getHeight());
        overlay.setFill(Color.BLACK);
        overlay.setOpacity(0);
        root.getChildren().add(overlay);

        Label label = new Label("YOU LOST!");
        Font customFont = Font.loadFont(StartGameScene.class.getResourceAsStream("/views/resources/fonts/font.ttf"), 64);
        label.setFont(customFont);
        label.setTextFill(Color.RED);
        label.setTranslateY(-200);
        Timeline labelFadeTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(label.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(label.opacityProperty(), 0.5)),
                new KeyFrame(Duration.seconds(1), new KeyValue(label.opacityProperty(), 1))
        );
        labelFadeTimeline.setCycleCount(Timeline.INDEFINITE);
        labelFadeTimeline.play();


        Image image = switch (hero.getName()) {
            case "Joel Miller" -> fighter1[3];
            case "David" -> fighter2[3];
            case "Riley Abel" -> explorer1[3];
            case "Tess" -> explorer2[3];
            case "Tommy Miller" -> explorer3[3];
            case "Ellie Williams" -> medic1[3];
            case "Bill" -> medic2[3];
            case "Henry Burell" -> medic3[3];
            default -> zombie[2];
        };
        Image deadImage = switch (hero.getName()) {
            case "Joel Miller" -> fighter1[4];
            case "David" -> fighter2[4];
            case "Riley Abel" -> explorer1[4];
            case "Tess" -> explorer2[4];
            case "Tommy Miller" -> explorer3[4];
            case "Ellie Williams" -> medic1[4];
            case "Bill" -> medic2[4];
            case "Henry Burell" -> medic3[4];
            default -> zombie[3];
        };
        ImageView imageGifView = new ImageView(image);
        ImageView imageDeadView = new ImageView(deadImage);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), overlay);
        fadeTransition.setToValue(0.65);
        fadeTransition.play();

        Button mainMenuButton = createButton("views/resources/startGame/mainMenuButton/mainMenuButtonNormal.png", "views/resources/startGame/mainMenuButton/mainMenuButtonSelected.png", 0.4, 0.2);
        Button playAgainButton = createButton("views/resources/startGame/playAgainButton/playAgainButtonNormal.png", "views/resources/startGame/playAgainButton/playAgainButtonSelected.png", 0.4, 0.2);
        Button exitButton = createButton("views/resources/startGame/exitButton/exitButtonNormal.png", "views/resources/startGame/exitButton/exitButtonSelected.png", 0.4, 0.2);

        fadeTransition.setOnFinished(actionEvent -> {
            root.getChildren().addAll(label, imageGifView);

            loseScreen.getChildren().add(mainMenuButton);
            mainMenuButton.setTranslateX(-500);
            mainMenuButton.setTranslateY(300);
            mainMenuButton.setOnMouseClicked(event -> {
                Main main = new Main();
                main.start(primaryStage);
                primaryStage.setFullScreen(true);
            });

            loseScreen.getChildren().add(playAgainButton);
            playAgainButton.setTranslateY(300);
            playAgainButton.setOnMouseClicked(event -> {
                try {
                    ChampionSelect championSelect = new ChampionSelect(primaryStage);
                    primaryStage.setScene(championSelect.getScene());
                    primaryStage.setFullScreen(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            loseScreen.getChildren().add(exitButton);
            exitButton.setTranslateX(500);
            exitButton.setTranslateY(300);
            exitButton.setOnMouseClicked(event -> Platform.runLater(() -> {
                primaryStage.close();
                Platform.exit();
            }));
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.8));
            pauseTransition.setOnFinished(event -> {
                root.getChildren().remove(imageGifView);
                root.getChildren().add(imageDeadView);
            });
            pauseTransition.play();
        });

        root.getChildren().add(loseScreen);
    }

    public void createNotification(String message, Color color, StackPane stackPane) {
        Label label = new Label(message);
        Font customFont = Font.loadFont(StartGameScene.class.getResourceAsStream("/views/resources/fonts/font.ttf"), 30);
        label.setFont(customFont);
        label.setTextFill(color);

        StackPane notificationPane = new StackPane();
        notificationPane.setAlignment(Pos.CENTER);

        Image image = new Image("views/resources/startGame/notificationBox.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setFitHeight(50);
        notificationPane.getChildren().addAll(imageView, label);

        stackPane.getChildren().add(notificationPane);

        FadeTransition fadeTransitionIn = new FadeTransition(Duration.seconds(0.3), notificationPane);
        fadeTransitionIn.setFromValue(0.0);
        fadeTransitionIn.setToValue(1.0);

        TranslateTransition translateTransitionUp = new TranslateTransition(Duration.seconds(5), notificationPane);
        translateTransitionUp.setFromY(0);
        translateTransitionUp.setToY(-500);

        FadeTransition fadeTransitionOut = new FadeTransition(Duration.seconds(1), notificationPane);
        fadeTransitionOut.setFromValue(1.0);
        fadeTransitionOut.setToValue(0.0);
        fadeTransitionOut.setOnFinished(event -> stackPane.getChildren().remove(notificationPane));

        fadeTransitionIn.setOnFinished(event -> {
            translateTransitionUp.play();
            fadeTransitionOut.play();
        });

        fadeTransitionIn.play();
    }

    public Button createButton(String normalImagePath, String hoverImagePath, double scaleX, double scaleY) {
        Image normalImage = new Image(normalImagePath);
        ImageView buttonView = new ImageView(normalImage);
        Image hoverImage = new Image(hoverImagePath);
        buttonView.setFitWidth(normalImage.getWidth() * scaleX);
        buttonView.setFitHeight(normalImage.getHeight() * scaleY);
        Button button = new Button();
        button.setGraphic(buttonView);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setOnMouseEntered(mouseEvent -> buttonView.setImage(hoverImage));
        button.setOnMouseExited(mouseEvent -> buttonView.setImage(normalImage));
        button.setPadding(Insets.EMPTY);
        return button;
    }

    public void checkDead() {
        if (selectedHero.getTarget() != null) {
            if (selectedHero.getTarget().getCurrentHp() <= 0) {
                selectedHero.setTarget(null);
            }
        }
        if (selectedHero.getCurrentHp() <= 0) {
            selectedHero = null;
        }
        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getCurrentHp() <= 0) {
                heroes.get(i).onCharacterDeath();
            }
        }
        for (int i = 0; i < zombies.size(); i++) {
            if (zombies.get(i).getCurrentHp() <= 0) {
                zombies.get(i).onCharacterDeath();
            }
        }
    }

    private void updateHeroContainer(VBox heroContainer, Hero hero) {
        heroContainer.getChildren().clear(); // Clear previous content
        String fontPath = "/views/resources/fonts/font.ttf"; // Update with the correct font file path
        Font labelFont = Font.loadFont(getClass().getResourceAsStream(fontPath), 30);

        if (selectedHero != null) {

            ImageView heroImageView = new ImageView(getCharacterImage(hero.getName()));
            heroImageView.setFitWidth(250);
            heroImageView.setPreserveRatio(true);
            heroImageView.setTranslateX(20);
            heroImageView.setTranslateY(-20);

            StringBuilder labelText = new StringBuilder();

            Label lineLabel = new Label("                                                                   ");
            Label nameLabel = new Label();
            Label typeLabel = new Label();
            Label healthLabel = new Label();
            Label attackDmgLabel = new Label();
            Label actionsAvailableLabel = new Label();
            Label amountSuppliesLabel = new Label();
            Label amountVaccinesLabel = new Label();
            Label targetLabel = new Label();
            Label targetHealthLabel = new Label();

            // Apply shared formatting to all labels
            Color labelColor = Color.LIGHTGOLDENRODYELLOW;
            String labelTextAlignment = "-fx-alignment: center;";

            lineLabel.setFont(labelFont);
            lineLabel.setTextFill(labelColor);

            nameLabel.setFont(labelFont);
            nameLabel.setTextFill(labelColor);
            nameLabel.setStyle(labelTextAlignment);

            typeLabel.setFont(labelFont);
            typeLabel.setTextFill(labelColor);
            typeLabel.setStyle(labelTextAlignment);

            healthLabel.setFont(labelFont);
            healthLabel.setTextFill(labelColor);
            healthLabel.setStyle(labelTextAlignment);

            attackDmgLabel.setFont(labelFont);
            attackDmgLabel.setTextFill(labelColor);
            attackDmgLabel.setStyle(labelTextAlignment);

            actionsAvailableLabel.setFont(labelFont);
            actionsAvailableLabel.setTextFill(labelColor);
            actionsAvailableLabel.setStyle(labelTextAlignment);

            amountSuppliesLabel.setFont(labelFont);
            amountSuppliesLabel.setTextFill(labelColor);
            amountSuppliesLabel.setStyle(labelTextAlignment);

            amountVaccinesLabel.setFont(labelFont);
            amountVaccinesLabel.setTextFill(labelColor);
            amountVaccinesLabel.setStyle(labelTextAlignment);

            targetLabel.setFont(labelFont);
            targetLabel.setTextFill(labelColor);
            targetLabel.setStyle(labelTextAlignment);

            targetHealthLabel.setFont(labelFont);
            targetHealthLabel.setTextFill(labelColor);
            targetHealthLabel.setStyle(labelTextAlignment);

            // Update label texts
            labelText.setLength(0);
            labelText.append("Name: ").append(hero.getName());
            nameLabel.setText(labelText.toString());

            labelText.setLength(0);
            if (hero instanceof Fighter) {
                labelText.append("Type: Fighter\n");
            } else if (hero instanceof Medic) {
                labelText.append("Type: Medic\n");
            } else if (hero instanceof Explorer) {
                labelText.append("Type: Explorer\n");
            }
            typeLabel.setText(labelText.toString());

            labelText.setLength(0);
            labelText.append("Health: ").append(hero.getCurrentHp());
            healthLabel.setText(labelText.toString());

            labelText.setLength(0);
            labelText.append("AttackDmg: ").append(hero.getAttackDmg());
            attackDmgLabel.setText(labelText.toString());

            labelText.setLength(0);
            labelText.append("Actions Available: ").append(hero.getActionsAvailable());
            actionsAvailableLabel.setText(labelText.toString());

            labelText.setLength(0);
            labelText.append("Supplies: ").append(hero.getSupplyInventory().size());
            amountSuppliesLabel.setText(labelText.toString());

            labelText.setLength(0);
            labelText.append("Vaccines: ").append(hero.getVaccineInventory().size());
            amountVaccinesLabel.setText(labelText.toString());

            labelText.setLength(0);
            labelText.append("Target: ").append(hero.getTarget() == null ? "NULL" : hero.getTarget().getName());
            targetLabel.setText(labelText.toString());

            labelText.setLength(0);
            labelText.append("Target Health: ").append(hero.getTarget() == null ? "NULL" : hero.getTarget().getCurrentHp());
            targetHealthLabel.setText(labelText.toString());

            // Set alignment properties
            heroContainer.setAlignment(Pos.CENTER);
            nameLabel.setAlignment(Pos.CENTER);
            typeLabel.setAlignment(Pos.CENTER);
            healthLabel.setAlignment(Pos.CENTER);
            attackDmgLabel.setAlignment(Pos.CENTER);
            actionsAvailableLabel.setAlignment(Pos.CENTER);
            amountSuppliesLabel.setAlignment(Pos.CENTER);
            amountVaccinesLabel.setAlignment(Pos.CENTER);
            targetLabel.setAlignment(Pos.CENTER);
            targetHealthLabel.setAlignment(Pos.CENTER);

            heroContainer.setSpacing(10); // Adjust the spacing as needed

            heroContainer.getChildren().addAll(heroImageView, lineLabel, nameLabel, typeLabel,
                    healthLabel, attackDmgLabel, actionsAvailableLabel, amountSuppliesLabel, amountVaccinesLabel, targetLabel, targetHealthLabel);
        } else {
            Label lineLabel = new Label("                                                                   ");
            Label nullLabel = new Label();

            StringBuilder labelText = new StringBuilder();
            Color labelColor = Color.LIGHTGOLDENRODYELLOW;
            String labelTextAlignment = "-fx-alignment: center;";

            lineLabel.setFont(labelFont);
            lineLabel.setTextFill(labelColor);

            nullLabel.setFont(labelFont);
            nullLabel.setTextFill(labelColor);
            nullLabel.setStyle(labelTextAlignment);

            labelText.setLength(0);
            labelText.append("SELECT A HERO");
            nullLabel.setText(labelText.toString());

            heroContainer.setAlignment(Pos.CENTER);
            nullLabel.setAlignment(Pos.CENTER);

            heroContainer.setSpacing(10); // Adjust the spacing as needed

            heroContainer.getChildren().addAll(lineLabel, nullLabel);
        }
    }

    private Background createBackground(String imagePath, double scaleX, double scaleY) {
        Image backgroundImg = new Image(imagePath);
        double width = backgroundImg.getWidth() * scaleX;
        double height = backgroundImg.getHeight() * scaleY;

        BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundImg,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, backgroundSize);

        return new Background(backgroundImage);
    }

    private void createMap(GridPane gridPane, BorderPane borderPane, VBox heroContainer, VBox buttonContainer, Button attackButton, Button useSpecialButton, Button cureButton, Button endTurnButton) {
        Cell[][] map = Game.map;
        int numRows = map.length;
        int numCols = map[0].length;

        buttons = new Button[numRows][numCols]; // Initialize the buttons array

        for (int row = numRows - 1; row >= 0; row--) {
            for (int col = 0; col < numCols; col++) {
                Cell cell = map[row][col];
                if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() != null && cell.isVisible()) {
                    String characterName = ((CharacterCell) cell).getCharacter().getName();
                    Image image = getCharacterImage(characterName);
                    Button button = createButton(cell, gridPane, cellImageUnlocked, image);
                    gridPane.add(button, col, numRows - 1 - row);
                    buttons[row][col] = button; // Store the reference to the button
                    button.setOnMouseClicked(event -> {
                        // Get the selected hero and update the hero container
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                            if (((CharacterCell) cell).getCharacter() instanceof Hero) {
                                selectedHero = (Hero) ((CharacterCell) cell).getCharacter();
                                Character Target = ((CharacterCell) cell).getCharacter();
                                selectedHero.setTarget(Target);
                                updateHeroContainer(heroContainer, selectedHero);
                            } else {
                                if (selectedHero != null) {
                                    Character Target = ((CharacterCell) cell).getCharacter();
                                    selectedHero.setTarget(Target);
                                    updateHeroContainer(heroContainer, selectedHero);
                                }
                            }
                        } else if (event.getButton().equals(MouseButton.SECONDARY)) {
                            if (((CharacterCell) cell).getCharacter() instanceof Hero) {
                                Character Target = ((CharacterCell) cell).getCharacter();
                                selectedHero.setTarget(Target);
                                updateHeroContainer(heroContainer, selectedHero);
                            } else {
                                if (selectedHero != null) {
                                    Character Target = ((CharacterCell) cell).getCharacter();
                                    selectedHero.setTarget(Target);
                                    updateHeroContainer(heroContainer, selectedHero);
                                }
                            }
                        }
                    });

                } else if (cell instanceof CollectibleCell && ((CollectibleCell) cell).getCollectible() instanceof Vaccine && cell.isVisible()) {
                    Button button = createButton(cell, gridPane, cellImageUnlocked, vaccineImage);
                    gridPane.add(button, col, numRows - 1 - row);
                    buttons[row][col] = button;
                } else if (cell instanceof CollectibleCell && ((CollectibleCell) cell).getCollectible() instanceof Supply && cell.isVisible()) {
                    Button button = createButton(cell, gridPane, cellImageUnlocked, supplyImage);
                    gridPane.add(button, col, numRows - 1 - row);
                    buttons[row][col] = button;
                } else if (cell.isVisible()) {
                    Button button = createButton(cell, gridPane, cellImageUnlocked, null);
                    gridPane.add(button, col, numRows - 1 - row);
                    buttons[row][col] = button;
                } else {
                    Button button = createButton(cell, gridPane, cellImage, null);
                    gridPane.add(button, col, numRows - 1 - row);
                    buttons[row][col] = button;
                }
            }
        }
    }

    private void updateMap(VBox heroContainer, VBox buttonContainer, Button attackButton, Button useSpecialButton, Button cureButton, Button endTurnButton) {
        Cell[][] map = Game.map;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                Cell cell = map[row][col];
                Button button = buttons[row][col];

                if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() != null && cell.isVisible()) {
                    String characterName = ((CharacterCell) cell).getCharacter().getName();
                    Image image = getCharacterImage(characterName);
                    updateButton(button, cellImageUnlocked, image, cell, heroContainer);

                } else if (cell instanceof CollectibleCell && ((CollectibleCell) cell).getCollectible() instanceof Vaccine && cell.isVisible()) {
                    updateButton(button, cellImageUnlocked, vaccineImage, cell, heroContainer);
                } else if (cell instanceof CollectibleCell && ((CollectibleCell) cell).getCollectible() instanceof Supply && cell.isVisible()) {
                    updateButton(button, cellImageUnlocked, supplyImage, cell, heroContainer);
                } else if (cell.isVisible()) {
                    updateButton(button, cellImageUnlocked, null, cell, heroContainer);
                } else {
                    updateButton(button, cellImage, null, cell, heroContainer);
                }
            }
        }
    }

    private void updateButton(Button button, Image cellImage1, Image image, Cell cell, VBox heroContainer) {
        ImageView buttonView1 = new ImageView(cellImage1);
        buttonView1.setFitWidth(66);
        buttonView1.setFitHeight(66);

        ImageView buttonView2 = new ImageView(image);
        buttonView2.setFitWidth(66);
        buttonView2.setFitHeight(66);

        StackPane stackPane = new StackPane();
        stackPane.setPadding(Insets.EMPTY);
        stackPane.getChildren().addAll(buttonView1, buttonView2);

        button.setPadding(Insets.EMPTY);
        button.setGraphic(stackPane);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        button.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (((CharacterCell) cell).getCharacter() instanceof Hero) {
                    selectedHero = (Hero) ((CharacterCell) cell).getCharacter();
                    Character target = ((CharacterCell) cell).getCharacter();
                    selectedHero.setTarget(target);
                    updateHeroContainer(heroContainer, selectedHero);
                } else {
                    if (selectedHero != null) {
                        Character target = ((CharacterCell) cell).getCharacter();
                        selectedHero.setTarget(target);
                        updateHeroContainer(heroContainer, selectedHero);
                    }
                }
            } else if (event.getButton().equals(MouseButton.SECONDARY)) {
                if (((CharacterCell) cell).getCharacter() instanceof Hero) {
                    Character target = ((CharacterCell) cell).getCharacter();
                    selectedHero.setTarget(target);
                    updateHeroContainer(heroContainer, selectedHero);
                } else {
                    if (selectedHero != null) {
                        Character target = ((CharacterCell) cell).getCharacter();
                        selectedHero.setTarget(target);
                        updateHeroContainer(heroContainer, selectedHero);
                    }
                }
            }
        });
    }

    private Button createButton(Cell cell, GridPane gridPane, Image cellImage1, Image image) {
        ImageView buttonView1 = new ImageView(cellImage1);
        buttonView1.setFitWidth(66);
        buttonView1.setFitHeight(66);

        ImageView buttonView2 = new ImageView(image);
        buttonView2.setFitWidth(66);
        buttonView2.setFitHeight(66);

        StackPane stackPane = new StackPane();
        stackPane.setPadding(Insets.EMPTY);
        stackPane.getChildren().addAll(buttonView1, buttonView2);

        Button button = new Button();
        button.setPadding(Insets.EMPTY);
        button.setGraphic(stackPane);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        return button;
    }

    private void handleKeyPress(KeyEvent event, Hero hero, VBox heroContainer, StackPane root) throws MovementException, NotEnoughActionsException {
        KeyCode keyCode = event.getCode();
        Direction direction;

        switch (keyCode) {
            case W -> direction = Direction.UP;
            case S -> direction = Direction.DOWN;
            case D -> direction = Direction.RIGHT;
            case A -> direction = Direction.LEFT;
            default -> {
                return;
            }
        }
        int previousHp = hero.getCurrentHp();
        int suppliesBefore = hero.getSupplyInventory().size();
        int vaccinesBefore = hero.getVaccineInventory().size();
        hero.move(direction);
        int currentHp = hero.getCurrentHp();
        int suppliesAfter = hero.getSupplyInventory().size();
        int vaccinesAfter = hero.getVaccineInventory().size();
        if (previousHp != currentHp)
            createNotification("Be Careful! You stepped on a trap", Color.RED, root);
        if (suppliesBefore != suppliesAfter)
            createNotification("Picked Up 1 supply", Color.LIGHTGREEN, root);
        if (vaccinesBefore != vaccinesAfter)
            createNotification("Picked Up 1 vaccine", Color.LIGHTGREEN, root);
        updateHeroContainer(heroContainer, hero);
    }

    private Image getCharacterImage(String characterName) {
        return switch (characterName) {
            case "Joel Miller" -> fighter1[0];
            case "David" -> fighter2[0];
            case "Riley Abel" -> explorer1[0];
            case "Tess" -> explorer2[0];
            case "Tommy Miller" -> explorer3[0];
            case "Ellie Williams" -> medic1[0];
            case "Bill" -> medic2[0];
            case "Henry Burell" -> medic3[0];
            default -> zombie[0];
        };
    }
    public Scene getScene() {
        return scene;
    }
}