package TrousProjekt.Tests;

import TrousProjekt.VektorUndMatrix.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VektorTest {

    private final Vektor v1 = new Vektor(1, 1, 1);
    private final Vektor v2 = new Vektor(2, 2, 2);
    private final Vektor v3 = new Vektor(-1, -1, -1);
    private final Vektor v4 = null;

    @Test
    void skaliere() {
        assertTrue(v1.skaliere(2).istGleich(v2));
        assertTrue(v1.skaliere(-1).istGleich(v3));
        assertTrue(v2.skaliere(-0.5).istGleich(v3));
    }

    @Test
    void addiere() {
        assertTrue(v1.addiere(v1).istGleich(v2));
    }

    @Test
    void getSkalarprodukt() {
        assertEquals(v1.getSkalarprodukt(v2), 6);
    }

    @Test
    void getLaenge() {
        assertEquals(v1.getLaenge(), Math.sqrt(3));
        assertEquals(v2.getLaenge(), Math.sqrt(12));
        assertEquals(v1.getLaenge(), Math.sqrt(3));
    }

    @Test
    void getWinkel() {
        assertEquals(v1.getWinkel(v2), Math.acos(6 / (Math.sqrt(3) * Math.sqrt(12))));
    }

    @Test
    void istIdentisch() {
        assertTrue(v1.istGleich(new Vektor(1, 1, 1)));
        assertTrue(v2.istGleich(new Vektor(2, 2, 2)));
    }

    @Test
    void multipliziere() {

        Matrix matrix = new Matrix(3,3);
        double[][] werte = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        matrix.setMatrix(werte);

        Vektor vektor = new Vektor(2, 2 ,2);
        Vektor test = new Vektor(6, 6, 6);

        assertTrue(vektor.multipliziere(matrix).istGleich(test));
    }
}