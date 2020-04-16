package Views.AdminPane;
import Constants.Const;
import Views.ClientPane.ServicesPane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FullCalendarView {

    private static FullCalendarView fullCalendarView = null;
    private FullCalendarView() {}


    private  ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private  VBox view;
    private  Label calendarTitle;
    private  YearMonth currentYearMonth;
    private  YearMonth yearMonth = YearMonth.now();
    public static FullCalendarView getInstance(){
        if(fullCalendarView==null)
            FullCalendarView.fullCalendarView = new FullCalendarView();

        return FullCalendarView.fullCalendarView;
    }
    /**
     * Set the days of the calendar to correspond to the appropriate date
     * @param yearMonth year and month of month to render
     */
    static Text txt;
    public void populateCalendar(YearMonth yearMonth) {
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("MONDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }

        // Populate the calendar with day numbers
        for (AnchorPaneNode ap : allCalendarDays) {
            ap.getChildren().clear();
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }
            txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            txt.setId("day-item");
            ap.setDate(calendarDate);
            ap.setId("calendar-item");
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 5.0);

            ap.getChildren().addAll(txt);
            items.clear();
            for (Pair p:seances) {
                if(p.getDate().equals(String.format("%s-%s-%s",calendarDate.getYear(),calendarDate.getMonthValue(),calendarDate.getDayOfMonth()))){
                    items.add(p);
                }
            }
            if(items.size()>0) ap.getChildren().add(itemPane(ap, items));
            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the calendar
        calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
    }

    /**
     * Move the month back by one. Repopulate the calendar with the correct dates.
     */
    private  void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Repopulate the calendar with the correct dates.
     */
    private  void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public VBox getView() {
        seances.clear();
        s.clear();
        loadTimes();

        currentYearMonth = yearMonth;
        // Create the calendar grid pane
        GridPane calendar = new GridPane();
        calendar.setPrefSize(680, 450);
        calendar.setId("full-calendar");
        // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                ap.setPrefSize(200,200);
                if (i%2==0 && j%2==0){
                    ap.setStyle(Const.CALENDAR_ITEM_STYLE);
                }
                else if (i%2!=0 && j%2!=0){
                    ap.setStyle(Const.CALENDAR_ITEM_STYLE);
                }

                calendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }
        // Days of the week labels
        Label[] dayNames = new Label[]{new Label("Monday"), new Label("Tuesday"),
                new Label("Wednesday"), new Label("Thursday"), new Label("Friday"),
                new Label("Saturday"), new Label("Sunday")};

        GridPane dayLabels = new GridPane();
        dayLabels.setId("days");
        dayLabels.setPrefWidth(600);

        Integer col = 0;
        for (Label txt : dayNames) {
            AnchorPane ap = new AnchorPane();
            txt.setId("day-names");
            ap.setPrefSize(200, 10);
            ap.setBottomAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            dayLabels.add(ap, col++, 0);
        }
        // Create calendarTitle and buttons to change current month
        calendarTitle = new Label();
        calendarTitle.setId("month-name");
        Button previousMonth = new Button("<<");
        previousMonth.setOnAction(e -> previousMonth());
        previousMonth.setId("month-changer");
        Button nextMonth = new Button(">>");
        nextMonth.setId("month-changer");
        nextMonth.setOnAction(e -> nextMonth());
        HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
        titleBar.setAlignment(Pos.BASELINE_CENTER);
        titleBar.setSpacing(10);
        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);
        // Create the calendar view
        view = new VBox(titleBar, dayLabels, calendar);
        Platform.runLater(()->{
            view.relocate(240 + (770 - view.getWidth())/2, (635 - view.getHeight())/2);
        });
        return view;
    }

    public ArrayList<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }

    static ScrollPane pane;
    private  List<Pair> seances = new ArrayList<Pair>();
    private  List<Pair> items = new ArrayList<Pair>();
    private  List<String> s = new ArrayList<String>();
    private  void loadTimes(){
        s.add("Hair dyeling Mia Mia");
        s.add("Manicure Mama Mia");
        seances.add(new Pair("2020-4-8", "11:00", s));
        seances.add(new Pair("2020-4-2", "10:00", s));
        seances.add(new Pair("2020-4-3", "12:00", s));
        seances.add(new Pair("2020-4-3", "11:00", s));
    }

    private static VBox content;
    private static Label time, seanse;
    private static ScrollPane itemPane(AnchorPane ap, List<Pair> pair){
        pane = new ScrollPane();
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        content = new VBox();

        for (Pair p:pair) {
            time = new Label(p.getTime());
            time.setId("cal-item-time");
            content.getChildren().add(time);
            for(int i=0; i<p.getProcedure().size(); i++){
                seanse = new Label(p.getProcedure().get(i));
                seanse.setId("cal-item-seanse");
                content.getChildren().add(seanse);
            }
        }

        pane.setContent(content);

        ImageView openIco = new ImageView(new Image("Resources/icons/open.png"));
        openIco.setId("icons");
        ap.getChildren().add(openIco);

        ap.setTopAnchor(openIco, 5.0);
        ap.setRightAnchor(openIco, 5.0);

        ap.setTopAnchor(pane, 23.0);
        ap.setLeftAnchor(pane, 5.0);
        ap.setRightAnchor(pane, 3.0);
        ap.setBottomAnchor(pane, 2.0);

        return pane;
    }

    public class Pair {
        private  String date;
        private  String time;
        private  List<String> procedure = new ArrayList<String>();

        public Pair(String date, String time, List<String> list){
            this.date = date;
            this.time = time;
            this.procedure = list;
        }

        public String getDate(){
            return date;
        }
        public String getTime(){
            return  time;
        }
        public List<String> getProcedure(){
            return procedure;
        }
    }
}
