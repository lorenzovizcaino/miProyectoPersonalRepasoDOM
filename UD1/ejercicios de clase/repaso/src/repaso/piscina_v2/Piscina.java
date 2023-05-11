package repaso.piscina_v2;

import java.io.Serializable;

public class Piscina implements Serializable {
    private int longitudPiscina;
    private int longitudParcela;
    private int anchuraPiscina;
    private int anchuraParcela;
    private int aforo;
    private final int DIST_MAXIMA=2;

    public Piscina(int longitudPiscina, int anchuraPiscina, int longitudParcela, int anchuraParcela){
        this.longitudPiscina=longitudPiscina;
        this.anchuraPiscina=anchuraPiscina;
        this.longitudParcela=longitudParcela;
        this.anchuraParcela=anchuraParcela;
        this.aforo=CalcularAforo();

    }

    public int CalcularAforo() {
        int superficiePiscina;
        int superficieParcela;
        int aforo;
        superficiePiscina=longitudPiscina*anchuraPiscina;
        superficieParcela=longitudParcela*anchuraParcela;
        aforo=Math.min((superficiePiscina/DIST_MAXIMA), superficieParcela/DIST_MAXIMA);
        return aforo;

    }

    @Override
    public String toString() {
        return "Piscina{" +
                "longitudPiscina=" + longitudPiscina +
                ", anchuraPiscina=" + anchuraPiscina +
                ", longitudParcela=" + longitudParcela +
                ", anchuraParcela=" + anchuraParcela +
                '}';
    }
}
