package Views.ClientPane;

import Constants.Const;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BonusesPane {
    private static BonusesPane bonusesPane = null;
    private BonusesPane() {}
    public static BonusesPane getInstance(){
        if(bonusesPane==null)
            BonusesPane.bonusesPane = new BonusesPane();
        return BonusesPane.bonusesPane;
    }

    private ScrollPane mainPane;
    private Pane contentPane;
    private static List<Image> bgs = new ArrayList<Image>();
    private static List<String> names = Arrays.asList("Hair dyeing", "Eyebrow tinting", "Manicure", "Pedicure", "Haircut", "Depilation");

    public Pane mainServicesPane(){
        if(contentPane != null){
            return contentPane;
        }

        contentPane = new Pane();
        contentPane.relocate(240, 0);


        mainPane = new ScrollPane();
        mainPane.setPrefViewportWidth(675);
        mainPane.setPrefViewportHeight(280);
        mainPane.relocate(48, 203);
        mainPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        Label name = new Label("Your coupons");
        name.setId("title");
        name.relocate(490, 133);

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(25);
        flowPane.setHgap(25);
        flowPane.setPrefWrapLength(675);

        Pane item;
        Region rectangle;
        Label callname, date;
        ImageView imv, imv2;
        for (int i = 0; i < 5; i++) {
            item = new Pane();

            rectangle = new Region();
            rectangle.setPrefSize(320, 70);
            rectangle.setStyle("" +
                    "-fx-background-color: rgba(35,51,101,0.4);" +
                    "   -fx-border-width: 0 0 3 0;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-radius: 10;" +
                    "    -fx-border-color: " + Const.colors[i] + ";");

            imv = new ImageView(new Image("Resources/account/coupons/" + (i+1) + ".png"));
            imv.relocate(22,12);
            imv.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(200,168,255,0.56), 20 , 0.0 , 0 , 0 );");

            imv2 = new ImageView(new Image("Resources/account/services_icon/1.png"));
            imv2.setScaleX(0.7);
            imv2.setScaleY(0.7);
            imv2.relocate(17,4);

            callname = new Label("Hair dyeling");
            callname.relocate(88, 15);
            callname.setId("name-review");

            date = new Label("Aug, 11");
            date.relocate(88, 37);
            date.setId("date-review");

            Label bonus = new Label("-20%");
            bonus.relocate(200,10);
            bonus.setId("bonus");
            bonus.setStyle( "-fx-border-color: " + Const.colors[i] + ";");

            Platform.runLater(()->{
                bonus.relocate(298 - bonus.getWidth(), (70 - bonus.getHeight())/2);
            });

            item.getChildren().addAll(rectangle, imv, imv2, callname, date, bonus);
            flowPane.getChildren().add(item);

        }


        mainPane.setContent(flowPane);
        contentPane.getChildren().addAll(name, mainPane);
        return contentPane;
    }
}
