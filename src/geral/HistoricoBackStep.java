package geral;

import componetes.Memoria;
import componetes.RegistradoresMap;

public class HistoricoBackStep 
{
	RegistradoresMap registradores;
	
	Memoria memoria;
	
	String conteudoPc;

	public HistoricoBackStep(RegistradoresMap registradores, Memoria memoria, String conteudoPc) 
	{
		this.registradores = registradores;
		this.memoria = memoria;
		
		this.conteudoPc = new String(conteudoPc);
	}

	public RegistradoresMap getRegistradores() {
		return registradores;
	}

	public Memoria getMemoria() {
		return memoria;
	}

	public String getConteudoPc() {
		return conteudoPc;
	}
}
