/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstore;

import Charts.xychart;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author madha
 */
public class Superstore extends Application {
    
    BorderPane root = new BorderPane();
    private String x;
    
    @Override
    public void start(Stage primaryStage) {
        
        xychart chart = new xychart("select year(order_date), sales from superstore where group by year(Order_Date)","State","Year","Sales");
        StackedBarChart sbc = chart.stackedbarchart();
        root.setCenter(sbc);
        ListNode head = chart.getNode();
        while(head != null){
            for(XYChart.Data<String,Double> data : head.getSeries().getData()){
                javafx.scene.Node n = data.getNode();
                n.setOnMouseClicked(e->{
                    x = data.getXValue();
                    
                    xychart chart1 = new xychart("select State, sales from superstore where and year(order_date) = '"+x+"' group by State","Country","State","Sales");
                    BarChart bc = chart1.barchart();
                    root.setCenter(bc);
                });
                n.setOnMouseEntered(e->{
                    primaryStage.getScene().setCursor(Cursor.HAND);
                });
                n.setOnMouseExited(e->{
                    primaryStage.getScene().setCursor(Cursor.DEFAULT);
                });
            }
            head = head.next;
        }
        Scene scene = new Scene(root, 1280, 720);
        
        primaryStage.setTitle("Superstore");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
