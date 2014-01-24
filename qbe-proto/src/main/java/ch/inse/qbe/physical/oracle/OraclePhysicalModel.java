package ch.inse.qbe.physical.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.javaunderground.jdbc.StatementFactory;

import ch.inse.qbe.model.BusinessData;
import ch.inse.qbe.model.BusinessRelation;
import ch.inse.qbe.physical.AbstractPhysicalModel;
import ch.inse.qbe.process.oracle.OracleQueryProcessor;

/**
 * Classe d'acces au modele physique Oracle
 * 
 * @author frp
 * 
 */
public class OraclePhysicalModel extends AbstractPhysicalModel<DataSource> {

    private static Log logger = LogFactory.getLog(OraclePhysicalModel.class);

    public OraclePhysicalModel(DataSource aDataSource) {
        super(aDataSource);
        initPhysicalModel();
    }

    private void initPhysicalModel() {
        setQueryProcessor(new OracleQueryProcessor(this));
        Connection con = null;
        try {
            con = getModelAccess().getConnection();
            for (String[] str : getPhysicalObjectsList(con)) {
                OraclePhysicalObject bo = new OraclePhysicalObject(str);
                putPhysicalObject(bo);
                for (String attr : getPhysicalDataList(str, con)) {
                    bo.putBusinessAttributes(getPhysicalData(str, attr, con));
                }
            }
            for (String rel : getPhysicalRelationsList(con)) {
                putPhysicalRelation(getRelation(rel, con));
            }
        } catch (SQLException e) {
            logger.error("Error initiating physical model", e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // Just do nothing
                }
            }
        }
    }

    private List<String[]> getPhysicalObjectsList(Connection con) {
        List<String[]> tables = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // USER_TABLES
            ps = StatementFactory.getStatement(con,
                    "select owner,table_name from all_tables where owner=user");
            rs = ps.executeQuery();
            while (rs.next()) {
                tables.add(new String[] { rs.getString(1), rs.getString(2) });
            }
            rs.close();
            ps.close();
            // SYNONYMS - en �vitant les vues et les s�quences...
            StringBuffer sql = new StringBuffer();
            sql.append("select s.table_owner,s.synonym_name from user_synonyms s,user_tab_privs p, all_tables t ");
            sql.append("where s.table_owner=p.owner and ");
            sql.append("p.owner=t.owner and p.table_name=t.table_name and s.table_name=p.table_name and ");
            sql.append("p.privilege='SELECT'");
            ps = StatementFactory.getStatement(con, sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                tables.add(new String[] { rs.getString(1), rs.getString(2) });
            }
        } catch (SQLException e) {
            logger.error("Error getting table names", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // Just do nothing
            }
        }
        return tables;
    }

    private List<String> getPhysicalDataList(String[] aNames, Connection con) {
        List<String> fields = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select column_name from all_tab_columns where table_name = '");
            sql.append(aNames[1].toUpperCase());
            sql.append("' and owner = '");
            sql.append(aNames[0].toUpperCase());
            sql.append("'");
            ps = StatementFactory.getStatement(con, sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                fields.add(rs.getString(1));
            }
        } catch (SQLException e) {
            logger.error("Error getting table names", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                // Just do nothing
            }
        }
        return fields;
    }

    private OraclePhysicalData getPhysicalData(String[] aNames,
            String aFieldName, Connection con) {
        OraclePhysicalData field = new OraclePhysicalData(aFieldName);
        return field;
    }

    private List<String> getPhysicalRelationsList(Connection con) {
        List<String> relations = new ArrayList<>();
        return relations;
    }

    private OraclePhysicalRelation getRelation(String aName, Connection con) {
        OraclePhysicalRelation relation = new OraclePhysicalRelation(aName,
                null, null);
        return relation;
    }

    @Override
    protected BusinessData getField(String anObjectName, String aFieldName) {
        return new BusinessData(anObjectName, getPhysicalObjects()
                .get(anObjectName).getAttribute(aFieldName).getName());
    }

    @Override
    protected BusinessRelation getRelation(String aName) {
        // TODO Auto-generated method stub
        return null;
    }

}
