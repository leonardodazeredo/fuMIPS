package instrucoes.tipoR;

import java.util.HashMap;

import geral.Util;
import instrucoes.Instrucao;
import componetes.InterfaceMontadorInstrucao;
import componetes.Memoria;
import componetes.Montador;

public abstract class TipoR extends Instrucao
{
	String op;
	String rs;
	String rt;
	String rd;
	String shamt;
	String funct;
	
	public TipoR(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	abstract protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels);
	
	public String getBinario()
	{		
		return op + rs + rt + rd + shamt + funct;
	}
	
	public void montarEm(Montador montador)
	{		
		montador.linguagemDeMaquina.add(getBinario());
	}
	
	protected void preencherCamposMaquina(String instrucao)
	{
		op = Util.getOp(instrucao);
		rs = Util.getRs(instrucao);
		rt = Util.getRt(instrucao);
		rd = Util.getRd(instrucao);
		shamt = Util.getShamt(instrucao);
		funct = Util.getFunct(instrucao);
	}
	
	public void carregarEm(Memoria memoria, int endereco, int enderecoDeInicio) throws Exception 
	{
		memoria.setWord(endereco, this.getBinario());
	}	
}