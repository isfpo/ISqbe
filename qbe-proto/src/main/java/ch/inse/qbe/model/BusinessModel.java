package ch.inse.qbe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.inse.qbe.physical.AbstractPhysicalModel;

/**
 * Modèle métier, contient la référence à des objets et des relations
 * 
 * @author frp
 * 
 */
public class BusinessModel {

    /**
     * Nom du modele
     */
    private String iName;

    /**
     * Lien sur le modele physique
     */
    private AbstractPhysicalModel<?> iPhysicalModel;

    /**
     * Liste des objets contenus dans ce modele
     */
    private Map<String, BusinessObject> iBusinessObjects = new HashMap<>();

    /**
     * Liste des relations par nom de relation
     * 
     * @param aName
     */
    private Map<String, BusinessRelation> iBusinessRelations = new HashMap<>();

    /**
     * Liste des relations par nom d'objet
     * 
     * @param aName
     */
    private Map<String, BusinessRelation> iObjectRelations = new HashMap<>();

    public BusinessModel(String aName, AbstractPhysicalModel<?> aPhysicalModel) {
        iName = aName;
        iPhysicalModel = aPhysicalModel;
    }

    public String getName() {
        return iName;
    }

    public void putBusinessObject(BusinessObject aBusinessObject) {
        iBusinessObjects.put(aBusinessObject.getName(), aBusinessObject);
    }

    public void putBusinessRelation(BusinessRelation aBusinessRelation) {
        iBusinessRelations.put(aBusinessRelation.getName(), aBusinessRelation);
        iObjectRelations.put(aBusinessRelation.getObjectFrom(),
                aBusinessRelation);
        iObjectRelations
                .put(aBusinessRelation.getObjectTo(), aBusinessRelation);
    }

    public List<BusinessObject> getBuinessList() {
        return new ArrayList<>(iBusinessObjects.values());
    }

    public AbstractPhysicalModel<?> getPhysicalModel() {
        return iPhysicalModel;
    }
}
