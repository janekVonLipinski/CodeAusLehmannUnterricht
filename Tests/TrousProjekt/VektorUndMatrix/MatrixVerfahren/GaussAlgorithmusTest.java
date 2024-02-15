package TrousProjekt.VektorUndMatrix.MatrixVerfahren;

import TrousProjekt.VektorUndMatrix.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GaussAlgorithmusTest {

    private final GaussAlgorithmus gaussAlgorithmus = new GaussAlgorithmus();

    @Test
    void Given_() {
        double[][] array = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        Matrix matrix = new Matrix(array);
        gaussAlgorithmus.tausche(matrix.getMatrix(), 0, 1);
    }

    @Test
    void Given_Then() {
        double[] array = {1,1,1};
        double[] testArray = gaussAlgorithmus.getMultiplizierteZeile(array, 2);

        for (double zahl : testArray) {
            assertEquals(2, zahl);
        }
    }

}