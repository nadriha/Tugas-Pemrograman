package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        JButton cuciButton = new JButton("It's Cuci Time");
        JButton displayNotaButton = new JButton("Display List Nota");

        return new JButton[]{cuciButton,displayNotaButton
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
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    
    private void displayNota() {
        if (NotaManager.notaList.length == 0){
            JOptionPane.showMessageDialog(null, "Belum ada nota:(",
                                            "List Nota", JOptionPane.ERROR_MESSAGE);
        } else {
            String allNota = "";
            for (Nota nota : NotaManager.notaList){
                allNota += "Nota "+nota.getIdNota()+" : "+nota.getNotaStatus()+"\n";
            }
            JOptionPane.showMessageDialog(null, allNota,
                                        "Nyuci Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        JOptionPane.showMessageDialog(null, "Stand Back! "+loggedInMember.getNama()+" beginning to nyuci!",
                                                     "Nyuci Time...", JOptionPane.INFORMATION_MESSAGE);
        

        if (NotaManager.notaList.length == 0){
            JOptionPane.showMessageDialog(null, "Nothing to cuci here :(",
                                            "Nyuci Results", JOptionPane.ERROR_MESSAGE);
        } else {
            String allNota = "";
            for (Nota nota : NotaManager.notaList){ //iterasi agar tiap nota bekerja
                allNota += nota.kerjakan()+"\n";
            }
            JOptionPane.showMessageDialog(null, allNota,
                                        "Nyuci Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
