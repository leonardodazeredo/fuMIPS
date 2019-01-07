package instrucoes.tipoR;

import geral.Constantes;
import geral.Util;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;
import componetes.Registrador;
import componetes.UCP;
import componetes.ULA;

public class InstrucaoSll extends TipoR
{
	public InstrucaoSll(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels)
	{
		super.op = Constantes.opR;
		super.rs = "00000";
		super.funct = Constantes.functSll;
		
		String[] instrucaoArray = instrucao.split(Constantes.quebraVirgulaOuEspacoVazioRegex);
			
		String rt = instrucaoArray[2];
		String rd = instrucaoArray[1];
			
		super.rt = Constantes.registradoresNomeToNumero.get(rt);
		super.rd = Constantes.registradoresNomeToNumero.get(rd);
	
		//converte a "numero/string" em binario
		String binario = Util.toStringBinaria(Integer.parseInt(instrucaoArray[3]));
		
		super.shamt = Util.formata5bits(binario);
	}

	@Override
	public void executaEm(UCP ucp) throws NumberFormatException, Exception 
	{
		Registrador rt = ucp.getRegistrador(this.rt);
		Registrador rd = ucp.getRegistrador(this.rd);
		
		rd.setConteudo(ULA.shiftL(rt.getConteudo(),Integer.parseInt(shamt)));
	}
}
