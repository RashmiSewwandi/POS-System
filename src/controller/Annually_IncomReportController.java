package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Annually_IncomReportController {
    public AnchorPane AnnuallyContext;

    public void DailyIncomOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/Daily_IncomReport.fxml"));
            pane = fxmlLoader.load();
            AnnuallyContext.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MonthlyIncomOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/Monthly_IncomeReport.fxml"));
            pane = fxmlLoader.load();
            AnnuallyContext.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
