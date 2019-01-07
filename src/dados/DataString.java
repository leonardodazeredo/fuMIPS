package dados;

import componetes.Memoria;

import geral.Linha;
import geral.Util;

public class DataString extends Data
{
	char [] caracteres;
	
	public DataString(Linha linha, Integer enderecoToLoad) 
	{
		super();
		
		super.linha = linha;
		
		super.enderecoToLoad = enderecoToLoad;
		
		String string = linha.getPrincipal().substring(1,linha.getPrincipal().length() - 1);
		
		caracteres = new char[string.length()];
		
		for(int i = 0 ; i < string.length() ; i++)
		{
			caracteres[i] = string.charAt(i);
		}
		
		if(caracteres.length%4!=0)
		{
			int temp = (int) caracteres.length/4;
			temp++;
			
			super.tamanho = temp * 4;
		}
		else
		{
			super.tamanho = caracteres.length;
		}
	}

	@Override
	public void carregarEm(Memoria memoria)
	{
		int offset = 0;
		
		for (int i = 0; i < caracteres.length; i++) 
		{
			try 
			{
				memoria.setByte(enderecoToLoad + offset, Util.formataChar(caracteres[i]));
				offset++;
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
