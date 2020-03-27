package Views;

import Constants.Const;
import Views.ClientPane.*;
import animatefx.animation.ZoomIn;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserMenu {
    private static UserMenu userMenu = null;
    private ImageView bg;
    private VBox menuButtons, menuIcons;
    private Pane mainPane, leftMenu, contentPane;
    private static HashMap<String, Button> menuItemsBtn = new HashMap<String, Button>();
    private static List<Image> unsImg = new ArrayList<Image>();
    private static List<Image> selectImg = new ArrayList<Image>();
    private static List<ImageView> icons = new ArrayList<ImageView>();
    private static List<Node> content = new ArrayList<Node>();
    private int select = 0;
    private Label ico, name, place;

    private UserMenu() {}

    public static UserMenu getInstance(){
        if(userMenu == null){
            UserMenu.userMenu = new UserMenu();
        }
        return UserMenu.userMenu;
    }

    protected Pane mainUserPane(String type){
        if(mainPane != null){
            return mainPane;
        }
        mainPane = new Pane();
        bg = new ImageView(new Image("Resources/account/bg.jpg"));

        mainPane.getChildren().addAll(bg, contentPane(type), leftMenu(type), IconsMenu.getInstance().getMainPane());
        return mainPane;
    }

    private Pane leftMenu(String type){
        leftMenu = new Pane();

        menuButtons = new VBox();
        menuButtons.setPrefSize(240, 635);
        menuButtons.setLayoutY(140);

        menuIcons = new VBox();
        menuIcons.relocate(34, 146);
        menuIcons.setSpacing(12);
        initializationLeftMenu(type);

        name = new Label("Olga Vilnus");
        name.setId("acc-name");
        name.relocate(90, 45);

        place = new Label(type);
        place.setId("acc-place");
        place.relocate(90, 65);

        ico = new Label("OV");
        ico.setId("ico");
        ico.relocate(30,40);
        ico.setPrefSize(45,45);

        leftMenu.getChildren().addAll(menuButtons, menuIcons, ico, name, place);
        return leftMenu;
    }

    private void initializationLeftMenu(String typeOfUser){
        for (int i=0; i<4; i++){
            Button button = new Button(generateLeftMenuItems(typeOfUser, i));
            button.setPrefSize(240,39);
            button.setId("leftMenuItem");
            menuItemsBtn.put(String.valueOf(i), button);
        }

        for(int i=0; i<menuItemsBtn.size(); i++){
            int finalI = i;
            menuItemsBtn.get(String.valueOf(i)).setOnMouseClicked(e->{
                for(int k = 0; k<icons.size(); k++){
                    if(k == finalI){
                        icons.get(k).setImage(selectImg.get(k));
                        icons.get(k).setStyle(Const.selectIconStyle);
                        menuItemsBtn.get(String.valueOf(k)).setStyle(Const.selectButtonStyle);
                        new ZoomIn(icons.get(k)).play();
                        content.get(k).setVisible(true);
                        new ZoomIn(content.get(k)).play();
                        continue;
                    }
                    menuItemsBtn.get(String.valueOf(k)).setStyle(Const.unselectButton);
                    icons.get(k).setImage(unsImg.get(k));
                    icons.get(k).setStyle("");
                    content.get(k).setVisible(false);
                    continue;
                }
            });
        }

        for(Map.Entry<String, Button> entry: menuItemsBtn.entrySet()){
            menuButtons.getChildren().add(entry.getValue());
        }

        loadIcons();
        startSettings();
    }

    private Pane contentPane(String type){
        contentPane = new Pane();
        switch (type){
            case "Client":
                content.add(PersonalAccountPane.getInstance().mainPane());
                content.add(ServicesPane.getInstance().mainServicesPane());
                content.add(HistoryPane.getInstance().mainPane());
                content.add(BonusesPane.getInstance().mainServicesPane());
                break;
            case "worker":
                break;
            case "Admin":
                content.add(PersonalAccountPane.getInstance().mainPane());
                content.add(ServicesPane.getInstance().mainServicesPane());
                content.add(HistoryPane.getInstance().mainPane());
                content.add(BonusesPane.getInstance().mainServicesPane());
                break;
            default:
                break;
        }

        content.forEach(pane -> {
            contentPane.getChildren().add(pane);
            pane.setVisible(false);
        });

        return contentPane;
    }

    private void startSettings() {
        icons.get(0).setImage(selectImg.get(0));
        icons.get(0).setStyle("-fx-effect: dropshadow( three-pass-box , #bb9def, 15 , 0.0 , 0 , 0 );");
        menuItemsBtn.get("0").setStyle("-fx-background-color: #28386f;\n" +
                "    -fx-text-fill: #c3a6e8;\n" +
                "    -fx-border-width: 0 0 0 4;\n" +
                "    -fx-border-color: #c3a6e8;");
        content.get(0).setVisible(true);
        new ZoomIn(content.get(0)).play();
    }

    private void loadIcons() {
        File path = new File(getClass().getClassLoader().getResource("Resources/account/client/unselect").getFile());
        File[] files = path.listFiles();
        for(File img:files){
            unsImg.add(new Image(img.toURI().toString()));
        }
        for(int i=0; i<10; i++){
            System.out.println((i>3 && i<7)? "A" : ((i>7 && i<9) ? "B" :"C"));
        }

        path = new File(getClass().getClassLoader().getResource("Resources/account/client/select").getFile());
        files = path.listFiles();
        for(File img:files){
            selectImg.add(new Image(img.toURI().toString()));
        }

        unsImg.forEach(img -> {
            icons.add(new ImageView(img));
        });

        icons.forEach(imageView -> {
            menuIcons.getChildren().add(imageView);
        });
    }

    private String generateLeftMenuItems(String typeOfUser, int i) {
        typeOfUser = (typeOfUser.equals("Client")) ? Const.clientMenu[i] : ((typeOfUser.equals("Admin")) ? Const.adminMenu[i] : Const.workersMenu[i]);
        return typeOfUser;
    }
}
