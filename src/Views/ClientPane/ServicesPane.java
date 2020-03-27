package Views.ClientPane;

import Constants.Const;
import animatefx.animation.ZoomIn;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicesPane {
    private static ServicesPane servicesPane = null;
    private ServicesPane() {}
    public static ServicesPane getInstance(){
        if(servicesPane==null)
            ServicesPane.servicesPane = new ServicesPane();
        return ServicesPane.servicesPane;
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


        mainPane = new ScrollPane();
        mainPane.setPrefViewportWidth(714);
        mainPane.setPrefViewportHeight(358);
        mainPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        loagBgs();

        Label name = new Label("All our services");
        name.setId("title");

        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(20, 20, 20,35));
        flowPane.setVgap(38);
        flowPane.setHgap(25);
        flowPane.setPrefWrapLength(664);

        Pane item = new Pane();
        ImageView imv;
        Label lbl1, lbl2;
        Button next;
        ImageView imv2;
        for (int i = 0; i < 6; i++) {
            item = new Pane();
            item.setPrefSize(198, 140);

            try {
                imv = new ImageView(bgs.get(i));
                imv.setId("item-service");
                ImageView icon = new ImageView(new Image("Resources/account/services_icon/" + (i+1) + ".png"));
                icon.relocate(120, 10);

                lbl1 = new Label("Total workers: 35");
                lbl1.relocate(20, 80);
                lbl1.setId("stat-descr");
                lbl2 = new Label(names.get(i));
                lbl2.relocate(20, 95);
                lbl2.setId("stat-num");

                next = new Button(">");
                next.relocate(10, 10);
                next.setPrefSize(20, 20);
                next.setId("next");

                item.getChildren().addAll(imv, icon, lbl2, lbl1, next);
            }
            catch (IndexOutOfBoundsException e){
                imv2 = new ImageView(bgs.get(getItemNum(i)));
                imv2.setId("item-service");
                item.getChildren().addAll(imv2);
            }

            flowPane.getChildren().add(item);


        }

        name.layout();
        Platform.runLater(()->{
            System.out.println();
            mainPane.relocate((Const.SCREEN_WIDTH - 714) / 2 + 120, 164);
            name.relocate(Const.SCREEN_WIDTH - name.getWidth() - 70, mainPane.getLayoutY() - 50);
        });
        //contentPane.getChildren().add(flowPane);
        mainPane.setContent(flowPane);
        contentPane.getChildren().addAll(name, mainPane);
        return contentPane;
    }
    private void loagBgs(){
        File path = new File(getClass().getClassLoader().getResource("Resources/account/services").getFile());
        File[] files = path.listFiles();
        for(File img:files){
            bgs.add(new Image(img.toURI().toString()));
        }
    }

    private int getItemNum(int i){
        i = i - ((i/5) * 5) - 1;
        return i;
    }
}
