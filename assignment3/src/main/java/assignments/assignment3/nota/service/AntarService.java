package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    @Override
    public String doWork() {
        // TODO
        return "";
    }

    @Override
    public boolean isDone() {
        // TODO
        return false;
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
