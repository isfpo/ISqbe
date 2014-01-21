package ch.inse.qbe.physical;

/**
 * Donnée brut du modèle de donnée
 * 
 * @author frp
 * 
 */
public abstract class AbstractPhysicalData {

    private String iName;

    public AbstractPhysicalData(String aName) {
        iName = aName;
    }

    public String getName() {
        return iName;
    }
}
