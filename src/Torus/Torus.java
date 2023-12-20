package Torus;

import Util.Matrix;
import Util.VektorR3;

public class Torus {

    private final VektorR3[] punkte;

    //Aus a und b wird die menge der Punkte errechnet. Anzahl der Punkte = 4 * a * b
    public Torus(int a, int b, double r1, double r2) {
        this.punkte = new VektorR3[4 * a * b];
        getTorus(a, b, r1, r2);
    }

    public VektorR3[] getPunkte() {
        return punkte;
    }

    public void getTorus(int a, int b, double r1, double r2) {

        //r2 ist der Radius bis zum Mittelpunkt des Kreises für die Kontruktion
        //r1 ist der Radius des Kreis
        //deltaTheta/deltaPhi sind die inkrementierung von Theta/Phi
        final double deltaTheta = Math.PI / a;
        final double deltaPhi = Math.PI / b;
        //Laufvariable
        int i = 0;

        // Theta geht um das Zentrum des Torus
        for (double theta = 0; theta < 2 * Math.PI; theta += deltaTheta) {

            double cosTheta = Math.cos(theta);
            double sinTheta = Math.sin(theta);

            // Phi geht um Theta
            for (double phi = 0; phi < 2 * Math.PI; phi += deltaPhi) {

                double cosPhi = Math.cos(phi);
                double sinPhi = Math.sin(phi);

                //Koordinaten des Torus. Es wird ein Punkt r2, 0, 0 gewählt aus diesem wird ein kreis gewählt mit
                //den Polarkoordinaten y = r cosTheta und z = r sinTheta
                //durch Multiplikation mit der Rotationsmatrix R_x wird entstandene Kreis um die x-Achse gedreht

                double x = r2 * cosPhi + (r2 + r1 * cosTheta) * sinPhi;
                double y = -r2 * sinPhi + (r2 + r1 * cosTheta) * cosPhi;
                double z = r2 + r1 * sinTheta;

                VektorR3 v = new VektorR3(x, y, z);
                punkte[i++] = v;
            }
        }
    }

    public void rotate(VektorR3 drehVektor) {

        //Drehvektor ist der Vektor um den gedreht wird

        //Drehvektor wird auf Länge eins skaliert
        if (drehVektor.getLaenge() != 1) {
            drehVektor.skaliere(1 / drehVektor.getLaenge());
        }


    }

    public Matrix harmonisiereDrehMatrix(VektorR3 drehvektor, VektorR3 stuetzVektor, double alpha) {

        double nx = drehvektor.getX();
        double ny = drehvektor.getY();
        double nz = drehvektor.getZ();

        Matrix drehMatrix = new Matrix(4, 4);

        double[][] drehWerte = {
                {
                        nx * nx * (1 - Math.cos(alpha)) + Math.cos(alpha),
                        nx * ny * (1 - Math.cos(alpha)) - nz * Math.sin(alpha),
                        nx * nz * (1 - Math.cos(alpha)) - nx * Math.sin(alpha),
                        0
                },
                {
                        ny * nx * (1 - Math.cos(alpha)) + nz * Math.sin(alpha),
                        ny * ny * (1 - Math.cos(alpha)) + Math.cos(alpha),
                        ny * nz * (1 - Math.cos(alpha)) - nx * Math.sin(alpha),
                        0
                },
                {
                        nz * nx * (1 - Math.cos(alpha)) - ny * Math.sin(alpha),
                        nz * ny * (1 - Math.cos(alpha)) + nx * Math.sin(alpha),
                        nz * nz * (1 - Math.cos(alpha)) + Math.cos(alpha),
                        0
                },
                {
                        0, 0, 0, 1
                }
        };

        drehMatrix.setMatrix(drehWerte);

        double sx = stuetzVektor.getX();
        double sy = stuetzVektor.getY();
        double sz = stuetzVektor.getZ();

        Matrix additionsMatrix = new Matrix(4, 4);
        double[][] additionAlsArray = {
                {1, 0, 0, sx},
                {0, 1, 0, sy},
                {0, 0, 1, sz},
                {0, 0, 0, 1}
        };
        additionsMatrix.setMatrix(additionAlsArray);

        Matrix subtraktionsMatrix = new Matrix(4, 4);
        double[][] subtraktionAlsMatrix = {
                {1, 0, 0, -sx},
                {0, 1, 0, -sy},
                {0, 0, 1, -sy},
                {0, 0, 0, 1}
        };
        subtraktionsMatrix.setMatrix(subtraktionAlsMatrix);

        return additionsMatrix.multipliziere(drehMatrix).multipliziere(subtraktionsMatrix);
    }
}

