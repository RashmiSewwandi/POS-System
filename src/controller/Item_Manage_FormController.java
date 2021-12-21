package controller;

import bo.BoFactory;
import bo.custom.ItemBO;
import dto.ItemDTO;
import com.jfoenix.controls.JFXButton;
import dao.custom.impl.ItemDAOImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import validationutil.ValidationUtil;
import views.tm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class Item_Manage_FormController implements Initializable {

    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtPackSize;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TableView<ItemTM> tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public JFXButton btnSave;
    public AnchorPane ItemManageContext;
    public TextField txtSearch;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    public void initialize(URL location, ResourceBundle resources){

            storeValidations();
            btnSave.setDisable(true);
            tblItem.setStyle("-fx-background-color: blue;");

            colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrize"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        try {
          //  addItemToTable(new ItemDAOImpl().getAllItems());
            LoadAllItems();
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
            ItemManageContext.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //saveItem
    public void Save_Item_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = new ItemDTO(txtItemCode.getText(), txtDescription.getText(), txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()));
        if(itemBO.addItems(itemDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            LoadAllItems();
            clearField();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }


    //updateItem
    public void Update_Item_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = new ItemDTO(txtItemCode.getText(), txtDescription.getText(), txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()));
        if(itemBO.updateItem(itemDTO)){
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            LoadAllItems();
            clearField();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }


    //deleteItem
    public void Delete_Item_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemTM item = tblItem.getSelectionModel().getSelectedItem();
        String code =item.getCode();
        if (itemBO.deleteItem(code)){
            new Alert(Alert.AlertType.INFORMATION,"Delete Complete").show();
            LoadAllItems();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
        clearField();
    }


    //getItems
    private void LoadAllItems() throws SQLException, ClassNotFoundException {
        tblItem.getItems().clear();
        try {
            ArrayList<ItemDTO> allItems = itemBO.getAllItem();
            for (ItemDTO item : allItems){
                tblItem.getItems().add(new ItemTM(item.getCode(), item.getDescription(), item.getPackSize(), item.getUnitPrize(), item.getQtyOnHand()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void clearField(){
        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

    public void table_Click_OnAction(MouseEvent mouseEvent) {
        ItemTM item = null;
        item = tblItem.getSelectionModel().getSelectedItem();
        txtItemCode.setText(item.getCode());
        txtDescription.setText(item.getDescription());
        txtPackSize.setText(item.getPackSize());
        txtUnitPrice.setText(String.valueOf(item.getUnitPrize()));
        txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));

    }
    

    public void Incom_ReportForm(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../views/Daily_IncomReport.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void move_ToDescription(ActionEvent actionEvent) {
        txtDescription.requestFocus();
    }

    public void move_ToPackSize(ActionEvent actionEvent) {
        txtPackSize.requestFocus();
    }

    public void move_ToUnitPrize(ActionEvent actionEvent) {
        txtUnitPrice.requestFocus();
    }

    public void move_ToQty(ActionEvent actionEvent) {
        txtQtyOnHand.requestFocus();
    }

    public void Search_Item(ActionEvent actionEvent) {
        search(txtSearch.getText());
    }

    private void search(String value) {
        try {
            ItemDAOImpl itemDAO = new ItemDAOImpl();
            List<ItemDTO> items = itemDAO.SearchItem(value);
            ObservableList<ItemTM> tableData = FXCollections.observableArrayList();
            for (ItemDTO item : items) {
                tableData.add(new ItemTM(
                        item.getCode(),
                        item.getDescription(),
                        item.getPackSize(),
                        item.getUnitPrize(),
                        item.getQtyOnHand()
                ));
            }
            tblItem.getItems().setAll(tableData);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern code = Pattern.compile("^[I|i][-][0-9]{3,}$");
    Pattern descriptoin = Pattern.compile("^[A-Za-z0-9 ]+$");
    Pattern packSize = Pattern.compile("^[A-Za-z0-9 ]+$");
    Pattern unitPrize = Pattern.compile("^[0-9]+[.]?[0-9]*$");
    Pattern QtyOnHand = Pattern.compile("^\\d+$");


    private void storeValidations() {
        map.put(txtItemCode, code);
        map.put(txtDescription, descriptoin);
        map.put(txtPackSize, packSize);
        map.put(txtUnitPrice, unitPrize);
        map.put(txtQtyOnHand, QtyOnHand);

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
