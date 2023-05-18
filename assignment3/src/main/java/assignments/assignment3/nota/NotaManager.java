//import package/library yang diperlukan
package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaManager {
    //membuat object yang diperlukan
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        cal.add(Calendar.DAY_OF_WEEK, 1);

        for (Nota nota : notaList){//iterasi agar tiap nota berganti hari
            nota.toNextDay(); 
        }
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){
        Nota[] newNotaList = new Nota[notaList.length+1];
        for (int i = 0; i < notaList.length; i++) {
            newNotaList[i] = notaList[i];
          }
          notaList = newNotaList;
          newNotaList[notaList.length - 1] = nota;
    }
    
}
