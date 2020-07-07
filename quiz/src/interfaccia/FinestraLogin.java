package interfaccia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import quiz.Utente;

public class FinestraLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JButton buttonLogin;
	private JTextField textField;
	private JLabel label;
	
	public FinestraLogin() {
		this.setTitle("Login utente");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 200);
		
		this.panel = new JPanel();
		this.panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.panel.setLayout(null);
		
		this.label = new JLabel();
		this.label.setText("Codice Utente");
		this.label.setBounds(50, 50, 100, 20);
		
		this.textField = new JTextField();
		this.textField.setBounds(155, 50, 150, 20);
		
		this.buttonLogin = new JButton("OK");
		this.buttonLogin.setBounds(50, 75, 60, 20);
		
		this.panel.add(this.label);
		this.panel.add(this.textField);
		this.panel.add(this.buttonLogin);
		
		this.buttonLogin.addActionListener(this);
		
		this.setContentPane(panel);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
        String codUtente = this.getTextField().getText();
        Utente utente = Utente.getUtente(codUtente);
        
        this.dispose();
        
        
        //Questo if simula il controllo su database e crea un quiz che nel progetto finito
        //sarà preso dal database
        if (!utente.isAdmin()) {
        	@SuppressWarnings("unused")
			FinestraUtente finestraUtente = new FinestraUtente(utente);
        	/*Quiz primo_quiz = new Quiz();
			
			Domanda domanda1 = new Domanda("Cosa e' il Sole?");
			
			domanda1.addRisposta(new Risposta("Stella", true));
			domanda1.addRisposta(new Risposta("Pianeta", false));
			domanda1.addRisposta(new Risposta("Satellite", false));
			
			Domanda domanda2 = new Domanda("Dove si trova Roma?");
			
			domanda2.addRisposta(new Risposta("Lazio", true));
			domanda2.addRisposta(new Risposta("Puglia", false));
			domanda2.addRisposta(new Risposta("Lombardia", false));
			
			Domanda domanda3 = new Domanda("Cosa e' il cane?");
			
			domanda3.addRisposta(new Risposta("Pesce", false));
			domanda3.addRisposta(new Risposta("Uccello", false));
			domanda3.addRisposta(new Risposta("Mammifero", true));
			
			primo_quiz.addDomanda(domanda1);
			primo_quiz.addDomanda(domanda2);
			primo_quiz.addDomanda(domanda3);
			
			primo_quiz.setTitolo("PRIMO QUIZ");
			primo_quiz.setTempo(10);
			
			QuizUtente quizUtente = new QuizUtente(new Utente(codUtente, "Mario", "Rossi", false), primo_quiz);
        	
        	FinestraQuiz finestraQuiz = new FinestraQuiz(quizUtente);*/
        }
        
        else if (utente.isAdmin()) {
        	@SuppressWarnings("unused")
			FinestraAmministratore finestraAdmin = new FinestraAmministratore(utente);
        }
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getButtonLogin() {
		return buttonLogin;
	}

	public void setButtonLogin(JButton buttonLogin) {
		this.buttonLogin = buttonLogin;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
}
