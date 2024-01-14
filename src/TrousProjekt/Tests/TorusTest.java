package TrousProjekt.Tests;

import TrousProjekt.Torus.Torus;
import TrousProjekt.VektorUndMatrix.Vektor;

import static org.junit.jupiter.api.Assertions.*;

class TorusTest {

    @org.junit.jupiter.api.Test
    void getTorus() {

        Torus torus = new Torus(20, 20);

        Vektor v = new Vektor(3, 0, 0);


        assertTrue(torus.getPunkte()[0].istGleich(v));

    }
}