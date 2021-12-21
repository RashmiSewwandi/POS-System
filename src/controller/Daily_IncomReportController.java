package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daily_IncomReportController {
    public AnchorPane DailyContext;
    public Label lblDate;

    public void initialize() {
        loadDate();
    }

    private void loadDate() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

    }

    public void DailyIncomOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/Daily_IncomReport.fxml"));
            pane = fxmlLoader.load();
            DailyContext.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MonthlyIncomOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/Monthly_IncomeReport.fxml"));
            pane = fxmlLoader.load();
            DailyContext.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AnnualyOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/Annually_IncomReport.fxml"));
            pane = fxmlLoader.load();
            DailyContext.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
