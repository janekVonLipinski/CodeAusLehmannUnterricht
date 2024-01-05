package Torus.GUI;

import Torus.Torus;
import Util.Matrix;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final double r1 = 200;
    private static final double r2 = 100;
    private static double alpha;
    private static double beta;
    private static double gamma;

    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setSize(WIDTH, HEIGHT);

        GUI gui = new GUI();
        Koordinatensystem koordinatensystem = new Koordinatensystem(WIDTH, HEIGHT);

        window.add(koordinatensystem);
        window.add(gui);

        window.setVisible(true);

        while (true) {
            alpha += 0.01;
            beta += 0.02;
            gamma += 0.03;
            gui.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        Torus torus = new Torus(20, 20, r1, r2);
        super.paint(g);
        g.translate(WIDTH / 2, HEIGHT / 2);
        Matrix drehMatrix = getDrehMatrix(alpha, beta, gamma);
        for (int i = 0; i < 1598; i++) {

            int[] xs = new int[3];
            int[] zs = new int[3];

            for (int k = 0; k < 3; k++) {
                int x = (int) torus.getPunkte()[(i+k)].multipliziere(drehMatrix).getX();
                int y = (int) torus.getPunkte()[(i+k)].multipliziere(drehMatrix).getY();
                int z = (int) torus.getPunkte()[(i+k)].multipliziere(drehMatrix).getZ();

                xs[k] = x - y / 2;
                zs[k] = z - y / 2;
            }
            g.setColor(Color.BLACK);
            g.drawPolygon(xs, zs, 3);
        }
    }

    private Matrix getDrehMatrix(double alpha, double beta, double gamma) {

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
