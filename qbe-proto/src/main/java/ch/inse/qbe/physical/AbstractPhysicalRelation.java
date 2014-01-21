package ch.inse.qbe.physical;

/**
 * Relation entre deux objets métier
 * 
 * @author frp
 * 
 */
public abstract class AbstractPhysicalRelation {

    /**
     * Nom de la relation
     */
    private String iName;

    /**
     * Object from
     */
    private AbstractPhysicalObject iObjectFrom;

    /**
     * ObjectTo
     */
    private AbstractPhysicalObject iObjectTo;

    public AbstractPhysicalRelation(String aName, AbstractPhysicalObject aFrom,
            AbstractPhysicalObject aTo) {
        iName = aName;
        iObjectFrom = aFrom;
        iObjectTo = aTo;
    }

    public String getName() {
        return iName;
    }

    public AbstractPhysicalObject getObjectFrom() {
        return iObjectFrom;
    }

    public AbstractPhysicalObject getObjectTo() {
        return iObjectTo;
    }

}
