/*
 * Nadhira Raihana Hafez
 * DDP2 G - 2206082000
 */

//import library yang diperlukan
package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;

//membuat class yang bernama sama dengan nama file
public class MainMenu {
    //membuat object dan variable yang diperlukan
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<String> idList = new ArrayList<String>();
    private static String paketLaundry;

    //Method main, program utama berjalan
    public static void main(String[] args) {
        //program start
        boolean isRunning = true;
        while (isRunning) {
            printMenu();

            //meminta input apa yang akan dilakukkan oleh user
            System.out.print("Pilihan : "); 
            String command = input.nextLine(); //membuat variable untuk menampung pilihan customer
            System.out.println("================================");
            switch (command){ //switch untuk melanjutkan program sesuai pilihan user
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        
        //program jika input user = 0
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    //method case 1 = Generate User
    private static void handleGenerateUser() {
        System.out.println("Masukkan nama Anda:"); //meminta input nama user
        String nama = input.nextLine();

        System.out.println("Masukkan nomor handphone Anda:"); //meminta input nomor hp user
        String nomorHp = input.nextLine(); 

         //while loop untuk mengecek karakter pada input no hp
         while (hasCharacter(nomorHp)){
            System.out.println("field hanya menerima digit"); //handle jika terdapat karakter pada no hp user
            nomorHp = input.nextLine();
            }
        
        //membuat object member baru
        Member member = new Member(nama, nomorHp);

        //mengecek jika member belum ada sebelumnya
        if (idList.contains(member.getId())){
            System.out.println("Member dengan nama "+nama+" dan nomor hp "+nomorHp+" sudah ada!");
        } else {
            System.out.println("Berhasil membuat member dengan ID " + member.getId()+"!");
            memberList.add(member);
            idList.add(member.getId());
        }
        
    }

    //method case 2 = Generate Nota
    private static void handleGenerateNota() {
        System.out.println("Masukkan Masukan ID member:"); //meminta input id user
        String memberId = input.nextLine();

        //Mencari object member yang sesuai dengan input user
        Member memberNow = cariMember(memberId);

        if (memberList.contains(memberNow)){
            boolean isPaketLaundryValid = true;
                while (isPaketLaundryValid){
                    System.out.println("Masukkan paket laundry:"); //meminta input paket laundry
                    paketLaundry = input.nextLine();
                    String paketLaundryCheck = paketLaundry.toLowerCase();

                    //memvalidasi dan menyesuaikan input user ke jalannya program
                    if (paketLaundryCheck.equals("express") || paketLaundryCheck.equals("fast") || paketLaundryCheck.equals("reguler")){
                        isPaketLaundryValid = false; //keluar dari while loop jika input sudah sesuai

                    } else if (paketLaundryCheck.equals("?")){
                        showPaket(); //memunculkan jenis paket jika input = ?

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

            //Membuat object nota baru dan memsukkannya ke dalam list nota
            Nota nota = new Nota(memberNow, paketLaundry, Integer.parseInt(beratCucian), fmt.format(cal.getTime()));
            memberNow.countBonus();                 //mendambah counter bonus
            notaList.add(nota);                     //add nota ke dalam list nota
            System.out.println(nota.getNota());     //print nota

        } else {
            System.out.println("Member dengan ID " + memberId +" tidak ditemukan!"); //handling jika id tidak valid
        }
    }

    //method case 3 = List Nota
    private static void handleListNota() {
        System.out.printf("Terdaftar %d nota dalam sistem.\n", notaList.size());
        if (notaList.size() != 0){ //mencetak nota-nota dalam notaList
            for (Nota nota : notaList){
                System.out.printf("- [%d] Status      	: %s\n", nota.getId(), nota.getStatusNota());
            }
        }
    }

    //method case 4 = List Member
    private static void handleListUser() {
        System.out.printf("Terdaftar %d member dalam sistem.\n", memberList.size());
        if (memberList.size() != 0){ //mencetak member-member dalam memberList
            for (Member member : memberList){
                System.out.printf("- %s : %s\n", member.getId(), member.getNama());
            }
        }
    }

    //method case 5 = Ambil Cucian
    private static void handleAmbilCucian() {
        System.out.println("Masukan ID nota yang akan diambil:"); //meminta input id nota user
        String idNota = input.nextLine();

        while (hasCharacter(idNota)){
            System.out.println("ID nota berbentuk angka!"); //handle jika terdapat karakter pada id nota user
            idNota = input.nextLine();
            }
            
        //Mencari object nota yang sesuai dengan id nota
        Nota nota = cariIdNota(Integer.parseInt(idNota));

        //mengecek status nota
        if (nota != null && nota.notaReady()){
            System.out.printf("Nota dengan ID %d berhasil diambil!\n", nota.getId());
            notaList.remove(nota); //menghapus nota jika status sudah ready dan sudah diambil
        } else if (nota == null){
            System.out.printf("Nota dengan ID %s tidak ditemukan!\n", idNota);
        } else if (!nota.notaReady()){
            System.out.printf("Nota dengan ID %s gagal diambil!\n", idNota);
        }
    }

    //method case 6 = Next Day
    private static void handleNextDay() {
        //menambah hari dalam object calendar
        cal.add(Calendar.DAY_OF_WEEK, 1);

        System.out.println("Dek Depe tidur hari ini... zzz...");//

        //iterasi notaList untuk mencetak nota yang sudah selesai
        for (Nota nota : notaList){
            nota.counterHariPengerjaan();
            if (nota.notaReady() == true){
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", nota.getId());
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

    //method untuk mencari object member berdasarkan id
    public static Member cariMember(String id){
        for(Member member: memberList){ 
            if(member.getId().equals(id))
                return member; 
        }
        return null;
    }

    //method untuk manecari object nota berdasarkan id
    public static Nota cariIdNota(int id){
        for(Nota nota: notaList){ 
            if(nota.getId() == id)
                return nota;
        }
        return null;
    }

    //method untuk validasi string angka
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

    //method untuk print show paket
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    //method untuk print menu
    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}
