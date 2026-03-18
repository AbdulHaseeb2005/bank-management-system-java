import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPageSimple {
    public static void main(String[] args) {
        JFrame f = new JFrame("Login Page");
        f.setBounds(400, 200,350,250);





        JTextField user = new JTextField();
        user.setBounds(100, 30, 150, 20);
        f.add(new JLabel("Username:")).setBounds(20, 30, 80, 20);
        f.add(user);




        JPasswordField pass = new JPasswordField();
        pass.setBounds(100, 60, 150, 20);
        f.add(new JLabel("Password:")).setBounds(20, 60, 80, 20);
        f.add(pass);




        JButton btn = new JButton("Sign Up");
        btn.setBounds(100, 90, 150, 30);
        f.add(btn);

        f.setLayout(null);
        f.setVisible(true);

//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn.addActionListener(e -> {
            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bankSystem", "root", "2005123");
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO users(username, password) VALUES (?, ?)");
                ps.setString(1, user.getText());
                ps.setString(2, new String(pass.getPassword()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(f, "User Registered!");
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
