package TrousProjekt.VektorUndMatrix.MatrixVerfahren;

import TrousProjekt.VektorUndMatrix.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GaussAlgorithmusTest {

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

        assertArrayEquals(korrekteLoesung.getVektorWerte(), loesung.getVektorWerte());
    }

    @Test
    void Given_0_auf_Diagonale_Then_gleichung_wird_geloest() {
        double[][] werte = {{0,1,4},{1,2,9},{0,5,2}};
        double[] xVektor = {1,2,3};

        Matrix m = new Matrix(werte);
        Vektor x = new Vektor(xVektor);

        Vektor l = x.multipliziere(m);

        Vektor gaussLoesung = gaussAlgorithmus.loeseGleichungssystem(m, l);

        assertArrayEquals(gaussLoesung.getVektorWerte(), x.getVektorWerte());

        Matrix j = generateQuadratischeTestMatrix(4);
    }

    @Test
    void Given_gleichung_ist_nicht_loesbar_Then_throw_IllegalArgument_Exception() {
        double[][] matrixWerte = {{1, 1, -1, -1}, {2, 5, -7, -5}, {2, -1, 1, 3}, {5, 2, -4, 2}};
        Matrix matrix = new Matrix(matrixWerte);

        double[] loesungsWerte = {1, -2, 4, 6};
        Vektor vektor = new Vektor(loesungsWerte);

        assertThrows(ArithmeticException.class,
                () -> gaussAlgorithmus.loeseGleichungssystem(matrix, vektor));
    }

    @Test
    void Given_Spalte_ist_null_Then_throw_IllegalArgument_Exception() {
        double[][] matrixWerte = {
                {1, -1, 0, 0, 1}, {1, 1, 0, -3, 0}, {2, -1, 0, 1, -1}, {-1, 2, 0, -2, -1}};
        Matrix m = new Matrix(matrixWerte);

        Vektor v = new Vektor(new double[4]);

        assertThrows(IllegalArgumentException.class,
                () -> gaussAlgorithmus.loeseGleichungssystem(m, v));
    }

    @ParameterizedTest
    @MethodSource("generateTestStream")
    void Given(Matrix koeffizientenMatrix, Vektor loeusngsvektor, Vektor xVektor) {
        Vektor loesungMitGauss = gaussAlgorithmus.loeseGleichungssystem(koeffizientenMatrix,
                loeusngsvektor);
        assertTrue(sindWerteKleinerAlsDiffernz(xVektor, loesungMitGauss));
    }

    private static Stream<Arguments> generateTestStream() {
        return IntStream.range(2, 10)
                .mapToObj(i -> {
                    Matrix matrix = generateQuadratischeTestMatrix(i);
                    Vektor xVektor = generateXVektor(i);
                    Vektor loesungsVektor = xVektor.multipliziere(matrix);
                    return Arguments.of(matrix, loesungsVektor, xVektor);
                });
    }

    private static Matrix generateQuadratischeTestMatrix(int zeilenAnzahl) {
        double[][] testMatrix = new double[zeilenAnzahl][zeilenAnzahl];
        Random random  = new Random();
        do {
            for (int i = 0; i < zeilenAnzahl; i++) {
                for (int j = 0; j < zeilenAnzahl; j++) {
                    if (random.nextInt(0, 10) == 1) {
                        testMatrix[i][j] = 0;
                        continue;
                    }
                    testMatrix[i][j] = random.nextDouble(1, 100000);
                }
            }
        } while (new Matrix(testMatrix).getDeterminante() == 0);

        return new Matrix(testMatrix);
    }

    private static Vektor generateXVektor(int zeilenAnzahl) {
        double[] testVektor = new double[zeilenAnzahl];
        Random random = new Random();
        for (int i = 0; i < zeilenAnzahl; i++) {
            if (random.nextInt(0, 10) == 1) {
                testVektor[i] = 0;
                continue;
            }
            testVektor[i] = random.nextDouble(1, 100000);
        }

        return new Vektor(testVektor);
    }

    private static boolean sindWerteKleinerAlsDiffernz(Vektor v1, Vektor v2) {
        int zeilenAnzahl = v1.getVektorWerte().length;
        double[] differenz = getDifferenfVonZweiVektoren(v1, v2);
        double[] abweichung = getAbweichungsArray(zeilenAnzahl);

        for (int i = 0; i < zeilenAnzahl; i++) {
            if (Math.abs(differenz[i]) > abweichung[i]) {
                return false;
            }
        }
        return true;
    }

    private static double[] getDifferenfVonZweiVektoren(Vektor v1, Vektor v2) {
        double[] werteVonV1 = v1.getVektorWerte();
        double[] werteVonV2 = v2.getVektorWerte();
        double[] differenz = new double[werteVonV2.length];
        for (int i = 0; i < werteVonV2.length; i++) {
            differenz[i] = werteVonV2[i] - werteVonV1[i];
        }
        return differenz;
    }

    private static double[] getAbweichungsArray(int zeilenAnzahl) {
        double[] abweichungsVektor = new double[zeilenAnzahl];
        for (int i = 0; i < zeilenAnzahl; i++) {
            abweichungsVektor[i] = 0.01;
        }
        return abweichungsVektor;
    }

}