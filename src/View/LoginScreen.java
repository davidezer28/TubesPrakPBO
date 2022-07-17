/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DataManager;
import Model.DataAdmin;
import Model.DataCustomer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author davidezer
 */
public class LoginScreen implements ActionListener {
    JFrame frame;
    JLabel labelDataDiri, labelUsername, labelPassword;
    JButton button_Register, button_Login,  button_back;
    JTextField tfUsername, tfNickname;
    JPasswordField passwordField;
    Boolean Username_filled = false, Nickname_filled = false, Email_filled = false, Pass_filled = false;
    public LoginScreen() {
        frame = new JFrame("Login Form");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        labelDataDiri = new JLabel("Login Screen");
        labelDataDiri.setForeground(Color.blue);
        labelDataDiri.setFont(new Font("Serif", Font.BOLD, 20));
        labelDataDiri.setBounds(225, 30, 400, 30);

        labelUsername = new JLabel("Username ");
        labelUsername.setBounds(80, 100, 200, 30);

        tfUsername = new JTextField();
        tfUsername.setBounds(300, 100, 200, 30);
        tfUsername.setColumns(20);

        labelPassword = new JLabel("Password ");
        labelPassword.setBounds(80, 180, 200, 30);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(300, 180, 200, 30);
        passwordField.setColumns(20);

        button_Register = new JButton("Login");
        button_Register.setBounds(225, 250, 100, 30);
        button_Register.addActionListener(this);

        button_back = new JButton("Back");
        button_back.setBounds(225, 295, 100, 30);
        button_back.setActionCommand("Back");
        button_back.addActionListener(this);
        
        frame.add(button_Register);
        frame.add(button_back);

        frame.add(labelDataDiri);
        frame.add(labelUsername);
        frame.add(labelPassword);

        frame.add(tfUsername);
        frame.add(passwordField);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        switch (command) {
            case "Login":
                String uname = tfUsername.getText();
                String pass = passwordField.getText();
                boolean cek = false;
                
                DataCustomer userHead = DataManager.getInstance().getData().getUserHead();
                DataAdmin adminHead = DataManager.getInstance().getData().getAdminHead();
                DataCustomer userTemp = userHead;
                DataAdmin adminTemp = adminHead;
                while((cek != true) && (userTemp != null)){
                    if(userTemp.getUsername().equals(uname) && userTemp.getPassword().equals(pass)){
                        cek = true;
                        JOptionPane.showMessageDialog(null, "Welcome User " + userTemp.getNama());
                        frame.setVisible(false);
                        DataManager.getInstance().getData().setDataUser(userTemp);
                        new MainMenuUser();
                        break;
                    }
                    userTemp = userTemp.getNext();
                }
                while((cek != true) && (adminTemp != null)){
                    if(adminTemp.getUsername().equals(uname) && adminTemp.getPassword().equals(pass)){
                        cek = true;
                        JOptionPane.showMessageDialog(null, "Welcome Admin " + adminTemp.getNama());
                        frame.setVisible(false);
                        DataManager.getInstance().getData().setDataAdmin(adminTemp);
                        new MainMenuAdmin();
                        break;
                    }
                    adminTemp = adminTemp.getNext();
                }
                if(!cek){
                    JOptionPane.showMessageDialog(null, "Username / Password Salah!!");
                    passwordField.setText("");
                }
                break;
            case "Back":
                frame.setVisible(false);
                new MainMenuScreen();
                break;
            default:
                break;
        }
    }
}
