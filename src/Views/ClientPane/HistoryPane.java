package Views.ClientPane;


import Constants.Const;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


public class HistoryPane {
    private static HistoryPane historyPane = null;
    private HistoryPane() {}
    public static HistoryPane getInstance(){
        if(historyPane==null)
            HistoryPane.historyPane = new HistoryPane();
        return HistoryPane.historyPane;
    }

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    private Pane mainPane;
    private ScrollPane historySP;

    public Pane mainPane(){
        mainPane = new Pane();

        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);
        lineChart.setId("chartline");
        lineChart.setTitle("Number of sessions visited, 2019");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Pedicure");

        series1.getData().add(new XYChart.Data("Jan", 23));
        series1.getData().add(new XYChart.Data("Feb", 14));
        series1.getData().add(new XYChart.Data("Mar", 15));
        series1.getData().add(new XYChart.Data("Apr", 24));
        series1.getData().add(new XYChart.Data("May", 34));
        series1.getData().add(new XYChart.Data("Jun", 36));
        series1.getData().add(new XYChart.Data("Jul", 22));
        series1.getData().add(new XYChart.Data("Aug", 45));
        series1.getData().add(new XYChart.Data("Sep", 43));
        series1.getData().add(new XYChart.Data("Oct", 17));
        series1.getData().add(new XYChart.Data("Nov", 29));
        series1.getData().add(new XYChart.Data("Dec", 25));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Haircut");
        series2.getData().add(new XYChart.Data("Jan", 33));
        series2.getData().add(new XYChart.Data("Feb", 34));
        series2.getData().add(new XYChart.Data("Mar", 25));
        series2.getData().add(new XYChart.Data("Apr", 44));
        series2.getData().add(new XYChart.Data("May", 39));
        series2.getData().add(new XYChart.Data("Jun", 16));
        series2.getData().add(new XYChart.Data("Jul", 55));
        series2.getData().add(new XYChart.Data("Aug", 54));
        series2.getData().add(new XYChart.Data("Sep", 48));
        series2.getData().add(new XYChart.Data("Oct", 27));
        series2.getData().add(new XYChart.Data("Nov", 37));
        series2.getData().add(new XYChart.Data("Dec", 29));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Manicure");
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

        lineChart.setPrefSize(600,295);
        lineChart.relocate(240 + 170/2, 310);
        lineChart.getData().addAll(series1,series2,series3);

        Label title = new Label("Last seances");
        title.setStyle("-fx-font-family: \"Euclid Flex\";\n" +
                "    -fx-font-weight: 700;\n" +
                "    -fx-text-fill: #ffffff;" +
                "-fx-font-size: 15pt;" +
                "  -fx-effect: dropshadow( three-pass-box , rgba(195, 166, 232, 0.55), 20 , 0.0 , 0 , 0 );");
        title.relocate(560, 35);
        mainPane.getChildren().addAll(lineChart, getHistorySP(), title);
        return mainPane;
    }
    private String[] names = {"Manicure", "Hair dyeing", "Manicure"};
    private int[] code = {3,1,3};
    private int[] prices = {260, 110, 220};

    Button review; Label complain;
    private ScrollPane getHistorySP(){
        historySP = new ScrollPane();
        historySP.setPrefViewportWidth(600);
        historySP.setPrefViewportHeight(210);
        historySP.relocate(240 + 170/2, 75);
        historySP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox seans = new VBox();
        seans.setPrefWidth(675);

        Pane item;
        Region rectangle;
        Label callname, date;
        ImageView imv, imv2;
        for (int i = 0; i < 3; i++) {
            item = new Pane();

            rectangle = new Region();
            rectangle.setPrefSize(600, 70);
            rectangle.setStyle("" +
                    "-fx-background-color: rgba(35,51,101,0.4);" +
                    "   -fx-border-width: 0 0 3 0;" +
                    "-fx-background-radius: 10;" +
                    "    -fx-border-color: " + Const.colors[i] + ";");

            imv = new ImageView(new Image("Resources/account/coupons/" + (i+1) + ".png"));
            imv.relocate(22,12);
            imv.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(200,168,255,0.56), 20 , 0.0 , 0 , 0 );");

            imv2 = new ImageView(new Image("Resources/account/services_icon/" + code[i] + ".png"));
            imv2.setScaleX(0.7);
            imv2.setScaleY(0.7);
            imv2.relocate(17,4);

            callname = new Label(names[i]);
            callname.relocate(88, 15);
            callname.setId("name-review");

            date = new Label("Aug, " + (i+10));
            date.relocate(88, 37);
            date.setId("date-review");

            Label bonus = new Label("-" + prices[i] + " ₴");
            bonus.relocate(200,10);
            bonus.setId("bonus");
            bonus.setStyle( "-fx-border-color: " + Const.colors[i] + ";");

            review = new Button("Leave feedback");
            review.setId("delete");
            review.setPrefWidth(150);
            review.relocate(578 - review.getPrefWidth(), 15);

            complain = new Label("To complain");
            complain.setId("forgot");
            complain.relocate(470, 42);
            Platform.runLater(()->{
                bonus.relocate((600 - bonus.getWidth())/2, (70 - bonus.getHeight())/2);
            });


            item.getChildren().addAll(rectangle, imv, imv2, callname, date, bonus, review, complain);
            seans.getChildren().add(item);

        }


        historySP.setContent(seans);

        return historySP;
    }
}
