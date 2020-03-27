package Views;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HybridLabel {
    public static HBox custom(String f, String s, String t){
        HBox hybrid = new HBox();
        Label first = new Label(f);
        Label second = new Label(s);
        Label third = new Label(t);
        first.setId("name"); second.setId("name2"); third.setId("name");
        hybrid.getChildren().addAll(first, second, third);
        hybrid.setSpacing(10);
        hybrid.setAlignment(Pos.BASELINE_CENTER);
        return hybrid;
    }
}
