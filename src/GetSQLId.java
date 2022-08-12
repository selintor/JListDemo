import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSQLId {
    static int Id;
    public static int getId(String data) throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            connection = helper.getConnection();
            String sql = "SELECT id FROM ericssonLinks WHERE Bağlantı = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, data);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Id = result.getInt("id");
            }
        }
        catch (SQLException ex){
            helper.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connection.close();
        }
        return Id;
    }
}
