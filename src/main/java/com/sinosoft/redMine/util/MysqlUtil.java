package com.sinosoft.redMine.util;  
  
  
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;  
  
public class MysqlUtil {  
    private static Properties property;  
    static{  
        String url=MysqlUtil.class.getClassLoader().getResource("mysql_conn.properties").getPath();  
        try {  
            property=new Properties();  
            property.load(new FileInputStream(url));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static Connection getConnection(){  
        Connection connection=null;  
        try {  
            Class.forName(property.getProperty("driver"));  
            String url=property.getProperty("url");  
            String username=property.getProperty("username");  
            String password=property.getProperty("password");  
            connection=DriverManager.getConnection(url, username, password);  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return connection;  
    }  
    public static void release(Connection con, Statement statement,ResultSet rs){  
        if(null!=rs){  
            try {  
                rs.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }finally{  
                rs=null;  
            }  
            if(null!=statement){  
                try {  
                    statement.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }finally{  
                    statement=null;  
                }  
            }  
            if(null!=con){  
                try {  
                    con.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }finally{  
                    con=null;  
                }  
            }  
        }  
    }  
}