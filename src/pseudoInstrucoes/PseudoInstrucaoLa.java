package pseudoInstrucoes;

import instrucoes.InterfaceInstrucao;
import instrucoes.tipoI.InstrucaoLui;
import instrucoes.tipoI.InstrucaoOri;

import java.util.HashMap;

import geral.Constantes;
import dados.Data;
import componetes.InterfaceMontadorInstrucao;
import componetes.Memoria;
import componetes.Montador;
import componetes.UCP;
import geral.Util;

public class PseudoInstrucaoLa extends PseudoInstrucao implements InterfaceInstrucao
{
	String ad;
	
	String registrador;
	
	public PseudoInstrucaoLa(String instrucao, InterfaceMontadorInstrucao montador)
	{
		if (montador != null) 
		{
			preencherCamposAssembly(instrucao,montador.getTabelaLabelDados());
		}
	
		super.numeroDeInstrucoes = 2;
	}
	
	protected void preencherCamposAssembly(String instrucao, HashMap tabelaDadosLabels)
	{
		String[] instrucaoArray = instrucao.split(Constantes.quebraVirgulaOuEspacoVazioRegex);

		String registrador = instrucaoArray[1];
			
		this.registrador = registrador;
		
		if (tabelaDadosLabels.get(instrucaoArray[2]) != null) 
		{
			Integer binario = ((Data) tabelaDadosLabels.get(instrucaoArray[2])).getEnderecoToLoad();
		
			this.ad = Util.toStringBinaria(binario);
		}
	}

	@Override
	public void montarEm(Montador montador) 
	{
		InstrucaoLui instrucaoLui = null;
		InstrucaoOri instrucaoOri = null;
		
		try 
		{
			instrucaoLui = new InstrucaoLui("lui $at," + Util.getInteiroComSinal(ad.substring(0, 16)), montador);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			instrucaoOri = new InstrucaoOri("ori " + registrador + ",$at," + Util.getInteiroComSinal(ad.substring(16, 32)), montador);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		montador.linguagemDeMaquina.add(instrucaoLui.getBinario());
		montador.linguagemDeMaquina.add(instrucaoOri.getBinario());
	}

	@Override
	public void carregarEm(Memoria memoria, int endereco, int enderecoDeInicio)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executaEm(UCP ucp) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
