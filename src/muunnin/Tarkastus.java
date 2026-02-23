package muunnin;

public class Tarkastus {
    Testitapaus testitapaus;

    public Tarkastus(Testitapaus testitapaus) {
        this.testitapaus = testitapaus;
    }

    public void onkoKelvollinen(String roomalainen) {
        char[] roomalainenTaulukko = roomalainen.toCharArray();

        // Tarkistetaan, että syöte ei ole tyhjä.
        testitapaus.tyhjaSyote(roomalainenTaulukko);
    
        // Tarkistetaan, että syötteessä on vain roomalaisia numeroita välillä I-M.
        testitapaus.merkitRoomalaisia(roomalainenTaulukko);

        // Tarkistetaan, että mitään merkkiä ei ole yli kolmea peräkkäin.
        testitapaus.enintaanKolmeSamaaPerakkain(roomalainenTaulukko);

        // Tarkistetaan, että luvussa on vain yksi V, L tai D.
        testitapaus.enintaanYksiMerkki('V', roomalainenTaulukko);
        testitapaus.enintaanYksiMerkki('L', roomalainenTaulukko);
        testitapaus.enintaanYksiMerkki('D', roomalainenTaulukko);

        // Tarkistetaan, että pienempiä numeroita ei ole yli kahta ennen isompaa numeroa.
        testitapaus.kaksiPienempaaEnnenIsoa(roomalainenTaulukko);

        // Tarkistetaan, että merkkien sijainnit ovat sallittuja suhteessa toisiin merkkeihin.
        testitapaus.sijaintiOk(roomalainenTaulukko);        
    }
}
