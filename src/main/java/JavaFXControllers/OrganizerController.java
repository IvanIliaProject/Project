package JavaFXControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.EventEntity;
import models.OrganizerEntity;
import models.SoldEntity;
import models.UserDetailsEntity;
import oracle.sql.DATE;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;

public class OrganizerController {
    @FXML
    public JFXTextField name;

    @FXML
    public JFXTextField price;

    @FXML
    public JFXTextField date;

    @FXML
    public JFXTextField quantity;

    @FXML
    public JFXButton addevent;

    @FXML
    public JFXTimePicker timePicker;

    @FXML
    public Label date1;

    @FXML
    public JFXDatePicker datePicker;

    @FXML
    public Label time;

    @FXML
    public JFXTextField place;

    @FXML
    public void addEvent() throws ParseException {
        LocalDate chosenDate = datePicker.getValue();
        String chosenTime= timePicker.getValue().toString();
        java.sql.Date date = Date.valueOf(chosenDate);
        String eventName = name.getText();
        Double eventPrice = Double.parseDouble(price.getText());
        Long eventQuantity = Long.parseLong(quantity.getText());
        String eventPlace = place.getText();

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Test" );
        EntityManager entityManager = emfactory.createEntityManager( );
        entityManager.getTransaction().begin();

        List<EventEntity> allEvents  = new ArrayList<>();
        allEvents = (List<EventEntity>) entityManager
                .createQuery("from EventEntity ").getResultList();

        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName(eventName);
        eventEntity.setEventPrice(eventPrice);
        eventEntity.setEventQuantity(eventQuantity);
        eventEntity.setEventPlace(eventPlace);
        eventEntity.setEventSeats(eventQuantity);

        //eventEntity.setEventTime(chosenTime);
       // eventEntity.setEventDate(date);
        OrganizerEntity organizerEntity = entityManager.find(OrganizerEntity.class,LoginController.currentUser.getUserId());
        eventEntity.setOrganizerEntity(organizerEntity);

        for (UserDetailsEntity allUser : LoginController.allUsers) {
            if (allUser.getUserTypeEntity().getUserTypeId() == 3) {
                SoldEntity soldEntity = new SoldEntity();
                soldEntity.setEventEntity(eventEntity);
                soldEntity.setDistributorEntity(allUser.getDistributorEntity());
                soldEntity.setSold(0);
                entityManager.persist(soldEntity);
            }
        }

        entityManager.persist(eventEntity);
        entityManager.getTransaction().commit();
    }
}