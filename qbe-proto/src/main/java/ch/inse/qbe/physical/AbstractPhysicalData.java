package ch.inse.qbe.physical;

/**
 * Donn�e brut du mod�le de donn�e
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
