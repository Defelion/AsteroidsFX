package dk.sdu.mmmi.cbse.highscore.objects;

public class TableCol {
    public String colName;
    public String value;

    public TableCol() {}

    public TableCol(String colName, String value) {
        this.colName = colName;
        this.value = value;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
