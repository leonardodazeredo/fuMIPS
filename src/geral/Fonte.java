package geral;

import instrucoes.InterfaceInstrucao;

import java.util.ArrayList;

import pseudoInstrucoes.PseudoInstrucao;

public class Fonte 
{
	ArrayList<Linha> linhas = new ArrayList<Linha>();
	
	Integer inicioSegmentoData = null;
	Integer inicioSegmentoText = null;
	
	public void adicionarLinha(Linha linha)
	{
		linhas.add(linha);
		
		if(linha.getDiretriz()!=null)
		{
			if (linha.getDiretriz().equals(Constantes.diretrizData)) 
			{
				inicioSegmentoData = linhas.size() - 1;
			} 
			else if (linha.getDiretriz().equals(Constantes.diretrizText)) 
			{
				inicioSegmentoText = linhas.size() - 1;
			}
		}
	}
	
	public ArrayList<Linha> extrairFontesText()
	{
		ArrayList<Linha> instrucoesComLabel = new ArrayList<Linha>();
		
		int i = 0;
		
		for(Linha linha: linhas)
		{
			if (i++ >= inicioSegmentoText) 
			{
				if (!linha.getLinha().equals("") && linha.getPrincipal()!=null && !linha.getPrincipal().equals("")) 
				{
					instrucoesComLabel.add(linha);
					
					try 
					{
						InterfaceInstrucao instrucaoLida = Identificador.identificaInstrucaoAssembly(linha.getPrincipal(), null);
						
						if (instrucaoLida instanceof PseudoInstrucao) 
						{
							for (int j = 0; j < ((PseudoInstrucao)instrucaoLida).getNumeroDeInstrucoes() - 1; j++) 
							{
								instrucoesComLabel.add(new Linha(""));
							}			
						}
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return instrucoesComLabel;
	}
	
	public ArrayList<Linha> getLinhas() {
		return linhas;
	}

	public Integer getInicioSegmentoData() {
		return inicioSegmentoData;
	}

	public Integer getInicioSegmentoText() {
		return inicioSegmentoText;
	}
}
