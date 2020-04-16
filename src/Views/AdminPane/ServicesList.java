package Views.AdminPane;

import Constants.Const;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ServicesList {
    private static ServicesList servicesList = null;
    private ServicesList(){}
    public static ServicesList getInstance(){
        if(servicesList == null){ ServicesList.servicesList = new ServicesList();}
        return ServicesList.servicesList;
    }

    private Pane mainPane;
    private VBox mainBox, servicesListVBox;
    private ScrollPane servicesListSP;
    private HBox service;
    private Label name, workers;
    private ImageView edit, close, imv;
    private TextField nameSearch;
    public Pane mainPane(){
        mainPane = new Pane();
        mainPane.setLayoutY(80);

        Label name = new Label("All services");
        name.setId("title");
        name.setLayoutX(380);

        mainBox = new VBox();
        mainBox.setId("scrl");
        mainBox.setSpacing(20);
        mainBox.getChildren().addAll(servicesList());

        mainBox.setLayoutY(60);

        Button add_services = new Button("Add services");
        add_services.relocate((540 - 220) /2, 455);
        add_services.setPrefSize(220, 30);
        add_services.setId("delete");

        nameSearch = new TextField();
        nameSearch.setPrefSize(145, 24);
        nameSearch.setPromptText("Search");
        nameSearch.setId("search-w");
        nameSearch.setFocusTraversable(false);
        nameSearch.relocate(80, 75);

        mainPane.getChildren().addAll(name, mainBox, add_services, nameSearch);
        mainPane.setLayoutX(355);
        return mainPane;
    }

    private Node servicesList() {
        servicesListSP = new ScrollPane();
        servicesListSP.setPrefViewportHeight(280);
        servicesListSP.setPrefViewportWidth(485);
        servicesListSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        servicesListVBox = new VBox();
        servicesListVBox.setSpacing(10);
        title();

        for(int i=0; i<8; i++){
            service = new HBox();
            service.setAlignment(Pos.CENTER_LEFT);
            if(i%2 == 0){ service.setId("par1"); } else {service.setId("no-par1"); }
            imv = new ImageView(new Image("Resources/account/services_icon/1.png"));
            try{
                if(i%2 == 0){ service.setId("par1"); } else {
                    service.setId("no-par1");

                }
                imv.setStyle("-fx-effect: dropshadow( three-pass-box ," + Const.colors[i] + ", 20 , 0.0 , 0 , 0 );");
            }
            catch (IndexOutOfBoundsException e){
                imv.setStyle("-fx-effect: dropshadow( three-pass-box ," + Const.colors[i - ((i/5) * 5) - 1] + ", 20 , 0.0 , 0 , 0 );");
            }

            name = new Label("Misha Kliuvak");
            name.setId("name-table");
            workers = new Label("10");
            workers.setId("work-count");
            titleSett(name, workers);

            edit = new ImageView(new Image("Resources/icons/edit.png"));
            close = new ImageView(new Image("Resources/icons/exit.png"));
            edit.setId("icons"); close.setId("icons");

            service.setSpacing(10);
            service.getChildren().addAll(imv, name, workers, edit, close);
            servicesListVBox.getChildren().add(service);
        }
        servicesListSP.setContent(servicesListVBox);
        return servicesListSP;
    }

    private void title(){
        service = new HBox();
        service.setId("title-top");

        name = new Label("Name:");
        workers = new Label("Total workers:");
        name.setId("titles"); workers.setId("titles");
        titleSett(name, workers);

        service.setAlignment(Pos.CENTER_LEFT);
        service.getChildren().addAll(name, workers);
        mainBox.getChildren().add(service);

    }

    private void titleSett(Label ... lbl){
        for (Label l: lbl) {
            if (l.getText().equals("Name:")) {
                l.setMaxWidth(250);
                l.setPrefWidth(250);
                continue;
            }
            l.setMaxWidth(150);
            l.setPrefWidth(150);
        }
    }
}
