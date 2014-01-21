package ch.inse.qbe.physical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représentation métier d'un objet
 * 
 * @author frp
 * 
 */
public abstract class AbstractPhysicalObject {

    /**
     * Nom de l'objet métier
     */
    private String iName;

    /**
     * Liste des attributs par nom
     * 
     * @param aName
     */
    private List<String> iDataList = new ArrayList<>();

    /**
     * Référence sur l'objet attribut
     * 
     * @param aName
     */
    private Map<String, AbstractPhysicalData> iPhysicalDatas = new HashMap<>();

    public AbstractPhysicalObject(String aName) {
        iName = aName;
    }

    public String getName() {
        return iName;
    }

    public void putBusinessAttributes(AbstractPhysicalData aPhysicalData) {
        iPhysicalDatas.put(aPhysicalData.getName(), aPhysicalData);
        iDataList.add(aPhysicalData.getName());
    }

    public List<String> getDataList() {
        return iDataList;
    }

    public AbstractPhysicalData getAttribute(String anAttributeName) {
        return iPhysicalDatas.get(anAttributeName);
    }
}
