package Torus;

import Util.VektorR3;

import static org.junit.jupiter.api.Assertions.*;

class TorusTest {

    @org.junit.jupiter.api.Test
    void getTorus() {

        Torus torus = new Torus(20, 20, 2, 1);

        VektorR3 v = new VektorR3(3, 0, 0);


        assertTrue(torus.getPunkte()[0].istGleich(v));

    }
}