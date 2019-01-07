package componetes;

import instrucoes.InterfaceInstrucao;
import geral.Constantes;
import geral.Identificador;
import geral.Util;
import principal.Simulador;


public class UCP 
{
	private Memoria memoria;
	
	private RegistradoresMap registradores;
	private final Registrador pc = new Registrador("pc","");

	public UCP(Simulador simulador, Memoria memoria, RegistradoresMap registradores) throws Exception 
	{
		this.memoria = memoria;
		this.registradores = registradores;
		
		pc.setConteudo(Constantes.zero32bits);
		
		getRegistrador(Constantes.sp).setConteudo(Util.toStringBinaria(Constantes.tamanhoMemoria));
	}
	
	public void setRegistradores(RegistradoresMap registradores) 
	{
		this.registradores = registradores;
	}
	
	public void setMemoria(Memoria memoria) 
	{
		this.memoria = memoria;
	}
	
	public Registrador getRegistrador(String identificador)
	{
		if (Util.isAssembly(identificador)) 
		{			
			return (Registrador) registradores.get(Constantes.registradoresNomeToNumero.get(identificador));
		}
		else
		{
			return (Registrador) registradores.get(identificador);
		}
	}
	
	public Registrador getRegistrador(Integer identificador)
	{
		return (Registrador) registradores.get(Util.formata5bits(Util.toStringBinaria(identificador)));
	}
	
//	EXECUTAR ----------------------------------------------------------------------------------------------
	
	public void executar() throws Exception
	{
		while(!isPcOnHalt()) 
		{
			executarStep();	
		}
		
		System.out.println("HALT atingido no endereco: " + Util.getInteiroComSinal(getConteudoPC()));
	}
	
	public void executarStep() throws Exception
	{
		System.out.println("Instrucao lida: " + getWordEmPC() + " Endereco: " + Util.getInteiroComSinal(getConteudoPC()));
		
		if(!isPcOnHalt()) 
		{
			incrementaPC();
			executar(Util.getInteiroComSinal(pc.getConteudo()) - 4);
		}
	}
	
	private void executar(Integer enderedoInstrucao) throws Exception
	{
		String instrucao = memoria.getWord(enderedoInstrucao);
		
		InterfaceInstrucao instrucaoInterface = Identificador.identificaInstrucaoBinario(instrucao);
		
		instrucaoInterface.executaEm(this);
	}
	
//	MEMORIA -----------------------------------------------------------------------------------------------
	
	public void setWordMemoria(int endereco, String bytes) throws Exception
	{
		memoria.setWord(endereco, bytes);
	}
	
	public String getWordMemoria(int endereco) throws Exception
	{
		return memoria.getWord(endereco);
	}
	
	public void setByteMemoria(int endereco, String bytes) throws Exception
	{
		memoria.setByte(endereco, bytes);
	}
	
	public String getByteMemoria(int endereco) throws Exception
	{
		return memoria.getByte(endereco);
	}
	
//	PC -------------------------------------------------------------------------------------------
	
	public void setConteudoPC(String pcConteudo) throws Exception
	{
		pc.setConteudo(new String(pcConteudo));
	}
	
	public String getConteudoPC() throws Exception
	{
		return new String(pc.getConteudo());
	}
	
	public void iniciaPC(Integer inicio) throws Exception
	{
		pc.setConteudo(Util.toStringBinaria(inicio));
	}
	
	private void incrementaPC() throws Exception
	{
		pc.setConteudo(ULA.soma(pc.getConteudo(), 4));
	}
	
	public Boolean isPcOnHalt() throws Exception
	{
		return getWordEmPC().equals(Constantes.halt);
	}
	
	private String getWordEmPC() throws Exception
	{
		return memoria.getWord(Util.getInteiroComSinal(pc.getConteudo()));
	}
}
