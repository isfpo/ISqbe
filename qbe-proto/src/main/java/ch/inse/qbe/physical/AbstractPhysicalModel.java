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
 * Classe d'accès à un modèle physique
 * 
 * T: définition de l'accès au modèle physique (Par exemple DataSource) C: accès
 * à la ressource (Par exemple conncetion
 * 
 * @author frp
 * 
 */
public abstract class AbstractPhysicalModel<T> {

    private T iModelAccess;

    /**
     * Liste des objets contenus dans ce modèle
     */
    private Map<String, AbstractPhysicalObject> iPhysicalObjects = new HashMap<>();

    /**
     * Liste des relations par nom de relation
     * 
     * @param aName
     */
    private Map<String, AbstractPhysicalRelation> iPhysicalRelations = new HashMap<>();

    /**
     * Constructeur en passant une classe d'accès au modèle
     * 
     * @param modelAccess
     */
    public AbstractPhysicalModel(T modelAccess) {
        iModelAccess = modelAccess;
    }

    /**
     * Obtention du modèle métier à partir de l'implémentation des recherches
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
     * Méthode permettant de retourner la classe d'accès au modèle
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
     * Retourne la liste des caractéristique d'un objet
     * 
     * @param anObjectName
     * @param dataAccess
     * @return
     */
    protected List<String> getFieldsList(String anObjectName) {
        return getPhysicalObjects().get(anObjectName).getDataList();
    }

    /**
     * Retorune les caractéristiques d'un champ d'un objet
     * 
     * @param anObjectName
     * @param aFieldName
     * @param dataAccess
     * @return
     */
    protected abstract BusinessData getField(String anObjectName,
            String aFieldName);

    /**
     * Retourne l'ensemble des noms des relations dans le modèle physique
     * 
     * @param dataAccess
     * @return
     */
    protected List<String> getRelationsList() {
        return new ArrayList<>(getPhysicalRelations().keySet());
    }

    /**
     * Retourne les caractéristiques d'une relation
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
