//import package/library yang diperlukan
package assignments.assignment3.nota;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;

public class Nota {
    //membuat object/variable yang diperlukan
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[1] ;
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int sisaHariPengerjaanTemp;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private String tanggalSelesaiStr;
    private String kompensasiKeterlambatan;
    private long hargaAkhir;
    static public int totalNota;

    //constructor class Nota
    public Nota(Member member, int berat, String paket, String tanggalMasuk) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.id = totalNota;
        totalNota++;

        //menginisiasikan sisa hari pekeraan berdasarkan paket yang dipilih
        if (this.paket.toLowerCase().equals("express")){
            this.sisaHariPengerjaan = 1;
            this.baseHarga = 12000;
        } else if (this.paket.toLowerCase().equals("fast")){
            this.sisaHariPengerjaan = 2;
            this.baseHarga = 10000;
        } else if (this.paket.toLowerCase().equals("reguler")){
            this.sisaHariPengerjaan = 3;
            this.baseHarga = 7000;
        }

        getTanggalSelesai();
        //memasukkan service cuci ke dalam array services
        CuciService cuci = new CuciService();
        services[0] = cuci;
    }

    //menambahkan service yang diminta oleh user ke array services
    public void addService(LaundryService service){
        LaundryService[] newServices = new LaundryService[services.length+1];
        for (int i = 0; i < services.length; i++) {
            newServices[i] = services[i];
            }
            services = newServices;
            newServices[services.length - 1] = service;
    
    }

    //string untuk do work
    public String kerjakan(){
        if (!this.isDone()){
            for (LaundryService service : services){//iterasi tiap service agar diselesaikan
                if (!service.isDone()){
                    return "Nota "+this.id+" : "+ service.doWork();
                }
            }}
        return "Nota "+this.id+" : "+ this.getNotaStatus();
    }

    //method untuk ganti hari
    public void toNextDay() {
        counterHariPengerjaan();
    }

    //calculate harga service
    public long calculateHarga(){
        if (this.sisaHariPengerjaan<0){
            this.sisaHariPengerjaanTemp = Math.abs(this.sisaHariPengerjaan);
            kompensasiKeterlambatan = "Ada kompensasi keterlambatan "+this.sisaHariPengerjaanTemp+" * 2000 hari";
            return hargaAkhir - (this.sisaHariPengerjaanTemp*2000); //hargaAkhir -> harga service
        } else {
            kompensasiKeterlambatan = "";
        }
        return hargaAkhir;
    }

    //method untuk mereturn status nota
    public String getNotaStatus(){
        if (this.isDone() == false){
            return "Belum selesai.";
        } else {
            return "Sudah selesai.";
        }
    }

    //method toString untuk print nota
    @Override
    public String toString(){
        String harga = this.berat +" kg x "+ this.baseHarga +" = "+(this.berat * this.baseHarga)+"\n";
        
        return
        "[ID Nota = " + this.id + "]\n"+
        "ID    : " + this.member.getId() +"\n"+
        "Paket : "+ this.paket +"\n" +
        "Harga :\n" +
        harga + 
        "Tanggal Terima  : " + this.tanggalMasuk +"\n"+
        "Tanggal Selesai : " + this.tanggalSelesaiStr +"\n"+
        "--- SERVICE LIST ---\n"+
        printService() +
        "Harga Akhir: "+ (calculateHarga()+(this.berat * this.baseHarga)) +" "+ kompensasiKeterlambatan + "\n";
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public int getIdNota() {
        return id;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

     //method untuk menambahkan tanggal dengan lama durasi yang disesuaikan dengan paket
    public String getTanggalSelesai(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggalMasuk = LocalDate.parse(this.tanggalMasuk, formatter);
        LocalDate tanggalSelesai = tanggalMasuk.plusDays(this.sisaHariPengerjaan); //menambahkan tanggal dengan lama durasi yang disesuaikan dengan paket
        tanggalSelesaiStr = tanggalSelesai.format(formatter);
        return tanggalSelesaiStr;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }

    //method untuk mengecek apakah service service pada nota sudah selesai atau belunm
    public boolean isDone() {
        for (LaundryService service : services){
            if (service.isDone() == false){
                return false;
            }
        } 
        return true;
    }

    public LaundryService[] getServices(){
        return services;
    }

    //method untuk memunculkan service service yang digunakan pada nota
    public String printService(){
        String serviceStr = "";
        this.hargaAkhir = 0;
        for (LaundryService service : services){
            this.hargaAkhir += service.getHarga(berat);
            serviceStr += "-"+service.getServiceName()+" @ Rp." + service.getHarga(berat) + "\n";
        }
        return serviceStr;
    }

    //method untuk decrement sisa hari pengerjaan
    public void counterHariPengerjaan(){
        if (this.isDone() == false){
            this.sisaHariPengerjaan--;
        }
    }
}
