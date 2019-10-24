package toto_alkalmazas;

import java.util.Scanner;
import szolgaltatas.TotoSzolgaltatas;

public class Toto_alkalmazas {

    static TotoSzolgaltatas toto = new TotoSzolgaltatas("toto.csv");
    public static void main(String[] args) {
        //Legnagyobb nyeremény
        LegnagyobbNyeremeny();
        
        //Esélykalkulálás, kiírás
        Eselyek();
        
        //Játék
        Jatek();
    }
    static void LegnagyobbNyeremeny(){
        System.out.print("A legnagyobb nyeremeny amit rogzitettek: "+toto.getRekordNyeremeny() + " Ft\n");
    }
    static void Eselyek(){
        double osszes = toto.getEgyNyer()+toto.getKettoNyer()+toto.getDontetlen();
        double EgySzazalek=Math.round((toto.getEgyNyer() / osszes * 100) * 100.0) / 100.0;
        double KettoSzazalek=Math.round((toto.getKettoNyer() / osszes * 100) * 100.0) / 100.0;
        double XSzazalek=Math.round(100-(EgySzazalek+KettoSzazalek));
        System.out.print("Statisztika: #1 csapat nyert: " + EgySzazalek + "%, #2 csapat nyert: " + KettoSzazalek + "%, dontetlen: " + XSzazalek + "%\n");
    }
    static void Jatek(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Kerem adjon meg egy datumot (pl: 2019.05.24): ");
        String datum = sc.next();
        if (toto.HelyesDatumE(datum) == true)
        {
            String tippsor = "";
            do
            {
                System.out.print("Kerem adjon meg egy tippet (1, 2 vagy x-): ");
                tippsor += sc.next();
            }
            while(tippsor.length() != 14);
            toto.getEredmeny(datum, tippsor);
        }
        else
        {
            System.out.println("Helytelen datum!");
        }
    }
}