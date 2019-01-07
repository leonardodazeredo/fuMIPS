package instrucoes.tipoJ;

import java.util.HashMap;

import geral.Util;
import instrucoes.Instrucao;
import componetes.InterfaceMontadorInstrucao;
import componetes.Montador;

public abstract class TipoJ extends Instrucao
{
	String op;
	String ad;
	
	public TipoJ(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	protected abstract void preencherCamposAssembly(String instrucao, HashMap tabelaLabels);
	
	public String getBinario()
	{		
		return op + ad;
	}
	
	public void montarEm(Montador montador)
	{		
		montador.linguagemDeMaquina.add(getBinario());
	}
	
	protected void preencherCamposMaquina(String instrucao)
	{
		op = Util.getOp(instrucao);
		ad = Util.getAd(instrucao);
	}
}