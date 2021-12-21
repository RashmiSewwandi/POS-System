package controller;

import animatefx.animation.FadeInDownBig;
import animatefx.animation.SlideInRight;
import animatefx.animation.Tada;
import animatefx.animation.Wobble;
import bo.BoFactory;
import bo.custom.CustomerBO;
import dto.CustomerDTO;
import com.jfoenix.controls.JFXButton;
import dao.custom.impl.CustomerDAOImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import validationutil.ValidationUtil;
import views.tm.CustomerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;



public class Customer_Manage_FormController implements Initializable {

    public TextField txtName;
    public TextField txtTitle;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtPostalCode;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCID;
    public TableColumn colName;
    public TableColumn colTitle;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn ColPostalCode;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public TextField txtSearch;
    public TextField txtCID;
    public AnchorPane ManageCustomerContext;


    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);

    public void initialize(URL location, ResourceBundle resources) {

        storeValidations();
        btnSave.setDisable(true);

        tblCustomer.setStyle("-fx-background-color: blue;");

        colCID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        ColPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        try {
            LoadAllCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });
    }



    public void login_Form_OnAction(ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/MainForm.fxml"));
            pane = fxmlLoader.load();
            ManageCustomerContext.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void Place_Order_onAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../views/Place_Order_Form.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        new SlideInRight(load).play();
    }


    //save
    public void Customer_Save_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO id = new CustomerDTO(txtCID.getText(), txtTitle.getText(), txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalCode.getText());
        if (customerBO.addCustomer(id)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            LoadAllCustomers();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        clearField();
    }



    //delete
    public void Customer_Delete_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerTM customer = tblCustomer.getSelectionModel().getSelectedItem();
        String id = customer.getId();
        if (customerBO.deleteCustomer(id)) {
            new Alert(Alert.AlertType.INFORMATION, "Delete Complete").show();
            LoadAllCustomers();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearField();
    }


    //update
    public void Customer_Update_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = new CustomerDTO(txtCID.getText(), txtTitle.getText(), txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalCode.getText());
        if (customerBO.updateCustomer(customerDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            LoadAllCustomers();
            clearField();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }



    //getall
    private void LoadAllCustomers() throws SQLException, ClassNotFoundException {
        tblCustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomer();
            for (CustomerDTO customer : allCustomers){
                tblCustomer.getItems().add(new CustomerTM(customer.getId(), customer.getTitle(), customer.getName(), customer.getAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void clearField() {
        txtCID.clear();
        txtTitle.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
    }


   private void search(String value) {
        try {
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            List<CustomerDTO> customers = customerDAO.SearchName(value);
            ObservableList<CustomerTM> tableData = FXCollections.observableArrayList();
            for (CustomerDTO customer : customers) {
                tableData.add(new CustomerTM(
                        customer.getId(),
                        customer.getTitle(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getCity(),
                        customer.getProvince(),
                        customer.getPostalCode()
                ));
            }
            tblCustomer.getItems().setAll(tableData);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void Search_Customer(ActionEvent actionEvent) {
        search(txtSearch.getText());
    }

    public void table_Click_OnAction(MouseEvent mouseEvent) {
        CustomerTM customer = null;
        customer = tblCustomer.getSelectionModel().getSelectedItem();
        txtCID.setText(customer.getId());
        txtTitle.setText(customer.getTitle());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtCity.setText(customer.getCity());
        txtProvince.setText(customer.getProvince());
        txtPostalCode.setText(customer.getPostalCode());
    }

    public void move_to_Title(ActionEvent actionEvent) {
        txtTitle.requestFocus();
    }

    public void move_To_Name(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void move_To_Address(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void move_To_City(ActionEvent actionEvent) {
        txtCity.requestFocus();
    }

    public void move_To_Province(ActionEvent actionEvent) {
        txtProvince.requestFocus();
    }

    public void move_To_PostalCode(ActionEvent actionEvent) {
        txtPostalCode.requestFocus();
    }

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern nicPattern = Pattern.compile("^([0-9]{9}[|v|V]|[0-9]{12})$");
    Pattern namePattern = Pattern.compile("^[A-z. ]{3,20}$");
    Pattern TitlePattern = Pattern.compile("^[A-z. ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ,]{6,30}[.]?$");
    Pattern cityPattern = Pattern.compile("^[A-z. ]{3,20}$");
    Pattern provincePattern = Pattern.compile("^[A-z. ]{3,20}$");
    Pattern postalcodePattern = Pattern.compile("^.{3,}$");

    private void storeValidations() {
        map.put(txtCID, nicPattern);
        map.put(txtName, namePattern);
        map.put(txtTitle, TitlePattern);
        map.put(txtAddress, addressPattern);
        map.put(txtCity, cityPattern);
        map.put(txtProvince, provincePattern);
        map.put(txtPostalCode, postalcodePattern);
    }


    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate_Text(map, btnSave);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.CONFIRMATION, "Success ..").showAndWait();
            }

        }
    }


}
