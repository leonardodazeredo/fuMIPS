package principal;

import java.awt.EventQueue;

import componetes.Registrador;
import geral.Util;
import view.Interface;

public class Principal 
{
	public static void main(String[] args) throws Exception 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Simulador simulador = new Simulador();
				
					Interface frame = new Interface(simulador);
	
					simulador.setInterfaceSimulador(frame);

					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings("unused")
	private static void teste_inicializaDados(Simulador simulador) throws Exception
	{
		Registrador testea = (Registrador) simulador.registradores.get(Util.formata5bits(Integer.toBinaryString(11)));
		Registrador testeb = (Registrador) simulador.registradores.get(Util.formata5bits(Integer.toBinaryString(12)));
		testea.setConteudo(Util.formata32bits(Integer.toBinaryString(4)));
		testeb.setConteudo(Util.formata32bits(Integer.toBinaryString(5)));
	}
}
