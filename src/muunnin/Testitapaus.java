package muunnin;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Testitapaus {
    private Set<Character> sallitut;
    private EnumMap <Numero, Integer> roomalaiset;

    public Testitapaus(Numeroarvot numeroarvot) {
        this.sallitut = new HashSet<>();

        Numero[] numerot = Numero.values();
        for (Numero numero : numerot) {
            char merkki = numero.name().charAt(0);
            sallitut.add(merkki);
        }

        this.roomalaiset = numeroarvot.getNumeroarvot();
    }   
    
    // Tarkistetaan, että syöte ei ole tyhjä.
    public void tyhjaSyote (char[] merkit) {
        if (merkit.length == 0) {
            throw new IllegalArgumentException("Numeroa ei ole vielä annettu.");
        }
    }

    // Tarkistetaan, että syötteessä on vain roomalaisia numeroita välillä I-M.
    public void merkitRoomalaisia(char[] merkit) {
        for (char merkki : merkit) {
            if (!sallitut.contains(merkki)) {
                throw new IllegalArgumentException(merkki + " ei kuulu roomalaisiin numeroihin I-M.");
        }
        }
    }

    // Tarkistetaan, että mitään merkkiä ei ole yli kolmea peräkkäin.
    public void enintaanKolmeSamaaPerakkain (char[] merkit) {
        int laskuri = 0;
        for (int i = 0; i < merkit.length - 1; i++) {
            if (merkit[i] == merkit[i + 1]) {
                laskuri++;
                if (laskuri > 2) {
                    throw new IllegalArgumentException("Merkki " + merkit[i] + " esiintyy yli kolme kertaa perakkain.");
                }
            } else {
                laskuri = 0;
            }
        }
    }

    // Tarkistetaan, että luvussa on vain yksi V, L tai D.
    public void enintaanYksiMerkki (char tutkittava, char[] merkit) {
        int laskuri = 0;

        for (char merkki : merkit) {
            if (merkki == tutkittava) {
                laskuri += 1;
                break;
            }
        }

        if (laskuri > 1) {
            throw new IllegalArgumentException("Merkki " + tutkittava + " ei voi esiintya useammin kuin kerran.");
        }
    }

    // Tarkistetaan, ettei isomman numeron vasemmalla puolella ole kahta samaa pienempää numeroa.
    public void kaksiPienempaaEnnenIsoa (char[] merkit) {
        for (int i = 2; i < merkit.length; i++) {
            if (roomalaiset.get(Numero.valueOf(String.valueOf(merkit[i-1]))) < roomalaiset.get(Numero.valueOf(String.valueOf(merkit[i]))) && merkit[i-1] == merkit[i-2]){
                throw new IllegalArgumentException("Merkkia " + merkit[i-1] + " ei voi esiintya kahta kertaa isomman numeron vasemmalla puolella.");
            }
        }
    }

    // Tehdään listat merkkien indekseille merkkien sijainnin vertailua varten.
    // Tämän jälkeen tarkistetaan, että merkkien sijainnit ovat sallittuja suhteessa toisiin merkkeihin.
    public void sijaintiOk (char[] merkit) {
        List<Integer> indeksitM = new ArrayList<>();
        List<Integer> indeksitD = new ArrayList<>();
        List<Integer> indeksitC = new ArrayList<>();
        List<Integer> indeksitL = new ArrayList<>();
        List<Integer> indeksitX = new ArrayList<>();
        List<Integer> indeksitV = new ArrayList<>();
        List<Integer> indeksitI = new ArrayList<>();

        for (int i = 0; i < merkit.length; i++) {
            switch (merkit[i]) {
                case 'M':
                    indeksitM.add(i);
                    break;
                case 'D':
                    indeksitD.add(i);
                    break;
                case 'C':
                    indeksitC.add(i);
                    break;
                case 'L':
                    indeksitL.add(i);
                    break;
                case 'X':
                    indeksitX.add(i);
                    break;
                case 'V':
                    indeksitV.add(i);
                    break;
                case 'I':
                    indeksitI.add(i);
                    break;
                default:
                    System.out.println("Virhe: " + merkit[i] + " ei kuulu roomalaisiin numeroihin I-M.");
                    break;
            }
        }
        
        // Tarkistetaan, ettei M:n edessä ole soveltumattomia merkkejä.
        if (!merkkienSijaintiOk(indeksitM, List.of(indeksitD, indeksitL, indeksitX, indeksitV, indeksitI))) {
            throw new IllegalArgumentException("Ennen merkkia M ei voi esiintya merkkeja D, L, X, V tai I.");
        }
        
        // Tarkistetaan, ettei D:n edessä ole soveltumattomia merkkejä.
        if (!merkkienSijaintiOk(indeksitD, List.of(indeksitL, indeksitX, indeksitV, indeksitI))) {
            throw new IllegalArgumentException("Ennen merkkia D ei voi esiintya merkkeja L, X, V tai I.");
        }

        // Tarkistetaan, ettei C:n edessä ole soveltumattomia merkkejä.
        if (!merkkienSijaintiOk(indeksitC, List.of(indeksitL, indeksitV, indeksitI))) {
            throw new IllegalArgumentException("Ennen merkkia C ei voi esiintya merkkeja L, V tai I.");
        }

        // Tarkistetaan, ettei L:n edessä ole soveltumattomia merkkejä.
        if (!merkkienSijaintiOk(indeksitL, List.of(indeksitV, indeksitI))) {
            throw new IllegalArgumentException("Ennen merkkia L ei voi esiintya merkkeja V tai I.");
        }

        // Tarkistetaan, ettei X:n edessä ole soveltumattomia merkkejä.
        if (!merkkienSijaintiOk(indeksitX, List.of(indeksitV))) {
            throw new IllegalArgumentException("Ennen merkkia X ei voi esiintya merkkiä V.");
        }

        // Tarkistetaan, että kaikki I:t ovat peräkkäin.
        for (int i = 0; i < indeksitI.size() - 1; i++) {
            if (indeksitI.get(i) + 1 != indeksitI.get(i + 1)) {
                throw new IllegalArgumentException("Kaikkien I-merkkien tulee olla perakkain.");
            }
        }

        // Tarkistetaan, onko luvussa X- ja I-merkkejä ja katsotaan, ettei I:n jälkeen ei tule kahta X-merkkiä.
        if (indeksitX.size() >= 2 && indeksitI.size() > 0) {
            int viimeisinI = indeksitI.get(indeksitI.size() - 1);
            if (indeksitX.get(0) > viimeisinI) {
                throw new IllegalArgumentException("I:n jalkeen voi olla vain yksi X-merkki.");
            }
        }
    }

    private boolean merkkienSijaintiOk (List<Integer> merkinIndeksit, List<List<Integer>> epasopivatIndeksit) {
        List<Integer> epasopivat = new ArrayList<>();

        // Lisätään kaikki epäsopivat indeksit yhteen listaan.
        for (List<Integer> lista : epasopivatIndeksit) {
            epasopivat.addAll(lista);
        }

        // Verrataan kirjaimen indeksejä epäsopivien indeksien kanssa.
        for (Integer indeksiE : epasopivat) {
            for (Integer indeksiM : merkinIndeksit) {
                if (indeksiE < indeksiM) {
                    return false;
                }
            }
        }
       
        return true;
    }
}
