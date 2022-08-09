import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private String user = "root";
    private String password = "1234";
    private String dbUrl = "jdbc:mysql://localhost:3306/links";
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(dbUrl,user,password);
    }
    public void showErrorMessage(SQLException ex){
        System.out.println("Error:" + ex.getMessage());
        System.out.println("Error code: " + ex.getErrorCode());
    }
}

