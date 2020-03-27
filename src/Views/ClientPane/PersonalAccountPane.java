package Views.ClientPane;

import animatefx.animation.ZoomIn;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonalAccountPane {
    private Pane mainPane, statisticPane, item, items;
    private ScrollPane remindersPane, reviewsPane;
    private HBox changerBox;

    private Label moneyLbl, moneyNum, falleLbl, falleNum, presentLbl, presentNum, date,title,description, nameLbl, dateLbl, ratingLbl, reviewLbl;
    private ImageView moneyIv, presentIv, faleIv, moneyIco, presentIco, faleIco, imv, edit, photo, bgStars, frontStars;
    private ObservableList<PieChart.Data> pieChartData;
    private ToggleButton remindersBtn, statisticBtn, reviewsBtn;
    private ToggleGroup group;
    private Button delete;
    private VBox reminders, reviews;
    private PieChart chart;
    private Rectangle circle;
    private Region rectangle;

    private String[] dates = {"March, 29", "April, 12", "April, 15"};
    private String[] names = {"Manicure", "Hair dyeing", "Pedicure"};
    private String[] text = {"We remind you that you have a scheduled session \nwith specialist Lena Olvus",
    "We remind you that you have a scheduled session \nwith specialist Igor Vasko", "We remind you that you have a scheduled session \nwith specialist Masha Klak"};
    private String[] days = {"Sunday", "Sunday", "Wednesday"};
    private String[] times = {"10:00", "12:00", "11:30"};

    private String[] reviewNames = {"Ivan Franko", "Lesya Ukrayinka", "Taras Shevchenko"};
    private double[] marks = {7.1, 9, 9.5};
    private String[] revs = {"Трохи запізнилася, в цілому все пройшло досить непогано, ввічлива клієнтка, є \nпро що поговорити",
            "Регулярно відвідує сенси, не відволікається на телефонні розмови, дотримується \nрекомендацій",
            "Дотримується усіх рекомендацій по догляду за волоссям, не відволікає майстра \nпід час роботи"};

    private static PersonalAccountPane personalAccountPane = null;
    private PersonalAccountPane(){}
    public static PersonalAccountPane getInstance(){
        if(personalAccountPane == null){ PersonalAccountPane.personalAccountPane = new PersonalAccountPane(); }
        return PersonalAccountPane.personalAccountPane;
    }

    public Pane mainPane(){
        if(mainPane != null){
            return mainPane;
        }
        mainPane = new Pane();
        mainPane.setPrefSize(770, 440);
        mainPane.relocate(240, 58);
        mainPane.setId("acc-pane");

        changerBox = new HBox();
        changerBox.setPrefWidth(770);
        changerBox.setAlignment(Pos.BASELINE_CENTER);
        changerBox.setSpacing(20);

        group = new ToggleGroup();
        remindersBtn = new ToggleButton("Reminders");
        remindersBtn.setToggleGroup(group);
        remindersBtn.setSelected(true);
        remindersBtn.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
                remindersPane.setVisible(true);
                new ZoomIn(remindersPane).play();
            }
            else {
                remindersPane.setVisible(false);
            }

        });
        reviewsBtn = new ToggleButton("Reviews");
        reviewsBtn.setToggleGroup(group);
        reviewsBtn.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
                reviewsPane.setVisible(true);
                new ZoomIn(reviewsPane).play();
            }
            else {
                reviewsPane.setVisible(false);
            }

        });

        statisticBtn = new ToggleButton("Statistics");
        statisticBtn.setToggleGroup(group);
        statisticBtn.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
                statisticPane.setVisible(true);
                new ZoomIn(statisticPane).play();
            }
            else {
                statisticPane.setVisible(false);
            }

        });

        remindersBtn.setId("pers-acc");
        reviewsBtn.setId("pers-acc");
        statisticBtn.setId("pers-acc");

        changerBox.getChildren().addAll(remindersBtn, statisticBtn, reviewsBtn);
        mainPane.getChildren().addAll(remindersPane(), staticsPane(), reviewsPane(), changerBox);
        return mainPane;
    }

    private ScrollPane remindersPane(){
        remindersPane = new ScrollPane();
        remindersPane.setPrefViewportWidth(650);
        remindersPane.setPrefViewportHeight(410);
        remindersPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        remindersPane.relocate((770 - 650)/2, statisticBtn.getLayoutY() + statisticBtn.getHeight() + 80);

        reminders = new VBox();
        reminders.setPrefWidth(770);
        reminders.setSpacing(20);



        for (int i = 0; i < 3; i++) {
            item = new Pane();

            date = new Label(dates[i]);
            date.relocate(35, -5);
            date.setId("rem-date");
            Label day;
            Label time;
            day = new Label(days[i]);
            day.setId("day");

            time = new Label(times[i]);
            time.setId("time");

            title = new Label(names[i]);
            title.setId("title-acc");

            description = new Label(text[i]);
            description.setId("des-acc");

            title.relocate(190, 50);
            description.relocate(190, 90);

            day.layout();
            Platform.runLater(()->{
                day.relocate((136 - day.getWidth())/2 + 25, 95);
                time.relocate((136 - time.getWidth())/2 + 25, 105);
            });

            delete = new Button("Delete");
            delete.relocate(510, 45);
            delete.setPrefSize(90, 20);
            delete.setId("delete");


            imv = new ImageView(new Image("Resources/account/client/reminders.png"));
            edit = new ImageView(new Image("Resources/account/client/edit.png"));
            edit.relocate(580, 0);
            edit.setId("edit");
            item.getChildren().addAll(imv, date, day, time, delete, title, description, edit);

            reminders.getChildren().addAll(item);
        }


      //  reminders.getChildren().addAll(btn);
        remindersPane.setContent(reminders);
        return remindersPane;
    }

    private Pane staticsPane(){
        statisticPane = new Pane();


        presentIv = new ImageView(new Image("Resources/account/services/1.png"));
        presentIv.relocate(490,70);
        presentIv.setId("bg-st");
        presentIco = new ImageView(new Image("Resources/account/client/present.png"));
        presentIco.relocate(590,78);
        presentLbl = new Label("Total: 15");
        presentLbl.relocate(510, 150);
        presentLbl.setId("stat-descr");
        presentNum = new Label("Sessions attended");
        presentNum.relocate(510, 165);
        presentNum.setId("stat-num");

        faleIv = new ImageView(new Image("Resources/account/services/2.png"));
        faleIv.relocate(490,220);
        faleIv.setId("bg-st");
        faleIco = new ImageView(new Image("Resources/account/client/fale1.png"));
        faleIco.relocate(590, 228);
        falleLbl = new Label("Total: 5");
        falleLbl.relocate(510, 300);
        falleLbl.setId("stat-descr");
        falleNum = new Label("Sessions missed");
        falleNum.relocate(510, 315);
        falleNum.setId("stat-num");

        moneyIv = new ImageView(new Image("Resources/account/services/5.png"));
        moneyIv.relocate(490, 370);
        moneyIv.setId("bg-st");
        moneyIco = new ImageView(new Image("Resources/account/client/money.png"));
        moneyIco.relocate(590,380);
        moneyLbl = new Label("Total: 3250 ₴");
        moneyLbl.relocate(510, 450);
        moneyLbl.setId("stat-descr");
        moneyNum = new Label("Spent money");
        moneyNum.relocate(510, 465);
        moneyNum.setId("stat-num");

        pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Manicure", 8),
                        new PieChart.Data("Pedicure", 2),
                        new PieChart.Data("Hair dyeing", 4),
                        new PieChart.Data("Haircut", 1));
        chart = new PieChart(pieChartData);
        chart.setTitle("Services");
        chart.setPrefSize(450,400);
        chart.relocate(20, 105);
        chart.setId("chart");
        chart.setLegendVisible(false);
        statisticPane.getChildren().addAll(chart, moneyIv, faleIv, presentIv,  moneyIco, faleIco, presentIco,
                presentNum, falleNum, moneyNum, moneyLbl,  falleLbl, presentLbl);
        statisticPane.setVisible(false);
        return statisticPane;
    }
    Label lbl;
    private ScrollPane reviewsPane(){
        reviewsPane = new ScrollPane();
        reviewsPane.setPrefViewportWidth(650);
        reviewsPane.setPrefViewportHeight(410);
        reviewsPane.setPadding(new Insets(0, 0, 10,15));
        reviewsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        reviewsPane.relocate((770 - 650)/2, statisticBtn.getLayoutY() + statisticBtn.getHeight() + 80);

        reviews = new VBox();
        reviews.setPrefWidth(650);
        reviews.setSpacing(20);



        for (int i = 0; i < 3; i++) {
            items = new Pane();

            reviewLbl = new Label(revs[i]);
            reviewLbl.relocate(35, 80);
            reviewLbl.setMaxWidth(555);
            reviewLbl.getLabelFor();
            reviewLbl.setWrapText(true);
            reviewLbl.setId("review-lbl");

            rectangle = new Region();
            rectangle.setPrefWidth(610);
            rectangle.setStyle("" +
                    "-fx-background-color: rgba(35,51,101,0.4);" +
                    "   -fx-border-width: 0 0 3 0;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-radius: 10;" +
                    "    -fx-border-color: #c3a6e8;");


            rectangle.setPrefHeight(100 + 20 * (reviewLbl.getText().split("\n").length));



            lbl = new Label();
            lbl.setGraphic(new ImageView(new Image("Resources/account/acc" + i + ".png")));
            lbl.setStyle("-fx-border-width: 1;" +
                    "-fx-border-color: white;" +
                    "-fx-border-radius: 50;" +
                    " -fx-effect: dropshadow( two-pass-box , rgba(202,140,255,0.33), 25, 0.0 , 0 , 0 );");
            lbl.relocate(30,20);

            nameLbl = new Label(reviewNames[i]);
            nameLbl.relocate(90, 20);
            nameLbl.setId("name-review");

            dateLbl = new Label(dates[i]);
            dateLbl.relocate(90, 45);
            dateLbl.setId("date-review");

            bgStars = new ImageView(new Image("Resources/account/bg-stars.png"));
            bgStars.relocate(493, 20);
            bgStars.setOpacity(0.2);

            frontStars = new ImageView(cutImage(new Image("Resources/account/bg-stars.png"), marks[i]));
            frontStars.relocate(493,20);
            frontStars.setStyle(" -fx-effect: dropshadow( three-pass-box , #a68bd4, 20 , 0.0 , 0 , 0 );");

            ratingLbl = new Label(String.valueOf(marks[i]));
            ratingLbl.relocate(465,18);
            ratingLbl.setId("mark-review");

            items.getChildren().addAll(rectangle, lbl, nameLbl, dateLbl, bgStars, frontStars, ratingLbl, reviewLbl);
            reviews.getChildren().addAll(items);
        }


        //  reminders.getChildren().addAll(btn);
        reviewsPane.setContent(reviews);
        reviewsPane.setVisible(false);
        return reviewsPane;
    }

    private Image cutImage(Image img, double width){
        double size = width * 87 / 10;
        PixelReader reader = img.getPixelReader();
        WritableImage newImage = new WritableImage(reader, 0, 0, (int)size, 16);
        return newImage;
    }
}
