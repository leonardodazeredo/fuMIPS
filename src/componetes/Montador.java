package componetes;

import geral.Constantes;
import geral.Fonte;
import geral.Identificador;
import geral.Linha;

import instrucoes.InterfaceInstrucao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import dados.Data;
import dados.DataArray;
import dados.DataString;

import principal.Simulador;
import pseudoInstrucoes.PseudoInstrucao;

public class Montador implements InterfaceMontadorInstrucao
{
	public final Simulador simulador;
	
	public Fonte fonte = new Fonte();
	
	public ArrayList<Data> dados = new ArrayList<Data>();
	public HashMap<String,Data> tabelaLabelDados = new HashMap<String,Data>();
	
	public ArrayList<String> linguagemDeMaquina = new ArrayList<String>();
	public HashMap<String,Integer> tabelaLabel = new HashMap<String,Integer>();
	
	private Integer enderecoDataSegmentDisponivel = Constantes.enderecoDataSegment;
	
	public Montador(Simulador simulador) 
	{
		this.simulador = simulador;
	}
	
	public void montar(File arquivo) throws Exception
	{	
		linguagemDeMaquina = new ArrayList<String>();
		
		fonte = extrairFonte(arquivo);
		
		extrairDadosFonte();
		
		ArrayList<String> instrucoesComLabel = extrairInstrucoesComLabelFonte();
		
		int i = 0;
		
		String instrucaoSemLabel;
		
		ArrayList<String> instrucoesSemLabel = new ArrayList<String>();
		
		for (String instrucao : (ArrayList<String>)instrucoesComLabel) 
		{
			instrucaoSemLabel = removerESalverLabel(instrucao,i);

			if (!instrucaoSemLabel.equals("")) 
			{
				instrucoesSemLabel.add(instrucaoSemLabel);
				i++;
				
				InterfaceInstrucao instrucaoLida = Identificador.identificaInstrucaoAssembly(instrucaoSemLabel,null);
				
				if (instrucaoLida instanceof PseudoInstrucao)
				{
					i+=((PseudoInstrucao)instrucaoLida).getNumeroDeInstrucoes() - 1;
				}
			}
		}
		
		for (String instrucao : (ArrayList<String>)instrucoesSemLabel) 
		{				
			Identificador.identificaInstrucaoAssembly(instrucao,this).montarEm(this);
		}
	}
	
	private ArrayList<String> extrairInstrucoesComLabelFonte()
	{
		ArrayList<String> instrucoesComLabel = new ArrayList<String>();
		
		int i = 0;
		
		for(Linha linha: fonte.getLinhas())
		{
			if (i++ > fonte.getInicioSegmentoText()) 
			{
				if (linha.getLabel()!=null) 
				{
					instrucoesComLabel.add(linha.getLabel() + ":" + linha.getPrincipal());
				}
				else
				{
					instrucoesComLabel.add(linha.getPrincipal());
				}
			}
		}
		
		return instrucoesComLabel;
	}
	
	private void extrairDadosFonte() 
	{
		int i = fonte.getInicioSegmentoData();
		
		for(Linha linha: fonte.getLinhas())
		{
			if(i>fonte.getInicioSegmentoData() && i<fonte.getInicioSegmentoText())
			{
				if(linha.getDiretriz() != null && linha.getDiretriz().equals(Constantes.diretrizAsciiz))
				{
					Data dado = new DataString(linha, enderecoDataSegmentDisponivel);
						
					dados.add(dado);
						
					enderecoDataSegmentDisponivel += dado.getTamanho();
						
					tabelaLabelDados.put(linha.getLabel(), dado);
				}
				else
				{
					Data dado = new DataArray(linha, enderecoDataSegmentDisponivel);
						
					dados.add(dado);
						
					enderecoDataSegmentDisponivel += dado.getTamanho();
						
					tabelaLabelDados.put(linha.getLabel(), dado);
				}
			}
			
			i++;
		}
	}
	
	static Fonte extrairFonte(File arquivo) 
	{
		Fonte fonte = new Fonte();

		try 
		{
			FileReader fileReader = new FileReader(arquivo);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha;

			while ((linha = bufferedReader.readLine()) != null) 
			{	
				if(!linha.replaceAll("\\t", "").trim().equals(""))
					fonte.adicionarLinha(new Linha(linha));
			}

			fileReader.close();
			
			bufferedReader.close();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return fonte;
	}
	
	private String removerESalverLabel(String instrucao, int numero)
	{
		int i = instrucao.indexOf(":");
		
		if (i>=0)
		{
			tabelaLabel.put(instrucao.substring(0, i ), new Integer(numero));
		}
		
		return instrucao.substring(i+1);
	}

	@Override
	public HashMap getTabelaLabel() 
	{
		return tabelaLabel;
	}

	@Override
	public HashMap getTabelaLabelDados() 
	{
		return tabelaLabelDados;
	}
}
