package componetes;

import geral.Util;

public class ULA 
{
	
//	SOMA --------------------------------------------------------------------
	public static String soma(int a, String b) throws Exception
	{	
		return soma(Util.toStringBinaria(a),b);		
	}
	
	public static String soma(String a, int b) throws Exception
	{
		return soma(a,Util.toStringBinaria(b));	
	}
	
	public static String soma(String a, String b) throws Exception
	{
		Integer A = Util.getInteiroComSinal(a);
		
		Integer B = Util.getInteiroComSinal(b);
		
		return Util.formata32bits(Util.toStringBinaria(A+B));	
	}
	
//	SUBTRACAO ------------------------------------------------------------------
	
	public static String subtrai(int a, String b) throws Exception
	{
		return subtrai(Util.toStringBinaria(a),b);		
	}
	
	public static String subtrai(String a, int b) throws Exception
	{	
		return subtrai(a,Util.toStringBinaria(b));	
	}
	
	public static String subtrai(String a, String b) throws Exception
	{		
		Integer A = Util.getInteiroComSinal(a);
		
		Integer B = Util.getInteiroComSinal(b);
		
		return Util.formata32bits(Util.toStringBinaria(A-B));	
	}
	
//	SHIFT LEFT -----------------------------------------------------------------

	private static String shiftL(String a)
	{
//		return a.substring(1, a.length()) + "0";
		return a + "0";
	}
	
	public static String shiftL(String a, int shamt)
	{
		String shiftado = new String(a);
		
		for (int i = 0; i < shamt; i++) 
		{
			shiftado = shiftL(shiftado);
		}	
		
		return shiftado;	
	}
	
//	SHIFT RIGHT -------------------------------------------------------------
	
	private static String shiftR(String a)
	{
		return "0" + a.substring(0, a.length()-1);	
	}
	
	public static String shiftR(String a, int shamt)
	{
		String shiftado = new String(a);
		
		for (int i = 0; i < shamt; i++) 
		{
			shiftado = shiftR(shiftado);
		}	
		
		return shiftado;
	}
	
//	LOGICOS --------------------------------------------------------------------
	
	public static String and(String a, String b)
	{
		String A = Util.formata32bits(a);
		String B = Util.formata32bits(b);
		
		String and = new String();
		
		for(int i = 0; i < 32; i++)
			if (A.charAt(i) == '1' && B.charAt(i) == '1') 
			{
				and += "1";
			}
			else
			{
				and += "0";
			}
		
		return and;
	}
	
	public static String or(String a, String b)
	{
		String A = Util.formata32bits(a);
		String B = Util.formata32bits(b);
		
		String or = new String();
		
		for(int i = 0; i < 32; i++)
			if (A.charAt(i) == '1' || B.charAt(i) == '1') 
			{
				or += "1";
			}
			else
			{
				or += "0";
			}
		
		return or;
	}
	
	public static String not(String a)
	{
		String A = Util.formata32bits(a);
		
		String not = new String();
		
		for(int i = 0; i < 32; i++)
			if (A.charAt(i) == '1') 
			{
				not += "0";
			}
			else
			{
				not += "1";
			}
		
		return not;
	}
}

