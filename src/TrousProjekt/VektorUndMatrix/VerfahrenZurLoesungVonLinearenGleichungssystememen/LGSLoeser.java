package TrousProjekt.VektorUndMatrix.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import TrousProjekt.VektorUndMatrix.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;

public interface LGSLoeser {

    Vektor loeseGleichungssystem(Matrix m,Vektor v);
}
