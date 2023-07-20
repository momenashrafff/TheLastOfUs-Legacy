package views;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static TranslateTransition attachAnimation(int from, int to, double time, Button button) {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(time), button);
        tt.setFromX(from);
        tt.setToX(to);
        tt.play();
        return tt;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Button createButton(String normalImagePath, String hoverImagePath, double scaleX, double scaleY, GridPane gridPane) {
        Image normalImage = new Image(normalImagePath);
        ImageView buttonView = new ImageView(normalImage);
        Image hoverImage = new Image(hoverImagePath);

        Button button = new Button();
        button.setGraphic(buttonView);
        button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        button.setOnMouseEntered(mouseEvent -> buttonView.setImage(hoverImage));
        button.setOnMouseExited(mouseEvent -> buttonView.setImage(normalImage));

        buttonView.setPreserveRatio(false);
        buttonView.fitWidthProperty().bind(gridPane.widthProperty().divide(gridPane.getColumnCount()).multiply(scaleX));
        buttonView.fitHeightProperty().bind(gridPane.heightProperty().divide(gridPane.getRowCount()).multiply(scaleY));

        return button;
    }

    private VBox createCreditsBox(Scene scene) {
        VBox creditsBox = new VBox();
        creditsBox.setAlignment(Pos.CENTER);
        creditsBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.7), CornerRadii.EMPTY, Insets.EMPTY)));
        creditsBox.setPadding(new Insets(10));

        Text title = new Text("Game Credits");
        title.setFill(Color.PALEGOLDENROD);
        title.setStyle("-fx-font-weight: bold;");

        Text developedBy = new Text("Developed by Team63\nGerman International University\n");
        developedBy.setFill(Color.PALEGOLDENROD);
        developedBy.setTextAlignment(TextAlignment.CENTER);

        Text teamMembers = new Text("Team Members:\nMo'men Ashraf\nHussein Mansoor\nMostafa Abozahra\n\n");
        teamMembers.setFill(Color.PALEGOLDENROD);
        teamMembers.setTextAlignment(TextAlignment.CENTER);

        Text specialThanks = new Text("Special Thanks:\nProf.Dr. Slim Abdelnadder\n© 2023 German International University. All rights reserved.");
        specialThanks.setFill(Color.PALEGOLDENROD);
        specialThanks.setTextAlignment(TextAlignment.CENTER);

        creditsBox.getChildren().addAll(title, developedBy, teamMembers, specialThanks);

        return creditsBox;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("The Last of Us: Legacy");
        Image icon = new Image("views/resources/icon.png");
//        ImageView iconView = new ImageView(icon);
        primaryStage.getIcons().add(icon);

        Image backgroundImage = new Image("views/resources/Background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        //Load the gifImage
        Image gifImage = new Image("views/resources/flyingBirds.gif");
        ImageView gifImageView = new ImageView(gifImage);
        gifImageView.setPreserveRatio(true);
        gifImageView.setFitWidth(700);
        gifImageView.setFitHeight(500);
        gifImageView.setTranslateY(-200);

        GridPane root = new GridPane();
        root.getChildren().addAll(backgroundImageView, gifImageView);

        //adjust the size of the background
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / 15);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100.0 / 15);

        for (int i = 0; i < 15; i++) {
            root.getColumnConstraints().add(columnConstraints);
            root.getRowConstraints().add(rowConstraints);
        }

        GridPane.setConstraints(backgroundImageView, 0, 0, 15, 15);
        GridPane.setConstraints(gifImageView, 4, 1, 15, 15);

        Scene scene = new Scene(root, 1920, 1080, Color.BLACK);
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            backgroundImageView.setFitWidth((double) newValue);
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            backgroundImageView.setFitHeight((double) newValue);
        });

        // add buttons
        Button startButton = createButton("views/resources/startButton/startButtonNormal.png",
                "views/resources/startButton/startButtonSelected.png",
                4, 1.2, root);
        root.add(startButton, 1, 4);

        Button settingsButton = createButton("views/resources/settingsButton/settingsButtonNormal.png",
                "views/resources/settingsButton/settingsButtonSelected.png",
                4, 1.2, root);
        root.add(settingsButton, 1, 6);

        Button creditsButton = createButton("views/resources/creditsButton/creditsButtonNormal.png",
                "views/resources/creditsButton/creditsButtonSelected.png"
                , 4, 1.2, root);
        root.add(creditsButton, 1, 8);

        Button exitButton = createButton("views/resources/exitButton/exitButtonNormal.png",
                "views/resources/exitButton/exitButtonSelected.png",
                4, 1, root);
        root.add(exitButton, 1, 10);


        // creditsButton functionality
        VBox creditsBox = createCreditsBox(scene);
        root.add(creditsBox, 5, 5);
        creditsBox.setVisible(false);
        creditsButton.setOnAction(event -> {
            if (creditsBox.isVisible()) {
                FadeTransition ft = new FadeTransition(Duration.millis(250), creditsBox);
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.play();
                ft.setOnFinished(actionEvent -> creditsBox.setVisible(false));
            } else {
                FadeTransition ft = new FadeTransition(Duration.millis(250), creditsBox);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();
                creditsBox.setVisible(true);
            }
        });
        // startButton functionality
        startButton.setOnAction(event -> {
            try {
                ChampionSelect championSelectScene = new ChampionSelect(primaryStage);

                if (creditsBox.isVisible()) {
                    creditsButton.fire();
                }
                TranslateTransition translateTransition = attachAnimation(1, -1200, 1, startButton);
                attachAnimation(1, -1200, 0.90, settingsButton);
                attachAnimation(1, -1200, 0.80, creditsButton);
                attachAnimation(1, -1200, 0.70, exitButton);
                translateTransition.setOnFinished(actionEvent -> Platform.runLater(() -> {
                    primaryStage.setScene(championSelectScene.getScene());
                    primaryStage.setFullScreen(true);

                }));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        exitButton.setOnAction(event -> {
            if (creditsBox.isVisible())
                creditsButton.fire();
            TranslateTransition translateTransition = attachAnimation(1, -1200, 1, startButton);
            attachAnimation(1, -1200, 0.90, settingsButton);
            attachAnimation(1, -1200, 0.80, creditsButton);
            attachAnimation(1, -1200, 0.70, exitButton);

            translateTransition.setOnFinished(actionEvent -> {
                Platform.runLater(() -> {
                    primaryStage.close();
                    Platform.exit();
                });
            });
        });

        // Adjust the animation
        attachAnimation(-2000, 1, 1.15, startButton);
        attachAnimation(-2000, 1, 1.25, settingsButton);
        attachAnimation(-2000, 1, 1.35, creditsButton);
        attachAnimation(-2000, 1, 1.45, exitButton);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
