/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstore;

import java.sql.Connection;
import java.sql.ResultSet;
import javafx.scene.chart.XYChart;
import javax.swing.JOptionPane;

/**
 *
 * @author madha
 */
public class ListNode{

    private Connection conn;
    private XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
    public ListNode next;
    
    public ListNode(){
        
    }
    public void LNode(String query, String l){
        try{
            conn = CONNECT.ConnecterDb();
            ResultSet rs = conn.createStatement().executeQuery(query);
            while(rs.next()){
                series.getData().add(new XYChart.Data(rs.getString(1), rs.getDouble(2)));
            }
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        series.setName(l);
    }
    public XYChart.Series<String, Double> getSeries(){
        return series;
    }
}
