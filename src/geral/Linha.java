package geral;

public class Linha 
{
	final String linha;
	String label = null;
	String diretriz = null;
	String principal = null;
	String comentario = null;
	
	public Linha(String linha)
	{
		linha = new String(linha);
		this.linha = new String(linha).replaceAll("\\t", "      ").trim();
		
		linha = linha.replaceAll("\\t", "").trim();
		
//		-----------------------------------------------------------------------------
		
		int i = linha.indexOf(":");
		
		if (i>=0)
		{
			label = new String(linha.substring(0, i)).trim();
			linha = linha.substring(i+1, linha.length()).trim();
		}
//		-----------------------------------------------------------------------------

		i = linha.indexOf("#");
		
		if (i>=0)
		{
			comentario = new String(linha.substring(i+1, linha.length())).trim();
			linha = linha.substring(0, i).trim();
		}	
		
//		-----------------------------------------------------------------------------
		
		if(linha.equals(".data"))
		{
			diretriz = ".data";
		}
		else if(linha.equals(".text"))
		{
			diretriz = ".text";
		}
		else if(linha.split(Constantes.quebraEspacoVazioRegex)[0].equals(".asciiz"))
		{
			diretriz = ".asciiz";
			principal = linha.split(Constantes.quebraEspacoVazioRegex)[1];
		}
		else
		{
			principal = linha;
		}
	}

	public String getLinha() {
		return linha;
	}

	public String getDiretriz() {
		return diretriz;
	}

	public void setDiretriz(String diretriz) {
		this.diretriz = diretriz;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
