package ch.inse.qbe.model;

public abstract class AbstractBusinessAttribute {

    /**
     * Nom de l'objet
     */
    private String iObjectName;

    /**
     * Nom de l'attribut
     */
    private String iName;

    public AbstractBusinessAttribute(String aObjectName, String aName) {
        iObjectName = aObjectName;
        iName = aName;
    }

    public String getObjectName() {
        return iObjectName;
    }

    public String getName() {
        return iName;
    }

}
