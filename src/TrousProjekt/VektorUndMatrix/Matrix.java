package TrousProjekt.VektorUndMatrix;

public class Matrix {

    private double[][] matrix;
    private final int anzahlZeilen;
    private final int anzahlSpalten;

    public Matrix(int anzahlZeilen, int anzahlSpalten) {
        this.anzahlZeilen = anzahlZeilen;
        this.anzahlSpalten = anzahlSpalten;
        matrix = new double[anzahlZeilen][anzahlSpalten];
    }

    public Matrix(int seite) {
        this.anzahlZeilen = this.anzahlSpalten = seite;
        matrix = new double[seite][seite];
    }

    public void setMatrix(double[][] werte) {
        matrix = werte;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getAnzahlSpalten() {
        return anzahlSpalten;
    }

    public int getAnzahlZeilen() {
        return anzahlZeilen;
    }

    public boolean istGleich(Matrix m) {
        if (anzahlSpalten != m.anzahlSpalten || anzahlZeilen != m.anzahlZeilen) {
            return false;
        }
        for (int i = 0; i < anzahlSpalten; i++) {
            for (int j = 0; j < anzahlSpalten; j++) {
                if (matrix[i][j] != m.matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix multipliziere(Matrix matrix) {

        if (anzahlSpalten != matrix.anzahlZeilen) {
            throw new IllegalArgumentException(
                    "Zeilen von linker Matrix müssen geleich den Spalten der zweiten sein");
        }

        Matrix ergebnis = new Matrix(anzahlZeilen, matrix.anzahlSpalten);

        for (int i = 0; i < ergebnis.anzahlZeilen; i++) {
            for (int j = 0; j < ergebnis.anzahlSpalten; j++) {
                ergebnis.matrix[i][j] = 0;
                for (int k = 0; k < matrix.anzahlZeilen; k++) {
                    ergebnis.matrix[i][j] += (this.matrix[i][k] * matrix.matrix[k][j]);
                }
            }
        }

        return ergebnis;
    }

    public Matrix skaliere(double skalar) {
        Matrix m = new Matrix(anzahlZeilen, anzahlSpalten);
        for (int i = 0; i < anzahlZeilen; i++) {
            for (int j = 0; j < anzahlSpalten; j++) {
                m.matrix[i][j] = matrix[i][j] * skalar;
            }
        }
        return m;
    }

    public Matrix getDrehMatrix(double alpha, double beta, double gamma) {

        double sinAlpha = Math.sin(alpha);
        double cosAlpha = Math.cos(alpha);

        double sinBeta = Math.sin(beta);
        double cosBeta = Math.cos(beta);

        double sinGamma = Math.sin(gamma);
        double cosGamma = Math.cos(gamma);

        double[][] drehmatrixX = {
                {1, 0, 0},
                {0, cosAlpha, -sinAlpha},
                {0, sinAlpha, cosAlpha}
        };
        Matrix m = new Matrix(3, 3);
        m.setMatrix(drehmatrixX);

        double[][] drehMatrixY = {
                {cosBeta, 0, - sinBeta},
                {0, 1, 0},
                {sinBeta, 0, cosBeta}
        };
        Matrix n = new Matrix(3, 3);
        n.setMatrix(drehMatrixY);

        double[][] drehMatrixZ = {
                {cosGamma, - sinGamma, 0},
                {sinGamma, cosGamma, 0},
                {0, 0, 1}
        };
        Matrix o = new Matrix(3, 3);
        o.setMatrix(drehMatrixZ);

        Matrix zwischenErgebnis = m.multipliziere(n);
        return zwischenErgebnis.multipliziere(o);
    }
}
