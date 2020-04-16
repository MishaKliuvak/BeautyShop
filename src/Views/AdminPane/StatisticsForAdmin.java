package Views.AdminPane;

import Constants.Const;
import animatefx.animation.ZoomIn;
import com.sun.javafx.charts.Legend;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class StatisticsForAdmin {
    private static StatisticsForAdmin statisticsForAdmin = null;
    private StatisticsForAdmin(){}
    public static StatisticsForAdmin getInstance(){
        if(statisticsForAdmin == null){ StatisticsForAdmin.statisticsForAdmin = new StatisticsForAdmin();}
        return StatisticsForAdmin.statisticsForAdmin;
    }

    private ScrollPane spane;
    private Pane mainPane, topPane, chartPane, topWorkers, servicesRating;
    private ImageView completeSeancesIV, getMoneyIV, percentIV, completeIco, moneyIco, percentIco;
    private Label completeLbl, moneyLbl, percentLbl, completeNum, moneyNum, percentNum;
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private ToggleGroup group1, group2, group3;
    private ToggleButton year1, year2, year3, month1, month2, month3, week1, week2, week3;

    Label title;
    Button saveResult, printResult;

    public ScrollPane getPane(){
        spane = new ScrollPane();
        spane.setLayoutY(60);
        spane.setLayoutX(20);
        spane.setPrefViewportHeight(515);
        spane.setPrefViewportWidth(960);
        spane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        mainPane = new Pane();

        title = new Label("Top workers");
        title.setId("title-tops");
        title.relocate(700, 188);

        saveResult = new Button("Save report");
        saveResult.relocate(695, 400);
        saveResult.setId("delete");
        saveResult.setPrefSize(235, 30);

        printResult = new Button("Print report");
        printResult.relocate(695, 445);
        printResult.setId("delete");
        printResult.setPrefSize(235, 30);

        group1 = new ToggleGroup();
        year1 = new ToggleButton("Year");
        month1 = new ToggleButton("Month");
        week1 = new ToggleButton("Week");
        toggle(group1, year1, month1, week1, 338, 188);

        group2 = new ToggleGroup();
        year2 = new ToggleButton("Year");
        month2 = new ToggleButton("Month");
        week2 = new ToggleButton("Week");
        toggle(group2, year2, month2, week2, 475, 525);

        group3 = new ToggleGroup();
        year3 = new ToggleButton("Year");
        month3 = new ToggleButton("Month");
        week3 = new ToggleButton("Week");
        toggle(group3, year3, month3, week3, 475, 957);


        mainPane.getChildren().addAll(topPane(), chartPane(), topWorkers(), title, saveResult,
                printResult, servicesRating(), servicesProfit(), year1, month1, week1, year2, month2, week2,
                year3, week3, month3);
        spane.setContent(mainPane);
        return spane;
    }

    private Pane topPane(){
        topPane = new Pane();

        completeSeancesIV = new ImageView(new Image("Resources/account/services/1.png"));
        completeSeancesIV.relocate(283,20);
        completeSeancesIV.setId("bg-st");
        completeIco = new ImageView(new Image("Resources/account/client/complete.png"));
        completeIco.relocate(383,38);
        completeLbl = new Label("Total: 158");
        completeLbl.relocate(303, 100);
        completeLbl.setId("stat-descr");
        completeNum = new Label("Sessions complete");
        completeNum.relocate(303, 115);
        completeNum.setId("stat-num");

        getMoneyIV = new ImageView(new Image("Resources/account/services/2.png"));
        getMoneyIV.relocate(506,20);
        getMoneyIV.setId("bg-st");
        moneyIco = new ImageView(new Image("Resources/account/client/money.png"));
        moneyIco.relocate(606, 38);
        moneyLbl = new Label("Total: 3250 ₴");
        moneyLbl.relocate(526, 100);
        moneyLbl.setId("stat-descr");
        moneyNum = new Label("Received money");
        moneyNum.relocate(526, 115);
        moneyNum.setId("stat-num");

        percentIV = new ImageView(new Image("Resources/account/services/5.png"));
        percentIV.relocate(729, 20);
        percentIV.setId("bg-st");
        percentIco = new ImageView(new Image("Resources/account/client/progress.png"));
        percentIco.relocate(829,38);
        percentLbl = new Label("Total: +12 %");
        percentLbl.relocate(749, 100);
        percentLbl.setId("stat-descr");
        percentNum = new Label("Previous month");
        percentNum.relocate(749, 115);
        percentNum.setId("stat-num");

        topPane.getChildren().addAll(completeSeancesIV, getMoneyIV, percentIV, completeIco, moneyIco, percentIco,
                completeLbl, percentLbl, moneyLbl, completeNum, moneyNum, percentNum);
        return topPane;
    }

    private Pane chartPane(){
        chartPane = new Pane();

        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);
        lineChart.setId("chartline");
        lineChart.setTitle("Number of sessions visited, 2019");

        XYChart.Series series3 = new XYChart.Series();
        series3.getData().add(new XYChart.Data("Jan", 44));
        series3.getData().add(new XYChart.Data("Feb", 35));
        series3.getData().add(new XYChart.Data("Mar", 36));
        series3.getData().add(new XYChart.Data("Apr", 33));
        series3.getData().add(new XYChart.Data("May", 31));
        series3.getData().add(new XYChart.Data("Jun", 26));
        series3.getData().add(new XYChart.Data("Jul", 22));
        series3.getData().add(new XYChart.Data("Aug", 25));
        series3.getData().add(new XYChart.Data("Sep", 43));
        series3.getData().add(new XYChart.Data("Oct", 44));
        series3.getData().add(new XYChart.Data("Nov", 45));
        series3.getData().add(new XYChart.Data("Dec", 44));
        lineChart.setPrefSize(385,265);
        lineChart.relocate(283, 225);
        lineChart.setLegendVisible(false);
        lineChart.getData().addAll(series3);

        chartPane.getChildren().addAll(lineChart);
        return chartPane;
    }

    private VBox servicesListVBox;
    private HBox service;
    private Label name, workers;

    private VBox topWorkers(){
        servicesListVBox = new VBox();
        servicesListVBox.setSpacing(10);
        servicesListVBox.relocate(695, 228);

        for(int i=0; i<3; i++){
            service = new HBox();
            service.setPrefWidth(235);
            service.setAlignment(Pos.CENTER_LEFT);
            service.setStyle("" +
                    "-fx-background-color: rgba(35,51,101,0.4);" +
                    "   -fx-border-width: 0 0 3 0;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-radius: 10;" +
                    "    -fx-border-color: " + Const.colors[i] + ";");

            name = new Label("Misha Kliuvak");
            name.setId("top-name");
            workers = new Label("+ 10235 ₴");
            workers.setId("top-count");
            workers.setStyle( "-fx-border-color: " + Const.colors[i] + ";");
            service.setAlignment(Pos.CENTER);
            service.setSpacing(7);
            service.getChildren().addAll(name, workers);
            servicesListVBox.getChildren().add(service);
        }
        return servicesListVBox;
    }

    private ObservableList<PieChart.Data> pieChartData;
    private PieChart chart;
    private BarChart servicesProfit(){
        CategoryAxis xAxis    = new CategoryAxis();

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");
        yAxis.setId("ax");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries1.setName("2014");
        dataSeries2.setName("22");


        dataSeries1.getData().add(new XYChart.Data("Desktop", 13));
        dataSeries1.getData().add(new XYChart.Data("віваві", 83));
        dataSeries1.getData().add(new XYChart.Data("віааппп \nпппппппп", 53));
        dataSeries1.getData().add(new XYChart.Data("Desktівааіop", 28));
        dataSeries1.getData().add(new XYChart.Data("Desktівіop", 49));
        dataSeries1.getData().add(new XYChart.Data("Desktвавop", 68));
        dataSeries1.getData().add(new XYChart.Data("Desktффop", 88));
        dataSeries1.getData().add(new XYChart.Data("Desktop", 56));


        barChart.getData().addAll(dataSeries1);
        barChart.setLegendVisible(false);
        barChart.setId("chart-bar");
        barChart.getXAxis().setTickMarkVisible(false);
        barChart.setPrefSize(670, 280);
        barChart.relocate(275, 994);
        barChart.setTitle("Service profit");
        return barChart;
    }

    private PieChart pieChart;
    private PieChart servicesRating(){
        pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Manicure - 8", 8),
                        new PieChart.Data("Pedicure - 2", 2),
                        new PieChart.Data("Hair dyeing - 4", 4),
                        new PieChart.Data("Haircut - 1", 1));
        pieChartData.add(new PieChart.Data("Hadir dyeing - 4", 4));
        pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Services");
        pieChart.setPrefSize(670,370);
        pieChart.relocate(280, 562);
        pieChart.setId("chart");
        pieChart.setLegendVisible(false);

        return pieChart;
    }

    private void toggle(ToggleGroup gr, ToggleButton bt1, ToggleButton bt2, ToggleButton bt3, int x, int y){
        bt1.setToggleGroup(gr);
        bt1.setSelected(true);
        bt1.relocate(x, y);
        bt1.setPrefSize(80, 15);
        bt1.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
            }
        });
        bt2.setToggleGroup(gr);
        bt2.relocate(x+100, y);
        bt2.setPrefSize(80, 15);
        bt2.setSelected(false);
        bt2.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
            }
        });
        bt3.setToggleGroup(gr);
        bt3.relocate(x+200, y);
        bt3.setPrefSize(80, 15);
        bt3.setSelected(false);
        bt3.selectedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
            }
        });

        bt1.setId("change-chart"); bt2.setId("change-chart"); bt3.setId("change-chart");
    }
}
