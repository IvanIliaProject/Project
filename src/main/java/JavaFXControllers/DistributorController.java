package JavaFXControllers;

import com.mysql.jdbc.log.Log;
import javafx.beans.binding.ObjectExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.DistributorEntity;
import models.EventEntity;
import models.SoldEntity;
import models.UserDetailsEntity;
import JavaFXControllers.LoginController;
import oracle.jdbc.util.Login;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DistributorController {
    @FXML
    public ComboBox comboBox;
    @FXML
    public ListView OrdersList;
    @FXML
    public TextField TicketPriceBox;
    @FXML
    public TextField TotalBox;
    @FXML
    public javafx.scene.control.Button Button;
    @FXML
    public ComboBox CountTicketsCombo;
    @FXML
    public Label isSuccess;
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
    EntityManager entitymanager = emfactory.createEntityManager( );

    List<EventEntity> eventEntities = entitymanager.createQuery("from EventEntity ").getResultList();
    @FXML
    private void initialize() {
            for (EventEntity event : eventEntities) {
                comboBox.getItems().add(event.getEventName());
            }
            CountTicketsCombo.getItems().addAll(1, 2, 3, 5, 6);
            Date date = new Date();
            LocalTime localTime = LocalTime.now();
            entitymanager.getTransaction().begin();
            List<SoldEntity> solds = new ArrayList<>();
            List<DistributorEntity> distributorEntityList = new ArrayList<>();
            solds = entitymanager.createQuery("from SoldEntity ").getResultList();

           /* for (EventEntity entity : eventEntities) {
                solds = entitymanager.createQuery("from SoldEntity where eventEntity.eventId =: id ")
                        .setParameter("id",entity.getEventId()).getResultList();
            }*/
            for (EventEntity entity : eventEntities) {
                if (date.after(entity.getEventDate()) && localTime.isAfter(LocalTime.parse(entity.getEventTime()))){
                    for (SoldEntity sold : solds) {
                        if (sold.getEventEntity().getEventId() == entity.getEventId()) {
                            if (sold.getDistributorEntity().getDistributorRating() >= 0 && sold.getDistributorEntity().getDistributorRating() <= 5) {
                                if (sold.getSold() < 0.2 * entity.getEventSeats() && sold.getDistributorEntity().getDistributorRating() >= 0.2) {
                                    sold.getDistributorEntity().setDistributorRating(sold.getDistributorEntity().getDistributorRating() - 0.2);
                                    System.out.println("aaa");

                                    entitymanager.persist(sold);

                                }if (sold.getSold() >= 0.8 * entity.getEventSeats() && sold.getDistributorEntity().getDistributorRating() <= 4.8) {
                                    sold.getDistributorEntity().setDistributorRating(sold.getDistributorEntity().getDistributorRating() + 0.2);
                                    System.out.println("bbbb");

                                    entitymanager.persist(sold);
                                }
                            }
                        }
                        // entityManager.persist(distributorEntity);

                    }
                    String name = entity.getEventName();
                    Long id = entity.getEventId();

                    // combobox.getItems().remove(entity.getEventName());
              /*  entityManager.createQuery("delete from SoldEntity s where s.eventEntity.eventId =: id")
                        .setParameter("id",id).executeUpdate();
                entityManager.createQuery("delete from EventEntity e where e.eventName=:name")
                        .setParameter("name",name).executeUpdate();*/
                }
            }
            entitymanager.getTransaction().commit();
        }
    @FXML
    private double getEventData(ActionEvent event){
        String chosenEvent = comboBox.getSelectionModel().getSelectedItem().toString();
        EventEntity eventEntity = new EventEntity();
        for (EventEntity entity : eventEntities) {
            if (entity.getEventName().equals(chosenEvent)){
                eventEntity = entity;
            }
        }
        Double ticketPrice = eventEntity.getEventPrice();
        TicketPriceBox.setText(ticketPrice.toString());
        return ticketPrice;
    }
    @FXML
    public void totalSum(ActionEvent event) {

        Double totalTicketPrice = getEventData(event);

        Integer chosenCount = (Integer) CountTicketsCombo.getSelectionModel().getSelectedItem();

        Double totalSum = totalTicketPrice*chosenCount;

        TotalBox.setText(totalSum.toString());

        OrdersList.getItems().setAll(
                "Event name is: " + comboBox.getSelectionModel().getSelectedItem().toString() + "\n"+
                        "Count of tickets is: " + chosenCount + "\n"+
                        "Total Price is: " + totalSum + "\n" + "\n"+
                        "If you want to proceed ... Press the button!");
    }
    @FXML
    public void ReserveTicket(ActionEvent event) {

        Integer chosenCount = (Integer) CountTicketsCombo.getSelectionModel().getSelectedItem();
        entitymanager.getTransaction( ).begin( );
        String chosenEvent = comboBox.getSelectionModel().getSelectedItem().toString();

        UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
        DistributorEntity distributorEntity = new DistributorEntity();
        userDetailsEntity = LoginController.currentUser;


        EventEntity eventEntity = new EventEntity();
        for (EventEntity entity : eventEntities) {
            if (entity.getEventName().equals(chosenEvent)){
                eventEntity  = entity;
            }
        }
        Long quantity = eventEntity.getEventQuantity();
        double rating = userDetailsEntity.getDistributorEntity().getDistributorRating();
        if (chosenCount<=quantity){
            eventEntity.setEventQuantity(quantity-chosenCount);
            /*userDetailsEntity.getDistributorEntity().setDistributorSold(
                    (userDetailsEntity.getDistributorEntity().getDistributorSold() + chosenCount));*/
            entitymanager.merge(userDetailsEntity);

            isSuccess.setVisible(true);
            isSuccess.setText("Successful");
            entitymanager.persist(eventEntity);
            entitymanager.getTransaction().commit();
        }else{
            isSuccess.setVisible(true);
            isSuccess.setText("No more tickets");
        }
    }

}
