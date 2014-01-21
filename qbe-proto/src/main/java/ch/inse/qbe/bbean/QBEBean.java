package ch.inse.qbe.bbean;

import java.util.List;

import javax.annotation.PostConstruct;

import ch.inse.qbe.model.BusinessModel;
import ch.inse.qbe.model.BusinessObject;
import ch.inse.qbe.physical.oracle.OraclePhysicalModel;
import ch.inser.dynamic.common.IContextManager;

public class QBEBean {

    /**
     * ContextManager mis à disposition par initialisation JSF
     */
    private IContextManager iContextManager;

    private BusinessModel iBusinessModel = null;

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
}
