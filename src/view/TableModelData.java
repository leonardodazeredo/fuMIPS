package view;

import javax.swing.table.AbstractTableModel;

import geral.Constantes;
import componetes.Memoria;
import geral.Util;

public class TableModelData extends AbstractTableModel 
{
	private String[] columnNames = {"Endereco", 
									"Valor(+0)", 
									"Valor(+4)",
									"Valor(+8)",
									"Valor(+12)",
									"Valor(+16)",
									"Valor(+20)",
									"Valor(+24)",		
									"Valor(+28)"};
	
	private Object[][] data;
	
	public TableModelData(Memoria memoria) 
	{
		super();
		
		data = new Object[Constantes.tamanhoMemoria/32][9];
		
		int endereco = 0;
		
		for(int linha = 0; linha < Constantes.tamanhoMemoria/32 ; linha++)
		{
			data[linha][0] = endereco;
			
			for(int coluna = 1; coluna < 9 ; coluna++,endereco+=4)
			{
				try 
				{
					data[linha][coluna] = Util.getInteiroComSinal(memoria.getWord(endereco));
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void atualizarDados(Memoria memoria, Boolean modoAscii)
	{
		int endereco = 0;
		
		for(int linha = 0; linha < Constantes.tamanhoMemoria/32 ; linha++)
		{
			data[linha][0] = endereco;
			
			for(int coluna = 1; coluna < 9 ; coluna++,endereco+=4)
			{
				if (!modoAscii) 
				{
					try 
					{
						data[linha][coluna] = Util.getInteiroComSinal(memoria.getWord(endereco));
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					try 
					{
						data[linha][coluna] = Util.formataAscii(memoria.getWord(endereco));
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		fireTableDataChanged();
	}

	public Class getColumnClass(int columnIndex) 
	{
		return String.class;
	}

	public boolean isCellEditable(int row, int column) 
	{
//		if(column > 0)
//		{
//			return true;
//		}
		
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
