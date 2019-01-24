/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javax.swing.JOptionPane;
import superstore.CONNECT;
import superstore.ListNode;

/**
 *
 * @author madha
 */
public class xychart {
    
    private String x;
    private String l;
    private String q;
    private String xL;
    private String yL;
    private String temp;
    private Connection conn;
    private String[] mod = new String[2];
    private ListNode head = new ListNode();
    private ListNode t = new ListNode();
    
    public xychart(String query, String st, String xLabel, String yLabel){
        l = st;
        q = query;
        xL = xLabel;
        yL = yLabel;
    }
    private ObservableList<XYChart.Series<String , Double>> data = FXCollections.observableArrayList();
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    
    private ObservableList<XYChart.Series<String, Double>> xyData(String q){
        try{
            conn = CONNECT.ConnecterDb();
            ResultSet rs = conn.createStatement().executeQuery("select distinct "+l+" from superstore");
            rs.next();
            mod = q.split("where");
            temp = mod[0]+ "where "+l+" = '"+rs.getString(1)+"' "+mod[1];
            head = new ListNode();
            head.LNode(temp, rs.getString(1));
            data.addAll(head.getSeries());
            head.next = t;
            while(rs.next()){
                mod = q.split("where");
                temp = mod[0]+ "where "+l+" = '"+rs.getString(1)+"' "+mod[1];
                t.LNode(temp, rs.getString(1));
                data.addAll(t.getSeries());
                t.next = new ListNode();
                t = t.next;
            }
            conn.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        } 
        return data;
    }
    public ListNode getNode(){
        return head;
    }
    public StackedBarChart stackedbarchart(){
        StackedBarChart sbc = new StackedBarChart(xAxis,yAxis,xyData(q));
        xAxis.setLabel(xL);
        yAxis.setLabel(yL);
        return sbc;
    }
    public BarChart barchart(){;
        BarChart bc = new BarChart(xAxis,yAxis,xyData(q));
        xAxis.setLabel(xL);
        yAxis.setLabel(yL);
        return bc;
    }
    public LineChart linechart(){
        LineChart lc = new LineChart(xAxis,yAxis,xyData(q));
        xAxis.setLabel(xL);
        yAxis.setLabel(yL);
        return lc;
    }
    public AreaChart areachart(){
        AreaChart ac = new AreaChart(xAxis,yAxis,xyData(q));
        xAxis.setLabel(xL);
        yAxis.setLabel(yL);
        return ac;
    }
    public BubbleChart bubblechart(){
        BubbleChart bbc = new BubbleChart(xAxis,yAxis,xyData(q));
        xAxis.setLabel(xL);
        yAxis.setLabel(yL);    
        return bbc;
    }
    public ScatterChart scatterchart(){
        ScatterChart scc = new ScatterChart(xAxis,yAxis,xyData(q));
        xAxis.setLabel(xL);
        yAxis.setLabel(yL);
        return scc;
    }
}