package instrucoes.tipoJ;

import geral.Constantes;
import geral.Util;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;
import componetes.Memoria;
import componetes.Registrador;
import componetes.UCP;
import componetes.ULA;

public class InstrucaoJal extends TipoJ
{
	public InstrucaoJal(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels)
	{
		super.op = Constantes.opJal;
		
		String[] instrucaoArray = instrucao.split(Constantes.quebraEspacoVazioRegex);
		
		if(!Util.isAssembly(instrucaoArray[1])) 
		{	
			//se for endereco binario
			super.ad = Util.formata26bits(instrucaoArray[1]);
		}
		else
		{
			//se for label
			//converte a "numero/string" em binario
			if (tabelaLabels.get(instrucaoArray[1]) != null) 
			{
				String binario = Util.toStringBinaria((Integer) tabelaLabels.get(instrucaoArray[1]));
				
				super.ad = Util.formata26bits(binario);
			}
		}	
	}
	
	private void corrigeLabel(int enderecoDeInicio) throws Exception
	{
		ad = Util.formata26bits(ULA.soma(ad, enderecoDeInicio/4));
	}

	public void carregarEm(Memoria memoria, int endereco, int enderecoDeInicio) throws Exception 
	{
		corrigeLabel(enderecoDeInicio);
		memoria.setWord(endereco, this.getBinario());
	}

	@Override
	public void executaEm(UCP ucp) throws Exception 
	{
		Registrador ra = ucp.getRegistrador(Constantes.ra);
		
		ra.setConteudo(ucp.getConteudoPC());
		
		String endereco = ULA.shiftL(ad,2);
		
		ucp.setConteudoPC(ucp.getConteudoPC().subSequence(0, 4) + endereco);
	}
}
