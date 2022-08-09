import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDb {
    public static void updateSql(String data) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            String sql = "UPDATE ericssonLinks SET Bağlantı = ? WHERE Bağlantı = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "güncellenmiş veri");
            statement.setString(2, data);
            int result = statement.executeUpdate();
            System.out.println("Kayıt güncellendi.");
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
