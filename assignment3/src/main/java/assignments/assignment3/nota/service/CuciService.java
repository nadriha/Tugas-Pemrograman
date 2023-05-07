package assignments.assignment3.nota.service;


public class CuciService implements LaundryService{
    int doWorkCounter;
    @Override
    public String doWork() {
        doWorkCounter++;
        return "Sedang mencuci...";
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
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
