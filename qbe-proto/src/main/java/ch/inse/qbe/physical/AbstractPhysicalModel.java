package ch.inse.qbe.physical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.inse.qbe.model.BusinessData;
import ch.inse.qbe.model.BusinessModel;
import ch.inse.qbe.model.BusinessObject;
import ch.inse.qbe.model.BusinessRelation;
import ch.inse.qbe.process.AbstractQueryProcessor;

/**
 * Classe d'acces a un modele physique
 * 
 * T: definition de l'accas au modale physique (Par exemple DataSource)
 * 
 * @author frp
 * 
 */
public abstract class AbstractPhysicalModel<T> {

    private T iModelAccess;

    /**
     * Liste des objets contenus dans ce modele
     */
    private Map<String, AbstractPhysicalObject> iPhysicalObjects = new HashMap<>();

    /**
     * Liste des relations par nom de relation
     * 
     * @param aName
     */
    private Map<String, AbstractPhysicalRelation> iPhysicalRelations = new HashMap<>();

    /**
     * Processeur associe a ce modele
     */
    private AbstractQueryProcessor<?> iQueryProcessor;

    /**
     * Constructeur en passant une classe d'acces au modele
     * 
     * @param modelAccess
     */
    public AbstractPhysicalModel(T modelAccess) {
        iModelAccess = modelAccess;
    }

    /**
     * Obtention du modele metier a partir de l'implementation des recherches
     * d'objets, de champs et de relations
     * 
     * @param aName
     * @return
     */
    public BusinessModel getBusinessModel(String aName) {
        BusinessModel model = new BusinessModel(aName, this);
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
     * Methode permettant de retourner la classe d'acces au modele
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
     * Retourne la liste des caracteristique d'un objet
     * 
     * @param anObjectName
     * @param dataAccess
     * @return
     */
    protected List<String> getFieldsList(String anObjectName) {
        return getPhysicalObjects().get(anObjectName).getDataList();
    }

    /**
     * Retorune les caracteristiques d'un champ d'un objet
     * 
     * @param anObjectName
     * @param aFieldName
     * @param dataAccess
     * @return
     */
    protected abstract BusinessData getField(String anObjectName,
            String aFieldName);

    /**
     * Retourne l'ensemble des noms des relations dans le modele physique
     * 
     * @param dataAccess
     * @return
     */
    protected List<String> getRelationsList() {
        return new ArrayList<>(getPhysicalRelations().keySet());
    }

    /**
     * Retourne les caracteristiques d'une relation
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

    public void setQueryProcessor(AbstractQueryProcessor<?> aQueryProcessor) {
        iQueryProcessor = aQueryProcessor;
    }

    public AbstractQueryProcessor<?> getQueryProcessor() {
        return iQueryProcessor;
    }

}
