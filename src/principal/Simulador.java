package principal;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import componetes.*;
import geral.*;

import view.*;

public class Simulador 
{
	public Stack<HistoricoBackStep> historicoPilha;
	
	public Montador montador;
	public Memoria memoria;
	public UCP ucp;
	public Loader loader;
	public ULA ula;
	public RegistradoresMap registradores;
	
	private Interface interfaceSimulador = null;
	
	private Integer inicio = Constantes.inicioDefault;
	
	public boolean modoAscii = false;
	
	public Simulador() throws Exception 
	{
		historicoPilha = new Stack<HistoricoBackStep>();
		
		registradores = new RegistradoresMap();
		montador = new Montador(this);
		memoria = new Memoria();
		ucp = new UCP(this, memoria, registradores);
		loader =  new Loader(this);
		ula = new ULA();
	}
	
	public void setInterfaceSimulador(Interface interfaceSimulador) 
	{
		if (this.interfaceSimulador == null) 
		{
			this.interfaceSimulador = interfaceSimulador;
		}
	}
	
	public void montar(File arquivo) throws Exception
	{
		montador.montar(arquivo);
		
		System.out.println("simulador.montar");
	}
	
	public void carregar(Integer inicio) throws Exception
	{
		this.inicio = inicio;
		
		if (!montador.linguagemDeMaquina.isEmpty()) 
		{
			loader.carregarEmMemoria(montador.linguagemDeMaquina, montador.dados, memoria,inicio);
		}
		
		ucp.iniciaPC(inicio);
		
		atualizaTabelaRegistradores();
		
		ArrayList<DadoTextoVO> dados = new ArrayList<DadoTextoVO>();
		
		DadoTextoVO dadoTextoVO = new DadoTextoVO();
		
		Integer enderecoAtual = new Integer(inicio);
		
		int i = 0;
		
		ArrayList<Linha> fontesText = montador.fonte.extrairFontesText();
		
		for (String instrucaoBinaria: montador.linguagemDeMaquina) 
		{
			dadoTextoVO = new DadoTextoVO();
			
			dadoTextoVO.setEndereco(enderecoAtual.toString());
			dadoTextoVO.setCodigo(instrucaoBinaria);
			
			dadoTextoVO.setFonte(fontesText.get(i++).getLinha());

			enderecoAtual+=4;
			dados.add(dadoTextoVO);
		}
		
		interfaceSimulador.tableTextSegment.setModel(new TableModelTexto(dados));
		
		interfaceSimulador.tableTextSegment.getColumnModel().getColumn(0).setResizable(true);
		interfaceSimulador.tableTextSegment.getColumnModel().getColumn(1).setResizable(true);
		interfaceSimulador.tableTextSegment.getColumnModel().getColumn(2).setResizable(true);
		
		interfaceSimulador.tableTextSegment.getTableHeader().setResizingAllowed(true);
		
		interfaceSimulador.tableTextSegment.getColumnModel().getColumn(0).setPreferredWidth(85);
		interfaceSimulador.tableTextSegment.getColumnModel().getColumn(1).setPreferredWidth(250);
		interfaceSimulador.tableTextSegment.getColumnModel().getColumn(2).setPreferredWidth(300);
		
		atualizaTabelaDados();
		
		highLightTextOnPc();
	}

	public void executar() throws Exception
	{
		ucp.executar();
		atualizaTabelaRegistradores();
		
		atualizaTabelaDados();
		
		adicionarMensagem("Halt atingido no endereco: " + Util.getInteiroComSinal(ucp.getConteudoPC()));
		
		//memoria.teste_imprimeMemoria();
		
		highLightTextOnPc();
	}
	
	public void reset() throws Exception
	{
		registradores = new RegistradoresMap();
		montador = new Montador(this);
		memoria = new Memoria();
		ucp = new UCP(this, memoria, registradores);
		loader =  new Loader(this);
		ula = new ULA();
		
		carregar(inicio);
		
		atualizaTabelaRegistradores();
		
		adicionarMensagem("-------------------------------------------------------------------------------");
		adicionarMensagem("");
		adicionarMensagem("Memoria e registradores reiniciados.");
		
		historicoPilha = new Stack<HistoricoBackStep>();
	}
	
	public Integer getInicio() 
	{
		return new Integer(inicio);
	}

	private void atualizaTabelaRegistradores() throws Exception
	{
		ArrayList<Integer> registradoresAlterados = ((TableModelRegistradores)(interfaceSimulador.tableRegistradores.getModel())).atualizarDados(registradores.values(),ucp.getConteudoPC());
	
		interfaceSimulador.tableRegistradores.setDefaultRenderer(String.class, new TableRegistradoresRenderer(registradoresAlterados));
		interfaceSimulador.tableRegistradores.repaint();
	}
	
	public void atualizaTabelaDados()
	{
		((TableModelData)(interfaceSimulador.tableDataSegment.getModel())).atualizarDados(memoria,modoAscii);
	}
	
	private void highLightTextOnPc()
	{
		try 
		{
			interfaceSimulador.tableTextSegment.setDefaultRenderer(String.class, new TableTextoRenderer(Util.getInteiroComSinal(ucp.getConteudoPC())));
			interfaceSimulador.tableTextSegment.repaint();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void adicionarMensagem(String mensagem)
	{
		interfaceSimulador.messageArea.append(mensagem);
		interfaceSimulador.messageArea.append(System.getProperty("line.separator"));
	}
	
	public void step() throws Exception
	{
		HistoricoBackStep historicoBackStep = new HistoricoBackStep(new RegistradoresMap(registradores), new Memoria(memoria), new String(ucp.getConteudoPC()));
		historicoPilha.push(historicoBackStep);
		
		ucp.executarStep();
		
		atualizaTabelaRegistradores();

		atualizaTabelaDados();
		
		if (ucp.isPcOnHalt()) 
		{
			adicionarMensagem("Halt atingido no endereco: " + Util.getInteiroComSinal(ucp.getConteudoPC()));
		}
		
		highLightTextOnPc();
	}
	
	public void backStep() throws Exception
	{
		HistoricoBackStep historicoBackStep = historicoPilha.pop();
		
		registradores =	historicoBackStep.getRegistradores();
		
		memoria = historicoBackStep.getMemoria();
		
		ucp.setConteudoPC(historicoBackStep.getConteudoPc());
		ucp.setMemoria(memoria);
		ucp.setRegistradores(registradores);

		atualizaTabelaRegistradores();
		atualizaTabelaDados();
		highLightTextOnPc();
	}
}
