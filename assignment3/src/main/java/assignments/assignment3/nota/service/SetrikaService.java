package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
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
        return berat*1000;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
