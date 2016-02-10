package br.com.fracgen.controller;

import br.com.fracgen.application.ApplicationTest;
import br.com.fracgen.application.StageOpenData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class OpenDataLayoutController implements Initializable {

    @FXML
    protected TableView tableData;

    @FXML
    protected void applyDataset() throws IOException {
        throw new IOException("Not implemented yet");
    }

    @FXML
    protected void closeFileStage() throws IOException {
        throw new IOException("Not implemented yet");
    }

    @FXML
    private TextField tfSeparator;

    //handle click event of button Locate
    @FXML
    protected void OpenFileDialog() throws IOException {
        final FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        ApplicationTest.getInstance().csv.Separator = tfSeparator.getCharacters().toString();
        if (file.getAbsolutePath() != null) {
            ApplicationTest.getInstance().csv.fileName = file.getAbsolutePath();
            refreshTable();
        }
    }

    @FXML
    private TableView tableView;

    //handle click event of button Change
    @FXML
    protected void refreshTable() {
        ApplicationTest.getInstance().csv.Separator = tfSeparator.getText();
        if (!ApplicationTest.getInstance().csv.fileName.isEmpty()) {
            populateTable(tableView,
                    ApplicationTest.getInstance().csv.fileName,
                    ApplicationTest.getInstance().csv.Separator,
                    true);
            populateComboBoxes();
        }
    }

    @FXML
    public void populateTable(TableView table, final String filename, final String separator,
            final boolean hasHeader) {
        table.getItems().clear();
        table.getColumns().clear();
        if (filename.trim() != null) {
            table.setPlaceholder(new Label("Loading..."));
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    BufferedReader br = new BufferedReader(new FileReader(filename));
                    final String headerLine = br.readLine();
                    final String[] headerValues = headerLine.split(separator);
                    if (hasHeader) {
                        ApplicationTest.getInstance().csv.headerValues = headerValues;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                for (int column = 0; column < headerValues.length; column++) {
                                    table.getColumns().add(
                                            createColumn(column, headerValues[column]));
                                }
                            }
                        });
                    }
                    String dataLine;
                    while ((dataLine = br.readLine()) != null) {
                        final String[] dataValues = dataLine.split(separator);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                // Add additional columns if necessary:
                                for (int columnIndex = table.getColumns().size();
                                        columnIndex < dataValues.length; columnIndex++) {
                                    table.getColumns().add(createColumn(columnIndex, "New Column"));
                                }
                                ObservableList<StringProperty> data = FXCollections
                                        .observableArrayList();
                                for (String value : dataValues) {
                                    data.add(new SimpleStringProperty(value));
                                }
                                table.getItems().add(data);
                            }
                        });
                    }
                    return null;
                }
            };
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        }
    }

    private TableColumn<ObservableList<StringProperty>, String> createColumn(
            final int columnIndex, String columnTitle) {
        TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
        String title;
        if (columnTitle == null || columnTitle.trim().length() == 0) {
            title = "Column " + (columnIndex + 1);
        } else {
            title = columnTitle;
        }
        column.setText(title);
        column.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<ObservableList<
                StringProperty>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    TableColumn.CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
                ObservableList<StringProperty> values = cellDataFeatures.getValue();
                if (columnIndex >= values.size()) {
                    return new SimpleStringProperty("");
                } else {
                    return cellDataFeatures.getValue().get(columnIndex);
                }
            }
        });
        return column;
    }

    @FXML
    private ComboBox cbId, cbSp, cbAp;

    @FXML
    private void populateComboBoxes() {
        List list = new ArrayList();
        if (ApplicationTest.getInstance().csv.headerValues != null) {
            list.addAll(Arrays.asList(ApplicationTest.getInstance().csv.headerValues));
            ObservableList ol = FXCollections.observableArrayList(list);
            cbId.setItems(ol);
            cbSp.setItems(ol);
            cbAp.setItems(ol);
            //Auto select fields for comboboxes
            if (ApplicationTest.getInstance().csv.headerValues.length > 2) {
                cbId.getSelectionModel().select(0);
                cbSp.getSelectionModel().select(1);
                cbAp.getSelectionModel().select(2);
            }
        }
    }

    @FXML
    public void exit() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
