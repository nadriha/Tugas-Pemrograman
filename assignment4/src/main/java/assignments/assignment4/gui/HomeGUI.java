package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel, BorderLayout.NORTH);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(25, 5, 25, 5);

        c.gridy = 0;
        c.gridx = 0;
        loginButton = new JButton("Login");
        mainPanel.add(loginButton, c);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleToLogin();
            }
            });
        
        c.gridy = 1;
        c.gridx = 0;
        registerButton = new JButton("Register");
        mainPanel.add(registerButton, c);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleToRegister();
            }
            });

        c.gridy = 2;
        c.gridx = 0;
        toNextDayButton = new JButton("Next Day");
        mainPanel.add(toNextDayButton, c);
        toNextDayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                handleNextDay();
            }
            });

        dateLabel = new JLabel("Hari ini: "+ NotaManager.fmt.format(NotaManager.cal.getTime()));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(dateLabel, BorderLayout.SOUTH);
        
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {
        toNextDay();
        dateLabel.setText("Hari ini: "+ NotaManager.fmt.format(NotaManager.cal.getTime()));
        JOptionPane.showMessageDialog(null, "Kamu tidur hari ini... zzz...",
                                                     "Zzz... Tidur😴", JOptionPane.INFORMATION_MESSAGE);

    }
}
