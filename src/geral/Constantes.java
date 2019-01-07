package geral;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constantes 
{
	public final static String zero = "$zero";
	public final static String at = "$at";
	public final static String v0 = "$v0";
	public final static String v1 = "$v1";
	public final static String a0 = "$a0";
	public final static String a1 = "$a1";
	public final static String a2 = "$a2";
	public final static String a3 = "$a3";
	public final static String t0 = "$t0";
	public final static String t1 = "$t1";
	public final static String t2 = "$t2";
	public final static String t3 = "$t3";
	public final static String t4 = "$t4";
	public final static String t5 = "$t5";
	public final static String t6 = "$t6";
	public final static String t7 = "$t7";
	public final static String s0 = "$s0";
	public final static String s1 = "$s1";
	public final static String s2 = "$s2";
	public final static String s3 = "$s3";
	public final static String s4 = "$s4";
	public final static String s5 = "$s5";
	public final static String s6 = "$s6";
	public final static String s7 = "$s7";
	public final static String t8 = "$t8";
	public final static String t9 = "$t9";
	public final static String k0 = "$k0";
	public final static String k1 = "$k1";
	public final static String gp = "$gp";
	public final static String sp = "$sp";
	public final static String fp = "$fp";
	public final static String ra = "$ra";  
	public final static String pc = "pc"; 
	
	public final static String opAddi = "001000";
	public final static String opLb = 	"100000";
	public final static String opSb = 	"101000";
	public final static String opJump = "000010";
	public final static String opSlti = "001010";
	public final static String opJal = 	"000011";
	public final static String opLw = 	"100011";
	public final static String opSw = 	"101011";
	public final static String opBeq = 	"000100";
	public final static String opBne = 	"000101";
	public final static String opAndi = "001100";
	public final static String opOri = 	"001101";
	public final static String opLui = 	"001111";
	
	public final static String opR = 	"000000";
	
	public final static String functSll = "000000";
	public final static String functSrl = "000010";
	public final static String functJr =  "001000";
	public final static String functAdd = "100000";
	public final static String functSub = "100010";
	public final static String functAnd = "100100";
	public final static String functOr =  "100101";
	public final static String functNor = "100111";
	public final static String functSlt = "101010";
	
	public final static String halt =  		"11111111111111111111111111111111";
	public final static String zero32bits = "00000000000000000000000000000000";
	
	public final static Integer tamanhoMemoria = 2592;
	public final static Integer inicioDefault = 144;
	
	public final static Integer enderecoDataSegment = 2048;
	
	public final static String quebraEspacoVazioRegex = "\\s+";
	public final static String quebraVirgulaOuEspacoVazioRegex = "\\s*,\\s*|\\s+";
	public final static String quebraLoadStoreRegex = "\\s*,\\s*|\\s*\\(\\s*|\\s*\\)\\s*|\\s+";
	
	public final static String diretrizData = ".data";
	public final static String diretrizText = ".text";
	public final static String diretrizAsciiz = ".asciiz";
	
	public static final Map<String, String> registradoresNomeToNumero = Collections.unmodifiableMap(new HashMap<String, String>()
	{  
		{  
			put(zero, Util.formata5bits(0));
			put(at, Util.formata5bits(1));
			put(v0, Util.formata5bits(2));
			put(v1, Util.formata5bits(3));
			put(a0, Util.formata5bits(4));
			put(a1, Util.formata5bits(5));
			put(a2, Util.formata5bits(6));
			put(a3, Util.formata5bits(7));
			put(t0, Util.formata5bits(8));
			put(t1, Util.formata5bits(9));
			put(t2, Util.formata5bits(10));
			put(t3, Util.formata5bits(11));
			put(t4, Util.formata5bits(12));
			put(t5, Util.formata5bits(13));
			put(t6, Util.formata5bits(14));
			put(t7, Util.formata5bits(15));
			put(s0, Util.formata5bits(16));
			put(s1, Util.formata5bits(17));
			put(s2, Util.formata5bits(18));
			put(s3, Util.formata5bits(19));
			put(s4, Util.formata5bits(20));
			put(s5, Util.formata5bits(21));
			put(s6, Util.formata5bits(22));
			put(s7, Util.formata5bits(23));
			put(t8, Util.formata5bits(24));
			put(t9, Util.formata5bits(25));
			put(k0, Util.formata5bits(26));
			put(k1, Util.formata5bits(27));
			put(gp, Util.formata5bits(28));
			put(sp, Util.formata5bits(29));
			put(fp, Util.formata5bits(30));
			put(ra, Util.formata5bits(31));  
		}  
	});  
			 
	public static final Map<String, String> registradoresNumeroToNome = Collections.unmodifiableMap(new HashMap<String, String>()
	{  
		{  
			put(Util.formata5bits(0),zero);
			put(Util.formata5bits(1), at);
			put(Util.formata5bits(2), v0);
			put(Util.formata5bits(3), v1);
			put(Util.formata5bits(4), a0);
			put(Util.formata5bits(5), a1);
			put(Util.formata5bits(6), a2);
			put(Util.formata5bits(7), a3);
			put(Util.formata5bits(8), t0);
			put(Util.formata5bits(9), t1);
			put(Util.formata5bits(10), t2);
			put(Util.formata5bits(11), t3);
			put(Util.formata5bits(12), t4);
			put(Util.formata5bits(13), t5);
			put(Util.formata5bits(14), t6);
			put(Util.formata5bits(15), t7);
			put(Util.formata5bits(16), s0);
			put(Util.formata5bits(17), s1);
			put(Util.formata5bits(18), s2);
			put(Util.formata5bits(19), s3);
			put(Util.formata5bits(20), s4);
			put(Util.formata5bits(21), s5);
			put(Util.formata5bits(22), s6);
			put(Util.formata5bits(23), s7);
			put(Util.formata5bits(24), t8);
			put(Util.formata5bits(25), t9);
			put(Util.formata5bits(26), k0);
			put(Util.formata5bits(27), k1);
			put(Util.formata5bits(28), gp);
			put(Util.formata5bits(29), sp);
			put(Util.formata5bits(30), fp);
			put(Util.formata5bits(31), ra);  
		}  
	}); 
}
