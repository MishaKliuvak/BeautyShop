import Constants.Const;
import Views.Authorization;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public void start(final Stage pStage) throws Exception {
        pStage.setTitle("BeautyShop");
        pStage.setScene(Authorization.getInstance().setAuthorizationPane());

        pStage.setWidth(Const.SCREEN_WIDTH);
        pStage.setHeight(Const.SCREEN_HEIGHT);
        setWindowPosition(pStage);

        pStage.setResizable(false);
        pStage.setMaximized(false);
        pStage.initStyle(StageStyle.TRANSPARENT);
        pStage.getScene().getStylesheets().add(this.getClass().getResource("Styles/main-styles.css").toExternalForm());
        pStage.show();
        com.sun.glass.ui.Window.getWindows().get(0).setUndecoratedMoveRectangle(20);
    }

    public static void main(String[] args){
        launch(args);
    }

    private void setWindowPosition(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - Const.SCREEN_WIDTH) / 2);
        stage.setY((primScreenBounds.getHeight() - Const.SCREEN_HEIGHT) / 2);
    }
}
