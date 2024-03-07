package TrousProjekt.VektorUndMatrix.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import TrousProjekt.VektorUndMatrix.MatrizenVerfahren.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LGSTest {

    private final GaussAlgorithmus gaussAlgorithmus = new GaussAlgorithmus();
    private final DeterminatenVerfahren determinatenVerfahren = new DeterminatenVerfahren();

    @ParameterizedTest
    @MethodSource("generateTestStream")
    void Given_GaussAlgorithmus(Matrix koeffizientenMatrix, Vektor loeusngsvektor, Vektor xVektor) {
        Vektor loesungMitGauss = gaussAlgorithmus.loeseGleichungssystem(koeffizientenMatrix,
                loeusngsvektor);
        assertTrue(sindWerteKleinerAlsDiffernz(xVektor, loesungMitGauss));
    }

    @ParameterizedTest
    @MethodSource("generateTestStream")
    void Given_Determinantenverfahren(Matrix koeffizientenMatrix, Vektor loeusngsvektor, Vektor xVektor) {
        Vektor loesungMitDet = determinatenVerfahren.loeseGleichungssystem(koeffizientenMatrix,
                loeusngsvektor);
        System.out.println(loesungMitDet);
        System.out.println("----------------");
        System.out.println(xVektor);
        assertTrue(sindWerteKleinerAlsDiffernz(xVektor, loesungMitDet));
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
        Arrays.fill(abweichungsVektor, 0.01);
        return abweichungsVektor;
    }

    private static Stream<Arguments> generateTestStream() {
        return IntStream.range(2, 12)
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

}