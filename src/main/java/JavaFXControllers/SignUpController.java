package JavaFXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class SignUpController {
    @FXML
    public TextField fullname;
    @FXML
    public PasswordField password;
    @FXML
    public TextField email;
    @FXML
    public Button login;
    @FXML
    public TextField Username;
    @FXML
    public TextField PhoneNumber;
    public Button sign;

    @FXML

    public void signUp(ActionEvent event) {

        final long constTypeId = 4L;

        String fullnameUser = fullname.getText();
        String passUser = password.getText();
        String emailUser = email.getText();
        String usernameUser = Username.getText();
        String phoneNumber = PhoneNumber.getText();

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        DistributorEntity distributorEntity = new DistributorEntity();

        entitymanager.persist(userDetailsEntity);
        distributorEntity.setDistributorId(userDetailsEntity.getUserId());
        userDetailsEntity.setDistributorEntity(distributorEntity);

        entitymanager.persist(userDetailsEntity);
        entitymanager.getTransaction().commit();

    }

    public void goLogin(ActionEvent event) throws Exception {
        login.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FxmlFiles/Login.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
    }
}
