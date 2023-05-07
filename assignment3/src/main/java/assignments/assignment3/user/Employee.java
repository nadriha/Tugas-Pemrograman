//import package/library yang diperlukan
package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount;
    
    //constructor class employee
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password); //inherit dari class member
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) {
        String[] namaArr = nama.split(" "); //array untuk menampung nama user yang sudah di split
        String namaUser = namaArr[0]; //mengambil nama depan pada array nama
        
        employeeCount++; //increment counter untuk membuat id employee
        
        return String.format("%s-%d",namaUser.toUpperCase(), employeeCount-1);

    }
}
