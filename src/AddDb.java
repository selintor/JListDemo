import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public class AddDb {
    public static void sqlAdd(String data) throws SQLException{
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "insert into ericssonLinks (Ortam, Bağlantı, OCPCloud, NextCloudPRPOrtamlar) values(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "boş");
            statement.setString(2, data);
            statement.setString(3, "boş");
            statement.setString(4, "boş");
            int result = statement.executeUpdate();
            System.out.println("Kayıt eklendi.");
        }
        catch (SQLException ex){
            helper.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connection.close();
        }
    }
}
