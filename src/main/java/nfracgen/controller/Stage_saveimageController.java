package nfracgen.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import nfracgen.javafxapplication.FracGenApplication;
import nfracgen.stage.ExportImageStage;

/**
 * FXML Controller class
 *
 */
public class Stage_saveimageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected TextField tfLocation;
    @FXML
    protected Button bCancel;

    @FXML
    protected LineChart lcPowerLaw;
    /**
     * Export Power Law Graph to PNG image
     */
    @FXML
    protected void exportPL() throws IOException {
        if (!tfLocation.getText().isEmpty()) {            
            WritableImage imagePL
                    = ExportImageStage.getImage();
                    //= lcPowerLaw.snapshot(new SnapshotParameters(), null);
            if (imagePL != null) {
                File imageFile = new File(tfLocation.getText().trim());
                ImageIO.write(SwingFXUtils.fromFXImage(
                        imagePL, null), "png", imageFile);
            }            
        }
    }

    @FXML
    protected void close() {
        Stage stageLine = (Stage) bCancel.getScene().getWindow();
        stageLine.close();
    }

    @FXML
    protected void setFilename() throws Exception {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            if (!file.exists()) {
                if (file.getAbsolutePath() != null) {
                    tfLocation.setText(file.getAbsolutePath());
                }
            } else {
                throw new Exception("File already exists.");
            }
        }
    }

}
