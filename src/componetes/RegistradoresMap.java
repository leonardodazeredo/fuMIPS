package componetes;

import geral.Constantes;
import geral.Util;

import java.util.HashMap;


public class RegistradoresMap extends HashMap 
{
	public RegistradoresMap() throws Exception
	{
		for (int i = 0; i < 32; i++) 
		{
			this.put(Util.formata5bits(Util.toStringBinaria(i)), Util.retornaReg(i));
		}
		
		for(Object value: this.values())
		{
			Registrador registrador = (Registrador) value;
			
			registrador.setConteudo(Constantes.zero32bits);
		}
	}
	
	public RegistradoresMap(RegistradoresMap registradores) throws Exception
	{
		for(Object value: registradores.values())
		{
			Registrador registradorOrigem = (Registrador) value;
			
			Registrador registradorDestino = new Registrador(registradorOrigem.getNome(), registradorOrigem.getNumero());
			
			registradorDestino.setConteudo(new String(registradorOrigem.getConteudo()));
			
			this.put(registradorDestino.getNumero(), registradorDestino);
		}
	}
}
