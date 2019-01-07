package componetes;

import geral.Constantes;

import java.util.HashMap;


public class Memoria {

	private HashMap<Integer,String> memoria = new HashMap<Integer,String>();
	
	private int tamanho;
	
	public Memoria() 
	{
		Integer endereco;
		String memoria;
		
		this.tamanho = Constantes.tamanhoMemoria;
		
		for (int i = 0; i < tamanho; i++) 
		{
			endereco = new Integer(i);
			memoria = new String("00000000");
			
			this.memoria.put(endereco, memoria);
		}
	}
	
	public Memoria(Memoria anterior) throws Exception
	{
		Integer endereco;
		String memoria;
		
		this.tamanho = anterior.tamanho;
		
		for (int i = 0; i < tamanho; i++) 
		{
			endereco = new Integer(i);
			memoria = new String(anterior.getByte(i));
			
			this.memoria.put(endereco, memoria);
		}
	}
	
	public int getTamanho() 
	{
		return tamanho;
	}

	public String getWord(int endereco) throws Exception
	{
		if (endereco%4!=0) 
		{
			throw new Exception("memoria.getWord.endereco.fora.de.alinhamento");
		}
		
		String word = new String("");
		
		for (int i = 0; i < 4; i++) 
		{
			word += getByte(endereco + i);
		}
		
		return word;
	}
	
	public String getByte(int endereco)
	{
		return memoria.get(new Integer(endereco));
	}
	
	public void setWord(int endereco, String bytes) throws Exception
	{
		if (!(bytes.length() == 32)) 
		{
			throw new Exception("memoria.setWord.numero.de.bits.diferente.de.trinta.e.dois");
		}
		
		for (int i = 0, j = 0; i < 4; i++,j += 8) 
		{
			setByte(endereco + i, bytes.substring(j, j+8));
		}
	}
	
	public void setByte(int endereco, String bytes) throws Exception
	{
		if (!(bytes.length() == 8)) 
		{
			throw new Exception("memoria.setByte.numero.de.bits.diferente.de.oito");
		}
		memoria.put(endereco,bytes);
	}
	
	public void teste_imprimeMemoria() throws Exception
	{
		int endereco = 0;
		
		while(endereco < this.tamanho)
		{	
			System.out.println(endereco + " - " + getWord(endereco));
			
			endereco+=4;
		}
	}
}
