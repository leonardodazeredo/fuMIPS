package instrucoes.tipoI;

import geral.Constantes;
import geral.Util;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;
import componetes.Registrador;
import componetes.UCP;
import componetes.ULA;

public class InstrucaoLw extends TipoI
{
	public InstrucaoLw(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels)
	{
		super.op = Constantes.opLw;
		
		String[] instrucaoArray = instrucao.split(Constantes.quebraLoadStoreRegex);
			
		String rs = instrucaoArray[3];
		String rt = instrucaoArray[1];
			
		super.rs = Constantes.registradoresNomeToNumero.get(rs);
		super.rt = Constantes.registradoresNomeToNumero.get(rt);
		
		String binario = Util.toStringBinaria(Integer.parseInt(instrucaoArray[2]));
		
		this.im = Util.formata16bits(binario);
	}

	@Override
	public void executaEm(UCP ucp) throws Exception 
	{
		Registrador rs = ucp.getRegistrador(this.rs);
		Registrador rt = ucp.getRegistrador(this.rt);
		
		String offset = ULA.soma(rs.getConteudo(), im);
		
		rt.setConteudo(ucp.getWordMemoria(Util.getInteiroComSinal(offset)));	
	}
}
