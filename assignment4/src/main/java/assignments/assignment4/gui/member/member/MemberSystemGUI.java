package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    //getter
    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton laundryButton = new JButton("Saya ingin laundry");
        JButton showNotaButton = new JButton("Lihat detail nota Saya");

        return new JButton[]{laundryButton, showNotaButton
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        //membuat scroll pane dan text area untuk display nota
        JTextArea textArea = new JTextArea(20, 30);  
        JScrollPane detaillNota = new JScrollPane(textArea); 

        if (loggedInMember.getNotaList().length == 0){ //output jika belum ada nota
            textArea.setText("Belum pernah laundy di CuciCuci. Hiks:(");
        } else { //print out nota pada JScrollPane
            String allNota = "";
            for (Nota nota : loggedInMember.getNotaList()){
                allNota += nota.toString()+"\n";
            }
            textArea.setText(allNota);
        }
        
        JOptionPane.showMessageDialog(null, detaillNota,
                                        "Detail Nota", JOptionPane.INFORMATION_MESSAGE);
        

    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo(CreateNotaGUI.KEY);
    }

}
