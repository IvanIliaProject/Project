package JavaFXControllers;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.DistributorEntity;
import models.EventEntity;
import models.SoldEntity;
import models.UserDetailsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DistributorController implements Runnable {

    @FXML
    public ListView OrdersList;

    @FXML
    public TextField TicketPriceBox;

    @FXML
    public TextField TotalBox;

    @FXML
    public JFXButton Button;

    @FXML
    public ComboBox combobox;

    @FXML
    public ComboBox CountTicketCombo;

    @FXML
    public Label successLabel;

    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Test");
    static EntityManager entityManager = emfactory.createEntityManager();
    static public List<SoldEntity> events = entityManager.createQuery("from SoldEntity ").getResultList();
    static public List<EventEntity> events1 = entityManager.createQuery("from EventEntity ").getResultList();
    static public SoldEntity soldEntity;
    @FXML
    public void initialize() {

        for (SoldEntity soldEntity : events) {
            if (LoginController.currentUser.getUserId() == soldEntity.getDistributorEntity().getDistributorId()) {
                combobox.getItems().add(soldEntity.getEventEntity().getEventName());
            }
        }
        CountTicketCombo.getItems().addAll(1, 2, 3, 5, 6);
        Date date = new Date();
        LocalTime localTime = LocalTime.now();
        ModuleLayer.Controller controller;

        entityManager.getTransaction().begin();
        List<SoldEntity> solds = new ArrayList<>();
        for (SoldEntity entity : events) {
            solds = entityManager.createQuery("from SoldEntity where eventEntity.eventId =: id ")
                    .setParameter("id",entity.getEventEntity().getEventId()).getResultList();
        }
        for (SoldEntity entity : events) {
            if (date.after(entity.getEventEntity().getEventDate()) && localTime.isAfter(LocalTime.parse(entity.getEventEntity().getEventTime()))){
                for (SoldEntity sold : solds) {

                    if(sold.getDistributorEntity().getDistributorRating() >= 0 && sold.getDistributorEntity().getDistributorRating() <=5) {
                        if (sold.getSold() < 0.2 * entity.getEventEntity().getEventSeats() && sold.getDistributorEntity().getDistributorRating()>=0.2) {
                            sold.getDistributorEntity().setDistributorRating(sold.getDistributorEntity().getDistributorRating() - 0.2);
                            System.out.println("aaa");

                            entityManager.persist(sold);
                        }
                        if (sold.getSold() > 0.8 * entity.getEventEntity().getEventSeats() && sold.getDistributorEntity().getDistributorRating()<=4.8) {
                            sold.getDistributorEntity().setDistributorRating(sold.getDistributorEntity().getDistributorRating() + 0.2);
                            System.out.println("bbbb");

                            entityManager.persist(sold);
                        }
                    }

                    // entityManager.persist(distributorEntity);

                }
                String name = entity.getEventEntity().getEventName();
                Long id = entity.getEventEntity().getEventId();

                // combobox.getItems().remove(entity.getEventName());
              /*  entityManager.createQuery("delete from SoldEntity s where s.eventEntity.eventId =: id")
                        .setParameter("id",id).executeUpdate();
                entityManager.createQuery("delete from EventEntity e where e.eventName=:name")
                        .setParameter("name",name).executeUpdate();*/
            }
        }
        entityManager.getTransaction().commit();
        initThread();
    }
    @FXML
    private void initThread(){
        Thread thread = new Thread(() -> {
            Runnable updater = () -> newEventNotification();
            Runnable updater1 = () -> unsoldTickets();
            EventEntity event = events1.get(events.size()-1);
            while (true) {
                try {
                    Thread.sleep(7200000);
                } catch (InterruptedException ex) {
                }

                EventEntity event1 = events1.get(events.size()-1);

                if (!event.equals(event1)) {
                    Platform.runLater(updater);
                }
                Platform.runLater(updater1);
            }

        });

        thread.setDaemon(true);
        thread.start();
    }


    @Override
    public void run() {

    }
    @FXML
    public void newEventNotification(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("New Event!");
        alert.showAndWait();
    }

    @FXML
    public void unsoldTickets()
    {
        for (EventEntity eventEntity : events1 ) {
            if(eventEntity.getEventDate().toLocalDate().isAfter(eventEntity.getEventDate().toLocalDate().minusDays(2L)) && soldEntity.getQuantity() > 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("There are unsold tickets!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void reserveTicket() {
        String chosenEvent = combobox.getSelectionModel().getSelectedItem().toString();
        EventEntity eventEntity = new EventEntity();
        entityManager.getTransaction().begin();

        for (EventEntity event1 : events1) {
            if (event1.getEventName().equals(chosenEvent))
                eventEntity = event1;
        }


        Integer chosenCount = (Integer) CountTicketCombo.getSelectionModel().getSelectedItem();


        UserDetailsEntity userDetailsEntity = LoginController.currentUser;
        DistributorEntity distributorEntity = LoginController.currentUser.distributorEntity;
        soldEntity = entityManager.createQuery("from SoldEntity where distributorEntity.distributorId =: distributorId and eventEntity.eventId =: eventId",SoldEntity.class)
                .setParameter("distributorId",LoginController.currentUser.getUserId()).setParameter("eventId",eventEntity.getEventId()).getSingleResult();


        if (chosenCount<=soldEntity.getQuantity()){
            soldEntity.setQuantity(soldEntity.getQuantity()-chosenCount);
            soldEntity.setSold(chosenCount+soldEntity.getSold());
            entityManager.merge(soldEntity);

            /*isSuccess.setVisible(true);
            isSuccess.setText("Successful");*/

            entityManager.persist(soldEntity);

        }else {
           /* isSuccess.setVisible(true);
            isSuccess.setText("No more tickets");*/
        }
        entityManager.getTransaction().commit();
    }

    @FXML
    public EventEntity getEventData() {
        String chosenEvent = combobox.getSelectionModel().getSelectedItem().toString();
        EventEntity eventEntity = new EventEntity();
        for (EventEntity event1 : events1) {
            if (event1.getEventName().equals(chosenEvent))
                eventEntity = event1;
        }
        Double eventPrice = eventEntity.getEventPrice();
        TicketPriceBox.setText(eventPrice.toString());
        return eventEntity;
    }

    @FXML
    public void setTotalBox() {
        String ticketCount1 = CountTicketCombo.getSelectionModel().getSelectedItem().toString();
        String ticketPrice1 = TicketPriceBox.getText();
        String eventName = combobox.getSelectionModel().getSelectedItem().toString();

        Integer ticketCount = Integer.parseInt(ticketCount1);
        Double ticketPrice = Double.parseDouble(ticketPrice1);

        Double totalPrice = (ticketCount * ticketPrice);
        TotalBox.setText(String.format("%.2f",totalPrice));

        OrdersList.getItems().setAll(
                "Event name is: " + eventName + "\n"+
                        "Count of tickets is: " + ticketCount1 + "\n"+
                        "Total Price is: " + totalPrice + "\n" + "\n"+
                        "If you want to proceed ... Press the button!");
    }
}