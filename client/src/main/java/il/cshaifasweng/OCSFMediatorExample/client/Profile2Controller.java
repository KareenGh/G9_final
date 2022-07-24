package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Profile2Controller {

    @FXML private static List<Order> order_list;

    public static List<Order> getOrder_list() { return order_list; }

    public static void setOrder_list(List<Order> order_list) { Profile2Controller.order_list = order_list; }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private TableView<Order> order_table;

    @FXML
    private TableColumn<Order, String> date;

    @FXML
    private TableColumn<Order, String> items;

    @FXML
    private TableColumn<Order, Long> orderId;

    @FXML
    private TableColumn<Order, String> recieveDate;

    @FXML
    private TableColumn<Order, String> status;

    @FXML
    private TableColumn<Order, String> totalPrice;

    @FXML
    void back(ActionEvent event) {
        try {
            App.setRoot("catalog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Registration username;

    public Registration getUsername() { return username; }

    public void setUsername(Registration username) { this.username = username; }


    public ObservableList<Order> getList2() {
        ObservableList<Order> List = FXCollections.observableArrayList();
        for (int i = 0; i < order_list.size(); i++) {

            String details1 = "";

            for (int j=0;j<order_list.get(i).getCart().getItems().size();j++) {
                if (j == order_list.get(i).getCart().getItems().size()-1) {
                    details1 = details1 + order_list.get(i).getCart().getAmount().get(j) + " x " + order_list.get(i).getCart().getItems().get(j).getName();
                } else
                    details1 = details1 + order_list.get(i).getCart().getAmount().get(j) + "x" + order_list.get(i).getCart().getItems().get(j).getName() + " + ";
            }
            System.out.println(details1);
            if (order_list.get(i).getClientname().equals(username.getUserName())) {
                List.add(new Order(order_list.get(i).getStatus(),order_list.get(i).getId(),order_list.get(i).getRecievedate(),order_list.get(i).getTotalprice(),order_list.get(i).getDate(),details1));
            }
        }
        return List;
    }

    @FXML
    void initialize() {
        assert back_button != null : "fx:id=\"back_button\" was not injected: check your FXML file 'Profile2.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'Profile2.fxml'.";
        assert items != null : "fx:id=\"items\" was not injected: check your FXML file 'Profile2.fxml'.";
        assert orderId != null : "fx:id=\"orderId\" was not injected: check your FXML file 'Profile2.fxml'.";
        assert recieveDate != null : "fx:id=\"recieveDate\" was not injected: check your FXML file 'Profile2.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'Profile2.fxml'.";
        assert totalPrice != null : "fx:id=\"totalPrice\" was not injected: check your FXML file 'Profile2.fxml'.";

        setUsername(App.getUser1());
        setOrder_list(App.getOrderList());
        orderId.setCellValueFactory(new PropertyValueFactory<Order, Long>("id"));
        status.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
        recieveDate.setCellValueFactory(new PropertyValueFactory<Order, String>("recievedate"));
        totalPrice.setCellValueFactory(new PropertyValueFactory<Order, String>("totalprice"));
        date.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        items.setCellValueFactory(new PropertyValueFactory<Order, String>("items1"));
        order_table.setItems(getList2());
        order_table.getColumns().addAll();
    }

}
