package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signup2 extends JFrame implements ActionListener {

    String formno;
    JComboBox comboBox , comboBox2 , comboBox3 ,comboBox4 ,comboBox5 , comboBox6;
    JTextField textCnic , textNtn;
    JButton next;

    JRadioButton r1, r2, e1, e2;
    public Signup2 (String formno){
        super("APPLICATION FORM");


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150,5,100,100);
        add(image);

        this.formno = formno;

        JLabel l01 = new JLabel("Form No :");
        l01.setFont(new Font("Raleway" , Font.BOLD ,14));
        l01.setBounds(700,10,100,30);
        add(l01);

        JLabel l0 = new JLabel(formno);
        l0.setFont(new Font("Raleway" , Font.BOLD ,14));
        l0.setBounds(760,10,100,30);
        add(l0);


        JLabel l1 = new JLabel("Page 2 :-");
        l1.setFont(new Font("Raleway" , Font.BOLD ,22));
        l1.setBounds(300,30,600,40);
        add(l1);

        JLabel l2 = new JLabel("Additional Details");
        l2.setFont(new Font("Raleway" , Font.BOLD ,22));
        l2.setBounds(300,60,600,40);
        add(l2);

        JLabel l3 = new JLabel("Religion :");
        l3.setFont(new Font("Raleway" , Font.BOLD ,18));
        l3.setBounds(100,120,100,30);
        add(l3);

        setUndecorated(true);
        String religion[] = {"Muslim" , "Hindu" ,"Sikh" ,"Christian" ,"Other"};
        comboBox = new JComboBox(religion);
        comboBox.setBackground(new Color(252,208,76));
        comboBox.setFont(new Font("Raleway" , Font.BOLD ,14));
        comboBox.setBounds(350,120,320,30);
        add(comboBox);

        JLabel l4 = new JLabel("Income :");
        l4.setFont(new Font("Raleway" , Font.BOLD ,18));
        l4.setBounds(100,170,100,30);
        add(l4);

        String income[] = {"NULL" , "<1,50,000" ,"<2,50,000" ,"<5,00,000" ,"Upto 10,00,000" ,"Above 10,00,000"};
        comboBox2 = new JComboBox(income);
        comboBox2.setBackground(new Color(252,208,76));
        comboBox2.setFont(new Font("Raleway" , Font.BOLD ,14));
        comboBox2.setBounds(350,170,320,30);
        add(comboBox2);

        JLabel l5 = new JLabel("Education :");
        l5.setFont(new Font("Raleway" , Font.BOLD ,18));
        l5.setBounds(100,220,150,30);
        add(l5);

        String education[] = {"Non-Graducate" , "Graducate" ,"Post-Graducate" ,"Doctrate" ,"Other"};
        comboBox3 = new JComboBox(education);
        comboBox3.setBackground(new Color(252,208,76));
        comboBox3.setFont(new Font("Raleway" , Font.BOLD ,14));
        comboBox3.setBounds(350,220,320,30);
        add(comboBox3);

        JLabel l6 = new JLabel("Occupation :");
        l6.setFont(new Font("Raleway" , Font.BOLD ,18));
        l6.setBounds(100,270,150,30);
        add(l6);

        String occupation[] = {"Salaried" , "Self-Employed" ,"Business" ,"Student" ,"Other"};
        comboBox4 = new JComboBox(occupation);
        comboBox4.setBackground(new Color(252,208,76));
        comboBox4.setFont(new Font("Raleway" , Font.BOLD ,14));
        comboBox4.setBounds(350,270,320,30);
        add(comboBox4);

        JLabel l7 = new JLabel("CNIC Number :");
        l7.setFont(new Font("Raleway", Font.BOLD, 18));
        l7.setBounds(100, 320, 150, 30);
        add(l7);

        textCnic = new JTextField("xxxxx-xxxxxxx-x");
        textCnic.setFont(new Font("Raleway", Font.BOLD, 14));
        textCnic.setBounds(350, 320, 320, 30);
        textCnic.setForeground(Color.GRAY);

// Placeholder logic
        textCnic.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textCnic.getText().equals("xxxxx-xxxxxxx-x")) {
                    textCnic.setText("");
                    textCnic.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textCnic.getText().isEmpty()) {
                    textCnic.setForeground(Color.GRAY);
                    textCnic.setText("xxxxx-xxxxxxx-x");
                }
            }
        });

// Auto-dash logic
        textCnic.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                String text = textCnic.getText();

                // Only allow digits and prevent overflow
                if (!Character.isDigit(ch) || text.length() >= 15) {
                    e.consume();
                    return;
                }

                // Automatically insert dash after 5 and 13 characters
                if (text.length() == 5 || text.length() == 13) {
                    textCnic.setText(text + "-");
                    // Manually reposition caret to end after setting text
                    EventQueue.invokeLater(() -> textCnic.setCaretPosition(textCnic.getText().length()));
                }
            }
        });

        add(textCnic);


        JLabel l8 = new JLabel("NTN Number :");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));
        l8.setBounds(100, 370, 150, 30);
        add(l8);

        textNtn = new JTextField("xxxxxx-x");
        textNtn.setFont(new Font("Raleway", Font.BOLD, 14));
        textNtn.setBounds(350, 370, 320, 30);
        textNtn.setForeground(Color.GRAY);

// Placeholder behavior
        textNtn.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textNtn.getText().equals("xxxxxx-x")) {
                    textNtn.setText("");
                    textNtn.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textNtn.getText().isEmpty()) {
                    textNtn.setText("xxxxxx-x");
                    textNtn.setForeground(Color.GRAY);
                }
            }
        });

// Auto-dash after 6 digits
        textNtn.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                String text = textNtn.getText();

                // Allow only digits and dash auto-insert
                if (!Character.isDigit(ch)) {
                    e.consume();
                    return;
                }

                // Prevent extra characters after full format
                if (text.length() >= 8) {
                    e.consume();
                    return;
                }

                // Auto-insert dash after 6 digits
                if (text.length() == 6) {
                    textNtn.setText(text + "-");
                }
            }
        });

        add(textNtn);


        JLabel l9 = new JLabel("Senior Citizen :");
        l9.setFont(new Font("Raleway" , Font.BOLD ,18));
        l9.setBounds(100,420,150,30);
        add(l9);

        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Raleway" , Font.BOLD ,14));
        r1.setBackground(new Color(252,208,76));
        r1.setBounds(350,420,100,30);
        add(r1);
        r2 = new JRadioButton("No");
        r2.setFont(new Font("Raleway" , Font.BOLD ,14));
        r2.setBackground(new Color(252,208,76));
        r2.setBounds(460,420,100,30);
        add(r2);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);

        JLabel l10 = new JLabel("Existing Account :");
        l10.setFont(new Font("Raleway" , Font.BOLD ,18));
        l10.setBounds(100,470,180,30);
        add(l10);

        e1 = new JRadioButton("Yes");
        e1.setFont(new Font("Raleway" , Font.BOLD ,14));
        e1.setBackground(new Color(252,208,76));
        e1.setBounds(350,470,100,30);
        add(e1);
        e2 = new JRadioButton("No");
        e2.setFont(new Font("Raleway" , Font.BOLD ,14));
        e2.setBackground(new Color(252,208,76));
        e2.setBounds(460,470,100,30);
        add(e2);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(e1);
        buttonGroup1.add(e2);

        next = new JButton("Next");
        next.setFont(new Font("Raleway" , Font.BOLD ,14));
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.setBounds(570,570,100,30);
        next.addActionListener(this);
        add(next);




        setLayout(null);
        setSize(850,750);
        setLocation(450,80);
        getContentPane().setBackground(new Color(252,208,76));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String rel = (String) comboBox.getSelectedItem();
        String inc = (String) comboBox2.getSelectedItem();
        String edu = (String) comboBox3.getSelectedItem();
        String occu = (String) comboBox4.getSelectedItem();

        String cnicno = textCnic.getText();
        String ntnno = textNtn.getText();

        String scitizen = "";
        if (r1.isSelected()) {
            scitizen = "Yes";
        }else if (r2.isSelected()) {
            scitizen = "No";
        }

        String eaccount = "";
        if (e1.isSelected()) {
            eaccount = "Yes";
        }else if (e2.isSelected()) {
            eaccount = "No";
        }

        try {
            String cnic = textCnic.getText().trim();
            String ntn = textNtn.getText().trim();

            if (cnic.equals("xxxxx-xxxxxxx-x") || ntn.equals("xxxxxx-x") ||
                    !cnic.matches("\\d{5}-\\d{7}-\\d{1}") ||
                    !ntn.matches("\\d{6}-\\d{1}")) {

                JOptionPane.showMessageDialog(null, "Please enter valid CNIC and NTN in the correct format.");
            } else {
                Con c1 = new Con();
                String q = "INSERT INTO Signuptwo VALUES ('" + formno + "', '" + rel + "', '" + inc + "', '" + edu + "', '" + occu + "', '" + cnic + "', '" + ntn + "', '" + scitizen + "', '" + eaccount + "')";
                c1.statement.executeUpdate(q);
                new Signup3(formno);
                setVisible(false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    public static void main(String[] args) {
        new Signup2("");
    }
}
