package assignments.assignment3.nota;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService[1] ;
    private long baseHarga;
    private int sisaHariPengerjaan;
    private int berat;
    private int id;
    private String tanggalMasuk;
    private String tanggalSelesaiStr;
    private boolean isDone;
    private long hargaAkhir;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggalMasuk) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.id = totalNota;
        totalNota++;

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

        CuciService cuci = new CuciService();
        services[0] = cuci;
    }

    public void addService(LaundryService service){
        LaundryService[] newServices = new LaundryService[services.length+1];
        for (int i = 0; i < services.length; i++) {
            newServices[i] = services[i];
            }
            services = newServices;
        
            newServices[services.length - 1] = service;
    
    }

    public String kerjakan(){
        // TODO
        return "";
    }
    public void toNextDay() {
        // TODO
    }

    public long calculateHarga(){
        // TODO
        return -1;
    }

    public String getNotaStatus(){
        // TODO
        return "";
    }

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
        "Tanggal Selesai : " + getTanggalSelesai() +"\n"+
        "Status          : " + getNotaStatus() +"\n"+
        "--- SERVICE LIST ---\n"+
        printService() +
        "Harga Akhir: "+ (this.hargaAkhir+(this.berat * this.baseHarga));
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
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }

    public String printService(){
        String serviceStr = "";
        for (LaundryService service : services){
            System.out.println(service.getServiceName());
            this.hargaAkhir += service.getHarga(berat);
            serviceStr += "-"+service.getServiceName()+" @ Rp." + service.getHarga(berat) + "\n";
        }
        return serviceStr;
    }
}
