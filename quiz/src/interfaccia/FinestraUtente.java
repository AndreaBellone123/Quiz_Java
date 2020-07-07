package interfaccia;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import quiz.Domanda;
import quiz.Quiz;
import quiz.QuizUtente;
import quiz.Utente;

public class FinestraUtente extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JButton buttonRisultati;
	private JButton[] buttonIniziaQuiz;
	private JTable tabellaRisultati;
	private ArrayList<Quiz> listaQuizDisponibili;
	private Utente utente;
	private String[] codiceAutori;
	private JButton buttonIndietro;

	public FinestraUtente(Utente utente) {
		this.utente = utente;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setTitle(utente.getNome().concat(" ").concat(utente.getCognome().concat(" - ").concat(utente.getCodUtente())));

		this.setContentPane(this.creaPanelMain());

		this.setResizable(false);
		this.setVisible(true);
	}

	public JPanel creaPanelMain() {
		JPanel panel = new JPanel();

		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());

		this.buttonRisultati = new JButton("RISULTATI");
		this.buttonRisultati.addActionListener(this);

		listaQuizDisponibili = Quiz.quizDisponibili(this.utente.getCodUtente()); 
		
		
		int dimensioni = listaQuizDisponibili.size();

        //for per eliminare i quiz incompleti dall'elenco disponibili
        for (int i = 0; i < dimensioni; i++) {
            boolean checkDomanda = false;
            boolean checkRisposta = false;

            try {
                if (this.listaQuizDisponibili.get(i).getDomande().size() == 10) {
                    checkDomanda = true;
                }
            } catch (NullPointerException e) {

            }

            for (Domanda domanda : this.listaQuizDisponibili.get(i).getDomande()) {
                try {
                    if (domanda.getRisposte().size() == 3) {
                        checkRisposta = true;
                    }
                    else {
                        checkRisposta = false;
                    }
                } catch (NullPointerException e) {

                }
            }

            if (!checkDomanda || !checkRisposta) {
                this.listaQuizDisponibili.remove(i);
            }
        }
		

		this.buttonIniziaQuiz = new JButton[listaQuizDisponibili.size()];
		this.codiceAutori = new String[listaQuizDisponibili.size()];

		JPanel panelQuiz = new JPanel();
		panelQuiz.setLayout(new GridLayout(0,4));

		for (int i = 0; i < buttonIniziaQuiz.length; i++) { 
			
			this.buttonIniziaQuiz[i] = new JButton(listaQuizDisponibili.get(i).getTitolo());
			this.buttonIniziaQuiz[i].setMaximumSize(new Dimension(100, 20));
			this.buttonIniziaQuiz[i].addActionListener(this);
			panelQuiz.add(this.buttonIniziaQuiz[i]);

			this.codiceAutori[i] = listaQuizDisponibili.get(i).getCodiceAutore();
		}
		
		panel.add(panelQuiz, BorderLayout.CENTER);
		panel.add(this.buttonRisultati, BorderLayout.PAGE_END);

		return panel;
	};


	@Override
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();

		if (src == this.buttonRisultati) {
			String[] colonne = {"Quiz", "Punteggio"};

			ArrayList<String[]> quizSvolti = this.getUtente().quizSvolti();
			int dimensioni = quizSvolti.size();

			Object[][] data = new Object[dimensioni][2];

			for(int i = 0; i < dimensioni; i++) {
				data[i][0] = quizSvolti.get(i)[0];
				data[i][1] = quizSvolti.get(i)[1];
			}

			JTable table = new JTable(data, colonne);

			JPanel panelTabella = new JPanel();
			panelTabella.setLayout(new BorderLayout());

			panelTabella.add(table.getTableHeader(), BorderLayout.PAGE_START);
			panelTabella.add(table, BorderLayout.CENTER);

			this.buttonIndietro = new JButton("INDIETRO");
			this.buttonIndietro.addActionListener(this);
			panelTabella.add(this.buttonIndietro, BorderLayout.PAGE_END);

			this.setContentPane(panelTabella);
			this.invalidate();
			this.validate();
		}

		if (src == this.buttonIndietro) {
			this.setContentPane(this.creaPanelMain());
			this.invalidate();
			this.validate();                        
		}

		for (int i = 0; i < this.buttonIniziaQuiz.length; i++) {
			if (src == this.buttonIniziaQuiz[i]) {
				QuizUtente quizUtente = new QuizUtente(this.utente, this.listaQuizDisponibili.get(i));
				@SuppressWarnings("unused")
				FinestraQuiz finestraQuiz = new FinestraQuiz(quizUtente);
				this.dispose();
			}
		}
	}

	//metodi di default
	public JButton getButtonRisultati() {
		return buttonRisultati;
	}

	public void setButtonRisultati(JButton buttonRisultati) {
		this.buttonRisultati = buttonRisultati;
	}

	public JButton[] getButtonIniziaQuiz() {
		return buttonIniziaQuiz;
	}

	public void setButtonIniziaQuiz(JButton[] buttonIniziaQuiz) {
		this.buttonIniziaQuiz = buttonIniziaQuiz;
	}

	public JTable getTabellaRisultati() {
		return tabellaRisultati;
	}

	public void setTabellaRisultati(JTable tabellaRisultati) {
		this.tabellaRisultati = tabellaRisultati;
	}

	public ArrayList<Quiz> getListaQuizDisponibili() {
		return listaQuizDisponibili;
	}

	public void setListaQuizDisponibili(ArrayList<Quiz> listaQuizDisponibili) {
		this.listaQuizDisponibili = listaQuizDisponibili;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String[] getCodiceAutori() {
		return codiceAutori;
	}

	public void setCodiceAutori(String[] codiceAutori) {
		this.codiceAutori = codiceAutori;
	}

}

