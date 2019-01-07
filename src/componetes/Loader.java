package componetes;

import geral.Constantes;
import geral.Identificador;
import instrucoes.InterfaceInstrucao;

import java.util.ArrayList;

import dados.Data;

import principal.Simulador;

public class Loader 
{
	public Simulador simulador;
	
	public Loader(Simulador simulador) 
	{
		super();
		this.simulador = simulador;
	}

	public void carregarEmMemoria(ArrayList<String> texto, ArrayList<Data> dados, Memoria memoria, int enderecoDeInicio) throws Exception
	{		
		if (enderecoDeInicio%4!=0) 
		{
			throw new Exception("loader.carregarEmMemoria.enderecoDeInicio.nao.e.multiplo.de.4");
		}
		
		for (Data dado: dados) 
		{
			dado.carregarEm(memoria);
		}
		
		int endereco = enderecoDeInicio;
		
		for (String instrucao: texto) 
		{
			InterfaceInstrucao instrucaoInterface = Identificador.identificaInstrucaoBinario(instrucao);
			
			instrucaoInterface.carregarEm(memoria, endereco, enderecoDeInicio);
			
			endereco+=4;
		}
		
		memoria.setWord(endereco, Constantes.halt);
		
		simulador.memoria.teste_imprimeMemoria();
	}
}
