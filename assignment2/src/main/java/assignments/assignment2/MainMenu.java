package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static ArrayList<String> idList = new ArrayList<String>();
    private static String paketLaundry;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
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
        
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        // Handle generate user
        System.out.println("Masukkan nama Anda:");
        String nama = input.nextLine();

        System.out.println("Masukkan nomor handphone Anda:"); //meminta input nomor hp user
        String nomorHp = input.nextLine(); 

         //while loop untuk mengecek karakter pada input no hp
         while (hasCharacter(nomorHp)){
            System.out.println("Nomor hp hanya menerima digit"); //handle jika terdapat karakter pada no hp user
            nomorHp = input.nextLine();
            }
        
        
        Member member = new Member(nama, nomorHp);

        if (idList.contains(member.getId())){
            System.out.println("Member dengan nama "+nama+" dan nomor hp "+nomorHp+" sudah ada!");
        } else {
            System.out.println("Berhasil membuat member dengan ID " + member.getId()+"!");
            memberList.add(member);
            idList.add(member.getId());
        }
        
    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        System.out.println("Masukkan Masukan ID member:");
        String memberId = input.nextLine();
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
            while (hasCharacter(beratCucian)){ //loop untuk error handling jika berat cucian bukan berupa angka
                System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                beratCucian = input.nextLine();
            }

            while (Integer.parseInt(beratCucian) < 2){ //loop untuk error handling jika berat cucian kurang dari 2kg
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                beratCucian = "2";
            }

            Nota nota = new Nota(memberNow, paketLaundry, Integer.parseInt(beratCucian), fmt.format(cal.getTime()));
            memberNow.countBonus();
            notaList.add(nota);
            System.out.println(nota.getNota());

        } else {
            System.out.println("Member dengan ID " + memberId +" tidak ditemukan!");
        }
    }


    private static void handleListNota() {
        System.out.printf("Terdaftar %d nota dalam sistem.\n", notaList.size());
        if (notaList.size() != 0){
            for (Nota nota : notaList){
                System.out.printf("- [%d] Status      	: %s\n", nota.getId(), nota.getStatusNota());
            }
        }
    }

    private static void handleListUser() {
        System.out.printf("Terdaftar %d member dalam sistem.\n", memberList.size());
        if (memberList.size() != 0){
            for (Member member : memberList){
                System.out.printf("- %s : %s\n", member.getId(), member.getNama());
            }
        }
    }

    private static void handleAmbilCucian() {
        System.out.println("Masukan ID nota yang akan diambil:");
        String idNota = input.nextLine();

        while (hasCharacter(idNota)){
            System.out.println("ID nota berbentuk angka!"); //handle jika terdapat karakter pada no hp user
            idNota = input.nextLine();
            }

        Nota nota = cariIdNota(Integer.parseInt(idNota));
        if (nota != null && nota.notaReady()){
            System.out.printf("Nota dengan ID %d berhasil diambil!\n", nota.getId());
            notaList.remove(nota);
        } else if (nota == null){
            System.out.printf("Nota dengan ID %s tidak ditemukan!\n", idNota);
        } else if (!nota.notaReady()){
            System.out.printf("Nota dengan ID %s gagal diambil!\n", idNota);
        }
    }

    private static void handleNextDay() {
        cal.add(Calendar.DAY_OF_WEEK, 1);
        System.out.println("Dek Depe tidur hari ini... zzz...");
        for (Nota nota : notaList){
            nota.counterHariPengerjaan();
            if (nota.notaReady() == true){
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", nota.getId());
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

    public static Member cariMember(String id){
        for(Member member: memberList){ 
            if(member.getId().equals(id))
                return member; 
        }
        return null;
    }

    public static Nota cariIdNota(int id){
        for(Nota nota: notaList){ 
            if(nota.getId() == id)
                return nota;
        }
        return null;
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

    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

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
