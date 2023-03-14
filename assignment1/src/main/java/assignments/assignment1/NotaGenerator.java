/*
Nama    : Nadhira Raihana Hafez
NPM     : 2206082000
Kelas   : DDP2-G
*/

package assignments.assignment1;

//import library yang diperlukan
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;

//membuat class yang bernama sama dengan nama file
public class NotaGenerator {
    //membuat object scan untuk input
    private static final Scanner input = new Scanner(System.in);

     //membuat variable yang akan diperlukan
    static int characterValue, hargaLaundry, check;
    static String id, tanggalSelesaiStr, paketLaundry;

    //Method main, program utama berjalan
    public static void main(String[] args) {
    //variable untuk memulai jalannya program
    boolean programRuns = true;

    while (programRuns){
            printMenu(); //memunculkan menu

            //meminta input apa yang akan dilakukan oleh user
            System.out.print("Pilihan: "); 
            String pilihanCust = input.next(); //membuat variable untuk menampung pilihan customer
            System.out.println("================================");

            //if else untuk melanjutkan program sesuai dengan input user
            if (pilihanCust.equals("1") || pilihanCust.equals("2")){
                System.out.println("Masukkan nama Anda:"); //meminta input nama user
                input.nextLine();
                String namaCust = input.nextLine(); //membuat variable untuk menampung nama customer

                System.out.println("Masukkan nomor handphone Anda:"); //meminta input nomor hp user
                String nomorHp = input.nextLine(); 

                //while loop untuk mengecek karakter pada input no hp
                while (hasCharacter(nomorHp)){
                    System.out.println("Nomor hp hanya menerima digit"); //handle jika terdapat karakter pada no hp user
                    nomorHp = input.nextLine();
                }

                //membuat id untuk dijalankan ke program selanjutnya
                generateId(namaCust, nomorHp);

                //melanjutkan program jika input user = Generate ID
                if(pilihanCust.equals("1")){
                    System.out.println("ID Anda : "+id); //mencetak id yang sudah dibuat
                } else {
                    System.out.println("Masukkan tanggal terima:"); //meminta input tanggal terima laundry
                    String tanggalTerimaStr = input.nextLine(); 
                    
                    //memulai loop untu meminta input paket laundry
                    boolean isPaketLaundryValid = true;
                    while (isPaketLaundryValid){
                        hargaLaundry = 0;
                        System.out.println("Masukkan paket laundry:"); //meminta input paket laundry
                        paketLaundry = input.nextLine();

                        paketLaundry = paketLaundry.toLowerCase();
                        //memvalidasi dan menyesuaikan input user ke jalannya program
                        if (paketLaundry.equals("express") || paketLaundry.equals("fast") || paketLaundry.equals("reguler")){
                            isPaketLaundryValid = false; //keluar dari while loop jika input sudah sesuai
                        } else if (paketLaundry.equals("?")){
                            showPaket(); //memunculkan jenis paket jika input = ?
                        } else { //error message jika input user tidak sesuai paket yang tersedia
                                System.out.println("Paket "+paketLaundry+" tidak diketahui.");
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

                    //mencetak nota laundry
                    System.out.println("Nota Laundry");
                    System.out.println(generateNota(id, paketLaundry, Integer.parseInt(beratCucian), tanggalTerimaStr)); //memanggil generateNota untuk membuat ringkasan nota
                }
            
            //program jika input user = exit
            } else if (pilihanCust.equals("0")){
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                programRuns = false; //keluar dari loop program
            }else { //else jika input user tidak sesuai dengan pilihan program
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
    }
    
    // Method untuk menampilkan menu di NotaGenerator.
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    // Method untuk menampilkan paket.
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    //method untuk mengecek adanya karakter pada input user
    private static boolean hasCharacter(String nomor) {
        check = 0; //variable cek untuk menampung jumlah char
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

    //Method untuk membuat ID dari nama dan nomor handphone.
    public static String generateId(String nama, String nomorHP){
        String[] namaArr = nama.split(" "); //array untuk menampung nama user yang sudah di split
        nama = namaArr[0]; //mengambil nama depan pada array nama

        nama = nama.toUpperCase(); //mengubah nama menjadi uppercase

        characterValue = 7; //value = 7 untuk menghitung "-" pada id

        //loop untuk checksum
        for (int i = 0; i < (nama+nomorHP).length();i++){
            if ((int)(nama+nomorHP).charAt(i) >= 65 && (int)(nama+nomorHP).charAt(i) <= 90){
                characterValue += ((int)(nama+nomorHP).charAt(i) - 64); //checksum jika karater berupa huruf
            } else {
                characterValue += ((int)(nama+nomorHP).charAt(i) - 48); //checksum jika karater berupa angka
            }
        }

        //membuat dan mereturn id
        id = nama+"-"+nomorHP+"-"+String.format("%02d", characterValue);
        return id;
    }

    //Method untuk membuat Nota.
    public static String generateNota(String id, String paketLaundry, int berat, String tanggalTerimaStr){
        //membuat object formatter untuk mengubah format tanggal
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        //mengubah tanggal yang diinput user menjadi date-time object
        LocalDate tanggalTerima = LocalDate.parse(tanggalTerimaStr, formatter);

        //menyesuaikan lamanya laundry terhadap paket yang telah dipilih user
        if (paketLaundry.equals("express")){
            hargaLaundry = 12000; //menetapkan harga untuk paket yang dipilih user
            LocalDate tanggalSelesai = tanggalTerima.plusDays(1); //menambahkan tanggal dengan lama durasi yang disesuaikan dengan paket
            tanggalSelesaiStr = tanggalSelesai.format(formatter); //mengubah kembali date-time menjadi string yang sudah ditentukan formatnya
            
        } else if (paketLaundry.equals("fast")){
            hargaLaundry = 10000;
            LocalDate tanggalSelesai = tanggalTerima.plusDays(2);
            tanggalSelesaiStr = tanggalSelesai.format(formatter);

        } else if (paketLaundry.equals("reguler")){
            hargaLaundry = 7000;
            LocalDate tanggalSelesai = tanggalTerima.plusDays(3);
            tanggalSelesaiStr = tanggalSelesai.format(formatter);
        }

        //mereturn ringkasan nota yang sudah dibuat
        return 
        "ID    : " +id+"\n"+
        "Paket : "+paketLaundry+"\n" +
        "Harga :\n" +
        berat+" kg x "+hargaLaundry+" = "+(berat*hargaLaundry)+"\n" + //mengalikan berat dengan harga per-kilo untuk total harga
        "Tanggal Terima  : " + tanggalTerimaStr +"\n"+
        "Tanggal Selesai : "+ tanggalSelesaiStr;
    }
}
