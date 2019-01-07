package instrucoes.tipoR;

import geral.Constantes;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;
import componetes.Registrador;
import componetes.UCP;
import componetes.ULA;

public class InstrucaoOr extends TipoR
{
	public InstrucaoOr(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels)
	{
		super.op = Constantes.opR;
		super.shamt = "00000";
		super.funct = Constantes.functOr;
		
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
		
		rd.setConteudo(ULA.or(rs.getConteudo(), rt.getConteudo()));
	}
}
