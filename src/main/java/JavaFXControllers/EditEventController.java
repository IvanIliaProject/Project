package JavaFXControllers;

import models.EventEntity;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EditEventController {
    @FXML
    public JFXTextField name;

    @FXML
    public JFXTextField price;

    @FXML
    public JFXTextField quantity;

    @FXML
    public Label date1;

    @FXML
    public JFXButton addevent;

    @FXML
    public JFXDatePicker datePicker;

    @FXML
    public JFXTimePicker timePicker;

    @FXML
    public Label time;

    @FXML
    public JFXTextField place;

    @FXML
    public JFXButton editEvent;

    @FXML
    public JFXComboBox combobox;

    @FXML
    public JFXButton goback;

    private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Test");
    private EntityManager entityManager = emfactory.createEntityManager();
    private List<EventEntity> events = entityManager.createQuery("from EventEntity ").getResultList();
    static  EventEntity eventEntity;
    @FXML
    public void initialize()
    {


        for (EventEntity event : events) {
            combobox.getItems().add(event.getEventName());
        }
    }

    @FXML
    public void getData()
    {
        String chosenEvent = combobox.getSelectionModel().getSelectedItem().toString();
        entityManager.getTransaction().begin();

        for (EventEntity event1 : events) {
            if (event1.getEventName().equals(chosenEvent))
                eventEntity = event1;
        }

        name.setText(eventEntity.getEventName());
        place.setText(eventEntity.getEventPlace());
        quantity.setText(eventEntity.getEventQuantity().toString());
        price.setText(eventEntity.getEventPrice().toString());
        datePicker.setValue(LocalDate.parse(eventEntity.getEventDate().toString()));
        timePicker.setValue(LocalTime.parse(eventEntity.getEventTime()));

        entityManager.getTransaction().commit();

    }

    public void goBack() throws IOException {

        goback.getScene().getWindow().hide();
        Stage Login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FxmlFiles/OrganizerForm.fxml"));
        Scene scene = new Scene(root);
        Login.setScene(scene);
        Login.show();

    }

    public void editEvent()
    {
        entityManager.getTransaction().begin();

        eventEntity.setEventName(name.getText());
        eventEntity.setEventPlace(place.getText());
        eventEntity.setEventPrice(Double.parseDouble(price.getText()));
        eventEntity.setEventQuantity(Long.parseLong(quantity.getText()));
        eventEntity.setEventSeats(Long.parseLong(quantity.getText()));
        eventEntity.setEventDate(Date.valueOf(datePicker.getValue().toString()));
        eventEntity.setEventTime(timePicker.getValue().toString());

        entityManager.persist(eventEntity);
        entityManager.getTransaction().commit();
    }
}