package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

//class sesuai dengan nama file yang dibuat
public class Member {
    // Menambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    //membuat consructor class
    public Member(String nama, String noHp) {
        // Membuat constructor untuk class
        this.nama = nama;
        this.noHp = noHp;
        this.bonusCounter = 0;
    }

    //method untuk membuat id
    public String generateId(String nama, String noHp){
        String[] namaArr = this.nama.split(" "); //array untuk menampung nama user yang sudah di split
        String namaUser = namaArr[0]; //mengambil nama depan pada array nama

        int characterValue = 7; //value = 7 untuk menghitung "-" pada id

        //loop untuk checksum
        String namaNoHp = namaUser + this.noHp;
        for (char c : namaNoHp.toCharArray()) {
            if (Character.isDigit(c)){
                characterValue += c - '0';
            } else if (Character.isLetter(c)){
                characterValue += Character.toUpperCase(c) - 'A' + 1;
            } else {
                characterValue += 7; //jika symbol maka ditambah 7
            }
        }

        //membuat dan mereturn id
        String lastTwoChars = String.format("%02d", characterValue % 100);
        return this.id = String.format("%s-%s-%s", namaUser.toUpperCase(), this.noHp, lastTwoChars);
        
    }

    //getter untuk id
    public String getId(){
        generateId(this.nama, this.noHp);
        return this.id;
    }

    //getter untuk nama
    public String getNama(){
        return this.nama;
    }

    //method untuk menambahkan bonus counter
    public void countBonus(){
        this.bonusCounter += 1;
    }

    //method untuk mengecek bonus diskon
    public boolean getBonus(){
        if (this.bonusCounter % 3 == 0){
            return true;
        } else {
        return false;
    }
    }
}
