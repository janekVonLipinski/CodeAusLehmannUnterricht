package TrousProjekt.VektorUndMatrix;

public class Vektor {

    private final double[] vektorWerte;

    public Vektor(double[] werte) {
        this.vektorWerte = werte;
    }

    public double[] getVektorWerte() {
        return vektorWerte;
    }

    public Vektor multipliziere(Matrix matrix) {
        double x = vektorWerte[0];
        double y = vektorWerte[1];
        double z = vektorWerte[2];
        if (matrix.getAnzahlSpalten() != 3 || matrix.getAnzahlZeilen() != 3) {
            throw new IllegalArgumentException("Matrix muss 3 Kreuz 3 sein");
        }

        double[][] vektorAlsArray = {{x}, {y}, {z}};
        Matrix vektorAlsMatrix = new Matrix(vektorAlsArray);

        Matrix neueMatrix = matrix.multipliziere(vektorAlsMatrix);
        double[][] array = neueMatrix.getMatrix();
        return new Vektor(array[0][0], array[1][0], array[2][0]);
    }

    @Override
    public String toString() {
        return "Vektor{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
