/*package interfaccia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.QuizDao;
import quiz.Risposta;
import quiz.Utente;
import quiz.Domanda;
import quiz.Quiz;

public class FinestraCreazione extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel label_inserisci_domanda;
	private JTextField testo_domanda;
	private JTextField r1;
	private JTextField r2;
	private JTextField r3;
	private JRadioButton rb1;
	private JRadioButton rb2;
	private JRadioButton rb3;
	private JButton button_inserisci;
	private Quiz quiz1;
	private JPanel panel;
	private JTextField tl;
	private Domanda domanda;
	private Risposta risposta1; 
	private Risposta risposta2; 
	private Risposta risposta3; 
	private String titolo;
	private String codUtente;
	
	public FinestraCreazione(String titoloQuiz,String codiceAutore) {
		this.codUtente = codiceAutore;
		this.titolo = titoloQuiz;
		this.quiz1 = Quiz.getQuiz(titolo,codUtente);
		this.setResizable(false);
		this.setTitle(this.titolo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setContentPane(creaPanel());
		this.setVisible(true); 

		// Creare i vari elementi del frame
	}
	public JPanel creaPanel() {

		panel = new JPanel();
		panel.setLayout(null);
		rb1 = new JRadioButton();
		rb2 = new JRadioButton();
		rb3 = new JRadioButton();
		tl = new JTextField();
		r1 = new JTextField();
		r2 = new JTextField();
		r3 = new JTextField();
		tl.setBounds(5,279,150,30);
		testo_domanda = new JTextField();
		testo_domanda.setBounds(5, 34, 150, 30);
		button_inserisci = new JButton("Inserisci");
		label_inserisci_domanda = new JLabel("Inserisci domanda");
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		button_inserisci.addActionListener(this);
		label_inserisci_domanda.setBounds(5, 5, 150, 30);
		r1.setBounds(5,75,150,30);
		r2.setBounds(5,105,150,30);
		r3.setBounds(5,135,150,30);
		rb1.setBounds(160,75,150,30);
		rb2.setBounds(160,105,150,30);
		rb3.setBounds(160,135,150,30);
		button_inserisci.setBounds(120, 514, 100, 41);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		this.panel.add(rb1);
		this.panel.add(rb2);
		this.panel.add(rb3);
		this.panel.add(label_inserisci_domanda);
		this.panel.add(button_inserisci);
		this.panel.add(r1);
		this.panel.add(r2);
		this.panel.add(r3);
		this.panel.add(testo_domanda);
		return this.panel;

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if(src == button_inserisci) {
			boolean checkEsatto = false;

			domanda = new Domanda(testo_domanda.getText());

			if (rb1.isSelected()) {
				checkEsatto = true;
			}

			else
				checkEsatto = false;

			risposta1 = new Risposta(r1.getText(), checkEsatto); 
			int add1 = domanda.addRisposta(risposta1,QuizDao.trovaIdQuiz(this.titolo,this.codUtente));

			if (rb2.isSelected()) {
				checkEsatto = true;

			}
			else
				checkEsatto = false;

			risposta2 = new Risposta(r2.getText(), checkEsatto);
			int add2 = domanda.addRisposta(risposta2,QuizDao.trovaIdQuiz(this.titolo,this.codUtente));

			if (rb3.isSelected()) {
				checkEsatto = true;
			}

			else
				checkEsatto = false;

			risposta3 = new Risposta(r3.getText(), checkEsatto); 
			int add3 = domanda.addRisposta(risposta3,QuizDao.trovaIdQuiz(this.titolo,this.codUtente));

			if (add1 == 0 && add2 == 0 && add3 == 0) {
				this.quiz1.addDomanda(domanda);
				this.setContentPane(creaPanel());
				this.invalidate();
				this.validate();
				JOptionPane.showMessageDialog(null, "Domanda inserita con successo!"); 
			}
			else {
				String messaggio = "Domanda non inserita!\n";

				if (add2 == 1) 
					messaggio = messaggio + "\nRisposta 2 non inserita! Risposta gia' presente";
				if (add3 == 1) 
					messaggio = messaggio + "\nRisposta 3 non inserita! Risposta gia' presente";
				if (add3 == 3)
					messaggio = messaggio + "Devi inserire almeno una risposta corretta!";

				JOptionPane.showMessageDialog(null, messaggio); 

			}
		}
	}
}

*/
