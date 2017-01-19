package nfracgen.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javax.mail.MessagingException;
import nfracgen.model.Email;
import nfracgen.stage.MainStage;

public class LoginController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label lblMessage;
    //comentario
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    protected void btnLoginAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
//        ExternalDatabase database = new ExternalDatabase();
//        if (database.connect()) {
//            if (database.validateLogin(txtUsername.getText(), txtPassword.getText())) {
//                ((Node) (event.getSource())).getScene().getWindow().hide();
//                MainStage.showMainStage();
//            } else {
//                lblMessage.setText("Username or Password invalid");
//                txtUsername.setText("");
//                txtPassword.setText("");
//            }
//        } else {
//            lblMessage.setText("Connection Error");
//        }
        if (txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin")) {

            ((Node) (event.getSource())).getScene().getWindow().hide();

            //lblMessage.setText("Bem vindo: " + txtUsername.getText());
            MainStage.showMainStage(txtUsername.getText());

        } else {
            lblMessage.setText("Username or Password invalid");
            txtUsername.setText("");
            txtPassword.setText("");
        }
    }

    @FXML
    Hyperlink site_nfracgen;

    //not working
    @FXML
    protected void siteLink() {
        site_nfracgen = new Hyperlink("http://www.ngracgen.com");
        mainPane.getChildren().addAll(site_nfracgen);
    }

    @FXML
    protected TextField tfName, tfEmail, tfProf, tfComp, tfCountry;

    @FXML
    protected void sendToNfracgen() throws MessagingException {
        String name = tfName.getText();
        String mail = tfEmail.getText();
        String prof = tfProf.getText();
        String comp = tfComp.getText();
        String country = tfCountry.getText();
        Email email = new Email(name, mail, prof, comp, country);
        email.sendMessage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
