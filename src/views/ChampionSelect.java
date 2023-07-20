package views;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;

import static engine.Game.availableHeroes;
import static engine.Game.loadHeroes;


public class ChampionSelect extends Main {
    HBox championbox1, championbox2, championbox3, championbox4, championbox5, championbox6, championbox7, championbox8;
    ImageView gif1, gif2, gif3, gif4, gif5, gif6, gif7, gif8;
    Button lockinButton;
    private Scene scene;


    public ChampionSelect(Stage primaryStage) throws IOException {
        loadHeroes("Heroes.csv");
        Image backgroundImage = new Image("views/resources/ChampionSelect/Background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        GridPane root = new GridPane();
        scene = new Scene(root, 1920, 1080);
        root.getChildren().addAll(backgroundImageView);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / 15);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100.0 / 15);

        for (int i = 0; i < 15; i++) {
            root.getColumnConstraints().add(columnConstraints);
            root.getRowConstraints().add(rowConstraints);
        }
        GridPane.setConstraints(backgroundImageView, 0, 0, 15, 15);
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            backgroundImageView.setFitWidth((double) newValue);
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            backgroundImageView.setFitHeight((double) newValue);
        });

        Button backButton = createButton("views/resources/ChampionSelect/backButton/backButtonNormal.png",
                "views/resources/ChampionSelect/backButton/backButtonSelected.png",
                2, 0.5, root);
        root.add(backButton, 1, 13);

        Button fighter1 = createButton("views/resources/ChampionSelect/fighter1/fighter1.png", "views/resources/ChampionSelect/fighter1/fighter1.png", 1.5, 1.5, root);
        gif1 = addGif("views/resources/ChampionSelect/fighter1/fighter1.gif");
        root.add(fighter1, 4, 11);
        attachAnimation(-2800, 4, 3, fighter1);

        Button fighter2 = createButton("views/resources/ChampionSelect/fighter2/fighter2.png", "views/resources/ChampionSelect/fighter2/fighter2.png", 1.5, 1.5, root);
        gif2 = addGif("views/resources/ChampionSelect/fighter2/fighter2.gif");
        root.add(fighter2, 6, 11);
        attachAnimation(-2800, 6, 3, fighter2);

        Button explorer1 = createButton("views/resources/ChampionSelect/explorer1/explorer1.png", "views/resources/ChampionSelect/explorer1/explorer1.png", 1.5, 1.5, root);
        gif3 = addGif("views/resources/ChampionSelect/explorer1/explorer1.gif");
        root.add(explorer1, 8, 11);
        attachAnimation(-2800, 8, 3, explorer1);

        Button explorer2 = createButton("views/resources/ChampionSelect/explorer2/explorer2.png", "views/resources/ChampionSelect/explorer2/explorer2.png", 1.5, 1.5, root);
        gif4 = addGif("views/resources/ChampionSelect/explorer2/explorer2.gif");
        root.add(explorer2, 10, 11);
        attachAnimation(-2800, 10, 3, explorer2);

        Button explorer3 = createButton("views/resources/ChampionSelect/explorer3/explorer3.png", "views/resources/ChampionSelect/explorer3/explorer3.png", 1.5, 1.5, root);
        gif5 = addGif("views/resources/ChampionSelect/explorer3/explorer3.gif");
        root.add(explorer3, 3, 9);
        attachAnimation(2800, 3, 3, explorer3);

        Button medic1 = createButton("views/resources/ChampionSelect/medic1/medic1.png", "views/resources/ChampionSelect/medic1/medic1.png", 1.5, 1.5, root);
        gif6 = addGif("views/resources/ChampionSelect/medic1/medic1.gif");
        root.add(medic1, 5, 9);
        attachAnimation(2800, 5, 3, medic1);

        Button medic2 = createButton("views/resources/ChampionSelect/medic2/medic2.png", "views/resources/ChampionSelect/medic2/medic2.png", 1.5, 1.5, root);
        gif7 = addGif("views/resources/ChampionSelect/medic2/medic2.gif");
        root.add(medic2, 7, 9);
        attachAnimation(2800, 7, 3, medic2);

        Button medic3 = createButton("views/resources/ChampionSelect/medic3/medic3.png", "views/resources/ChampionSelect/medic3/medic3.png", 1.5, 1.5, root);
        gif8 = addGif("views/resources/ChampionSelect/medic3/medic3.gif");
        root.add(medic3, 9, 9);
        attachAnimation(2800, 9, 3, medic3);

        attachAnimation(-1200, 2, 2, backButton);

        backButton.setOnAction(event -> {
            Main main = new Main();
            attachAnimation(1, -2800, 1, backButton);
            attachAnimation(12, 2800, 1, lockinButton);
            attachAnimation(4, -2800, 1, fighter1);
            attachAnimation(6, -2800, 1, fighter2);
            attachAnimation(8, -2800, 1, explorer1);
            attachAnimation(10, -2800, 1, explorer2);
            attachAnimation(3, 2800, 1, explorer3);
            attachAnimation(5, 2800, 1, medic1);
            attachAnimation(7, 2800, 1, medic2);
            TranslateTransition translateTransition = attachAnimation(9, 2800, 1, medic3);
            translateTransition.setOnFinished(actionEvent -> Platform.runLater(() -> {
                main.start(primaryStage);
                primaryStage.setFullScreen(true);
            }));
        });

        championbox1 = createCenterHBox(scene, "views/resources/ChampionSelect/fighter1/fighter1Hover.png");
        championbox2 = createCenterHBox(scene, "views/resources/ChampionSelect/fighter2/fighter2Hover.png");
        championbox3 = createCenterHBox(scene, "views/resources/ChampionSelect/explorer1/explorer1Hover.png");
        championbox4 = createCenterHBox(scene, "views/resources/ChampionSelect/explorer2/explorer2Hover.png");
        championbox5 = createCenterHBox(scene, "views/resources/ChampionSelect/explorer3/explorer3Hover.png");
        championbox6 = createCenterHBox(scene, "views/resources/ChampionSelect/medic1/medic1Hover.png");
        championbox7 = createCenterHBox(scene, "views/resources/ChampionSelect/medic2/medic2Hover.png");
        championbox8 = createCenterHBox(scene, "views/resources/ChampionSelect/medic3/medic3Hover.png");

        lockinButton = createButton("views/resources/ChampionSelect/lockinButton/lockinButtonNormal.png", "views/resources/ChampionSelect/lockinButton/lockinButtonSelected.png", 2, 0.5, root);

        fighter1.setOnAction(actionEvent -> {
            if (root.getChildren().contains(championbox1)) {
                root.getChildren().removeAll(championbox1, gif1, lockinButton);
            } else {
                removeAll(root);
                root.add(championbox1, 4, 5);
                root.add(gif1, 7, 5);
                root.add(lockinButton, 12, 13);
                lockinButton.setOnAction(actionEvent1 -> {
                    attachAnimation(1, -2800, 1, backButton);
                    attachAnimation(12, 2800, 1, lockinButton);
                    attachAnimation(4, -2800, 1, fighter1);
                    attachAnimation(6, -2800, 1, fighter2);
                    attachAnimation(8, -2800, 1, explorer1);
                    attachAnimation(10, -2800, 1, explorer2);
                    attachAnimation(3, 2800, 1, explorer3);
                    attachAnimation(5, 2800, 1, medic1);
                    attachAnimation(7, 2800, 1, medic2);
                    TranslateTransition translateTransition = attachAnimation(9, 2800, 1, medic3);
                    translateTransition.setOnFinished(event -> Platform.runLater(() -> {
                        try {
                            StartGameScene startGameScene = new StartGameScene(primaryStage, availableHeroes.get(0));
                            primaryStage.setScene(startGameScene.getScene());
                            primaryStage.setFullScreen(true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                });
            }
        });

        fighter2.setOnAction(actionEvent -> {
            if (root.getChildren().contains(championbox2)) {
                root.getChildren().removeAll(championbox2, gif2, lockinButton);
            } else {
                removeAll(root);
                root.add(championbox2, 4, 5);
                root.add(gif2, 7, 5);
                root.add(lockinButton, 12, 13);
                lockinButton.setOnAction(actionEvent1 -> {
                    attachAnimation(1, -2800, 1, backButton);
                    attachAnimation(12, 2800, 1, lockinButton);
                    attachAnimation(4, -2800, 1, fighter1);
                    attachAnimation(6, -2800, 1, fighter2);
                    attachAnimation(8, -2800, 1, explorer1);
                    attachAnimation(10, -2800, 1, explorer2);
                    attachAnimation(3, 2800, 1, explorer3);
                    attachAnimation(5, 2800, 1, medic1);
                    attachAnimation(7, 2800, 1, medic2);
                    TranslateTransition translateTransition = attachAnimation(9, 2800, 1, medic3);
                    translateTransition.setOnFinished(event -> Platform.runLater(() -> {
                        try {
                            StartGameScene startGameScene = new StartGameScene(primaryStage, availableHeroes.get(1));
                            primaryStage.setScene(startGameScene.getScene());
                            primaryStage.setFullScreen(true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                });
            }
        });

        explorer1.setOnAction(actionEvent -> {
            if (root.getChildren().contains(championbox3)) {
                root.getChildren().removeAll(championbox3, gif3, lockinButton);
            } else {
                removeAll(root);
                root.add(championbox3, 4, 5);
                root.add(gif3, 7, 5);
                root.add(lockinButton, 12, 13);
                lockinButton.setOnAction(actionEvent1 -> {
                    attachAnimation(1, -2800, 1, backButton);
                    attachAnimation(12, 2800, 1, lockinButton);
                    attachAnimation(4, -2800, 1, fighter1);
                    attachAnimation(6, -2800, 1, fighter2);
                    attachAnimation(8, -2800, 1, explorer1);
                    attachAnimation(10, -2800, 1, explorer2);
                    attachAnimation(3, 2800, 1, explorer3);
                    attachAnimation(5, 2800, 1, medic1);
                    attachAnimation(7, 2800, 1, medic2);
                    TranslateTransition translateTransition = attachAnimation(9, 2800, 1, medic3);
                    translateTransition.setOnFinished(event -> Platform.runLater(() -> {
                        try {
                            StartGameScene startGameScene = new StartGameScene(primaryStage, availableHeroes.get(2));
                            primaryStage.setScene(startGameScene.getScene());
                            primaryStage.setFullScreen(true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                });
            }
        });

        explorer2.setOnAction(actionEvent -> {
            if (root.getChildren().contains(championbox4)) {
                root.getChildren().removeAll(championbox4, gif4, lockinButton);
            } else {
                removeAll(root);
                root.add(championbox4, 4, 5);
                root.add(gif4, 7, 5);
                root.add(lockinButton, 12, 13);
                lockinButton.setOnAction(actionEvent1 -> {
                    attachAnimation(1, -2800, 1, backButton);
                    attachAnimation(12, 2800, 1, lockinButton);
                    attachAnimation(4, -2800, 1, fighter1);
                    attachAnimation(6, -2800, 1, fighter2);
                    attachAnimation(8, -2800, 1, explorer1);
                    attachAnimation(10, -2800, 1, explorer2);
                    attachAnimation(3, 2800, 1, explorer3);
                    attachAnimation(5, 2800, 1, medic1);
                    attachAnimation(7, 2800, 1, medic2);
                    TranslateTransition translateTransition = attachAnimation(9, 2800, 1, medic3);
                    translateTransition.setOnFinished(event -> Platform.runLater(() -> {
                        try {
                            StartGameScene startGameScene = new StartGameScene(primaryStage, availableHeroes.get(3));
                            primaryStage.setScene(startGameScene.getScene());
                            primaryStage.setFullScreen(true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                });
            }
        });

        explorer3.setOnAction(actionEvent -> {
            if (root.getChildren().contains(championbox5)) {
                root.getChildren().removeAll(championbox5, gif5, lockinButton);
            } else {
                removeAll(root);
                root.add(championbox5, 4, 5);
                root.add(gif5, 7, 5);
                root.add(lockinButton, 12, 13);
                lockinButton.setOnAction(actionEvent1 -> {
                    attachAnimation(1, -2800, 1, backButton);
                    attachAnimation(12, 2800, 1, lockinButton);
                    attachAnimation(4, -2800, 1, fighter1);
                    attachAnimation(6, -2800, 1, fighter2);
                    attachAnimation(8, -2800, 1, explorer1);
                    attachAnimation(10, -2800, 1, explorer2);
                    attachAnimation(3, 2800, 1, explorer3);
                    attachAnimation(5, 2800, 1, medic1);
                    attachAnimation(7, 2800, 1, medic2);
                    TranslateTransition translateTransition = attachAnimation(9, 2800, 1, medic3);
                    translateTransition.setOnFinished(event -> Platform.runLater(() -> {
                        try {
                            StartGameScene startGameScene = new StartGameScene(primaryStage, availableHeroes.get(4));
                            primaryStage.setScene(startGameScene.getScene());
                            primaryStage.setFullScreen(true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                });
            }
        });

        medic1.setOnAction(actionEvent -> {
            if (root.getChildren().contains(championbox6)) {
                root.getChildren().removeAll(championbox6, gif6, lockinButton);
            } else {
                removeAll(root);
                root.add(championbox6, 4, 5);
                root.add(gif6, 7, 5);
                root.add(lockinButton, 12, 13);
                lockinButton.setOnAction(actionEvent1 -> {
                    attachAnimation(1, -2800, 1, backButton);
                    attachAnimation(12, 2800, 1, lockinButton);
                    attachAnimation(4, -2800, 1, fighter1);
                    attachAnimation(6, -2800, 1, fighter2);
                    attachAnimation(8, -2800, 1, explorer1);
                    attachAnimation(10, -2800, 1, explorer2);
                    attachAnimation(3, 2800, 1, explorer3);
                    attachAnimation(5, 2800, 1, medic1);
                    attachAnimation(7, 2800, 1, medic2);
                    TranslateTransition translateTransition = attachAnimation(9, 2800, 1, medic3);
                    translateTransition.setOnFinished(event -> Platform.runLater(() -> {
                        try {
                            StartGameScene startGameScene = new StartGameScene(primaryStage, availableHeroes.get(5));
                            primaryStage.setScene(startGameScene.getScene());
                            primaryStage.setFullScreen(true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                });
            }
        });

        medic2.setOnAction(actionEvent -> {
            if (root.getChildren().contains(championbox7)) {
                root.getChildren().removeAll(championbox7, gif7, lockinButton);
            } else {
                removeAll(root);
                root.add(championbox7, 4, 5);
                root.add(gif7, 7, 5);
                root.add(lockinButton, 12, 13);
                lockinButton.setOnAction(actionEvent1 -> {
                    attachAnimation(1, -2800, 1, backButton);
                    attachAnimation(12, 2800, 1, lockinButton);
                    attachAnimation(4, -2800, 1, fighter1);
                    attachAnimation(6, -2800, 1, fighter2);
                    attachAnimation(8, -2800, 1, explorer1);
                    attachAnimation(10, -2800, 1, explorer2);
                    attachAnimation(3, 2800, 1, explorer3);
                    attachAnimation(5, 2800, 1, medic1);
                    attachAnimation(7, 2800, 1, medic2);
                    TranslateTransition translateTransition = attachAnimation(9, 2800, 1, medic3);
                    translateTransition.setOnFinished(event -> Platform.runLater(() -> {
                        try {
                            StartGameScene startGameScene = new StartGameScene(primaryStage, availableHeroes.get(6));
                            primaryStage.setScene(startGameScene.getScene());
                            primaryStage.setFullScreen(true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                });
            }
        });

        medic3.setOnAction(actionEvent -> {
            if (root.getChildren().contains(championbox8)) {
                root.getChildren().removeAll(championbox8, gif8, lockinButton);
            } else {
                removeAll(root);
                root.add(championbox8, 4, 5);
                root.add(gif8, 7, 4);
                root.add(lockinButton, 12, 13);
                lockinButton.setOnAction(actionEvent1 -> {
                    attachAnimation(1, -2800, 1, backButton);
                    attachAnimation(12, 2800, 1, lockinButton);
                    attachAnimation(4, -2800, 1, fighter1);
                    attachAnimation(6, -2800, 1, fighter2);
                    attachAnimation(8, -2800, 1, explorer1);
                    attachAnimation(10, -2800, 1, explorer2);
                    attachAnimation(3, 2800, 1, explorer3);
                    attachAnimation(5, 2800, 1, medic1);
                    attachAnimation(7, 2800, 1, medic2);
                    TranslateTransition translateTransition = attachAnimation(9, 2800, 1, medic3);
                    translateTransition.setOnFinished(event -> Platform.runLater(() -> {
                        try {
                            StartGameScene startGameScene = new StartGameScene(primaryStage, availableHeroes.get(7));
                            primaryStage.setScene(startGameScene.getScene());
                            primaryStage.setFullScreen(true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
                });
            }
        });
    }

    private HBox createCenterHBox(Scene scene, String imagePath) {
        HBox centerHBox = new HBox();
        centerHBox.setAlignment(Pos.CENTER);

        // Load the background image
        Image backgroundImage = new Image(imagePath);
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Adjust the scale factor as needed
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty().multiply(0.47));
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty().multiply(0.4));

        centerHBox.getChildren().addAll(backgroundImageView);

        return centerHBox;
    }

    private ImageView addGif(String gifPath) {
        Image gifImage = new Image(gifPath);
        ImageView gifImageView = new ImageView(gifImage);
        gifImageView.setPreserveRatio(true);
        gifImageView.setFitWidth(700);
        gifImageView.setFitHeight(500);
        return gifImageView;
    }

    private void removeAll(GridPane root) {
        root.getChildren().removeAll(championbox1, championbox2, championbox3, championbox4,
                championbox5, championbox6, championbox7, championbox8, gif1, gif2, gif3, gif4, gif5, gif6, gif7, gif8, lockinButton);
    }

    public Scene getScene() {
        return scene;
    }
}
