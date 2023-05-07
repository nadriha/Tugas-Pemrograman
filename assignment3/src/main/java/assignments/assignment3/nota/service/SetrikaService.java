package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    int doWorkCounter; //counter untuk mengecek service sudah dijalankan atau belum

    @Override
    public String doWork() {
        doWorkCounter++;
        return "Sedang menyetrika..."; //print status
    }

    //method untuk mengecek service sudah dijalankan atau belum
    @Override
    public boolean isDone() {
        if (doWorkCounter != 0 ){
            return true;
            } else {
                return false;
            }
    }

    //getter
    @Override
    public long getHarga(int berat) {
        return berat*1000;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
