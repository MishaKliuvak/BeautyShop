package Views.AdminPane;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;



public class Workers {
    private static Workers workers = null;
    private Workers(){}
    public static Workers getInstance(){
        if(workers == null){ Workers.workers = new Workers();}
        return Workers.workers;
    }

    private VBox workersListVBox, mainBox;
    private HBox worker;
    private Pane mainPane;
    private TextField nameSearch;
    private ScrollPane workersListSP;
    private ImageView imv, add, edit, close;
    private Label name, email, services;

    public Pane mainPane(){
        mainPane = new Pane();
        mainPane.setLayoutY(70);

        Label name = new Label("All workers");
        name.setId("title");
        name.setLayoutX(480);

        mainBox = new VBox();
        mainBox.setId("scrl");
        mainBox.setSpacing(20);
        mainBox.getChildren().addAll(workersList());

        mainBox.setLayoutY(60);

        Button add_worker = new Button("Add worker");
        add_worker.relocate((690 - 220) /2, 465);
        add_worker.setPrefSize(220, 30);
        add_worker.setId("delete");

        nameSearch = new TextField();
        nameSearch.setPrefSize(145, 24);
        nameSearch.setPromptText("Search");
        nameSearch.setId("search-w");
        nameSearch.setFocusTraversable(false);
        nameSearch.relocate(80, 75);

        String week_days[] =
                { "Monday", "Tuesday", "Wednesday",
                        "Thrusday", "Friday" };

        // Create a combo box
        ComboBox combo_box =
                new ComboBox(FXCollections
                        .observableArrayList(week_days));
        combo_box.relocate(470, 75);
        combo_box.setPrefSize(145, 24);

        mainPane.getChildren().addAll(name, mainBox, add_worker, nameSearch, combo_box);
        mainPane.setLayoutX(273);
        return mainPane;
    }

    private ScrollPane workersList(){
        workersListSP = new ScrollPane();
        workersListSP.setPrefViewportHeight(300);
        workersListSP.setPrefViewportWidth(650);
        workersListSP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        workersListVBox = new VBox();
        workersListVBox.setSpacing(10);
        title();
        for(int i=0; i<8; i++){
            worker = new HBox();
            worker.setAlignment(Pos.CENTER_LEFT);
            if(i%2 == 0){ worker.setId("par"); } else {worker.setId("no-par"); }

            imv = new ImageView(new Image("Resources/account/acc0.png"));

            name = new Label("Misha Kliuvak");
            email = new Label("mishakl10@gmail.com");
            services = new Label("- Hair dyeing\n- Manicure");
            name.setId("name-table"); email.setId("em-table"); services.setId("ser-table");
            titleSett(name, email, services);

            add = new ImageView(new Image("Resources/icons/add.png"));
            edit = new ImageView(new Image("Resources/icons/edit.png"));
            close = new ImageView(new Image("Resources/icons/exit.png"));
            add.setId("icons"); edit.setId("icons"); close.setId("icons");

            worker.setSpacing(10);
            worker.getChildren().addAll(imv, name, email, services, add, edit, close);
            workersListVBox.getChildren().add(worker);
        }
        workersListSP.setContent(workersListVBox);
        return workersListSP;
    }

    private void title(){
        worker = new HBox();
        worker.setSpacing(10);
        worker.setId("title-top");

        name = new Label("Name:");
        email = new Label("E-mail:");
        services = new Label("Services:");
        name.setId("titles"); email.setId("titles"); services.setId("titles");
        titleSett(name, email, services);

        worker.setAlignment(Pos.CENTER_LEFT);
        worker.getChildren().addAll(name,email,services);
        mainBox.getChildren().add(worker);

    }

    private void titleSett(Label ... lbl){
        for (Label l: lbl) {
            if (l.getText().equals("Name:")) {
                l.setMaxWidth(205);
                l.setPrefWidth(205);
                continue;
            }
            l.setMaxWidth(150);
            l.setPrefWidth(150);
        }
    }
}
