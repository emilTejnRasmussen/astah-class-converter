import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class AstahConverterApp extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(AstahConverterApp.class.getResource("AstahConverter.fxml"));

        Parent view = loader.load();
        Scene scene = new Scene(view);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(
                new Image(Objects.requireNonNull(AstahConverterApp.class.getResourceAsStream("/icon/converterLogo.png")))
        );
        primaryStage.show();
    }
}
