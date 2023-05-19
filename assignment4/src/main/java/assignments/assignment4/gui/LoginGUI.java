package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    //membuat variable yang akan dibutuhkan
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout
        this.loginManager = loginManager;

        // Set up main panel
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //inisiasi objek
        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * */
    private void initGUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 20, 15, 20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;

        //menambahkan objek yang diperlukan pada main panel
        c.gridy = 0;
        c.gridx = 0;
        idLabel = new JLabel("Masukkan ID Anda:");
        mainPanel.add(idLabel, c);

        c.insets = new Insets(0, 20, 15, 20);
        c.gridy = 1;
        c.gridx = 0;
        idTextField = new JTextField();
        mainPanel.add(idTextField, c);

        c.gridy = 2;
        c.gridx = 0;
        passwordLabel = new JLabel("Masukkan password Anda:");  
        mainPanel.add(passwordLabel, c);
        
        c.gridy = 3;
        c.gridx = 0;
        passwordField = new JPasswordField();  
        mainPanel.add(passwordField, c);

        c.insets = new Insets(20, 10, 0, 10);
        c.fill = GridBagConstraints.NONE;
        c.gridy = 6;
        c.gridx = 0;
        loginButton = new JButton("Login");
        mainPanel.add(loginButton, c);
        loginButton.addActionListener(new ActionListener() { //handler jika user mengklik button
            public void actionPerformed(ActionEvent e){
                handleLogin();
            }
            });

        c.insets = new Insets(10, 10, 0, 10);
        c.gridy = 7;
        c.gridx = 0;
        backButton = new JButton("Kembali");
        mainPanel.add(backButton, c);
        backButton.addActionListener(new ActionListener() { //handler jika user mengklik button
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
        idTextField.setText(""); //set text menjadi kosong kembali
        passwordField.setText("");

        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        //mengambil isi dari object yang sudah dibuat
        String idString = idTextField.getText();
        char[] passwordChar = passwordField.getPassword();
        String passwordString = new String(passwordChar);

        MainFrame mainFrame = MainFrame.getInstance();
        if (!mainFrame.login(idString, passwordString)){ //mengecek jika user gagal login dan menampilkan pesan error
            JOptionPane.showMessageDialog(null, "ID atau password invalid",
                                                     "Invalid ID or Password", JOptionPane.ERROR_MESSAGE);
        } else {
            idTextField.setText("");
            passwordField.setText("");
        }
    }
}
