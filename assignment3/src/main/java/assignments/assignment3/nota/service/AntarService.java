package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    int doWorkCounter;
    @Override
    public String doWork() {
        doWorkCounter++;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        if (doWorkCounter != 0){
        return true;
        } else {
            return false;
        }
    }

    @Override
    public long getHarga(int berat) {
        if (berat <= 4){
            return berat*1000;
        }
        else{
            return (4000)+((berat-4)*500);
        }
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
