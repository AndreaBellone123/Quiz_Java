package interfaccia;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Duration;
import java.time.Instant;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

import quiz.QuizUtente;
import quiz.Domanda;
import quiz.Risposta;

public class FinestraQuiz extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JLabel labelNumeroDomanda;
	private JLabel labelTestoDomanda;
	private JButton buttonTermina;
	private JButton buttonAvanti;
	private JButton buttonIndietro;
	private QuizUtente quizUtente;
	private int i;
	private JRadioButton radio0, radio1, radio2;
	private Timer timer;
	private Instant tempoInizio;
	public FinestraQuiz(QuizUtente quizUtente) {

		this.quizUtente = quizUtente;
		this.quizUtente.getQuiz().setTempo(500000);

		this.setContentPane(creaPanel(this.i));
		this.setTitle(this.quizUtente.getQuiz().getTitolo());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setResizable(false);
		this.setVisible(true);

		this.timer = new Timer(this.quizUtente.getQuiz().getTempo(), this);
		this.timer.start();
		this.tempoInizio = Instant.now();
	}

	public JPanel creaPanel(int i) {

		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.panel.setLayout(null);

		this.labelNumeroDomanda = new JLabel();
		this.labelNumeroDomanda.setText("Domanda " + (this.i+1) + "/" + this.quizUtente.getQuiz().getDomande().size());
		this.labelNumeroDomanda.setBounds(5, 5, 200, 30);

		Domanda domanda = this.quizUtente.getQuiz().getDomande().get(this.i);
		Risposta risposta0 = domanda.getRisposte().get(0);
		Risposta risposta1 = domanda.getRisposte().get(1);
		Risposta risposta2 = domanda.getRisposte().get(2);

		this.labelTestoDomanda = new JLabel();
		this.labelTestoDomanda.setText(domanda.getDomanda());
		this.labelTestoDomanda.setBounds(5, 25, 400, 50);

		this.buttonTermina = new JButton("TERMINA");
		this.buttonTermina.setBounds(675, 535, 100, 20);
		this.buttonTermina.addActionListener(this);

		this.buttonIndietro = new JButton("INDIETRO");
		this.buttonIndietro.setBounds(5, 535, 100, 20);
		this.buttonIndietro.addActionListener(this);

		this.buttonAvanti = new JButton("AVANTI");
		this.buttonAvanti.setBounds(120, 535, 100, 20);
		this.buttonAvanti.addActionListener(this);

		this.radio0 = new JRadioButton(risposta0.getRisposta());
		this.radio0.setBounds(5, 85, 200, 15);
		if (this.quizUtente.getRisposteDate().get(domanda) != null && this.quizUtente.getRisposteDate().get(domanda).equals(risposta0)) {
			this.radio0.setSelected(true);
		}

		this.radio1 = new JRadioButton(risposta1.getRisposta());
		this.radio1.setBounds(5, 105, 200, 15);
		if (this.quizUtente.getRisposteDate().get(domanda) != null && this.quizUtente.getRisposteDate().get(domanda).equals(risposta1)) {
			this.radio1.setSelected(true);
		}

		this.radio2 = new JRadioButton(risposta2.getRisposta());
		this.radio2.setBounds(5, 125, 200, 15);
		if (this.quizUtente.getRisposteDate().get(domanda) != null && this.quizUtente.getRisposteDate().get(domanda).equals(risposta2)) {
			this.radio2.setSelected(true);
		}

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radio0);
		buttonGroup.add(radio1);
		buttonGroup.add(radio2);

		this.radio0.addItemListener(this);
		this.radio1.addItemListener(this);
		this.radio2.addItemListener(this);

		this.panel.add(labelNumeroDomanda);
		this.panel.add(labelTestoDomanda);
		this.panel.add(buttonAvanti);
		this.panel.add(buttonIndietro);
		this.panel.add(buttonTermina);
		this.panel.add(radio0);
		this.panel.add(radio1);
		this.panel.add(radio2);

		return this.panel;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		Domanda domanda = this.quizUtente.getQuiz().getDomande().get(this.i);
		Risposta risposta0 = domanda.getRisposte().get(0);
		Risposta risposta1 = domanda.getRisposte().get(1);
		Risposta risposta2 = domanda.getRisposte().get(2);

		if (radio0.isSelected()) {
			this.quizUtente.aggiungiRispostaMappa(domanda, risposta0);
			//System.out.println(this.quizUtente.getRisposteDate().get(this.quizUtente.getQuiz().getDomande().get(this.i)));
		}

		if (radio1.isSelected()) {
			this.quizUtente.aggiungiRispostaMappa(domanda, risposta1);
			//System.out.println(this.quizUtente.getRisposteDate().get(this.quizUtente.getQuiz().getDomande().get(this.i)));
		}

		if (radio2.isSelected()) {
			this.quizUtente.aggiungiRispostaMappa(domanda, risposta2);
			//System.out.println(this.quizUtente.getRisposteDate().get(this.quizUtente.getQuiz().getDomande().get(this.i)));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();

		if (src == this.timer)
			this.buttonTermina.doClick();

		if (src == this.buttonAvanti) {
			if (this.i != 9)
				this.setI(this.i + 1);
			else
				this.setI(0);

			this.setContentPane(creaPanel(this.i));
			this.invalidate();
			this.validate();
		}

		if (src == this.buttonIndietro) {
			if (this.i != 0)
				this.setI(this.i - 1);
			else
				this.setI(this.quizUtente.getQuiz().getDomande().size() - 1);

			this.setContentPane(creaPanel(this.i));
			this.invalidate();
			this.validate();
		}	

		if (src == this.buttonTermina) {

			Instant tempoFine = Instant.now();
			this.quizUtente.setTempoImpiegato(Duration.between(this.tempoInizio, tempoFine).getSeconds());

			this.quizUtente.risultatoQuiz();

			this.quizUtente.aggiornaQuizDB();

			String[] colonne = {"Domanda", "Risposte corrette", "Risposte date"};
			JLabel punteggio = new JLabel("Punteggio : " + quizUtente.getPunteggio() + " /30");
			int dimensioni = this.quizUtente.getQuiz().getDomande().size();

			Object[][] data = new Object[dimensioni][3];

			for (int i = 0; i < dimensioni; i++) {
				data[i][0] = this.getQuizUtente().getQuiz().getDomande().get(i);
				data[i][1] = this.getQuizUtente().getQuiz().elencoRisposteEsatte()[i];
				data[i][2] = this.getQuizUtente().getRisposteDate().get(this.getQuizUtente().getQuiz().getDomande().get(i));
			}

			JTable table = new JTable(data, colonne);

			JPanel panelTabella = new JPanel();
			panelTabella.setLayout(new BorderLayout());

			panelTabella.add(table.getTableHeader(), BorderLayout.PAGE_START);
			panelTabella.add(table, BorderLayout.CENTER);
			panelTabella.add(punteggio,BorderLayout.PAGE_END);

			this.setContentPane(panelTabella);
			this.invalidate();
			this.validate();

			/*String messaggio = "";

            if(quizUtente.isSuperato())
                    messaggio = "SUPERATO (Punteggio: " + quizUtente.getPunteggio() + ")";
            else
                    messaggio = "NON SUPERATO (Punteggio: " + quizUtente.getPunteggio() + ")";

            messaggio = messaggio + "\n\nRISPOSTE ESATTE:";

            for (Domanda domanda : this.quizUtente.getQuiz().getDomande())
            {
                    for (Risposta risposta : domanda.getRisposte()) {
                            if (risposta.isEsatto()) {
                                    messaggio = messaggio + "DOMANDA: " + domanda.getDomanda() + "\nRISPOSTA: " + risposta.getRisposta() + "\n";
                                    break;
                            }
                    }
            }

            messaggio = messaggio + "\nRISPOSTE DATE:";

            for (Domanda domanda : quizUtente.getQuiz().getDomande())
            {
                    if (this.quizUtente.getRisposteDate().get(domanda) == null)
                            messaggio = messaggio + "DOMANDA: " + domanda.getDomanda() + "\nRISPOSTA: Nessuna risposta data\n";
                    else
                            messaggio = messaggio + "DOMANDA: " + domanda.getDomanda() + "\nRISPOSTA: " + this.quizUtente.getRisposteDate().get(domanda) + "\n";
            }

            int result = JOptionPane.showConfirmDialog(this, messaggio, "Risultato Quiz", JOptionPane.DEFAULT_OPTION);
            if (result == JOptionPane.OK_OPTION)
                    System.exit(0);*/

		}

	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getLabelNumeroDomanda() {
		return labelNumeroDomanda;
	}

	public void setLabelNumeroDomanda(JLabel labelNumeroDomanda) {
		this.labelNumeroDomanda = labelNumeroDomanda;
	}

	public JLabel getLabelTestoDomanda() {
		return labelTestoDomanda;
	}

	public void setLabelTestoDomanda(JLabel labelTestoDomanda) {
		this.labelTestoDomanda = labelTestoDomanda;
	}

	public JButton getButtonTermina() {
		return buttonTermina;
	}

	public void setButtonTermina(JButton buttonTermina) {
		this.buttonTermina = buttonTermina;
	}

	public JButton getButtonAvanti() {
		return buttonAvanti;
	}

	public void setButtonAvanti(JButton buttonAvanti) {
		this.buttonAvanti = buttonAvanti;
	}

	public JButton getButtonIndietro() {
		return buttonIndietro;
	}

	public void setButtonIndietro(JButton buttonIndietro) {
		this.buttonIndietro = buttonIndietro;
	}

	public QuizUtente getQuizUtente() {
		return quizUtente;
	}

	public void setQuizUtente(QuizUtente quizUtente) {
		this.quizUtente = quizUtente;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
