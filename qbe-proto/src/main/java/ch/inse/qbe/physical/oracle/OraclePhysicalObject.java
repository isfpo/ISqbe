package ch.inse.qbe.physical.oracle;

import ch.inse.qbe.physical.AbstractPhysicalObject;

public class OraclePhysicalObject extends AbstractPhysicalObject {

    public String iTableName;

    public String iOwnerName;

    public OraclePhysicalObject(String aName) {
        super(aName);
    }

    public OraclePhysicalObject(String[] aNames) {
        super(aNames[0] + "." + aNames[1]);
        iTableName = aNames[1];
        iOwnerName = aNames[0];
    }

    public String getTableName() {
        return iTableName;
    }

    public String getOwnerName() {
        return iOwnerName;
    }

}
