package instrucoes.tipoI;

import java.util.HashMap;

import geral.Util;
import instrucoes.Instrucao;
import componetes.InterfaceMontadorInstrucao;
import componetes.Memoria;
import componetes.Montador;

public abstract class TipoI extends Instrucao
{
	String op;
	String rs;
	String rt;
	String im;
	
	public TipoI(String instrucao, InterfaceMontadorInstrucao montador) 
	{
		super(instrucao,montador);
	}
	
	protected abstract void preencherCamposAssembly(String instrucao, HashMap tabelaLabels);
	
	public void montarEm(Montador montador)
	{		
		montador.linguagemDeMaquina.add(this.getBinario());
	}
	
	public String getBinario()
	{		
		return op + rs + rt + im;
	}
	
	public void carregarEm(Memoria memoria, int endereco, int enderecoDeInicio) throws Exception 
	{
		memoria.setWord(endereco, this.getBinario());
	}
	
	protected void preencherCamposMaquina(String instrucao)
	{
		op = Util.getOp(instrucao);
		rs = Util.getRs(instrucao);
		rt = Util.getRt(instrucao);
		im = Util.getIm(instrucao);
	}
}