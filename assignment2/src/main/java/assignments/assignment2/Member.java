package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    // Menambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp) {
        // Membuat constructor untuk class
        this.nama = nama;
        this.noHp = noHp;
        this.bonusCounter = 0;
    }

    public String generateId(String nama, String noHp){
        String[] namaArr = this.nama.split(" "); //array untuk menampung nama user yang sudah di split
        String namaUser = namaArr[0]; //mengambil nama depan pada array nama

        int characterValue = 7; //value = 7 untuk menghitung "-" pada id

        //loop untuk checksum
        String namaNoHp = namaUser + this.noHp;
        for (char c : namaNoHp.toCharArray()) {
            if (Character.isDigit(c)){
                characterValue += c - '0';
            } else {
                characterValue += Character.toUpperCase(c) - 'A' + 1;
            }
        }

        //membuat dan mereturn id
        String lastTwoChars = String.format("%02d", characterValue % 100);
        return this.id = String.format("%s-%s-%s", namaUser.toUpperCase(), this.noHp, lastTwoChars);
        
    }

    public String getId(){
        generateId(this.nama, this.noHp);
        return this.id;
    }

    public String getNama(){
        return this.nama;
    }

    public void countBonus(){
        this.bonusCounter += 1;
    }

    public boolean getBonus(){
        if (this.bonusCounter % 3 == 0){
            return true;
        } else {
        return false;
    }
    }
}
