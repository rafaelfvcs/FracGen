package br.com.fracgen.controller;

import br.com.fracgen.application.StageOpenData;
import java.io.IOException;
import javafx.fxml.FXML;

public class Controller {
    
    @FXML
    protected void openFileStage() throws IOException {
        StageOpenData openData = new StageOpenData();
        openData.createStage();
    }

	@FXML public void exit() {}

}
