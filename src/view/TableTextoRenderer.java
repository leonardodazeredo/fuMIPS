package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class TableTextoRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
	private Integer conteudoPC;
	
	public TableTextoRenderer(Integer conteudoPC) 
	{
		super();
		this.conteudoPC = conteudoPC;
	}

	private final Color COLOR_ON_PC = Color.ORANGE;
	
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
		
        if (Integer.parseInt((String)(table.getModel().getValueAt(row, 0))) == conteudoPC)
        {
        	comp.setBackground(COLOR_ON_PC);  
        } 
        
        return comp;
	}
}
