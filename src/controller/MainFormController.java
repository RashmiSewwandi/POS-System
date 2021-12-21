package controller;

import animatefx.animation.FadeInDownBig;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFormController {


    public JFXButton btnAdmin;
    public JFXButton btnCashier;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public ImageView imgCustomer;
    public AnchorPane LoginFormContext;


    public void Admin_Form_OnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("rashu") & txtPassword.getText().equals("12345")){
            URL resource = getClass().getResource("../views/Item_Manage_Form.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) LoginFormContext.getScene().getWindow();
            window.setScene(new Scene(load));
            new Alert(Alert.AlertType.CONFIRMATION, "Save Password..").show();
            new FadeInDownBig(load).play();

        }else{
            new Alert(Alert.AlertType.WARNING, "Wrong UserName Or Password..").show();
        }
    }

    public void Casheir_Form_OnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("rashu") & txtPassword.getText().equals("12345")){
            URL resource = getClass().getResource("../views/Customer_Manage_Form.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) LoginFormContext.getScene().getWindow();
            window.setScene(new Scene(load));
            new Alert(Alert.AlertType.CONFIRMATION, "Save Password..").show();
            new FadeInDownBig(load).play();

        }else{
            new Alert(Alert.AlertType.WARNING, "Wrong UserName Or Password..").show();
        }
    }

    public void move_ToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}
