package instrucoes;

import componetes.Memoria;
import componetes.Montador;
import componetes.UCP;

public interface InterfaceInstrucao 
{
	public void montarEm(Montador montador);
	
	public void carregarEm(Memoria memoria, int endereco, int enderecoDeInicio) throws Exception;
	
	public void executaEm(UCP ucp) throws Exception;
}
