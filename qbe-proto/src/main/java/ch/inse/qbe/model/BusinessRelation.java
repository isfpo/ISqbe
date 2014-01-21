package ch.inse.qbe.model;

/**
 * Relation entre deux objets métier
 * 
 * @author frp
 * 
 */
public class BusinessRelation {

    /**
     * Nom de la relation
     */
    private String iName;

    /**
     * Object from
     */
    private String iObjectFrom;

    /**
     * ObjectTo
     */
    private String iObjectTo;

    public BusinessRelation(String aName, String aFrom, String aTo) {
        iName = aName;
        iObjectFrom = aFrom;
        iObjectTo = aTo;
    }

    public String getName() {
        return iName;
    }

    public String getObjectFrom() {
        return iObjectFrom;
    }

    public String getObjectTo() {
        return iObjectTo;
    }

}
