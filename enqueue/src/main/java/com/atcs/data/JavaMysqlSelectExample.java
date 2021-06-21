package com.atcs.data;

import java.sql.*;
import com.mysql.jdbc.Driver;

public class JavaMysqlSelectExample {
    public static void main(String[] args)
    {
       try {
           Class.forName("com.mysql.jdbc.Driver");
           // Setup the connection with the DB
           Connection connect = DriverManager
                   .getConnection("jdbc:mysql://localhost/feedback?"
                           + "user=sqluser&password=sqluserpw");


       } catch (SQLException ex) {

            ex.printStackTrace();
        } catch(Exception e) {
           e.printStackTrace();
       }

    }
}
