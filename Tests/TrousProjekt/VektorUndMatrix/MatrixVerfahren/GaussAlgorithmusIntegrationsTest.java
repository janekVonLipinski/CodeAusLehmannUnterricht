package TrousProjekt.VektorUndMatrix.MatrixVerfahren;

import TrousProjekt.VektorUndMatrix.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GaussAlgorithmusIntegrationsTest {

    private final GaussAlgorithmus gaussAlgorithmus = new GaussAlgorithmus();

    @Test
    void Given_matrix_ist_valide_Then_Vektor_ist_gleich() {

        double[][] test = {{1,0,2}, {3,2,1}, {4,1,3}};
        double[] vektorArray = {1,0,0};

        Matrix matrix = new Matrix(test);
        Vektor vektor = new Vektor(vektorArray);

        Vektor loesung = gaussAlgorithmus.loeseGleichungssystem(matrix, vektor);

        double[] korrekt = {-1, 1, 1};
        Vektor korrekteLoesung = new Vektor(korrekt);

        System.out.println(Arrays.toString(loesung.getVektorWerte()));

        assertArrayEquals(korrekteLoesung.getVektorWerte(), loesung.getVektorWerte());
    }

    @Test
    void Given_gleichung_ist_nicht_loesbar_Return_null() {
        double[][] matrixWerte = {{1, 1, -1, -1}, {2, 5, -7, -5}, {2, -1, 1, 3}, {5, 2, -4, 2}};
        Matrix matrix = new Matrix(matrixWerte);
        double[] loesungsWerte = {1, -2, 4, 6};
        Vektor vektor = new Vektor(loesungsWerte);

        Vektor hoffentlichNull = gaussAlgorithmus.loeseGleichungssystem(matrix, vektor);
        assertNull(hoffentlichNull);
    }

}