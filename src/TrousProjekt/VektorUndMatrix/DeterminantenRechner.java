package TrousProjekt.VektorUndMatrix;

public class DeterminantenRechner {

    protected double getDeterminante(Matrix m) {
        int anzahlZeilen = m.getAnzahlZeilen();
        int anzahlSpalten = m.getAnzahlSpalten();
        double[][] matrix = m.getMatrix();
        double ergebnis = 0;

        if (anzahlZeilen != anzahlSpalten) {
            throw new IllegalArgumentException("Matrix mus n x n sein");
        }

        if (anzahlZeilen == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
        }

        for (int i = 0; i < anzahlSpalten; i++) {
            Matrix verkleinerteMatrix = getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(m, i);
            double zunahme = Math.pow(-1, i) * matrix[0][i] * getDeterminante(verkleinerteMatrix);
            ergebnis += zunahme;
        }

        return ergebnis;
    }


    private Matrix getMatrixMitGestrichenerErstenZeileUndUebergebenerSpalte(Matrix m, int spalte) {
        int anzahlSpalten = m.getAnzahlSpalten();
        int anzahlZeilen = m.getAnzahlZeilen();
        double[][] matrix = m.getMatrix();

        double[][] neueMatrix = new double[anzahlZeilen - 1][anzahlSpalten - 1];
        double[][] alteMatrix = matrix.clone();

        int l = 1;
        for (int i = 0; i < neueMatrix.length; i++) {
            int k = 0;
            for (int j = 0; j < neueMatrix.length; j++) {
                if (k == spalte) {
                    k++;
                }
                neueMatrix[i][j] = alteMatrix[l][k];
                k++;
            }
            l++;
        }
        return new Matrix(neueMatrix);
    }
}
