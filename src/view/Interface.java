package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import principal.Simulador;
import javax.swing.JToolBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;

import java.awt.Toolkit;
import java.awt.Frame;
import javax.swing.JCheckBox;


public class Interface extends JFrame implements ActionListener 
{
	private JPanel contentPane;
	public JTable tableRegistradores;
	JMenuBar menuBar;
	JMenu mnArquivo;
	JMenuItem mntmNovo, mntmAbrir, mntmFechar, mntmFecharTodos, mntmSalvar, mntmSalvarComo, mntmSalvarTodos, mntmSair;
	JTextArea textArea;
	public JTextArea messageArea;
	JScrollPane paneEditor, pane2, pane3;
	JFileChooser fc = new JFileChooser();
	private JButton btnLimparMessageArea;
	private JTabbedPane tabbedPane;
	
	private JToolBar toolBar;
	private JButton btnStep;
	private JButton btnReset;
	private JButton btnRun;
	private JButton btnBkstep;
	
	Simulador simulador;
	private JButton btnMontarCarregar;
	private JSeparator separator;
	private JSeparator separator2;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	
	private File arquivo = null;
	public JTable tableTextSegment;
	public JTable tableDataSegment;
	private JCheckBox chckbxAscii;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;

	/**
	 * Create the frame.
	 */

	public Interface (Simulador simulador) 
	{
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));
		setTitle("Simulador Mips Fuu");	
		this.simulador = simulador;
		
		fc = new JFileChooser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {  
		    public void windowClosing(WindowEvent evt) {  
		        sair();
		    }  
		});  
		setBounds(100, 100, 900, 681);

		// INICIO MENU ------------------------------------
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		mntmNovo = new JMenuItem("Novo");
		mntmNovo.addActionListener(this);
		mnArquivo.add(mntmNovo);

		mntmAbrir = new JMenuItem("Abrir...");
		mntmAbrir.addActionListener(this);
		mnArquivo.add(mntmAbrir);

		mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(this);
		mnArquivo.add(mntmSalvar);

		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(this);
		mnArquivo.add(mntmSair);

		// INICIO JPANEL ---------------------------------------------
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[22.00][605.00,grow][grow][-237.00][300.00,grow]", "[][][477.00][100.00:100.00:100.00,shrink 0][]"));
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, "flowx,cell 1 0");
		
		btnRun = new JButton("");
		btnRun.setIcon(new ImageIcon("executar.png"));
		btnRun.setToolTipText("Executar");
		//toolBar.add(btnRun);
		btnRun.addActionListener(this);
		
		btnMontarCarregar = new JButton("");
		btnMontarCarregar.addActionListener(this);
		btnMontarCarregar.setIcon(new ImageIcon("montar.png"));
		btnMontarCarregar.setToolTipText("Montar/Carregar");
		toolBar.add(btnMontarCarregar);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut);
		toolBar.add(btnRun);
		
		btnBkstep = new JButton("");
		btnBkstep.addActionListener(this);
		btnBkstep.setIcon(new ImageIcon("backstep.png"));
		btnBkstep.setToolTipText("Backstep");
		toolBar.add(btnBkstep);
		
		btnStep = new JButton("");
		btnStep.addActionListener(this);
		btnStep.setIcon(new ImageIcon("step.png"));
		btnStep.setToolTipText("Step");
		toolBar.add(btnStep);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_3);
		
		separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator2);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_2);
		
		btnReset = new JButton("");
		btnReset.addActionListener(this);
		btnReset.setIcon(new ImageIcon("reset.png"));
		btnReset.setToolTipText("Reset");
		toolBar.add(btnReset);
		
		
		
		// TABBED PANE ------------------------------------------------
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "cell 0 1 4 2,grow");

		// AREA DE TEXTO ---------------------------------------------
		textArea = new JTextArea();
		paneEditor = new JScrollPane(textArea);
		tabbedPane.addTab("Editor", null, paneEditor, null);
		
		textArea.setSize(100, 500);
		
		JScrollPane paneText = new JScrollPane();
		tabbedPane.addTab("Execucao", null, paneText, null);
		
		tableTextSegment = new JTable();
		paneText.setViewportView(tableTextSegment);
		
		JScrollPane paneData = new JScrollPane();
		tabbedPane.addTab("Memoria", null, paneData, null);
		
		tableDataSegment = new JTable(new TableModelData(simulador.memoria));
		paneData.setViewportView(tableDataSegment);
		
		// TABELA DE REGISTRADORES ---------------------------------------------
		try 
		{
			tableRegistradores = new JTable(new TableModelRegistradores(simulador.registradores,simulador.ucp.getConteudoPC()));
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableRegistradores.setDefaultRenderer(String.class, new TableRegistradoresRenderer(null));
		
		TableRegistradoresListener tableRegistradoresListener = new TableRegistradoresListener(simulador.registradores);
		
		tableRegistradores.getColumnModel().getColumn(0).setResizable(false);
		tableRegistradores.getColumnModel().getColumn(1).setResizable(false);
		
		tableRegistradores.getTableHeader().setResizingAllowed(true);
		
		tableRegistradores.getModel().addTableModelListener(tableRegistradoresListener);
		
		pane2 = new JScrollPane(tableRegistradores);
		contentPane.add(pane2, "cell 4 1 1 4,grow");

		// MESSAGE AREA ---------------------------------------------
		messageArea = new JTextArea();
		messageArea.setEditable(false);
		pane3 = new JScrollPane(messageArea);
		contentPane.add(pane3, "cell 1 3 2 2,grow");
		
		btnLimparMessageArea = new JButton("Limpar");
		btnLimparMessageArea.addActionListener(this);
		contentPane.add(btnLimparMessageArea, "cell 0 3 1 2,alignx center");
		
		horizontalStrut_4 = Box.createHorizontalStrut(20);
		contentPane.add(horizontalStrut_4, "cell 1 0");
		
		chckbxAscii = new JCheckBox("ASCII");
		chckbxAscii.addActionListener(this);
		contentPane.add(chckbxAscii, "cell 1 0");
	}

	
	// ACOES -------------------------------------------------------------------
	public void actionPerformed(ActionEvent e)
	{
		//Menu ---------------------------------------------------------------------------------------
		if (e.getSource() == mntmNovo)
		{
			if (textArea.getText().trim().length() != 0)
			{
				int confirmed = JOptionPane.showConfirmDialog(null, "Deseja salvar as alteracoes no documento?", "", JOptionPane.YES_NO_CANCEL_OPTION);
				
				if (confirmed == JOptionPane.YES_OPTION)
				{
					salvar();
					textArea.setText("");
				}
				else if (confirmed == JOptionPane.NO_OPTION)
				{
					textArea.setText("");
					arquivo = null;
				}
			}
		}
		else if (e.getSource() == mntmAbrir) 
		{
			System.out.println("acao.menu.abrir");
			
			abrir();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
		else if (e.getSource() == mntmSalvar) 
		{
			salvar();
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
		else if (e.getSource() == mntmSair)
		{
			sair();
		}
		//Botoes Toolbar
		else if (e.getSource() == btnMontarCarregar)
		{
			if (arquivo == null && textArea.getText().trim().length()==0) 
			{
				abrir();
			} 
			else 
			{
				salvar();
			}
			
			try 
			{
				simulador.montar(arquivo);
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
			
			String inputInicioNaMemoria = JOptionPane.showInputDialog("Onde deseja carregar o segmento de texto?", simulador.getInicio());
			
			try 
			{
				simulador.carregar(Integer.parseInt(inputInicioNaMemoria));
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == btnRun)
		{
			try 
			{
				simulador.executar();
			} catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == btnStep)
		{
			try 
			{
				simulador.step();
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == btnBkstep)
		{
			try 
			{
				simulador.backStep();
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == btnReset)
		{
			try 
			{
				simulador.reset();
			} catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//Botao Limpar Menssagens
		else if (e.getSource() == btnLimparMessageArea)
		{
			messageArea.setText("");
		}
		
		else if (e.getSource() == chckbxAscii)
		{
			simulador.modoAscii = new Boolean(chckbxAscii.isSelected());
			
			simulador.atualizaTabelaDados();
		}
	}
	
	public void abrir()
	{
		int returnVal = fc.showOpenDialog(Interface.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			File file = fc.getSelectedFile();
			
			try 
			{ 
				FileReader reader = new FileReader(file); 
				
				BufferedReader input = new BufferedReader(reader); 
				
				String linha; 
				
				textArea.setText("");
				
				while ((linha = input.readLine()) != null) 
				{   
					textArea.append(linha); 
					textArea.append(System.getProperty("line.separator"));
				} 
				
				input.close(); 
				
				arquivo = file;
				
			} 
			catch (IOException ioe) 
			{ 
				System.out.println(ioe); 
			}
		}
	}

	public void salvar ()
	{
		if (arquivo == null) 
		{
			try
			{
				int returnVal = fc.showSaveDialog(Interface.this);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					File file = fc.getSelectedFile();
					FileWriter inArq = new FileWriter(file.getPath());

					inArq.write(textArea.getText()); // escreve o arquivo

					inArq.close();
					
					arquivo = file;
				}
			}
			catch(IOException ioe) 
			{
				JOptionPane.showMessageDialog(this,"Erro ao salvar o arquivo.");
			}
		}
		else
		{
			try
			{
				File file = arquivo;
				FileWriter inArq = new FileWriter(file.getPath());

				inArq.write(textArea.getText()); // escreve o arquivo

				inArq.close();
					
				arquivo = file;
			}
			catch(IOException ioe) 
			{
				JOptionPane.showMessageDialog(this,"Erro ao salvar o arquivo.");
			}
		}
	}
	
	public void sair()
	{
		if (textArea.getText().trim().length() != 0)
		{
			int confirmed = JOptionPane.showConfirmDialog(null, "Deseja salvar as alteracoes no documento?", "", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if (confirmed == JOptionPane.YES_OPTION)
			{
				salvar();
				dispose();
			}
			else if (confirmed == JOptionPane.NO_OPTION)
			{
				dispose();
			}
		}
		else 
		{
			dispose();
		}
	}
}
