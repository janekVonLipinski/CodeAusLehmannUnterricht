package TrousProjekt.VektorUndMatrix;

import TrousProjekt.VektorUndMatrix.MatrixVerfahren.DeterminantenRechner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {


    private final DeterminantenRechner determinantenRechner = new DeterminantenRechner();

    @Test
    void Given_() {
        double[][] matrix = {{3,1,2}, {4, 1, 3}, {-1,1,5}};
        Matrix alteMatrix = new Matrix(matrix);
        System.out.println(alteMatrix);

        assertEquals(-7, determinantenRechner.getDeterminante(alteMatrix));
    }

}