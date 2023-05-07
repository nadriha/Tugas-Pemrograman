package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    int doWorkCounter;
    @Override
    public String doWork() {
        doWorkCounter++;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        if (doWorkCounter != 0 ){
            return true;
            } else {
                return false;
            }
    }

    @Override
    public long getHarga(int berat) {
        return berat*1000;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
