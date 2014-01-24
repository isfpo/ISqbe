package ch.inse.qbe.process;

import ch.inse.qbe.physical.AbstractPhysicalModel;
import ch.inse.qbe.request.BusinessRequest;
import ch.inse.qbe.result.QueryResult;

public abstract class AbstractQueryProcessor<T extends AbstractPhysicalModel> {

    private T iPhysicalModel;

    public AbstractQueryProcessor(T physicalModel) {
        iPhysicalModel = physicalModel;
    }

    public abstract QueryResult process(BusinessRequest aBusinessRequest);

    public T getPhysicalModel() {
        return iPhysicalModel;
    }
}
