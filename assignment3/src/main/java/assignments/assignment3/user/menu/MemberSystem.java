package assignments.assignment3.user.menu;
import assignments.assignment3.user.Member;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;


public class MemberSystem extends SystemCLI {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private String paketLaundry;
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch (choice) {
            case 1 -> laundry();
            case 2 -> detailNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }
    

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        Member[] newMemberList = new Member[memberList.length+1];

        for (int i = 0; i < memberList.length; i++) {
            newMemberList[i] = memberList[i];
          }
          memberList = newMemberList;
      
          newMemberList[memberList.length - 1] = member;
    }

    public void laundry() {
        boolean isPaketLaundryValid = true;
                while (isPaketLaundryValid){
                    System.out.println("Masukkan paket laundry:"); //meminta input paket laundry
                    showPaket();
                    paketLaundry = input.nextLine();
                    String paketLaundryCheck = paketLaundry.toLowerCase();

                    //memvalidasi dan menyesuaikan input user ke jalannya program
                    if (paketLaundryCheck.equals("express") || paketLaundryCheck.equals("fast") || paketLaundryCheck.equals("reguler")){
                        isPaketLaundryValid = false; //keluar dari while loop jika input sudah sesuai

                    } else { //error message jika input user tidak sesuai paket yang tersedia
                            System.out.println("Paket "+paketLaundryCheck+" tidak diketahui.");
                            System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    } 
                }

            System.out.println("Masukkan berat cucian Anda [Kg]"); //meminta input berat cucian
            String beratCucian = input.nextLine();

            //while loop untuk validasi input user
            while (hasCharacter(beratCucian)){ //loop untuk mengecek jika berat cucian bukan berupa angka
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                beratCucian = input.nextLine();
            }

            while (Integer.parseInt(beratCucian) < 2){ //loop untuk mengecek jika berat cucian kurang dari 2kg
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                beratCucian = "2";
            }

            Nota nota = new Nota(loginMember, Integer.parseInt(beratCucian), paketLaundry, fmt.format(cal.getTime()));
            // NotaManager.addNota(nota);
            loginMember.addNota(nota);

            System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
            System.out.println("Hanya tambah 1000 / kg");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String setrikaUser = input.nextLine();
            System.out.println(setrikaUser);
            if (!setrikaUser.equals("x")){
                System.out.println("setrika");
                SetrikaService setrika = new SetrikaService();
                nota.addService(setrika);
                }

            System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
            System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
            System.out.print("[Ketik x untuk tidak mau]: ");
            String antarUser = input.nextLine();
            if (!antarUser.equals("x")){
                AntarService antar = new AntarService();
                nota.addService(antar);
                }

            System.out.println("Nota berhasil dibuat!");
        
    }

    public void detailNota(){
        for (Nota nota : loginMember.getNotaList()){
            System.out.println(nota);
        }
    }

    public void showPaket(){
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    private static boolean hasCharacter(String nomor) {
        int check = 0; //variable cek untuk menampung jumlah char
        for(int i = 0; i < nomor.length(); i++){
            if (Character.isDigit(nomor.charAt(i))){
                check += 1;
            }
        }
        if (check == nomor.length()){ 
            return false; //return false jika input user sudah valid
        } else {
            return true; //return false jika terdapat character pada input user
        }
    }
    
}
