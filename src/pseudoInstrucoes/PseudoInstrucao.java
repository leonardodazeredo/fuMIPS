package pseudoInstrucoes;

import java.util.HashMap;

public abstract class PseudoInstrucao 
{
	protected Integer numeroDeInstrucoes;
	
	public Integer getNumeroDeInstrucoes() {
		return numeroDeInstrucoes;
	}

	protected abstract void preencherCamposAssembly(String instrucao, HashMap tabelaDadosLabels);
}
