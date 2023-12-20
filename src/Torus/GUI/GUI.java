package Torus.GUI;

import Torus.Torus;
import Util.VektorR3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class GUI {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        double r1 = 2;
        int fensterBreite = 1000;
        int fensterHoehe = 1000;
        int skalierungsKosntatne = 10;


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Torus torus = new Torus(20, 20, r1, 1);

        window.setSize(fensterBreite, fensterHoehe);

        window.getContentPane().add(
                new JComponent() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                for (int i = 0; i <= 1598; i++) {

                    VektorR3 punkt = torus.getPunkte()[i];
                    VektorR3 naechsterPunkt = torus.getPunkte()[i + 1];

                    double x1 = punkt.getX();
                    double x2 = naechsterPunkt.getX();

                    double y1 = punkt.getY();
                    double y2 = naechsterPunkt.getY();

                    double z1 = punkt.getZ();
                    double z2 = naechsterPunkt.getZ();

                    //Shape l = new Line2D.Double(punkt2D.getX(), punkt2D.getY(),
                    //        naechsterPunkt2D.getX(), naechsterPunkt2D.getY());
                    Shape l = new Line2D.Double(200 + 30 * x1, 200 + 30 * y1,
                            200 + 30 * x2,200 + 30 * y2);
                    g2.draw(l);
                    Shape m = new Line2D.Double(500 + 30 * x1, 500 + 30 * z1,
                            500 + 30 * x2,500 + 30 * z2);
                    g2.draw(m);
                    Shape n = new Line2D.Double(800 + 30 * y1, 800 + 30 * z1,
                            800 + 30 * y2,800 + 30 * z2);
                    g2.draw(n);
                }
            }
        });

        window.setVisible(true);

    }
}
