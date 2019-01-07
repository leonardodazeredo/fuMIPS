package view;

import geral.Util;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import componetes.*;

public class TableRegistradoresListener implements TableModelListener
{
	public RegistradoresMap registradores;
	
	public TableRegistradoresListener(RegistradoresMap registradores)
	{
		this.registradores = registradores;
	}
	
	@Override
	public void tableChanged(TableModelEvent e) 
	{
		int column = e.getColumn();
		
//		Atualiza o valor no registrador caso a celula correspondente tenha sido alterada na interface
		if (column==2) 
		{
			int row = e.getFirstRow();
			
			System.out.println("Atualizando resgistrador " + row);

	        TableModel model = (TableModel)e.getSource();
	        
	        String data = (String) model.getValueAt(row, column);
	        
	        Registrador registrador = (Registrador) registradores.get(Util.formata5bits(Integer.toBinaryString(row)));
	        
	        try 
	        {
				registrador.setConteudo(Util.formata32bits(Integer.toBinaryString(Integer.parseInt(data))));
			} 
	        catch (Exception e1) 
	        {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
