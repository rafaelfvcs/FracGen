package nfracgen.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import nfracgen.javafxapplication.FracGenApplication;

public class ExportImageStage {

    private static WritableImage image;

    public ExportImageStage(WritableImage image) {
        this.image = image;
    }

    public static WritableImage getImage() {
        return image;
    }

    public void createStage() throws IOException {
        FXMLLoader loader
                = new FXMLLoader(FracGenApplication.getInstance().getClass()
                        .getResource("/views/stage_saveimage.fxml"));
        Parent parent = (Parent) loader.load();                
        /**
         * Create and show stage
         */
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setTitle("Export Power Law Graph to PNG");
        stage.setScene(scene);
        stage.show();
    }

}
