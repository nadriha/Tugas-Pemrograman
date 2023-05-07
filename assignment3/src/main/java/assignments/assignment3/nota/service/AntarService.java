package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    int doWorkCounter; //counter untuk mengecek service sudah dijalankan atau belum

    @Override
    public String doWork() {
        doWorkCounter++;
        return "Sedang mengantar..."; //print status
    }

    //method untuk mengecek service sudah dijalankan atau belum
    @Override
    public boolean isDone() {
        if (doWorkCounter != 0){
            return true;
        } else {
            return false;
        }
    }

    //getter
    @Override
    public long getHarga(int berat) {
        if (berat <= 4){
            return 2000;
        }
        else{
            return (2000)+((berat-4)*500);
        }
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
