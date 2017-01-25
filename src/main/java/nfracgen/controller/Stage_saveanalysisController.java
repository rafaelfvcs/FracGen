/*
 * Copyright (C) 2017 elidioxg
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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import nfracgen.database.InternalDatabase;
import nfracgen.stage.MainStage;

/**
 * FXML Controller class
 *
 * @author elidioxg
 */
public class Stage_saveanalysisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected TextField tfName, tfUser;

    @FXML
    protected void save() throws Exception {
        String databaseName = tfName.getText().trim();
        String user = tfUser.getText();
        InternalDatabase database = new InternalDatabase(databaseName);
        if (database.isConnected()) {
            database.saveAnalysis(MainStage.getScanlineAnalysis());
        } else {
            System.out.println("Not conected");
        }
    }

}
