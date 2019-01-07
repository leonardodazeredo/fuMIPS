package geral;

import instrucoes.InterfaceInstrucao;
import instrucoes.tipoI.InstrucaoAddi;
import instrucoes.tipoI.InstrucaoAndi;
import instrucoes.tipoI.InstrucaoBeq;
import instrucoes.tipoI.InstrucaoBne;
import instrucoes.tipoI.InstrucaoLb;
import instrucoes.tipoI.InstrucaoLui;
import instrucoes.tipoI.InstrucaoLw;
import instrucoes.tipoI.InstrucaoOri;
import instrucoes.tipoI.InstrucaoSb;
import instrucoes.tipoI.InstrucaoSlti;
import instrucoes.tipoI.InstrucaoSw;
import instrucoes.tipoJ.InstrucaoJ;
import instrucoes.tipoJ.InstrucaoJal;
import instrucoes.tipoR.InstrucaoAdd;
import instrucoes.tipoR.InstrucaoAnd;
import instrucoes.tipoR.InstrucaoJr;
import instrucoes.tipoR.InstrucaoNor;
import instrucoes.tipoR.InstrucaoOr;
import instrucoes.tipoR.InstrucaoSll;
import instrucoes.tipoR.InstrucaoSlt;
import instrucoes.tipoR.InstrucaoSrl;
import instrucoes.tipoR.InstrucaoSub;

import java.util.HashMap;

import pseudoInstrucoes.PseudoInstrucaoLa;

import componetes.InterfaceMontadorInstrucao;
import componetes.Montador;

public class Identificador 
{
	public static InterfaceInstrucao identificaInstrucaoAssembly(String instrucao, InterfaceMontadorInstrucao montador) throws Exception
	{
		String instrucaoID = instrucao.trim().split(" ")[0];
		
		if(instrucaoID.equals("add"))
		{
			return new InstrucaoAdd(instrucao, montador);
		}
		else if(instrucaoID.equals("and"))
		{
			return new InstrucaoAnd(instrucao, montador);
		}
		else if(instrucaoID.equals("jr"))
		{
			return new InstrucaoJr(instrucao, montador);
		}
		else if(instrucaoID.equals("nor"))
		{
			return new InstrucaoNor(instrucao, montador);
		}
		else if(instrucaoID.equals("or"))
		{
			return new InstrucaoOr(instrucao, montador);
		}
		else if(instrucaoID.equals("sll"))
		{
			return new InstrucaoSll(instrucao, montador);
		}
		else if(instrucaoID.equals("slt"))
		{
			return new InstrucaoSlt(instrucao, montador);
		}
		else if(instrucaoID.equals("srl"))
		{
			return new InstrucaoSrl(instrucao, montador);
		}
		else if(instrucaoID.equals("sub"))
		{
			return new InstrucaoSub(instrucao, montador);
		}
		else if(instrucaoID.equals("addi"))
		{
			return new InstrucaoAddi(instrucao, montador);
		}
		else if(instrucaoID.equals("andi"))
		{
			return new InstrucaoAndi(instrucao, montador);
		}
		else if(instrucaoID.equals("beq"))
		{
			return new InstrucaoBeq(instrucao, montador);
		}
		else if(instrucaoID.equals("bne"))
		{
			return new InstrucaoBne(instrucao, montador);
		}
		else if(instrucaoID.equals("lui"))
		{
			return new InstrucaoLui(instrucao, montador);
		}
		else if(instrucaoID.equals("lw"))
		{
			return new InstrucaoLw(instrucao, montador);
		}
		else if(instrucaoID.equals("lb"))
		{
			return new InstrucaoLb(instrucao, montador);
		}
		else if(instrucaoID.equals("ori"))
		{
			return new InstrucaoOri(instrucao, montador);
		}
		else if(instrucaoID.equals("slti"))
		{
			return new InstrucaoSlti(instrucao, montador);
		}
		else if(instrucaoID.equals("sw"))
		{
			return new InstrucaoSw(instrucao, montador);
		}
		else if(instrucaoID.equals("sb"))
		{
			return new InstrucaoSb(instrucao, montador);
		}
		else if(instrucaoID.equals("j"))
		{
			return new InstrucaoJ(instrucao, montador);
		}
		else if(instrucaoID.equals("jal"))
		{
			return new InstrucaoJal(instrucao, montador);
		}
		else if(instrucaoID.equals("la"))
		{
			return new PseudoInstrucaoLa(instrucao, montador);
		}
		
		throw new Exception("identificador.identificaInstrucaoAssembly.nenhuma.instrucao.compativel");
	}
	
	public static InterfaceInstrucao identificaInstrucaoBinario(String instrucao) throws Exception
	{
		String op = Util.getOp(instrucao);
		
		if(op.equals(Constantes.opR))
		{
			String funct = Util.getFunct(instrucao);
			
			if(funct.equals(Constantes.functAdd))
			{
				return new InstrucaoAdd(instrucao,null);
			}
			else if(funct.equals(Constantes.functAnd))
			{
				return new InstrucaoAnd(instrucao,null);
			}
			else if(funct.equals(Constantes.functJr))
			{
				return new InstrucaoJr(instrucao,null);
			}
			else if(funct.equals(Constantes.functNor))
			{
				return new InstrucaoNor(instrucao,null);
			}
			else if(funct.equals(Constantes.functOr))
			{
				return new InstrucaoOr(instrucao,null);
			}
			else if(funct.equals(Constantes.functSll) && !Util.getShamt(instrucao).equals("00000"))
			{
				return new InstrucaoSll(instrucao,null);
			}
			else if(funct.equals(Constantes.functSlt))
			{
				return new InstrucaoSlt(instrucao,null);
			}
			else if(funct.equals(Constantes.functSrl) && !Util.getShamt(instrucao).equals("00000"))
			{
				return new InstrucaoSrl(instrucao,null);
			}
			else if(funct.equals(Constantes.functSub))
			{
				return new InstrucaoSub(instrucao,null);
			}
		}
		else if(op.equals(Constantes.opAddi))
		{
			return new InstrucaoAddi(instrucao,null);
		}
		else if(op.equals(Constantes.opAndi))
		{
			return new InstrucaoAndi(instrucao,null);
		}
		else if(op.equals(Constantes.opBeq))
		{
			return new InstrucaoBeq(instrucao,null);
		}
		else if(op.equals(Constantes.opBne))
		{
			return new InstrucaoBne(instrucao,null);
		}
		else if(op.equals(Constantes.opLui))
		{
			return new InstrucaoLui(instrucao,null);
		}
		else if(op.equals(Constantes.opLw))
		{
			return new InstrucaoLw(instrucao,null);
		}
		else if(op.equals(Constantes.opLb))
		{
			return new InstrucaoLb(instrucao,null);
		}
		else if(op.equals(Constantes.opOri))
		{
			return new InstrucaoOri(instrucao,null);
		}
		else if(op.equals(Constantes.opSlti))
		{
			return new InstrucaoSlti(instrucao,null);
		}
		else if(op.equals(Constantes.opSw))
		{
			return new InstrucaoSw(instrucao,null);
		}
		else if(op.equals(Constantes.opSb))
		{
			return new InstrucaoSb(instrucao,null);
		}
		else if(op.equals(Constantes.opJump))
		{
			return new InstrucaoJ(instrucao,null);
		}
		else if(op.equals(Constantes.opJal))
		{
			return new InstrucaoJal(instrucao,null);
		}
		
		throw new Exception("identificador.identificaInstrucaoBinario.nenhuma.instrucao.compativel");
	}
}
