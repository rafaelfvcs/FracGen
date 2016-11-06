package nfracgen.stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nfracgen.javafxapplication.FracGenApplication;

public class PowerLawStage {

    /**
     * Create a new Stage for Line Chart plot with Power Law values
     *
     * @throws IOException
     */
    public void createStage() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                FracGenApplication.getInstance().getClass().getResource("/views/stage_powerlaw.fxml"));
        Parent parent = (Parent) loader.load();
        Stage stageLine = new Stage();
        Scene scene = new Scene(parent);
        stageLine.setTitle("Line Chart");
        stageLine.setScene(scene);
        stageLine.show();
    }
}
