package view;

import geral.DadoTextoVO;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelTexto extends AbstractTableModel 
{
	private String[] columnNames = {"Endereco", "Codigo", "Fonte"};
	
	private Object[][] data;
	
	public TableModelTexto(ArrayList<DadoTextoVO> dados) 
	{
		super();
		
		data = new Object[dados.size()][3];
		
		int i = 0;
		
		for(DadoTextoVO dadoTextoVO: dados)
		{
			data[i][0] = dadoTextoVO.getEndereco();
			data[i][1] = dadoTextoVO.getCodigo();
			data[i][2] = dadoTextoVO.getFonte();
			
			i++;
		}
	}
	
	public Class getColumnClass(int columnIndex) 
	{
		return String.class;
	}

	public boolean isCellEditable(int row, int column) 
	{
		return false;
	}
	
    public Object getValueAt(int row, int col) 
    {
        return data[row][col];
    }
    
    public void setValueAt(Object value, int row, int col) 
    {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    public int getColumnCount() 
    {
        return columnNames.length;
    }

    public int getRowCount() 
    {
        return data.length;
    }
    
    public String getColumnName(int col) 
    {
        return columnNames[col];
    }
}
