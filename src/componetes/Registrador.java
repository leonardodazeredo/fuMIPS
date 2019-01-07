package componetes;

import geral.Util;

public class Registrador 
{
	final String nome;
	final String numero;
	String conteudo;
	
	public String getConteudo() 
	{
		return conteudo;
	}

	public void setConteudo(String conteudo) throws Exception 
	{
		if (conteudo.length() != 32) 
		{
			throw new Exception("resgistrador.tamanho.do.conteudo.diferente.de.32");
		}
		
		this.conteudo = new String(Util.formata32bits(conteudo));
	}

	public Registrador(String nome, String numero) 
	{
		this.nome = new String(nome);
		this.numero = new String(numero);
	}
	
	public String getNome() 
	{
		return nome;
	}

	public String getNumero() 
	{
		return numero;
	}
}
