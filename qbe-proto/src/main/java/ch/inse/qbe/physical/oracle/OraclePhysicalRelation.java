package ch.inse.qbe.physical.oracle;

import ch.inse.qbe.physical.AbstractPhysicalObject;
import ch.inse.qbe.physical.AbstractPhysicalRelation;

public class OraclePhysicalRelation extends AbstractPhysicalRelation {

    public OraclePhysicalRelation(String aName, AbstractPhysicalObject aFrom,
            AbstractPhysicalObject aTo) {
        super(aName, aFrom, aTo);
    }

}
