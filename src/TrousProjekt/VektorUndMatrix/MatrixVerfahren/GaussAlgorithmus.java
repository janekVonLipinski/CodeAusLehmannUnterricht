package TrousProjekt.VektorUndMatrix.MatrixVerfahren;

import TrousProjekt.VektorUndMatrix.Matrix;

import java.util.Arrays;

public class GaussAlgorithmus {

    public double[][] loeseGleichungssystem(Matrix koeffizientenMatrix, double[] vektor) {
        double[][] matrixArray = koeffizientenMatrix.getMatrix().clone();

        double[][] dreiecksMatrix = getDreieckMatrix(matrixArray, vektor);
        int groessterIndex = dreiecksMatrix.length - 1;
        if (dreiecksMatrix[groessterIndex][groessterIndex] == 0) {
            // mach was
        }
        double[][] diagonalMatrix = getDiagonalMatrixVonDreiecksMatrix(dreiecksMatrix, vektor);

        return diagonalMatrix;
    }

    protected double[][] getDiagonalMatrixVonDreiecksMatrix(double[][] matrix, double[] vektor) {

        for (int j = matrix.length - 1; j >= 0; j--) {
            for (int k = 1; j - k >= 0; k++) {
                double[] zeileVonDerSubtrahiertWird = matrix[j-k];
                double[] zeileDieSubtrahiertWird = matrix[j];

                double koeffizient = getKoeffizient(
                        zeileDieSubtrahiertWird[j],
                        zeileVonDerSubtrahiertWird[j]
                );

                matrix[j - k] = subtrahiereZeile(zeileDieSubtrahiertWird,
                        zeileVonDerSubtrahiertWird,
                        koeffizient);

                vektor[j - k] = vektor[j - k] - koeffizient * vektor[j];
            }
        }
        return matrix;
    }

    protected double[][] getDreieckMatrix(double[][] matrix, double[] vektor) {

        for (int j = 0; j < matrix.length; j++) {
            int i = 1;

            while (matrix[j][j] == 0) {
                tausche(matrix, j, i);
                tausche(vektor, j, i++);
            }
            for (int k = 1; j + k < matrix.length; k++) {
                double[] zeileVonDerSubtrahiertWird = matrix[j + k];
                double[] zeileDieSubtrahiertWird = matrix[j];

                double koeffizient = getKoeffizient(
                        zeileDieSubtrahiertWird[j],
                        zeileVonDerSubtrahiertWird[j]
                );

                matrix[j + k] = subtrahiereZeile(zeileDieSubtrahiertWird,
                        zeileVonDerSubtrahiertWird, koeffizient);

                vektor[j + k] = vektor[j + k] - koeffizient * vektor[j];
            }
        }
        return matrix;
    }

    protected void tausche(double[][] matrixArray, int indexErsteZeile, int indexZweiteZeile) {

        double[] ersteZeile = matrixArray[indexErsteZeile];
        double[] zweiteZeile = matrixArray[indexZweiteZeile];

        matrixArray[indexErsteZeile] = zweiteZeile;
        matrixArray[indexZweiteZeile] = ersteZeile;
    }

    protected void tausche(double[] vektor, int ersterIndex, int zweiterIndex) {
        double temp = vektor[ersterIndex];
        vektor[ersterIndex] = vektor[zweiterIndex];
        vektor[zweiterIndex] = temp;
    }

    protected double[] subtrahiereZeile(double[] ersteZeile, double[] zweiteZeile,
                                        double koeffiezient) {

        double[] multiplizierteZeile = getMultiplizierteZeile(ersteZeile, koeffiezient);

        double[] returnArray = new double[ersteZeile.length];
        for (int i = 0; i < ersteZeile.length; i++) {
            returnArray[i] = zweiteZeile[i] - multiplizierteZeile[i];
        }

        return returnArray;
    }

    protected double[] getMultiplizierteZeile(double[] zeile, double koeffizient)  {
        double[] copy = Arrays.copyOf(zeile, zeile.length);
        return Arrays.stream(copy)
                .map(i -> i * koeffizient)
                .toArray();
    }

    protected double getKoeffizient(double ersterWert, double zweiterWert) {
        return zweiterWert / ersterWert;
    }
}
