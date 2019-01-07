package dados;

import componetes.Memoria;

import geral.Linha;

public abstract class Data 
{
	Linha linha;
	
	Integer enderecoToLoad;
	
	Integer tamanho;

	public Integer getTamanho() 
	{
		return new Integer(tamanho);
	}
	
	public Integer getEnderecoToLoad() 
	{
		return new Integer(enderecoToLoad);
	}

	public abstract void carregarEm(Memoria memoria);
}
