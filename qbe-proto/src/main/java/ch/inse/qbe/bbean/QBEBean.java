package ch.inse.qbe.bbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import ch.inse.qbe.model.AbstractBusinessAttribute;
import ch.inse.qbe.model.BusinessModel;
import ch.inse.qbe.model.BusinessObject;
import ch.inse.qbe.model.BusinessRelation;
import ch.inse.qbe.physical.oracle.OraclePhysicalModel;
import ch.inse.qbe.request.BusinessRequest;
import ch.inse.qbe.result.QueryResult;
import ch.inser.dynamic.common.IContextManager;

public class QBEBean {

    /**
     * ContextManager mis a dispositon par initialisation JSF
     */
    private IContextManager iContextManager;

    private BusinessModel iBusinessModel = null;

    /**
     * Objets sélectionnées pour la requête
     */
    private List<BusinessObject> iObjectSelected = new ArrayList<>();

    /**
     * Relations sélectionnées pour la requête
     */
    private List<BusinessRelation> iRelationSelected = new ArrayList<>();

    /**
     * Champs sélectionnées pour la requête
     */
    private List<AbstractBusinessAttribute> iAttributeSelected = new ArrayList<>();

    private QueryResult iQueryResult = new QueryResult();

    /**
     * Where clause
     */
    private String iWhereClause = "";

    /**
     * Objet en cours pour la sélection
     */
    private BusinessObject iSelectedObject;

    /**
     * Objet en cours pour la sélection
     */
    private AbstractBusinessAttribute iSelectedAttribute;

    public void setContextManager(IContextManager aContextManager) {
        iContextManager = aContextManager;
    }

    @PostConstruct
    public void initBusinessModel() {
        iBusinessModel = new OraclePhysicalModel(
                iContextManager.getDataSource()).getBusinessModel("test");
    }

    public List<BusinessObject> getObjectList() {
        return iBusinessModel.getBuinessList();
    }

    public List<BusinessObject> getObjectSelected() {
        return iObjectSelected;
    }

    public String getWhereClause() {
        return iWhereClause;
    }

    public void setWhereClause(String aWhereClause) {
        iWhereClause = aWhereClause;
    }

    public List<BusinessRelation> getRelationSelected() {
        return iRelationSelected;
    }

    public List<AbstractBusinessAttribute> getAttributeSelected() {
        return iAttributeSelected;
    }

    // Utilitaires pour les actions
    public void setAdditionalProperty(BusinessObject obj) {
        iSelectedObject = obj;
    }

    // Utilitaires pour les actions
    public void setAdditionalAttrProperty(AbstractBusinessAttribute obj) {
        iSelectedAttribute = obj;
    }

    public QueryResult getQueryResult() {
        return iQueryResult;
    }

    // ACTIONS
    public String onTableSelect() {
        iObjectSelected.add(iSelectedObject);
        return null;
    }

    public String onTableUnselect() {
        iObjectSelected.remove(iSelectedObject);
        return null;
    }

    public String onAttributeSelect() {
        iAttributeSelected.add(iSelectedAttribute);
        return null;
    }

    public String onAttributeWhere() {
        iWhereClause = iWhereClause + iSelectedAttribute.getName();
        return null;
    }

    public void doExecute() {
        BusinessRequest request = new BusinessRequest();
        request.setAttributeSelected(iAttributeSelected);
        request.setObjectSelected(iObjectSelected);
        request.setRelationSelected(iRelationSelected);
        request.setWhereClause(iWhereClause);
        iQueryResult = iBusinessModel.getPhysicalModel().getQueryProcessor()
                .process(request);
    }
}
