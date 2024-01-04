package Torus;

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

                int x = (int) ((r1 + r2 * cosTheta) * cosPhi);
                int y = (int) (r2 * sinTheta);
                int z = (int) (-(r1 + r2 * cosTheta) * sinPhi);

                VektorR3 v = new VektorR3(x, y, z);
                punkte[i++] = v;
            }
        }
    }
}

