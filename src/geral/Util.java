package geral;

import componetes.Registrador;

public class Util 
{	
	public static String formataAscii(String word) throws Exception
	{
		String wordEmAscii = new String();
		
		wordEmAscii += getInteiroComSinal(word.substring(0, 8)) != 0 ? (char)(int)getInteiroComSinal(word.substring(0, 8)) : "\\0";
		wordEmAscii += getInteiroComSinal(word.substring(8, 16)) != 0 ? (char)(int)getInteiroComSinal(word.substring(8, 16)) : "\\0";
		wordEmAscii += getInteiroComSinal(word.substring(16, 24)) != 0 ? (char)(int)getInteiroComSinal(word.substring(16, 24)) : "\\0";
		wordEmAscii += getInteiroComSinal(word.substring(24, 32)) != 0 ? (char)(int)getInteiroComSinal(word.substring(24, 32)) : "\\0";
		
		return wordEmAscii;
	}
	
//	VERIFICA SE INSTRUCAO NA STRING ESTA NA FORMA DE LINGUAGEM ASSEMBLY OU NA FORMA BINARIA --------------------
	
	public static Boolean isAssembly(String instrucao)
	{
		return !(instrucao.startsWith("0") || instrucao.startsWith("1"));
	}
	
//	COMPARA OS VALORES DOS NUMEROS EM DUAS STRINGS BINARIAS ----------------------------------------------------
	
	public static int comparaBinario(String a, String b) throws Exception
	{
		if (Util.getInteiroComSinal(a) > Util.getInteiroComSinal(b)) 
		{
			return 1;
		}
		else if (Util.getInteiroComSinal(a) < Util.getInteiroComSinal(b)) 
		{
			return -1;
		}
		
		return 0;
	}
	
//	INSTANCIA REGISTRADORES -------------------------------------------------------------------------------------
	
	public static Registrador retornaReg(String identificador)
	{
		if (isAssembly(identificador)) 
		{			
			return new Registrador(identificador,Constantes.registradoresNomeToNumero.get(identificador));
		}
		else
		{
			return new Registrador(Constantes.registradoresNumeroToNome.get(identificador),identificador);
		}
	}
	
	public static Registrador retornaReg(Integer identificador)
	{
		return new Registrador(Constantes.registradoresNumeroToNome.get(formata5bits(toStringBinaria(identificador))),formata5bits(toStringBinaria(identificador)));
	}
	
//	CONVERTE NUMERO ENTRE BINARIO E INTEGER -------------------------------------------------------------------------
	
	public static String toStringBinaria(Integer numero)
	{
		if (numero < 0) 
		{
			return Integer.toBinaryString(numero);
		}
		else
		{
			return formata32bits(Integer.toBinaryString(numero));
		}
	}
	
	public static Integer getInteiroComSinal(String stringBinaria) throws Exception
	{
		if (stringBinaria.length() <= 32) 
		{
			if (stringBinaria.startsWith("1")) 
			{			
				String binarioComplementado = new String("");
				
				for (int i = 0; i < stringBinaria.length(); i++) 
				{
					if (stringBinaria.charAt(i)=='0') 
					{
						binarioComplementado += "1";
					}
					else if (stringBinaria.charAt(i)=='1') 
					{
						binarioComplementado += "0";
					}
				}
				
				Integer inteiro = Integer.parseInt(binarioComplementado.substring(1, binarioComplementado.length()),2);
				
				inteiro++;
				
				return inteiro * -1;
			}
			else if (stringBinaria.startsWith("0")) 
			{
				if (stringBinaria.length() == 32) 
				{
					return Integer.parseInt(stringBinaria.substring(1, stringBinaria.length()),2);
				} 
				else 
				{
					return Integer.parseInt(stringBinaria,2);
				}
			}
			else
			{
				throw new Exception("util.getInteiroComSinal.inicio.diferente.de.0.ou.1");
			}
		}
		else
		{
			throw new Exception("util.getInteiroComSinal.maior.que.32");
		}
	}
	
//	FORMATA NUMEROS ----------------------------------------------------------------------------------------
	
	public static String formataChar(char caractere)
	{
		String string = toStringBinaria((int) caractere);
		
		String adFormatado = "";
		
		if (string.length() > 8) 
		{
			adFormatado = string.substring(string.length() - 8, string.length());
		} 
		else if (string.length() < 8)
		{
			for(int i = 0; i < (8 - string.length()); i++)
				adFormatado+= "0";
			adFormatado += string;
		}
		else
		{
			adFormatado = new String(string);
		}
	
		return adFormatado;
	}
	
	
	public static String formata5bits(Integer numero)
	{
		return formata5bits(toStringBinaria(numero));
	}
	
	public static String formata5bits(String string)
	{
		String adFormatado = "";
		
		if (string.length() > 5) 
		{
			adFormatado = string.substring(string.length() - 5, string.length());
		} 
		else if (string.length() < 5)
		{
			for(int i = 0; i < (5 - string.length()); i++)
				adFormatado+= "0";
			adFormatado += string;
		}
		else
		{
			adFormatado = new String(string);
		}
	
		return adFormatado;
	}
	
	public static String formata16bits(String ad)
	{
		String adFormatado = "";
		
		if (ad.length() > 16) 
		{
			adFormatado = ad.substring(ad.length() - 16, ad.length());
		} 
		else if (ad.length() < 16)
		{
			for(int i = 0; i < (16 - ad.length()); i++)
				adFormatado+= "0";
			adFormatado += ad;
		}
		else
		{
			adFormatado = new String(ad);
		}
	
		return adFormatado;
	}
	
	public static String formata26bits(String ad)
	{
		String adFormatado = "";
		
		if (ad.length() > 26) 
		{
			adFormatado = ad.substring(ad.length() - 26, ad.length());
		} 
		else if (ad.length() < 26)
		{
			for(int i = 0; i < (26 - ad.length()); i++)
				adFormatado += "0";
			
			adFormatado += ad;
		}
		else
		{
			adFormatado = new String(ad);
		}
	
		return adFormatado;
	}

	public static String formata32bits(String string)
	{
		String adFormatado = "";
		
		if (string.length() > 32) 
		{
			adFormatado = string.substring(string.length() - 32, string.length());
		} 
		else if (string.length() < 32)
		{
			for(int i = 0; i < (32 - string.length()); i++)
				adFormatado+= "0";
			adFormatado += string;
		}
		else
		{
			adFormatado = new String(string);
		}
	
		return adFormatado;
	}
	
	public static String formata32bitsEstendendoSinal(String string)
	{
		String adFormatado = "";
		
		if (string.length() > 32) 
		{
			adFormatado = string.substring(string.length() - 32, string.length());
		} 
		else if (string.length() < 32)
		{
			String bitDeSinal = new String();
			
			if (string.startsWith("0")) 
			{
				bitDeSinal = "0";
			} 
			else 
			{
				bitDeSinal = "1";
			}
			
			for(int i = 0; i < (32 - string.length()); i++)
				adFormatado+= bitDeSinal;
			adFormatado += string;
		}
		else
		{
			adFormatado = new String(string);
		}
	
		return adFormatado;
	}

//	GET COISAS --------------------------------------------------------------------------------------------
	
	public static String getOp(String instrucao)
	{
		return instrucao.substring(0, 6);
	}
	
	public static String getFunct(String instrucao)
	{
		return instrucao.substring(26, 32);
	}
	
	public static String getAd(String instrucao)
	{
		return instrucao.substring(6, 32);
	}
	
	public static String getIm(String instrucao)
	{
		return instrucao.substring(16, 32);
	}
	
	public static String getRs(String instrucao)
	{
		return instrucao.substring(6, 11);
	}
	
	public static String getRt(String instrucao)
	{
		return instrucao.substring(11, 16);
	}
	
	public static String getRd(String instrucao)
	{
		return instrucao.substring(16, 21);
	}
	
	public static String getShamt(String instrucao)
	{
		return instrucao.substring(21, 26);
	}
}
