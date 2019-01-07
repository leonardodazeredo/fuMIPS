package view;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableRegistradoresRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
	private ArrayList<Integer> registradoresAlterados = new ArrayList<Integer>();
	
	public TableRegistradoresRenderer(ArrayList<Integer> registradoresAlterados) 
	{
		super();
		this.registradoresAlterados = registradoresAlterados;
	}

	private final Color COLOR_MUDADA = Color.ORANGE;
	
	private final Color COLOR_NAO_MUDADA_PAR = Color.WHITE;
	
	private final Color COLOR_NAO_MUDADA_IMPAR = Color.LIGHT_GRAY;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
	{
		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (row%2!=0)
	    {
	    	comp.setBackground(COLOR_NAO_MUDADA_IMPAR);
	    } 
		else
		{
			comp.setBackground(COLOR_NAO_MUDADA_PAR);
		}
		
		if (registradoresAlterados != null) 
		{
			for (Integer idReg: registradoresAlterados) 
			{
				if (idReg == row)
			    {
			        comp.setBackground(COLOR_MUDADA);  
			    } 
			}
		}
		
        return comp;
	}
}
