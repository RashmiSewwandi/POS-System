package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Monthly_IncomeReportController {
    public AnchorPane MonthlyContext;


    public void Anually_IncomOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/Annually_IncomReport.fxml"));
            pane = fxmlLoader.load();
            MonthlyContext.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
