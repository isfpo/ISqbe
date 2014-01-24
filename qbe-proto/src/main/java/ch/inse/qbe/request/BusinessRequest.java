package ch.inse.qbe.request;

import java.util.List;

import ch.inse.qbe.model.AbstractBusinessAttribute;
import ch.inse.qbe.model.BusinessModel;
import ch.inse.qbe.model.BusinessObject;
import ch.inse.qbe.model.BusinessRelation;

/**
 * Classe contenant une requete organisee selon le modele business
 * 
 * @author frp
 * 
 */
public class BusinessRequest {

    private BusinessModel iBusinessModel;

    private List<BusinessObject> iObjectSelected;

    private List<AbstractBusinessAttribute> iAttributeSelected;

    private List<BusinessRelation> iRelationSelected;

    public BusinessModel getBusinessModel() {
        return iBusinessModel;
    }

    public void setBusinessModel(BusinessModel aBusinessModel) {
        iBusinessModel = aBusinessModel;
    }

    public List<BusinessObject> getObjectSelected() {
        return iObjectSelected;
    }

    public void setObjectSelected(List<BusinessObject> aObjectSelecetd) {
        iObjectSelected = aObjectSelecetd;
    }

    public List<AbstractBusinessAttribute> getAttributeSelected() {
        return iAttributeSelected;
    }

    public void setAttributeSelected(
            List<AbstractBusinessAttribute> aAttributeSelected) {
        iAttributeSelected = aAttributeSelected;
    }

    public List<BusinessRelation> getRelationSelected() {
        return iRelationSelected;
    }

    public void setRelationSelected(List<BusinessRelation> aRelationSelected) {
        iRelationSelected = aRelationSelected;
    }

    public String getWhereClause() {
        return iWhereClause;
    }

    public void setWhereClause(String aWhereClause) {
        iWhereClause = aWhereClause;
    }

    private String iWhereClause;

}
