import java.util.Scanner;

import muunnin.Muunnin;
import muunnin.Numeroarvot;
import muunnin.Tarkastus;
import muunnin.Testitapaus;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Numeroarvot numeroarvot = new Numeroarvot();
        Muunnin muunnin = new Muunnin(numeroarvot);
        Testitapaus testitapaus = new Testitapaus(numeroarvot);
        Tarkastus tarkastus = new Tarkastus(testitapaus);

        boolean kaynnissa = true;

        System.out.println("Tervetuloa numeromuuntimeen. Anna roomalainen numero. Jos haluat lopettaa, paina Q.");
        
        while (kaynnissa) {
            try {
                System.out.println("Anna roomalainen numero:");

                // Luetaan käyttäjän syöte.
                String roomalainen = scanner.nextLine();
                String roomalainenIso = roomalainen.toUpperCase();

                // Tarkistetaan, haluaako käyttäjä lopettaa.
                if (roomalainenIso.equals("Q")) {
                    kaynnissa = false;
                    System.out.println("Numeromuunnin sammutettu.");
                    break;
                }

                // Tarkistetaan syötteen kelvollisuus.
                tarkastus.onkoKelvollinen(roomalainenIso);

                // Muunnetaan roomalainen numero länsimaiseksi numeroksi.
                int luku = muunnin.muunna(roomalainenIso, 0);
                System.out.println(roomalainen + " vastaa lukua " + luku);

            } catch (Exception e) {
                System.out.println("Jokin meni pieleen." + e);
            }
        }

        scanner.close();
    }
}

