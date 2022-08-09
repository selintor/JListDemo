import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteDb {
    public static void deleteSql(String data) throws SQLException{
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "DELETE FROM ericssonLinks WHERE Bağlantı=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, data);
            int result = statement.executeUpdate();
            System.out.println("Kayıt silindi.");
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
