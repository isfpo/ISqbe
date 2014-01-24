package ch.inse.qbe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repr�sentation m�tier d'un objet
 * 
 * @author frp
 * 
 */
public class BusinessObject {

    /**
     * Nom de l'objet m�tier
     */
    private String iName;

    /**
     * Liste des attributs par nom
     * 
     * @param aName
     */
    private List<String> iAttributesList = new ArrayList<>();

    /**
     * Reference sur l'objet attribut
     * 
     * @param aName
     */
    private Map<String, AbstractBusinessAttribute> iBusinessAttributes = new HashMap<>();

    public BusinessObject(String aName) {
        iName = aName;
    }

    public String getName() {
        return iName;
    }

    public void putBusinessAttributes(
            AbstractBusinessAttribute aBusinessAttribute) {
        iBusinessAttributes.put(aBusinessAttribute.getName(),
                aBusinessAttribute);
        iAttributesList.add(aBusinessAttribute.getName());
    }

    public List<String> getAttributesList() {
        return iAttributesList;
    }

    public Map<String, AbstractBusinessAttribute> getBusinessAttributes() {
        return iBusinessAttributes;
    }
}
