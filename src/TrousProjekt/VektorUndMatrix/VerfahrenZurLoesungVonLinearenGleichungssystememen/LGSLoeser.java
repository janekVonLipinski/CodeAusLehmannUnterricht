package TrousProjekt.VektorUndMatrix.VerfahrenZurLoesungVonLinearenGleichungssystememen;

import TrousProjekt.VektorUndMatrix.MatrizenVerfahren.Matrix;
import TrousProjekt.VektorUndMatrix.Vektor;

public interface LGSLoeser {

    Vektor loeseGleichungssystem(Matrix m,Vektor v);
}
