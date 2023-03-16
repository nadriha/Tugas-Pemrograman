package assignments.assignment2;

//import library yang diperlukan
import assignments.assignment1.NotaGenerator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//class sesuai dengan nama file yang dibuat
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

    //membuat constructor untuk class nota
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.idNota = countNota;
        countNota += 1; //count up nota

        //menginisiasi harga laundry berdasarkan jenis paket
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

    //method untuk mengecek status nota
    public boolean notaReady(){
        if (this.sisaHariPengerjaan == 0){
            return true;
        } else {
            return false;
        }
    }

    //method untuk mencetak status nota
    public String getStatusNota(){
        if (this.sisaHariPengerjaan == 0){
            return "Sudah dapat diambil!";
        }
        else{
            return "Belum bisa diambil :(";
        }
    }

    //method untuk mengurangi sisa hari pengerjaan tiap berganti hari
    public void counterHariPengerjaan(){
        if (this.sisaHariPengerjaan != 0){
            this.sisaHariPengerjaan -= 1;
        }
    }

    //method untuk menambahkan tanggal masuk dengan lama pengerjaan
    public String getTanggalSelesai(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggalMasuk = LocalDate.parse(this.tanggalMasuk, formatter);
        LocalDate tanggalSelesai = tanggalMasuk.plusDays(this.sisaHariPengerjaan); //menambahkan tanggal dengan lama durasi yang disesuaikan dengan paket
        tanggalSelesaiStr = tanggalSelesai.format(formatter);
        return tanggalSelesaiStr;
    }

    //method get Id
    public int getId(){
        return this.idNota;
    }

    //method untuk mencetak nota
    public String getNota(){
        String harga;
        if (this.member.getBonus()){ //mengecek jika member sudah mendapatkan diskon atau belum
            harga = String.format("%d kg x %d = %d = %d (Discount member 50%%!!!)\n",
                                    this.berat, this.hargaLaundry,(this.berat * this.hargaLaundry),(this.berat * this.hargaLaundry)/2);
        } else {
            harga = this.berat +" kg x "+ this.hargaLaundry +" = "+(this.berat * this.hargaLaundry)+"\n";
        }
        return //mereturn nota
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
