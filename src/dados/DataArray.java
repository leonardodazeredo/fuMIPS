package dados;

import geral.Linha;
import geral.Util;
import componetes.Memoria;

public class DataArray extends Data
{
	int [] words;
	
	public DataArray(Linha linha, Integer enderecoToLoad) 
	{
		super();
		
		super.linha = linha;
		
		super.enderecoToLoad = enderecoToLoad;
		
		String[] temp = linha.getPrincipal().split(",");
		
		words = new int[temp.length];
		
		for(int i = 0 ; i < temp.length ; i++)
		{
			words[i] = (char) Integer.parseInt(temp[i]);
		}
		
		super.tamanho = temp.length * 4;
	}

	@Override
	public void carregarEm(Memoria memoria) 
	{
		int offset = 0;
		
		for (int i = 0; i < words.length; i++) 
		{
			try 
			{
				memoria.setWord(enderecoToLoad + offset, Util.toStringBinaria(words[i]));
				offset+=4;
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
