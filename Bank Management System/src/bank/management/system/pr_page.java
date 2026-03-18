package bank.management.system;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

public class pr_page {
    public static void main(String[] args) {
        JFrame f1 = new JFrame("Sample Page");
        f1.setBounds(500,200,500,500);


        JLabel h1 = new JLabel("Register A Student");
        h1.setBounds(20,30,200,50);
        h1.setFont(new Font("Raleway" ,Font.BOLD,20));
        f1.add(h1);


        JLabel l1 = new JLabel("Name : ");
        l1.setBounds(20,55,200,50);
        l1.setFont(new Font("Raleway",Font.BOLD , 15));
        f1.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(70,70,200,25);
        f1.add(t1);

        JLabel l2 = new JLabel("DOB :");
        l2.setBounds(20,90,200,20);
        l2.setFont(new Font("Raleway" ,Font.BOLD,15));
        f1.add(l2);

        JDateChooser d1 = new JDateChooser();
        d1.setBounds(70,95,200,20);
        f1.add(d1);

        JLabel l3 = new JLabel("Password :");
        l3.setBounds(20,105,200,50);
        f1.add(l3);

        JPasswordField p1 = new JPasswordField();
        p1.setBounds(90,120,200,20);
        f1.add(p1);

        JLabel l4 = new JLabel("Gender :");
        l4.setBounds(20,130,200,50);
        f1.add(l4);

        JRadioButton r1 = new JRadioButton("Male");
        r1.setBounds(70,145,60,20);
        f1.add(r1);

        JRadioButton r2 = new JRadioButton("Female");
        r2.setBounds(130,145,200,20);
        f1.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        String course[] = {"Maths" , "Phsics" ,"ICS"};
        JComboBox c1 = new JComboBox(course);
        c1.setBounds(30,160,200,50);
        f1.add(c1);
        

        f1.setLayout(null);
        f1.setVisible(true);
    }
}
