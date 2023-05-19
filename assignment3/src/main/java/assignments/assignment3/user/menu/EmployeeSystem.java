//import package/library yang diperlukan
package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {
    
    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    public void addEmployee(Employee[] employees) {
        for (Employee employee : employees){
            addEmployeetoList(employee);
        }

    }

    public void addEmployeetoList(Member member){
        Member[] newMemberList = new Member[memberList.length+1];

        for (int i = 0; i < memberList.length; i++) {
            newMemberList[i] = memberList[i];
          }
          memberList = newMemberList;
      
          newMemberList[memberList.length - 1] = member;
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch (choice) {
            case 1 -> cuciTime();
            case 2 -> displayListlNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }

    //method cuciTime jika input employee = 1
    public void cuciTime(){
        System.out.println("Stand back! "+ loginMember.getNama() +" beginning to nyuci!"); //print status
        for (Nota nota : notaList){ //iterasi agar tiap nota bekerja
            System.out.println(nota.kerjakan());
        }
    }

    //method untuk menampilkan nota-nota beserta statusnya
    public void displayListlNota(){
        for (Nota nota : notaList){
            System.out.println("Nota "+nota.getIdNota()+" : "+nota.getNotaStatus());
        }
        System.out.println();
    }
}