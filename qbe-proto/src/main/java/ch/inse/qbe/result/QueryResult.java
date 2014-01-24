package ch.inse.qbe.result;

import java.util.ArrayList;
import java.util.List;

public class QueryResult {

    private List<String> iColumns = new ArrayList<>();

    private List<String[]> iDatas = new ArrayList<>();

    public QueryResult() {
        // Dummy datas
        iColumns.add("Column 1");
        iColumns.add("Column 2");
        iColumns.add("Column 3");
        iColumns.add("Column 4");
        for (int i = 0; i < 5; i++) {
            String[] data = new String[4];
            data[0] = "tralala " + i;
            data[1] = "tralala " + i;
            data[2] = "tralala " + i;
            data[3] = "tralala " + i;
            iDatas.add(data);
        }
    }

    public QueryResult(List<String> columns, List<String[]> datas) {
        // Dummy datas
        iColumns = columns;
        iDatas = datas;
    }

    public List<String> getColumns() {
        return iColumns;
    }

    public List<String[]> getDatas() {
        return iDatas;
    }

}
