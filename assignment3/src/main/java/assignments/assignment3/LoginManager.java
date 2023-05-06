package assignments.assignment3;

import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

public class LoginManager {
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;
    protected String id;

    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    /**
     * Method mapping dari ke SystemCLI yang sesuai.
     *
     * @param id -> ID dari user yang akan menggunakan SystemCLI
     * @return SystemCLI object yang sesuai dengan ID, null if  ID tidak ditemukan.
     */
    public SystemCLI getSystem(String id){
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){
            return employeeSystem;
        }
        return null;
    }

    /**
     * Mendaftarkan member baru dengan informasi yang diberikan.
     *
     * @param nama -> Nama member.
     * @param noHp -> Nomor handphone member.
     * @param password -> Password akun member.
     * @return Member object yang berhasil mendaftar, return null jika gagal mendaftar.
     */
    public Member register(String nama, String noHp, String password) {
        id = generateId(nama, noHp);
        if (getSystem(id) == null){
            Member newMember = new Member(nama, id, password);
            memberSystem.addMember(newMember);
            return newMember;
        }

        return null;
    }


    //method untuk membuat id
    public String generateId(String nama, String noHp){
        String[] namaArr = nama.split(" "); //array untuk menampung nama user yang sudah di split
        String namaUser = namaArr[0]; //mengambil nama depan pada array nama

        int characterValue = 7; //value = 7 untuk menghitung "-" pada id

        //loop untuk checksum
        String namaNoHp = namaUser + noHp;
        for (char c : namaNoHp.toCharArray()) {
            if (Character.isDigit(c)){
                characterValue += c - '0';
            } else if (Character.isLetter(c)){
                characterValue += Character.toUpperCase(c) - 'A' + 1;
            } else {
                characterValue += 7; //jika symbol maka ditambah 7
            }
        }

        //membuat dan mereturn id
        String lastTwoChars = String.format("%02d", characterValue % 100);
        return id = String.format("%s-%s-%s", namaUser.toUpperCase(), noHp, lastTwoChars);
        
    }
}