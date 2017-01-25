/*
 * Copyright (C) 2016 elidioxg
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nfracgen.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javafx.scene.chart.LineChart;

/**
 * FXML Controller class
 *
 * @author elidioxg
 */
public class Stage_powerlawController implements Initializable {

    @FXML
    protected LineChart scFractureIntensity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void exportGraph() throws IOException, Exception {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            if (!file.exists()) {
                if (file.getAbsolutePath() != null) {
                    WritableImage imagePL = 
                            scFractureIntensity.snapshot(new SnapshotParameters(), null);;
                    if (imagePL != null) {
                        File imageFile = new File(file.getAbsolutePath());
                        ImageIO.write(SwingFXUtils.fromFXImage(imagePL, null), "png", imageFile);
                    }
                } else {
                    throw new Exception("Filename is empty.");
                }
            } else {
                throw new Exception("File already exists.");
            }
        }
    }
}
