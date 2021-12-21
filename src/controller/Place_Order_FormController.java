package controller;

import bo.BoFactory;
import bo.custom.PurchaseOrderBO;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.ItemDetailsDTO;
import dto.OrderDTO;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.OrderDAOImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import views.tm.CartTM;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Place_Order_FormController {
    public Label lblOrderId;
    public Label lblDate;
    public Label lblTime;
    public ComboBox<String> cmbItemIds;
    public ComboBox <String>cmbCustomerIds;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtProvince;
    public TextField txtDescription;
    public TextField txtqtyOnHand;
    public TextField txtUnitPrice;
    public TextField txtPackSize;
    public TableView <CartTM>tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQTY;
    public TableColumn colTotal;
    public TextField txtQty;
    public Label txtTtl;
    public Label txtDiscount;

    //table 4 rata commen,uniqu methoda access karanna puluwab
    private final PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PURCHASE_ORDER);


    int cartSelectedRowForRemove = -1;

    private void loadDateAndTime() {
        //load date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        //load time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        setOrderid();

        //cmb -> dataset ,name,title,address
        cmbCustomerIds.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        // -> dataset description,packsize.....
        cmbItemIds.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setItemData(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        //cmbbox ids
        loadAllCustomerIds();
        loadAllItemCodes();

        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;

        });


    }


    private void loadAllCustomerIds() {
        try {
           // PurchaseOrderBO purchaseOrderBO = new PurchaseOrderBOImpl();
            ArrayList<CustomerDTO> all = purchaseOrderBO.getAllCustomers();
            for (CustomerDTO customerDTO : all) {
                cmbCustomerIds.getItems().add(customerDTO.getId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void loadAllItemCodes() {
        try {
         //   PurchaseOrderBO purchaseOrderBO = new PurchaseOrderBOImpl();
            ArrayList<ItemDTO> all = purchaseOrderBO.getAllItems();
            for (ItemDTO dto : all) {
                cmbItemIds.getItems().add(dto.getCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void setItemData(String code) throws SQLException, ClassNotFoundException {
        ItemDTO i1 = new ItemDAOImpl().getItem(code);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {

            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPackSize());
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrize()));
            txtqtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
        }
    }


    private void setCustomerData(String id) throws SQLException, ClassNotFoundException {
        CustomerDTO c1 = new CustomerDAOImpl().getCustomer(id);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtName.setText(c1.getName());
            txtAddress.setText(c1.getAddress());
            txtProvince.setText(c1.getProvince());
        }
    }




    public void clearOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        } else {
            obList.remove(cartSelectedRowForRemove);
            tblCart.refresh();
        }
    }


    private void setOrderid() {
        try {
            lblOrderId.setText(new OrderDAOImpl().getOrderid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    ObservableList<CartTM> obList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {

        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtqtyOnHand.getText());
        int qtyForCustomer = Integer.parseInt(txtQty.getText());
        double total = qtyForCustomer * unitPrice;

        if (qtyOnHand < qtyForCustomer) {
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            return;
        }

        CartTM tm = new CartTM(
                cmbItemIds.getValue(),
                description,
                unitPrice,
                qtyForCustomer,
                total
        );


        int rowNumber = isExists(tm);

        if (rowNumber == -1) {// new Add item row
            obList.add(tm);
        } else {
            // update
            CartTM temp = obList.get(rowNumber);
            CartTM newTm = new CartTM(
                    temp.getCode(),
                    temp.getDescription(),
                    unitPrice,
                    temp.getQty()+qtyForCustomer,
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }

        tblCart.setItems(obList);
        calculate();
        calculateDiscount();



    }

    private int isExists(CartTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getCode().equals(obList.get(i).getCode())) {
                return i;
            }
        }
        return -1;
    }

    void calculate() {
        double total = 0;
        for (CartTM tm : obList
        ) {
            total += tm.getTotal();
        }
        txtTtl.setText(total + " /=");
    }

    void calculateDiscount(){
        double discount =0;
        for (CartTM tm : obList
        ) {
            discount = tm.getTotal()-(tm.getTotal()/100*3);
        }
        txtDiscount.setText(discount + " /=");
    }


    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ArrayList<ItemDetailsDTO> items= new ArrayList<>();
        double total=0;
        for (CartTM tempTm:obList
        ) {
            total+=tempTm.getTotal();
            items.add(new ItemDetailsDTO(tempTm.getCode(),lblOrderId.getText(),tempTm.getQty(),
                    tempTm.getUnitPrice()));
        }

        OrderDTO order= new OrderDTO(
                lblOrderId.getText(),
                cmbCustomerIds.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                items

        );

        if (purchaseOrderBO.placeOrder(order)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderid();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

}
