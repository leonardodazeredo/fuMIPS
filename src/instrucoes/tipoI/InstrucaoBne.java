package instrucoes.tipoI;

import geral.Constantes;
import geral.Util;

import java.util.HashMap;

import componetes.InterfaceMontadorInstrucao;
import componetes.Memoria;
import componetes.Registrador;
import componetes.UCP;
import componetes.ULA;

public class InstrucaoBne extends TipoI
{
	public InstrucaoBne(String instrucao, InterfaceMontadorInstrucao montador)
	{
		super(instrucao,montador);
	}
	
	protected void preencherCamposAssembly(String instrucao, HashMap tabelaLabels)
	{
		super.op = Constantes.opBne;
		
		String[] instrucaoArray = instrucao.split(Constantes.quebraVirgulaOuEspacoVazioRegex);
			
		String rs = instrucaoArray[1];
		String rt = instrucaoArray[2];
			
		super.rs = Constantes.registradoresNomeToNumero.get(rs);
		super.rt = Constantes.registradoresNomeToNumero.get(rt);
		
		//converte a "numero/string" em binario
		if (tabelaLabels.get(instrucaoArray[3]) != null) 
		{
			String binario = Util.toStringBinaria((Integer) tabelaLabels.get(instrucaoArray[3]));
		
			super.im = Util.formata16bits(binario);
		}
	}

	private void corrigeLabel(int endereco, int enderecoDeInicio) throws Exception
	{
		int posicaoRelativaOrigem = 1 + ((endereco/4) - (enderecoDeInicio/4));
		
		String diferenca = ULA.subtrai(im, posicaoRelativaOrigem);
		
		im = Util.formata16bits(diferenca);
	}

	@Override
	public void carregarEm(Memoria memoria, int endereco, int enderecoDeInicio) throws Exception 
	{
		corrigeLabel(endereco,enderecoDeInicio);
		memoria.setWord(endereco, this.getBinario());
	}

	@Override
	public void executaEm(UCP ucp) throws Exception 
	{
		Registrador rs = ucp.getRegistrador(this.rs);
		Registrador rt = ucp.getRegistrador(this.rt);
		
		String destino = ULA.shiftL(im, 2);
		
		if (!(rs.getConteudo().equals(rt.getConteudo()))) 
		{
			ucp.setConteudoPC(ULA.soma(ucp.getConteudoPC(), destino));
		}
	}
}
