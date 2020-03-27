package Views;

import Constants.Const;
import Views.ClientPane.IconsMenu;
import animatefx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.jws.soap.SOAPBinding;


public class Authorization {
    private static Authorization authorization = null;
    public Scene scene;
    private Label greeting, description, forgot;
    private Button signBtn, signInBtn;
    private Pane mainPane, authorizationPane, registrationPane;
    private VBox infoBox;
    private TextField emailTF;
    private PasswordField passwordTF, retunrPasswordTF;
    private Image bgImage, emailIm, changeEmailIm, passwordIm, changePasswordIm, returnIm, changeReturnIm;
    private ImageView bgIv, emailIv, passwordIv, returnIv, nEmailIv, nPasswordIv;

    private boolean auth = true;
    private String text = "SIGN UP";

    private Authorization() {}

    public static Authorization getInstance() {
        if (authorization == null) {
            Authorization.authorization = new Authorization();
        }
        return Authorization.authorization;
    }

    public Scene setAuthorizationPane(){
        scene = new Scene(mainPane(), 500, 500);
        scene.setFill(Color.BLACK);
        return scene;
    }

    public Pane mainPane(){
        mainPane = new Pane();
        bgImage = new Image("Resources/authorization/bg.jpg");
        bgIv = new ImageView(bgImage);
        mainPane.getChildren().addAll(bgIv, registrationPane(), authorizationPane(), infoBox(), IconsMenu.getInstance().getMainPane());
        authorizationPane.setVisible(true);
        registrationPane.setVisible(false);
        return mainPane;
    }

    public VBox infoBox(){
        infoBox = new VBox();
        infoBox.setPrefSize(381, 635);
        infoBox.setLayoutY((mainPane.getHeight() - infoBox.getHeight()) / 2);
        infoBox.setSpacing(20);
        infoBox.setAlignment(Pos.CENTER);

        greeting = new Label("Hello, Friend");
        greeting.setId("greeting");
        description = new Label("To keep connected with us please\n" + "login with your personal info");
        description.setId("description");

        signBtn = new Button();
        signBtn.setPrefSize(172,40);
        signBtn.setId("signBtn");
        Label txt = new Label("SIGN UP");
        txt.setId("txt");
        signBtn.setGraphic(txt);
        signBtn.setOnMouseClicked(event -> {
            if (auth) change(authorizationPane, registrationPane); else change(registrationPane, authorizationPane);
            auth = !auth;
            text =  (auth) ? "SIGN UP" : "SIGN IN";
            txt.setText(text);
        });

        infoBox.getChildren().addAll(greeting, description, signBtn);
        new FadeIn(infoBox).play();
        return infoBox;
    }

    public Pane registrationPane(){
        registrationPane = new Pane();
        registrationPane.setLayoutX(382);
        registrationPane.setPrefWidth(629);

        HBox name = HybridLabel.custom("Sign up", "to", "Moonlight");
        name.setPrefWidth(629);

        emailTF = new TextField();
        emailTF.setPrefSize(336, 47);
        emailTF.setPromptText("E-mail");
        emailTF.setFocusTraversable(false);
        emailTF.relocate((629 - 336) / 2, name.getLayoutY() + name.getHeight() + 70);
        emailTF.setOnMouseClicked(e->{
            selectTF(emailIm, changePasswordIm, changeReturnIm,
                     "-fx-effect: dropshadow( three-pass-box , #c8a8ff, 10 , 0.0 , 0 , 0 );", "", "");

        });


        emailIm = new Image("Resources/icons/email.png");
        changeEmailIm = changeImageColor(emailIm, Color.web("#4c5c9c"));
        nEmailIv = new ImageView(changeEmailIm);
        nEmailIv.relocate(emailTF.getLayoutX() + 8, emailTF.getLayoutY() + 6);
        nEmailIv.setId("email-image");

        passwordTF = new PasswordField();
        passwordTF.setPrefSize(336, 47);
        passwordTF.setFocusTraversable(false);
        passwordTF.setPromptText("Password");
        passwordTF.relocate(emailTF.getLayoutX(), emailTF.getLayoutY() + emailTF.getWidth() + 70);
        passwordTF.setOnMouseClicked(e->{
            selectTF(changeEmailIm, passwordIm, changeReturnIm,
                    "", "-fx-effect: dropshadow( three-pass-box , #c8a8ff, 10 , 0.0 , 0 , 0 );", "");
        });



        passwordIm = new Image("Resources/icons/password.png");
        changePasswordIm = new Image("Resources/icons/password2.png");
        nPasswordIv = new ImageView(changePasswordIm);
        nPasswordIv.relocate(passwordTF.getLayoutX() + 8, passwordTF.getLayoutY() + 6);

        retunrPasswordTF = new PasswordField();
        retunrPasswordTF.setPrefSize(336, 47);
        retunrPasswordTF.setFocusTraversable(false);
        retunrPasswordTF.setPromptText("Return password");
        retunrPasswordTF.relocate(passwordTF.getLayoutX(), passwordTF.getLayoutY() + passwordTF.getWidth() + 70);
        retunrPasswordTF.setOnMouseClicked(e->{
            selectTF(changeEmailIm, changePasswordIm, returnIm,
                    "", "", "-fx-effect: dropshadow( three-pass-box , #c8a8ff, 10 , 0.0 , 0 , 0 );");
        });

        returnIm = new Image("Resources/icons/return2.png");
        changeReturnIm = new Image("Resources/icons/return.png");
        returnIv = new ImageView(changeReturnIm);
        returnIv.relocate(retunrPasswordTF.getLayoutX() + 8, retunrPasswordTF.getLayoutY() + 6);

        signInBtn = new Button("SIGN UP");
        signInBtn.setPrefSize(152,35);
        signInBtn.relocate((629 - 152) / 2, retunrPasswordTF.getLayoutY() + retunrPasswordTF.getHeight() + 70);
        signInBtn.setId("signInBtn");
        signInBtn.setOnMouseClicked(event -> {
            new ZoomOut(authorizationPane).play();
        });


        registrationPane.layout();
        Platform.runLater(()->{
            registrationPane.setLayoutY((Const.SCREEN_HEIGHT - registrationPane.getHeight()) / 2);
        });


        registrationPane.getChildren().addAll(name, emailTF, nEmailIv, passwordTF, nPasswordIv, signInBtn, retunrPasswordTF, returnIv);
        new ZoomIn(registrationPane).play();
        return registrationPane;
    }

    public Pane authorizationPane(){
        authorizationPane = new Pane();
        authorizationPane.setLayoutX(382);
        authorizationPane.setPrefWidth(629);

        HBox name = HybridLabel.custom("Sign in", "to", "Moonlight");
        name.setPrefWidth(629);

        emailTF = new TextField();
        emailTF.setPrefSize(336, 47);
        emailTF.setPromptText("E-mail");
        emailTF.setFocusTraversable(false);
        emailTF.relocate((629 - 336) / 2, name.getLayoutY() + name.getHeight() + 70);
        emailTF.setOnMouseClicked(e->{
            passwordIv.setImage(changePasswordIm);
            emailIv.setImage(emailIm);
            emailIv.setStyle("-fx-effect: dropshadow( three-pass-box , #c8a8ff, 10 , 0.0 , 0 , 0 );");
            passwordIv.setStyle("");
        });

        emailIm = new Image("Resources/icons/email.png");
        changeEmailIm = changeImageColor(emailIm, Color.web("#4c5c9c"));
        emailIv = new ImageView(changeEmailIm);
        emailIv.relocate(emailTF.getLayoutX() + 8, emailTF.getLayoutY() + 6);
        emailIv.setId("email-image");

        passwordTF = new PasswordField();
        passwordTF.setPrefSize(336, 47);
        passwordTF.setFocusTraversable(false);
        passwordTF.setPromptText("Password");
        passwordTF.relocate(emailTF.getLayoutX(), emailTF.getLayoutY() + emailTF.getWidth() + 70);
        passwordTF.setOnMouseClicked(e->{
            passwordIv.setImage(passwordIm);
            emailIv.setImage(changeEmailIm);
            passwordIv.setStyle("-fx-effect: dropshadow( three-pass-box , #c8a8ff, 10 , 0.0 , 0 , 0 );");
            emailIv.setStyle("");
        });

        passwordIm = new Image("Resources/icons/password.png");
        changePasswordIm = new Image("Resources/icons/password2.png");
        passwordIv = new ImageView(changePasswordIm);
        passwordIv.relocate(passwordTF.getLayoutX() + 8, passwordTF.getLayoutY() + 6);

        signInBtn = new Button("SIGN IN");
        signInBtn.setPrefSize(152,35);
        signInBtn.relocate((629 - 152) / 2, passwordTF.getLayoutY() + passwordTF.getHeight() + 70);
        signInBtn.setId("signInBtn");
        signInBtn.setOnMouseClicked(event -> {
            scene.setRoot(UserMenu.getInstance().mainUserPane("Admin"));
        });


        forgot = new Label("Forgot password");
        forgot.setId("forgot");

        authorizationPane.layout();
        Platform.runLater(()->{
            System.out.println(authorizationPane.getHeight());
            authorizationPane.setLayoutY((Const.SCREEN_HEIGHT - authorizationPane.getHeight()) / 2);
            forgot.setLayoutX((629 - forgot.getWidth()) / 2);
            forgot.setLayoutY(signInBtn.getLayoutY() + signInBtn.getHeight() + 10);
        });


        authorizationPane.getChildren().addAll(name, emailTF, emailIv, passwordTF, passwordIv, signInBtn, forgot);
        new ZoomIn(authorizationPane).play();
        return authorizationPane;
    }


    public static final WritableImage changeImageColor(Image image, Color newColor)
    {
        if (image == null)
            throw new IllegalArgumentException("Cannot change the color of a null image.");

        WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter writer = newImage.getPixelWriter();
        PixelReader reader = image.getPixelReader();

        for (int readY = 0; readY < image.getHeight(); readY++)
        {
            for (int readX = 0; readX < image.getWidth(); readX++)
            {
                Color color = reader.getColor(readX, readY);
                if (color.getOpacity() > 0)
                {
                    writer.setColor(readX, readY, newColor);
                }
            }
        }

        return newImage;
    }

    private void change(Pane paneHide, Pane paneShow){
        new ZoomOut(paneHide).play();
        paneHide.setVisible(false);
        paneShow.setVisible(true);
        new ZoomIn(paneShow).play();
    }

    private void selectTF(Image eIm, Image pIm, Image rIm, String eS, String pS, String rS){
        nEmailIv.setImage(eIm);
        nPasswordIv.setImage(pIm);
        returnIv.setImage(rIm);

        returnIv.setStyle(rS);
        nEmailIv.setStyle(eS);
        nPasswordIv.setStyle(pS);
    }
}

