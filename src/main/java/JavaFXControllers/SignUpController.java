package JavaFXControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;


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
    public TextField username;
    @FXML
    public TextField PhoneNumber;
    @FXML
    public Button sign;
    @FXML
    public Label isValid;
    @FXML
    public Label passwordValidate;
    @FXML
    public Label phoneValidate;
    @FXML
    public Label usernamevalid;
    @FXML
    public Label fullnamevalid;
    @FXML
    public Label succesfully;

    @FXML
    public void signUp(ActionEvent event) {

        final long constTypeId = 4L;

        String fullnameUser = fullname.getText();
        String passUser = password.getText();
        String emailUser = email.getText();
        String usernameUser = username.getText();
        String phoneNumber = PhoneNumber.getText();

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        try {
            if (!(fullnameUser.equals("") && usernameUser.equals(""))
                    && isValidEamil(emailUser) && isValidPassword(passUser)
                    && isValidPhone(phoneNumber)) {
                userDetailsEntity.setUserFullname(fullnameUser);
                userDetailsEntity.setUserUsername(usernameUser);
                userDetailsEntity.setUserEmail(emailUser);
                userDetailsEntity.setUserPhone(phoneNumber);
                userDetailsEntity.setUserPassword(passUser);
                UserTypeEntity userTypeEntity = entitymanager.find(UserTypeEntity.class, constTypeId);
                userDetailsEntity.setUserTypeEntity(userTypeEntity);
                entitymanager.persist(userDetailsEntity);
                succesfully.setVisible(true);
                fullnamevalid.setVisible(false);
                isValid.setVisible(false);
                phoneValidate.setVisible(false);
                passwordValidate.setVisible(false);
                usernamevalid.setVisible(false);
            } else {
                if (fullnameUser.equals("")){
                    fullnamevalid.setVisible(true);
                }else{
                    fullnamevalid.setVisible(false);
                }
                if (usernameUser.equals("")){
                    usernamevalid.setVisible(true);
                }else{
                    usernamevalid.setVisible(false);
                }
                if (isValidPassword(passUser)) {
                    passwordValidate.setVisible(false);
                } else {
                    passwordValidate.setVisible(true);
                }
                if (isValidPhone(phoneNumber)) {
                    phoneValidate.setVisible(false);
                } else {
                    phoneValidate.setVisible(true);
                }
                if (isValidEamil(emailUser)) {
                    isValid.setVisible(false);
                } else {
                    isValid.setVisible(true);
                }
            }
        }catch(Exception e){
            System.out.println("Something went wrong");
        }

        entitymanager.getTransaction().commit();

    }

    public void goLogin(ActionEvent event) throws Exception {
        login.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FxmlFiles/LoginForm.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
    }
    public static boolean isValidEamil(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
   public static boolean isValidPassword(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null){
            return false;
        }else{
            return pat.matcher(password).matches();
        }
   }
   public static boolean isValidPhone(String phone){
        String phoneNumber = "\\d{10,15}";
        Pattern pat = Pattern.compile(phoneNumber);
        if (phone == null){
            return false;
        }else{
            return pat.matcher(phone).matches();
        }
   }
}
