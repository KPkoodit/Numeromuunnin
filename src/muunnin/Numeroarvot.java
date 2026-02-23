package muunnin;

import java.util.EnumMap;

public class Numeroarvot {
    private EnumMap <Numero, Integer> numeroarvot;

    public Numeroarvot() {
        this.numeroarvot = new EnumMap<Numero, Integer>(Numero.class);
        this.numeroarvot.put(Numero.I, 1);
        this.numeroarvot.put(Numero.V, 5);
        this.numeroarvot.put(Numero.X, 10);
        this.numeroarvot.put(Numero.L, 50);
        this.numeroarvot.put(Numero.C, 100);
        this.numeroarvot.put(Numero.D, 500);
        this.numeroarvot.put(Numero.M, 1000);
    }

    public EnumMap<Numero, Integer> getNumeroarvot() {
        return this.numeroarvot;
    }
}
