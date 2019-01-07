package instrucoes.tipoR;

import geral.Constantes;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;
import componetes.UCP;

public class InstrucaoJr extends TipoR
{
	public InstrucaoJr(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}

	protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels)
	{
		super.op = Constantes.opR;
		super.rt = "00000";
		super.rd = "00000";
		super.shamt = "00000";
		super.funct = Constantes.functJr;
		
		String[] instrucaoArray = instrucao.split(Constantes.quebraEspacoVazioRegex);
			
		String rs = instrucaoArray[1];
			
		super.rs = Constantes.registradoresNomeToNumero.get(rs);
	}

	@Override
	public void executaEm(UCP ucp) throws Exception 
	{
		if (ucp.getRegistrador(this.rs).getConteudo().equals(Constantes.zero32bits)) 
		{
			throw new Exception("intrucaoJr.executarEm.endereco.de.destino.0.invalido");
		}
		
		ucp.setConteudoPC(ucp.getRegistrador(this.rs).getConteudo());
	}
}
