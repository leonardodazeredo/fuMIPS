package view;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import geral.Constantes;
import componetes.Registrador;
import componetes.RegistradoresMap;
import geral.Util;

public class TableModelRegistradores extends AbstractTableModel 
{
    private String[] columnNames = {"Nome", "N\u00FAmero", "Valor"};
    
    private Object[][] data = new Object[][] {
			{Constantes.zero, new Integer(0), ""},
			{Constantes.at, new Integer(1), ""},
			{Constantes.v0, new Integer(2), ""},
			{Constantes.v1, new Integer(3), ""},
			{Constantes.a0, new Integer(4), ""},
			{Constantes.a1, new Integer(5), ""},
			{Constantes.a2, new Integer(6), ""},
			{Constantes.a3, new Integer(7), ""},
			{Constantes.t0, new Integer(8), ""},
			{Constantes.t1, new Integer(9), ""},
			{Constantes.t2, new Integer(10), ""},
			{Constantes.t3, new Integer(11), ""},
			{Constantes.t4, new Integer(12), ""},
			{Constantes.t5, new Integer(13), ""},
			{Constantes.t6, new Integer(14), ""},
			{Constantes.t7, new Integer(15), ""},
			{Constantes.s0, new Integer(16), ""},
			{Constantes.s1, new Integer(17), ""},
			{Constantes.s2, new Integer(18), ""},
			{Constantes.s3, new Integer(19), ""},
			{Constantes.s4, new Integer(20), ""},
			{Constantes.s5, new Integer(21), ""},
			{Constantes.s6, new Integer(22), ""},
			{Constantes.s7, new Integer(23), ""},
			{Constantes.t8, new Integer(24), ""},
			{Constantes.t9, new Integer(25), ""},
			{Constantes.k0, new Integer(26), ""},
			{Constantes.k1, new Integer(27), ""},
			{Constantes.gp, new Integer(28), ""},
			{Constantes.sp, new Integer(29), ""},
			{Constantes.fp, new Integer(30), ""},
			{Constantes.ra, new Integer(31), ""},
			{Constantes.pc, "", ""},
	};
    
    public TableModelRegistradores(RegistradoresMap registradores, String conteudoPC) 
    {
		super();
		
		for (int i = 0; i < 32; i++) 
		{
			String valor = "Erro";
			
			try 
			{
				valor = String.valueOf(Util.getInteiroComSinal(((Registrador)(registradores.get(Util.formata5bits(Integer.toBinaryString(i))))).getConteudo()));
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			data[i][2] = valor;
		}
		
		try {
			data[32][2] = Util.getInteiroComSinal(conteudoPC);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    Class[] columnTypes = new Class[] {String.class, String.class, String.class};
	public Class getColumnClass(int columnIndex) 
	{
		return columnTypes[columnIndex];
	}
	
	boolean[] columnEditables = new boolean[] {false, false, true};
	public boolean isCellEditable(int row, int column) 
	{
		if (row >= 31) 
		{
			return false;
		}
		
		return columnEditables[column];
	}
	
    public Object getValueAt(int row, int col) 
    {
        return data[row][col];
    }
    
    public ArrayList<Integer> atualizarDados(Collection registradores,String conteudoPC) 
    {
    	ArrayList<Integer> registradoresAlterados = new ArrayList<Integer>();
    	
    	for (Object registrador: registradores) 
		{
    		String valor = "Erro";
    		
			try 
			{
				valor = String.valueOf(Util.getInteiroComSinal(((Registrador)registrador).getConteudo()));
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Integer numero = Integer.parseInt(((Registrador)registrador).getNumero(),2);
			
			if (!data[numero][2].equals(valor)) 
			{
				registradoresAlterados.add(numero);
			}
			
			data[numero][2] = valor;
		}
    	
    	try 
    	{
			data[32][2] = Util.getInteiroComSinal(conteudoPC);
		} 
    	catch (Exception e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
        fireTableDataChanged();
        
        return registradoresAlterados;
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