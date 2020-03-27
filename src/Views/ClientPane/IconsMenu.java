package Views.ClientPane;

import animatefx.animation.ZoomOut;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class IconsMenu {
    private static IconsMenu iconsMenu = null;
    private IconsMenu() {}
    public static IconsMenu getInstance(){
        if(iconsMenu==null)
            IconsMenu.iconsMenu = new IconsMenu();
        return IconsMenu.iconsMenu;
    }

    private Pane mainPane;
    public Pane getMainPane(){
        mainPane = new Pane();
        Label close = new Label();
        Label hide = new Label();
        close.setGraphic(new ImageView(new Image("Resources/icons/close.png")));
        hide.setGraphic(new ImageView(new Image("Resources/icons/hide.png")));
        close.relocate(970, 20);
        hide.relocate(940, 20);
        close.setPrefSize(17,17);
        hide.setPrefSize(17,17);
        close.setId("close");
        hide.setId("hide");

        close.setOnMouseClicked(e -> {
            Platform.exit();

        });

        hide.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                Stage stage = (Stage)mainPane.getScene().getWindow();
                stage.setIconified(true);
            }
        });

        mainPane.getChildren().addAll(close, hide);
        return mainPane;
    }
}
