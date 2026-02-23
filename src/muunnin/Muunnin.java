package muunnin;

import java.util.EnumMap;

public class Muunnin {
    private EnumMap <Numero, Integer> roomalaiset;
    private int lukuarvo;

    public Muunnin (Numeroarvot numeroarvot) {
        this.roomalaiset = numeroarvot.getNumeroarvot();
        this.lukuarvo = 0;
    }

    public int muunna(String roomalainen, int indeksi) {

        // Alustetaan uusi luku.
        if (indeksi == 0) {
            lukuarvo = 0;
        }

        // Verrataan kahta merkkiä toisiinsa lukusuunnassa.
        // Vähennetään pienempi luku isommasta, jos pienempi on ennen isompaa.
        // Tällöin käsitellään kaksi merkkiä samaan aikaan ja lisätään tulos kymmenlukuun.
        // Muussa tilanteessa käsitellään merkki kerrallaan ja lisätään lukuarvo kymmenlukuun.
        if (roomalainen.length() - indeksi >= 2){
            String ekaMerkki = roomalainen.substring(indeksi, indeksi + 1);
            int ekaLuku = roomalaiset.get(Numero.valueOf(ekaMerkki));
            String tokaMerkki = roomalainen.substring(indeksi + 1, indeksi + 2);
            int tokaLuku = roomalaiset.get(Numero.valueOf(tokaMerkki));
            
            if (ekaLuku < tokaLuku) {
                lukuarvo += tokaLuku - ekaLuku;
                return muunna(roomalainen, indeksi + 2);
            } else {
                lukuarvo += ekaLuku;
                return muunna(roomalainen, indeksi + 1);
            }
        }

        // Käsitellään mahdollinen viimeinen merkki.
        if (roomalainen.length() - indeksi == 1) {
            String vikaMerkki = roomalainen.substring(roomalainen.length() - 1);
            int vikaLuku = roomalaiset.get(Numero.valueOf(vikaMerkki));
            lukuarvo += vikaLuku;
        }

        return lukuarvo;
    }
}
