import java.sql.*;

public class InsertData {
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/pr_02",
                        "root",
                        "2005123"
                );
         PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO employee VALUES (?, ?, ?)"
                );

            // Set values for the placeholders
            ps.setInt(1, 107);              // employee id
            ps.setString(2, "Sara");        // employee name
            ps.setDouble(3,4156.0);     // salary

            int rows = ps.executeUpdate();  // execute insert

            if (rows > 0) {
                System.out.println("Record inserted successfully!");
            } else {
                System.out.println("Insert failed.");
            }
            con.close();

    }
}
