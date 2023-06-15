import javax.swing.table.AbstractTableModel;

public class Model extends AbstractTableModel {
    // Dichiarazione delle variabili per i dati della tabella
    private Object[][] data;
    private String[] columnNames;

    // Costruttore per inizializzare il modello della tabella
    public Model(Object[][] data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    // Implementazione dei metodi dell'interfaccia TableModel
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}

