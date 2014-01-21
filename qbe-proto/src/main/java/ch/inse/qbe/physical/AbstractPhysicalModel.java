package ch.inse.qbe.physical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.inse.qbe.model.BusinessData;
import ch.inse.qbe.model.BusinessModel;
import ch.inse.qbe.model.BusinessObject;
import ch.inse.qbe.model.BusinessRelation;

/**
 * Classe d'acc�s � un mod�le physique
 * 
 * T: d�finition de l'acc�s au mod�le physique (Par exemple DataSource) C: acc�s
 * � la ressource (Par exemple conncetion
 * 
 * @author frp
 * 
 */
public abstract class AbstractPhysicalModel<T> {

    private T iModelAccess;

    /**
     * Liste des objets contenus dans ce mod�le
     */
    private Map<String, AbstractPhysicalObject> iPhysicalObjects = new HashMap<>();

    /**
     * Liste des relations par nom de relation
     * 
     * @param aName
     */
    private Map<String, AbstractPhysicalRelation> iPhysicalRelations = new HashMap<>();

    /**
     * Constructeur en passant une classe d'acc�s au mod�le
     * 
     * @param modelAccess
     */
    public AbstractPhysicalModel(T modelAccess) {
        iModelAccess = modelAccess;
    }

    /**
     * Obtention du mod�le m�tier � partir de l'impl�mentation des recherches
     * d'objets, de champs et de relations
     * 
     * @param aName
     * @return
     */
    public BusinessModel getBusinessModel(String aName) {
        BusinessModel model = new BusinessModel(aName);
        for (String str : getObjectsList()) {
            BusinessObject bo = new BusinessObject(str);
            model.putBusinessObject(bo);
            for (String attr : getFieldsList(str)) {
                bo.putBusinessAttributes(getField(str, attr));
            }
        }
        for (String rel : getRelationsList()) {
            model.putBusinessRelation(getRelation(rel));
        }
        return model;
    }

    /**
     * M�thode permettant de retourner la classe d'acc�s au mod�le
     * 
     * @return
     */
    public T getModelAccess() {
        return iModelAccess;
    }

    /**
     * Retourne la liste des objets physiques
     * 
     * @param dataAccess
     * @return
     */
    protected List<String> getObjectsList() {
        return new ArrayList<>(getPhysicalObjects().keySet());
    }

    /**
     * Retourne la liste des caract�ristique d'un objet
     * 
     * @param anObjectName
     * @param dataAccess
     * @return
     */
    protected List<String> getFieldsList(String anObjectName) {
        return getPhysicalObjects().get(anObjectName).getDataList();
    }

    /**
     * Retorune les caract�ristiques d'un champ d'un objet
     * 
     * @param anObjectName
     * @param aFieldName
     * @param dataAccess
     * @return
     */
    protected abstract BusinessData getField(String anObjectName,
            String aFieldName);

    /**
     * Retourne l'ensemble des noms des relations dans le mod�le physique
     * 
     * @param dataAccess
     * @return
     */
    protected List<String> getRelationsList() {
        return new ArrayList<>(getPhysicalRelations().keySet());
    }

    /**
     * Retourne les caract�ristiques d'une relation
     * 
     * @param aName
     * @param dataAccess
     * @return
     */
    protected abstract BusinessRelation getRelation(String aName);

    public void putPhysicalObject(AbstractPhysicalObject aBusinessObject) {
        iPhysicalObjects.put(aBusinessObject.getName(), aBusinessObject);
    }

    public void putPhysicalRelation(AbstractPhysicalRelation aBusinessRelation) {
        iPhysicalRelations.put(aBusinessRelation.getName(), aBusinessRelation);
        iPhysicalRelations.put(aBusinessRelation.getObjectFrom().getName(),
                aBusinessRelation);
        iPhysicalRelations.put(aBusinessRelation.getObjectTo().getName(),
                aBusinessRelation);
    }

    public Map<String, AbstractPhysicalObject> getPhysicalObjects() {
        return iPhysicalObjects;
    }

    public Map<String, AbstractPhysicalRelation> getPhysicalRelations() {
        return iPhysicalRelations;
    }

}
