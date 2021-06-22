package com.atcs.data;

import java.sql.*;
import com.mysql.jdbc.Driver;


public class JavaMysqlSelectExample {
    public static void main(String[] args)
    {
       try {
           //Class.forName("software.aws.rds.jdbc.Driver");
           Class.forName("com.mysql.jdbc.Driver");
           // Setup the connection with the DB
           Connection connect = DriverManager
                   .getConnection("jdbc:mysql://airtrafficcontrol.cpm6gbpcuwyo.us-east-1.rds.amazonaws.com:3306/airTrafficDB?user=masterUserName&password=masterAtcs!");
            Statement st = connect.createStatement();
            String sql = "select * from air_traffic_queue";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                int plane_id = rs.getInt("plane_id");
                System.out.println(plane_id);
            }


       } catch (SQLException ex) {

            ex.printStackTrace();
        } catch(Exception e) {
           e.printStackTrace();
       }

    }
}
