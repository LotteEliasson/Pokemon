package com.example.pokemon.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static Connection connection = null;

    //giver connection til databasse
    public static Connection getConnection(String db_url, String uid, String pwd){
        //Connection initialiseret?
        if(connection == null){

            //initialize connection
            try{
                connection = DriverManager.getConnection(db_url, uid, pwd);

            }catch (SQLException e){
                System.out.println("Could not connect to database");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
