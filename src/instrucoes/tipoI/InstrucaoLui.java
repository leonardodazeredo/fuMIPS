package instrucoes.tipoI;

import geral.Constantes;
import geral.Util;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;
import componetes.Registrador;
import componetes.UCP;

public class InstrucaoLui extends TipoI
{
	public InstrucaoLui(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels)
	{
		super.op = Constantes.opLui;
		super.rs = "00000";
		
		String[] instrucaoArray = instrucao.split(Constantes.quebraVirgulaOuEspacoVazioRegex);
			
		String rt = instrucaoArray[1];
			
		super.rt = Constantes.registradoresNomeToNumero.get(rt);
		
		Integer numero = Integer.parseInt(instrucaoArray[2]);
		
		this.im = Util.formata16bits(Util.toStringBinaria(numero));
	}

	@Override
	public void executaEm(UCP ucp) throws Exception 
	{
		Registrador rt = ucp.getRegistrador(this.rt);
		
		rt.setConteudo(Util.formata16bits(im) + Util.formata16bits("0"));
	}
}
