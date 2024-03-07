package TrousProjekt.VektorUndMatrix.MatrizenVerfahren;

public class Drehmatrix {

    public static Matrix getDrehMatrix(double alpha, double beta, double gamma) {

        double sinAlpha = Math.sin(alpha);
        double cosAlpha = Math.cos(alpha);

        double sinBeta = Math.sin(beta);
        double cosBeta = Math.cos(beta);

        double sinGamma = Math.sin(gamma);
        double cosGamma = Math.cos(gamma);

        double[][] drehMatrixArrayX = {
                {1, 0, 0},
                {0, cosAlpha, -sinAlpha},
                {0, sinAlpha, cosAlpha}
        };
        Matrix drehMatrixX = new Matrix(drehMatrixArrayX);

        double[][] drehMatrixArrayY = {
                {cosBeta, 0, - sinBeta},
                {0, 1, 0},
                {sinBeta, 0, cosBeta}
        };
        Matrix drehMatrixY = new Matrix(drehMatrixArrayY);

        double[][] drehMatrixArrayZ = {
                {cosGamma, - sinGamma, 0},
                {sinGamma, cosGamma, 0},
                {0, 0, 1}
        };
        Matrix drehMatrixZ = new Matrix(drehMatrixArrayZ);

        return drehMatrixX.multipliziere(drehMatrixY).multipliziere(drehMatrixZ);
    }
}
