package nfracgen.controller;

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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import nfracgen.javafxapplication.FracGenApplication;
import nfracgen.stage.MainStage;

/**
 * FXML Controller class
 *
 * @author elidioxg
 */
public class Tab_outputController implements Initializable {
    
    @FXML
    protected TextField tfUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        check_output_adv_study.selectedProperty().addListener((v, oldv, newv) -> {
            if (newv == true) {
                grid_output_adv_study.setDisable(false);
            } else {
                grid_output_adv_study.setDisable(true);
            }
        });
        
        check_output_comments.selectedProperty().addListener((v, oldv, newv) -> {
            if (newv == true) {
                textarea_output_comments.setDisable(false);
            } else {
                textarea_output_comments.setDisable(true);
            }
        });                    
        tfUser.setText(MainStage.getScanlineAnalysis().getUser());
    }

    @FXML
    private TextArea textarea_output_comments;

    @FXML
    private CheckBox check_output_comments;

    @FXML
    private CheckBox check_output_adv_study;

    @FXML
    private CheckBox check_output_newproject;

    @FXML
    private GridPane grid_output_adv_study;

    @FXML
    private GridPane grid_output_project;

    @FXML
    TreeView<String> treeview_outs;

    @SuppressWarnings("unchecked")
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //createTree();
        //create root
        TreeItem<String> root = new TreeItem<>("Output Structure");

        //root.setExpanded(true);
        //create child
        TreeItem<String> item1 = new TreeItem<>("Data collect");
        TreeItem<String> item1c1 = new TreeItem<>("Data Base");
        TreeItem<String> item1c2 = new TreeItem<>("Field Trip");
        TreeItem<String> item1c3 = new TreeItem<>("Darwin01");
        item1.getChildren().addAll(item1c1, item1c2, item1c3);
        //item1.setExpanded(false);

        TreeItem<String> item2 = new TreeItem<>("Scanline");

        TreeItem<String> item2C1 = new TreeItem<>("Power Law");
        TreeItem<String> item2C2 = new TreeItem<>("New data");
        item2.getChildren().addAll(item2C1, item2C2);

        TreeItem<String> item3 = new TreeItem<>("Modeling");

        TreeItem<String> item3C1 = new TreeItem<>("2D");
        TreeItem<String> item3C2 = new TreeItem<>("3D");
        item3.getChildren().addAll(item3C1, item3C2);

        //itemChild2.setExpanded(false);
        //root is the parent of itemChild
        root.getChildren().addAll(item1, item2, item3);

        treeview_outs.getSelectionModel().selectedItemProperty().addListener((v, oldvalue, newvalue) -> {
            if (newvalue != null) {
                System.out.println(newvalue.toString());
            }
        });

        treeview_outs.setRoot(root);
        grid_output_project.setDisable(true);
        check_output_newproject.setDisable(false);
    }

}
