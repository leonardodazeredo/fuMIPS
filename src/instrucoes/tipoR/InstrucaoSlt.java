package instrucoes.tipoR;

import geral.Constantes;
import geral.Util;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;
import componetes.Registrador;
import componetes.UCP;

public class InstrucaoSlt extends TipoR
{
	public InstrucaoSlt(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels)
	{
		super.op = Constantes.opR;
		super.shamt = "00000";
		super.funct = Constantes.functSlt;
		
		String[] instrucaoArray = instrucao.split(Constantes.quebraVirgulaOuEspacoVazioRegex);
			
		String rs = instrucaoArray[2];
		String rt = instrucaoArray[3];
		String rd = instrucaoArray[1];
			
		super.rs = Constantes.registradoresNomeToNumero.get(rs);
		super.rt = Constantes.registradoresNomeToNumero.get(rt);
		super.rd = Constantes.registradoresNomeToNumero.get(rd);	
	}

	@Override
	public void executaEm(UCP ucp) throws Exception 
	{
		Registrador rs = ucp.getRegistrador(this.rs);
		Registrador rt = ucp.getRegistrador(this.rt);
		Registrador rd = ucp.getRegistrador(this.rd);
		
		if (Util.comparaBinario(rs.getConteudo(), rt.getConteudo()) < 0) 
		{
			rd.setConteudo(Util.formata32bits("1"));
		}
		else
		{
			rd.setConteudo(Util.formata32bits("0"));
		}
	}
}
