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
package stage;

import br.com.fracgen.javafxapplication.JavaFXFracGenApplication;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author elidioxg
 */
public class StageOpenData {
    /**
     * The Open Data Stage is used for add datasets to the ListView on main
     * stage
     */

    /**
     * This textfield handles the full path and name of dataset
     */
    public TextField tfFilename;

    public void createStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(JavaFXFracGenApplication.getInstance().getClass()
                .getResource("/views/stage_opendata.fxml"));        
        GridPane parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Open Dataset");
        stage.setScene(scene);
        stage.show();     
    }

    public void setFilename(String fn) {
        this.tfFilename.setText(fn);
    }
    
}
