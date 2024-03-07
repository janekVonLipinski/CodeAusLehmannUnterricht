package TrousProjekt.VektorUndMatrix;

import TrousProjekt.VektorUndMatrix.MatrizenVerfahren.DeterminantenRechner;
import TrousProjekt.VektorUndMatrix.MatrizenVerfahren.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void Given_() {
        double[][] matrix = {{3,1,2}, {4, 1, 3}, {-1,1,5}};
        Matrix alteMatrix = new Matrix(matrix);
        System.out.println(alteMatrix);

        assertEquals(-7, alteMatrix.getDeterminante());
    }

}