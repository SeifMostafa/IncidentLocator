package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private Statement getStatement() throws SQLException {
        try{
            String url = ""; // database url
            Class.forName (""); // database name
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();
            return stmt;
            
            
        } catch (ClassNotFoundException e) {
            return null;
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public ResultSet runQuery(String sql){
        try{
            return getStatement().executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }

    }
    
}
