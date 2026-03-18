import java.sql.*;

public class ReadData {
    public static void main(String[] args) throws SQLException {
        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/pr_02",
                        "root",
                        "2005123"
                );
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM employee")
        ) {
            while (rs.next()) {
                System.out.println(
                        rs.getString(1) + " | " +
                                rs.getString(2) + " | " +
                                rs.getString(3)
                );
            }
        }
    }
}
