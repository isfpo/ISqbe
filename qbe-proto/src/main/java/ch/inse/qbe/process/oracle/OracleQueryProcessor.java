package ch.inse.qbe.process.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.javaunderground.jdbc.StatementFactory;

import ch.inse.qbe.model.AbstractBusinessAttribute;
import ch.inse.qbe.model.BusinessObject;
import ch.inse.qbe.physical.oracle.OraclePhysicalModel;
import ch.inse.qbe.process.AbstractQueryProcessor;
import ch.inse.qbe.request.BusinessRequest;
import ch.inse.qbe.result.QueryResult;

public class OracleQueryProcessor extends
        AbstractQueryProcessor<OraclePhysicalModel> {

    private static Log logger = LogFactory.getLog(OracleQueryProcessor.class);

    public OracleQueryProcessor(OraclePhysicalModel physicalModel) {
        super(physicalModel);
    }

    @Override
    public QueryResult process(BusinessRequest aBusinessRequest) {
        // TODO: faire le mapping sur le modèle physique
        List<String> columns = new ArrayList<>();
        List<String[]> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        for (AbstractBusinessAttribute data : aBusinessRequest
                .getAttributeSelected()) {
            sql.append(data.getName());
            sql.append(",");
            columns.add(data.getName());
        }
        sql.setLength(sql.length() - 1);
        sql.append(" from ");
        for (BusinessObject obj : aBusinessRequest.getObjectSelected()) {
            sql.append(obj.getName());
            sql.append(",");
        }
        sql.setLength(sql.length() - 1);
        sql.append(" where ");
        // TODO corriger le problème de sécurité à l'injection
        sql.append(aBusinessRequest.getWhereClause());
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getPhysicalModel().getModelAccess().getConnection();

            ps = StatementFactory.getStatement(con, sql.toString());
            rs = ps.executeQuery();
            int size = columns.size();
            while (rs.next()) {
                List<String> raw = new ArrayList<>();
                for (int i = 1; i <= size; i++) {
                    Object obj = rs.getObject(i);
                    if (obj == null) {
                        raw.add("");
                    } else {
                        raw.add(obj.toString());
                    }
                }

                result.add(raw.toArray(new String[0]));
            }
        } catch (SQLException e) {
            logger.error("Error procesing", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                // Just do nothing
            }
        }
        return new QueryResult(columns, result);
    }

}
