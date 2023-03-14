package assignments.assignment2;

import assignments.assignment1.NotaGenerator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota {
    // Menambahkan attributes yang diperlukan untuk class ini
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private static int countNota;
    private int hargaLaundry;
    private String tanggalSelesaiStr;

    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.idNota = countNota;
        countNota += 1;


        if (this.paket.toLowerCase().equals("express")){
            this.sisaHariPengerjaan = 1;
            this.hargaLaundry = 12000;
        } else if (this.paket.toLowerCase().equals("fast")){
            this.sisaHariPengerjaan = 2;
            this.hargaLaundry = 10000;
        } else if (this.paket.toLowerCase().equals("reguler")){
            this.sisaHariPengerjaan = 3;
            this.hargaLaundry = 7000;
        }
    }

    public boolean notaReady(){
        if (this.sisaHariPengerjaan == 0){
            return true;
        } else {
            return false;
        }
    }

    public String getStatusNota(){
        if (this.sisaHariPengerjaan == 0){
            return "Sudah dapat diambil!";
        }
        else{
            return "Belum bisa diambil :(";
        }
    }

    public void counterHariPengerjaan(){
        if (this.sisaHariPengerjaan != 0){
            this.sisaHariPengerjaan -= 1;
        }
    }

    public String getTanggalSelesai(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggalMasuk = LocalDate.parse(this.tanggalMasuk, formatter);
        LocalDate tanggalSelesai = tanggalMasuk.plusDays(this.sisaHariPengerjaan); //menambahkan tanggal dengan lama durasi yang disesuaikan dengan paket
        tanggalSelesaiStr = tanggalSelesai.format(formatter);
        return tanggalSelesaiStr;
    }

    public int getId(){
        return this.idNota;
    }

    public String getNota(){
        String harga;
        if (this.member.getBonus()){
            harga = String.format("%d kg x %d = %d = %d (Discount member 50%%!!!)\n",
                                    this.berat, this.hargaLaundry,(this.berat * this.hargaLaundry),(this.berat * this.hargaLaundry)/2);
        } else {
            harga = this.berat +" kg x "+ this.hargaLaundry +" = "+(this.berat * this.hargaLaundry)+"\n";
        }
        return 
        "Berhasil menambahkan nota!\n"+
        "[ID Nota = " + this.idNota + "]\n"+
        "ID    : " + this.member.getId() +"\n"+
        "Paket : "+ this.paket +"\n" +
        "Harga :\n" +
        harga + 
        "Tanggal Terima  : " + this.tanggalMasuk +"\n"+
        "Tanggal Selesai : " + getTanggalSelesai() +"\n"+
        "Status          : " + getStatusNota();
    }

}   
