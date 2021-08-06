package back_end.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

    public static Connection getConnection(){
        Connection connection = null;
        String jdbcURL = "jdbc:mysql://localhost:3306/products?useUnicode=true&characterEncoding=UTF-8";
        String jdbcUsername = "root";
        String jdcbPassword = "Remnef131299";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdcbPassword);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

}