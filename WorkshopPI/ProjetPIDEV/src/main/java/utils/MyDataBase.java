package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {

    final String URL="jdbc:mysql://localhost:3306/vitaplat";
    final String USERNAME="root";
    final String PASSWORD="";
   static MyDataBase instance;
    Connection connection;
    private MyDataBase(){
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public  static MyDataBase getInstance(){
        if(instance==null){
            instance= new MyDataBase();
        }
        return instance;

    }

    public Connection getConnection() {
        return connection;
    }
}
