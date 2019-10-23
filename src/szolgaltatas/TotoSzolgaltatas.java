package szolgaltatas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import tarolo.Eredmeny;
import tarolo.Fordulo;

public class TotoSzolgaltatas
{
    private List<Fordulo> forduloLista = new ArrayList<>();
    List<Eredmeny> tippekLista = new ArrayList<>();
    
    public TotoSzolgaltatas(String fajlNev)
    {
        try
        {
            FileReader fr = new FileReader(fajlNev);
            BufferedReader br = new BufferedReader(fr);
            String sor = br.readLine();
            while (sor != null)
            {
                Fordulo f=new Fordulo(sor);
                this.forduloLista.add(f);
                sor=br.readLine();
            }
            fr.close();
            br.close();
        }
        catch (Exception e)
        {
            System.out.printf("Hiba " + e);
        }
    }
    //Melyik volt a legnagyobb nyeremény, amit valaha rögzítettek?
    public int getRekordNyeremeny()
    {
        int rekord = 0;
        for (int i = 0; i < this.forduloLista.size(); i++)
        {
            if (rekord < this.forduloLista.get(i).getMaxNyeremeny())
            {
                rekord = this.forduloLista.get(i).getMaxNyeremeny();
            }
        }
        return rekord;
    }
    //Győzelmek arányának számítása
    //Egyes győz
    public int getEgyNyer()
    {
        int winCount = 0;
        for (int i = 0; i < this.forduloLista.size(); i++)
        {
            for (int j = 0; j < this.forduloLista.get(i).getEredmenyek().size(); j++)
            {
                if (this.forduloLista.get(i).getEredmenyek().get(j).equals(Eredmeny._1))
                {
                    winCount++;
                }
            }
        }
        return winCount;
    }
    //Kettes győz
    public int getKettoNyer()
    {
        int winCount = 0;
        for (int i = 0; i < this.forduloLista.size(); i++)
        {
            for (int j = 0; j < this.forduloLista.get(i).getEredmenyek().size(); j++)
            {
                if (this.forduloLista.get(i).getEredmenyek().get(j).equals(Eredmeny._2))
                {
                    winCount++;
                }
            }
        }
        return winCount;
    }
    //Döntetlen
    public int getDontetlen()
    {
        int dontetlen = 0;
        for (int i = 0; i < this.forduloLista.size(); i++)
        {
            for (int j = 0; j < this.forduloLista.get(i).getEredmenyek().size(); j++)
            {
                if (this.forduloLista.get(i).getEredmenyek().get(j).equals(Eredmeny.X))
                {
                    dontetlen++;
                }
            }
        }
        return dontetlen;
    }
    //Nyeremény és találatok megállapítása
     public void getEredmeny(String datumSzamol, String tippsor)
    {
        String[] datumok = datumSzamol.replace(".", ",").split(",");
        LocalDate datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));

        String[] tippek = tippsor.toUpperCase().split("");
        for (String tipp : tippek) {
            switch (tipp) {
                case "1": tippekLista.add(Eredmeny._1); break;
                case "2": tippekLista.add(Eredmeny._2); break;
                case "X": tippekLista.add(Eredmeny.X); break;
            }
        }
        
        int talalatSzama = 0;
        int nyeremeny = 0;
        for (Fordulo fordulo : this.forduloLista) {
            if (fordulo.getDatum().equals(datum)) {
                for (int j = 0; j < fordulo.getEredmenyek().size(); j++) {
                    if (fordulo.getEredmenyek().get(j) == tippekLista.get(j)) {
                        talalatSzama++;
                    }
                }
                if (talalatSzama > 9) {
                    for (int j = 0; j < fordulo.getTalalatok().size(); j++) {
                        nyeremeny = fordulo.getTalalatok().get(j).getNyeremeny();
                    }
                    System.out.printf("Eredmény: találat: " + talalatSzama + ", nyeremény: " + nyeremeny + " Ft!");
                } else {
                    System.out.println("Nem nyertél!");
                }
            }
        }
    }
    //Dátumvizsgálat
    public boolean HelyesDatumE(String datumEllenorzes)
    {
        String[] datumok = datumEllenorzes.replace(".", ",").split(",");
        LocalDate datum = LocalDate.of(Integer.parseInt(datumok[0]), Integer.parseInt(datumok[1]), Integer.parseInt(datumok[2]));
        
        boolean helyesDatum=false;
        for (Fordulo fordulo : this.forduloLista) {
            if (fordulo.getDatum().equals(datum)) {
                helyesDatum = true;
            }
        }
        return helyesDatum;
    }
}
