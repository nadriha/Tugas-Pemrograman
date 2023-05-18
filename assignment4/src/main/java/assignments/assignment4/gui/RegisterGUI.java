package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 20, 15, 20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;

        c.gridy = 0;
        c.gridx = 0;
        nameLabel = new JLabel("Masukkan nama Anda:");
        mainPanel.add(nameLabel, c);

        c.insets = new Insets(0, 20, 15, 20);
        c.gridy = 1;
        c.gridx = 0;
        nameTextField = new JTextField();
        mainPanel.add(nameTextField, c);

        c.gridy = 2;
        c.gridx = 0;
        phoneLabel = new JLabel("Masukkan nomor handphone Anda:");  
        mainPanel.add(phoneLabel, c);
        
        c.gridy = 3;
        c.gridx = 0;
        phoneTextField = new JTextField();  
        mainPanel.add(phoneTextField, c);

        c.gridy = 4;
        c.gridx = 0;
        passwordLabel = new JLabel("Masukkan password Anda:");  
        mainPanel.add(passwordLabel, c);
        
        c.gridy = 5;
        c.gridx = 0;
        passwordField = new JPasswordField();  
        mainPanel.add(passwordField, c);

        c.insets = new Insets(20, 20, 0, 20);
        c.fill = GridBagConstraints.NONE;
        c.gridy = 6;
        c.gridx = 0;
        registerButton = new JButton("Register");
        mainPanel.add(registerButton, c);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleRegister();
            }
            });

        c.insets = new Insets(0, 20, 0, 20);
        c.gridy = 7;
        c.gridx = 0;
        backButton = new JButton("Kembali");
        mainPanel.add(backButton, c);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleBack();
            }
            });
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        String nameString = nameTextField.getText();
        String phoneString = phoneTextField.getText();
        char[] passwordChar = passwordField.getPassword();
        String passwordString = new String(passwordChar);

        if (nameString.equals("") || phoneString.equals("") || passwordString.equals("")){
            JOptionPane.showMessageDialog(null, "isi semua field",
                                                     "Info", JOptionPane.ERROR_MESSAGE);
        } else if (MemberSystem.hasCharacter(phoneString)){
            JOptionPane.showMessageDialog(null, "Nomor handphone harus bersisi angka!",
                                                     "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
        } else {
            Member loginMember = loginManager.register(nameString, phoneString, passwordString);
            if (loginMember == null){
                JOptionPane.showMessageDialog(null, "User dengan nama "+ nameString + " sudah ada!",
                                                     "Registration Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Berhasil membuat user dengan ID "+ loginMember.getId() + "!",
                                                     "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
                nameTextField.setText("");
                phoneTextField.setText("");
                passwordField.setText("");
                MainFrame mainFrame = MainFrame.getInstance();
                mainFrame.navigateTo(HomeGUI.KEY);
            }
        }               
    }
}
