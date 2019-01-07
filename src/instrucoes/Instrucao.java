package instrucoes;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;

import geral.Util;

public abstract class Instrucao implements InterfaceInstrucao
{
	protected String instrucao;
	
	public Instrucao(String instrucao, InterfaceMontadorInstrucao montador) 
	{
		this.instrucao = new String(instrucao);
		
		if (Util.isAssembly(instrucao)) 
		{
			if (montador != null)
			{
				preencherCamposAssembly(instrucao, montador.getTabelaLabel());	
			}
		}
		else
		{
			preencherCamposMaquina(instrucao);
		}
	}
	
	protected abstract void preencherCamposAssembly(String instrucao, HashMap tabelaLabels);
	
	protected abstract void preencherCamposMaquina(String instrucao);
}
