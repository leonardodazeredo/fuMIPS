package instrucoes.tipoI;

import geral.Constantes;
import geral.Util;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;
import componetes.Registrador;
import componetes.UCP;

public class InstrucaoSlti extends TipoI
{
	public InstrucaoSlti(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels)
	{
		super.op = Constantes.opSlti;
		
		String[] instrucaoArray = instrucao.split(Constantes.quebraVirgulaOuEspacoVazioRegex);
			
		String rs = instrucaoArray[2];
		String rt = instrucaoArray[1];
			
		super.rs = Constantes.registradoresNomeToNumero.get(rs);
		super.rt = Constantes.registradoresNomeToNumero.get(rt);
		
		String binario = Util.toStringBinaria(Integer.parseInt(instrucaoArray[3]));
		
		this.im = Util.formata16bits(binario);
	}

	@Override
	public void executaEm(UCP ucp) throws Exception 
	{
		Registrador rs = ucp.getRegistrador(this.rs);
		Registrador rt = ucp.getRegistrador(this.rt);
		
		if (Util.comparaBinario(rs.getConteudo(), im) < 0) 
		{
			rt.setConteudo(Util.formata32bits("1"));
		}
		else
		{
			rt.setConteudo(Util.formata32bits("0"));
		}
	}
}
