package assignments.assignment4.gui.member.member;

import assignments.assignment3.LoginManager;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.Loginable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JPanel mainPanel;
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set up main panel, Feel free to make any changes
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
        c.insets = new Insets(50,0, 15, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;

        c.gridy = 0;
        c.gridx = 0;
        paketLabel = new JLabel("Paket Laundry: ");
        mainPanel.add(paketLabel, c);

        c.gridy = 0;
        c.gridx = 1;
        paketComboBox = new JComboBox<>(new String[]{"Express", "Fast", "Reguler"});
        mainPanel.add(paketComboBox);

        c.gridy = 0;
        c.gridx = 2;
        showPaketButton = new JButton("Show Paket");
        mainPanel.add(showPaketButton, c);
        showPaketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showPaket();
            }
            });

        c.insets = new Insets(0,0, 15, 10);
        c.gridy = 1;
        c.gridx = 0;
        beratLabel = new JLabel("Berat Cucian (Kg): ");
        mainPanel.add(beratLabel, c);
        
        c.gridy = 1;
        c.gridx = 1;
        beratTextField = new JTextField();
        mainPanel.add(beratTextField, c);

        c.gridy = 2;
        c.gridx = 0;
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000 / Kg)");
        mainPanel.add(setrikaCheckBox, c);

        c.gridy = 3;
        c.gridx = 0;
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 / 4 Kg pertama, kemudian 500 / Kg)");
        mainPanel.add(antarCheckBox, c);

        c.gridwidth = 3;

        c.gridy = 4;
        c.gridx = 0;
        createNotaButton = new JButton("Buat Nota");
        mainPanel.add(createNotaButton, c);
        createNotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                createNota();
            }
            });

        c.gridy = 5;
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
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        String paketString = (String) paketComboBox.getSelectedItem();
        String berat = beratTextField.getText();
        int beratInt = 0;

        if (MemberSystem.hasCharacter(berat)){
            JOptionPane.showMessageDialog(null, "Berat cucian harus berisi angka!",
                                "Error", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
            return;
        }

        if (berat == null || berat.equals("")){
            beratInt = 2;
            JOptionPane.showMessageDialog(null, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg",
                                "Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (Integer.parseInt(berat) < 2){
            beratInt = 2;
            JOptionPane.showMessageDialog(null, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg",
                                "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            beratInt = Integer.parseInt(beratTextField.getText());
        }
        
        Nota newNota = new Nota(memberSystemGUI.getLoggedInMember(), beratInt, paketString, fmt.format(cal.getTime()));
        NotaManager.addNota(newNota); //add nota ke dalam notalist
        memberSystemGUI.getLoggedInMember().addNota(newNota);

        if (setrikaCheckBox.isSelected()) {
            SetrikaService setrika = new SetrikaService();
            newNota.addService(setrika);
        }
        if (antarCheckBox.isSelected()) {
            AntarService antar = new AntarService();
            newNota.addService(antar);
        }
        JOptionPane.showMessageDialog(null, "Nota berhasil dibuat!",
                                "Info", JOptionPane.ERROR_MESSAGE);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
        paketComboBox.setSelectedItem("Express");
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(MemberSystemGUI.KEY);
    }
}
