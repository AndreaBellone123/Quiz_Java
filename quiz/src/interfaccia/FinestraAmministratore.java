package interfaccia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import quiz.Domanda;
import quiz.Quiz;
import quiz.Risposta;
import quiz.Utente;

public class FinestraAmministratore extends JFrame implements ActionListener {

        private static final long serialVersionUID = 1L;

        private JTable tabellaQuiz;
        private JButton buttonModifica, buttonCancella, buttonCrea;
        private Utente utente;
        private ArrayList<Quiz> listaQuiz;

        public FinestraAmministratore(Utente utente) {
                this.utente = utente;

                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setTitle(this.utente.getNome().concat(" ").concat(this.utente.getCognome()).concat(" - ").concat(this.utente.getCodUtente()));
                this.setSize(800, 600);

                this.setContentPane(this.creaPanelMain());

                this.setResizable(false);
                this.setVisible(true);
        }

        public JPanel creaPanelMain() {
                JPanel panel = new JPanel();

                panel.setBorder(new EmptyBorder(5, 5, 5, 5));
                panel.setLayout(new BorderLayout());

                this.listaQuiz = Quiz.quizDaAutore(this.utente.getCodUtente());

                String[] colonne = {"Quiz creati da ".concat(utente.getNome()).concat(" ").concat(utente.getCognome()), ""};

                int dimensioni = this.listaQuiz.size();

                Object[][] data = new Object[dimensioni][2];

                for (int i = 0; i < dimensioni; i++) {
                        data[i][0] = this.listaQuiz.get(i).getTitolo();

                        boolean checkDomanda = false;
                        boolean checkRisposta = false;

                        try {
                                if (this.listaQuiz.get(i).getDomande().size() == 10) {
                                        checkDomanda = true;
                                }
                        } catch (NullPointerException e) {

                        }

                        for (Domanda domanda : this.listaQuiz.get(i).getDomande()) {
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

                        if (checkDomanda && checkRisposta) {
                                data[i][1] = "QUIZ PRONTO";
                        }
                        else {
                                data[i][1] = "QUIZ INCOMPLETO";
                        }
                }

                this.tabellaQuiz = new JTable(data, colonne);

                panel.add(this.tabellaQuiz.getTableHeader(), BorderLayout.PAGE_START);
                panel.add(this.tabellaQuiz, BorderLayout.CENTER);

                this.buttonModifica = new JButton("MODIFICA");
                this.buttonCancella = new JButton("CANCELLA");
                this.buttonCrea = new JButton("NUOVO QUIZ");

                this.buttonModifica.addActionListener(this);
                this.buttonCancella.addActionListener(this);
                this.buttonCrea.addActionListener(this);

                JPanel panelButton = new JPanel();

                panelButton.add(buttonModifica);
                panelButton.add(buttonCancella);
                panelButton.add(buttonCrea);

                panel.add(panelButton, BorderLayout.PAGE_END);

                return panel;
        }

        public void creaDialog(Utente utente) {
                JDialog dialog = new JDialog(this, "INSERISCI NUOVO QUIZ");

                dialog.setSize(400, 300);

                JPanel panelDialog = new JPanel();

                panelDialog.setLayout(new BorderLayout());
                panelDialog.setBorder(new EmptyBorder(5, 5, 5, 5));

                JLabel label = new JLabel("Inserisci titolo e tempo massimo in millisecondi del nuovo quiz");
                JTextField textTitolo = new JTextField("Titolo");
                JTextField textTempo = new JTextField("Tempo");

                textTitolo.setForeground(Color.GRAY);
                textTitolo.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                                if (textTitolo.getText().equals("Titolo")) {
                                        textTitolo.setText("");
                                        textTitolo.setForeground(Color.BLACK);
                                }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                                if (textTitolo.getText().isEmpty()) {
                                        textTitolo.setForeground(Color.GRAY);
                                        textTitolo.setText("Titolo");
                                }
                        }
                });

                textTempo.setForeground(Color.GRAY);
                textTempo.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                                if (textTempo.getText().equals("Tempo")) {
                                        textTempo.setText("");
                                        textTempo.setForeground(Color.BLACK);
                                }
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                                if (textTempo.getText().isEmpty()) {
                                        textTempo.setForeground(Color.GRAY);
                                        textTempo.setText("Tempo");
                                }
                        }
                });

                
                JPanel panelCentrale = new JPanel();
                panelCentrale.setLayout(new BoxLayout(panelCentrale, BoxLayout.PAGE_AXIS));

                panelCentrale.add(label);
                panelCentrale.add(textTitolo);
                panelCentrale.add(textTempo);

                panelDialog.add(panelCentrale, BorderLayout.CENTER);

                JButton buttonConferma = new JButton("CONFERMA");

                buttonConferma.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                Quiz quiz = new Quiz();
                                quiz.setTitolo(textTitolo.getText());
                                quiz.setCodiceAutore(utente.getCodUtente());
                                quiz.setTempo(Integer.parseInt(textTempo.getText()));
                                quiz.addQuiz(quiz);

                                FinestraAmministratore frame = (FinestraAmministratore) dialog.getOwner();

                                dialog.dispose();

                                frame.setUtente(utente);
                                frame.setContentPane(frame.creaPanelMain());
                                frame.invalidate();
                                frame.validate();

                        }
                });

                panelDialog.add(buttonConferma, BorderLayout.PAGE_END);

                dialog.setContentPane(panelDialog);
                dialog.setResizable(false);
                dialog.setVisible(true);
        }

        public JPanel creaModificaQuiz(Quiz quiz, int i) { // Di base è 0 quando premiamo Modifica

                JPanel panel = new JPanel();
                panel.setBorder(new EmptyBorder(5, 5, 5, 5));
                panel.setLayout(new BorderLayout());

                JPanel panelCentrale = new JPanel();
                panelCentrale.setLayout(new GridLayout(0,2));

                JPanel panelBottoni = new JPanel();
                panelBottoni.setLayout(new FlowLayout());

                JLabel labelNumeroDomanda = new JLabel();
                labelNumeroDomanda.setText("Domanda " + (i+1) + "/10");

                Domanda domanda = new Domanda("");
                Risposta risposta0 = new Risposta("", true);
                Risposta risposta1 = new Risposta("", false);
                Risposta risposta2 = new Risposta("", false);

                if (i <= quiz.getDomande().size() - 1) {
                	
                        domanda = quiz.getDomande().get(i);
                        risposta0 = domanda.getRisposte().get(0);
                        risposta1 = domanda.getRisposte().get(1);
                        risposta2 = domanda.getRisposte().get(2);
                }

                JTextField textDomanda = new JTextField(domanda.getDomanda());
                JTextField textRisposta0 = new JTextField(risposta0.getRisposta());
                JTextField textRisposta1 = new JTextField(risposta1.getRisposta());
                JTextField textRisposta2 = new JTextField(risposta2.getRisposta());

                JRadioButton radio0 = new JRadioButton();
                JRadioButton radio1 = new JRadioButton();
                JRadioButton radio2 = new JRadioButton();

                if (risposta0.isEsatto()) {
                        radio0.setSelected(true);
                }
                if (risposta1.isEsatto()) {
                        radio1.setSelected(true);
                }
                if (risposta2.isEsatto()) {
                        radio2.setSelected(true);
                }

                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(radio0);
                buttonGroup.add(radio1);
                buttonGroup.add(radio2);

                panelCentrale.add(labelNumeroDomanda);
                panelCentrale.add(textDomanda);
                panelCentrale.add(textRisposta0);
                panelCentrale.add(radio0);
                panelCentrale.add(textRisposta1);
                panelCentrale.add(radio1);
                panelCentrale.add(textRisposta2);
                panelCentrale.add(radio2);

                JButton buttonTermina = new JButton("TERMINA");
                JButton buttonIndietro = new JButton("INDIETRO");
                JButton buttonAvanti = new JButton("AVANTI");

                buttonAvanti.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int j = 0;

                                if (i != 9) {
                                        j = i + 1;
                                }

                                if (i <= quiz.getDomande().size() -1) {
                                        quiz.getDomande().get(i).setDomanda(textDomanda.getText());
                                        quiz.getDomande().get(i).getRisposte().get(0).setRisposta(textRisposta0.getText());
                                        quiz.getDomande().get(i).getRisposte().get(1).setRisposta(textRisposta1.getText());
                                        quiz.getDomande().get(i).getRisposte().get(2).setRisposta(textRisposta2.getText());
                                        
                                        if (radio0.isSelected()) {
                                                quiz.getDomande().get(i).getRisposte().get(0).setEsatto(true);
                                                quiz.getDomande().get(i).getRisposte().get(1).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(2).setEsatto(false);
                                        }
                                        if (radio1.isSelected()) {
                                                quiz.getDomande().get(i).getRisposte().get(0).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(1).setEsatto(true);
                                                quiz.getDomande().get(i).getRisposte().get(2).setEsatto(false);
                                        }
                                        if (radio2.isSelected()) {
                                                quiz.getDomande().get(i).getRisposte().get(0).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(1).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(2).setEsatto(true);
                                        }
                                }
                                
                                else {
                                        Domanda nuovaDomanda = new Domanda(textDomanda.getText());
                                        if (radio0.isSelected()) {
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta0.getText(), true));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta1.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta2.getText(), false));
                                        }
                                        
                                        if (radio1.isSelected()) {
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta0.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta1.getText(), true));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta2.getText(), false));
                                        }
                                        
                                        if (radio2.isSelected()) {
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta0.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta1.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta2.getText(), true));
                                        }

                                        quiz.addDomanda(nuovaDomanda);
                                }

                                FinestraAmministratore frame = (FinestraAmministratore) SwingUtilities.getWindowAncestor(panel); // Per risalire al contenitore dell'oggetto
                                
                                frame.setContentPane(frame.creaModificaQuiz(quiz, j));
                                frame.invalidate();
                                frame.validate();
                        }

                });

                buttonIndietro.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int j = 9;

                                if (i != 0) {
                                        j = i - 1;
                                }
                                
                                if (i <= quiz.getDomande().size() -1) {
                                        quiz.getDomande().get(i).setDomanda(textDomanda.getText());
                                        quiz.getDomande().get(i).getRisposte().get(0).setRisposta(textRisposta0.getText());
                                        quiz.getDomande().get(i).getRisposte().get(1).setRisposta(textRisposta1.getText());
                                        quiz.getDomande().get(i).getRisposte().get(2).setRisposta(textRisposta2.getText());
                                        
                                        if (radio0.isSelected()) {
                                                quiz.getDomande().get(i).getRisposte().get(0).setEsatto(true);
                                                quiz.getDomande().get(i).getRisposte().get(1).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(2).setEsatto(false);
                                        }
                                        if (radio1.isSelected()) {
                                                quiz.getDomande().get(i).getRisposte().get(0).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(1).setEsatto(true);
                                                quiz.getDomande().get(i).getRisposte().get(2).setEsatto(false);
                                        }
                                        if (radio2.isSelected()) {
                                                quiz.getDomande().get(i).getRisposte().get(0).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(1).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(2).setEsatto(true);
                                        }
                                }
                                
                                else {
                                        Domanda nuovaDomanda = new Domanda(textDomanda.getText());
                                        if (radio0.isSelected()) {
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta0.getText(), true));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta1.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta2.getText(), false));
                                        }
                                        
                                        if (radio1.isSelected()) {
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta0.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta1.getText(), true));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta2.getText(), false));
                                        }
                                        
                                        if (radio2.isSelected()) {
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta0.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta1.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta2.getText(), true));
                                        }

                                        quiz.addDomanda(nuovaDomanda);
                                }

                                FinestraAmministratore frame = (FinestraAmministratore) SwingUtilities.getWindowAncestor(panel);

                                frame.setContentPane(frame.creaModificaQuiz(quiz, j));
                                frame.invalidate();
                                frame.validate();
                        }

                });

                buttonTermina.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                
                                if (i <= quiz.getDomande().size() -1) {
                                        quiz.getDomande().get(i).setDomanda(textDomanda.getText());
                                        quiz.getDomande().get(i).getRisposte().get(0).setRisposta(textRisposta0.getText());
                                        quiz.getDomande().get(i).getRisposte().get(1).setRisposta(textRisposta1.getText());
                                        quiz.getDomande().get(i).getRisposte().get(2).setRisposta(textRisposta2.getText());
                                        
                                        if (radio0.isSelected()) {
                                                quiz.getDomande().get(i).getRisposte().get(0).setEsatto(true);
                                                quiz.getDomande().get(i).getRisposte().get(1).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(2).setEsatto(false);
                                        }
                                        if (radio1.isSelected()) {
                                                quiz.getDomande().get(i).getRisposte().get(0).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(1).setEsatto(true);
                                                quiz.getDomande().get(i).getRisposte().get(2).setEsatto(false);
                                        }
                                        if (radio2.isSelected()) {
                                                quiz.getDomande().get(i).getRisposte().get(0).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(1).setEsatto(false);
                                                quiz.getDomande().get(i).getRisposte().get(2).setEsatto(true);
                                        }
                                }
                                
                                else {
                                        Domanda nuovaDomanda = new Domanda(textDomanda.getText());
                                        if (radio0.isSelected()) {
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta0.getText(), true));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta1.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta2.getText(), false));
                                        }
                                        
                                        if (radio1.isSelected()) {
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta0.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta1.getText(), true));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta2.getText(), false));
                                        }
                                        
                                        if (radio2.isSelected()) {
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta0.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta1.getText(), false));
                                                nuovaDomanda.addRisposta(new Risposta(textRisposta2.getText(), true));
                                        }

                                        quiz.addDomanda(nuovaDomanda);
                                }
                                
                                int quiz_id = quiz.trovaIdQuiz(quiz.getTitolo(), quiz.getCodiceAutore());
                                
                                System.out.println(quiz_id);
                                
                                for (Domanda domandaDaAggiungere : quiz.getDomande()) {
                                        
                                        domandaDaAggiungere.addDomanda(domandaDaAggiungere, quiz_id);
                                        
                                        int domanda_id = domandaDaAggiungere.trovaId(domandaDaAggiungere.getDomanda(), quiz_id);
                                        
                                        System.out.println(domanda_id);
                                        
                                        for (Risposta rispostaDaAggiungere : domandaDaAggiungere.getRisposte()) {
                                                rispostaDaAggiungere.addRisposta(rispostaDaAggiungere, domanda_id);
                                                
                                        }
                                }

                                FinestraAmministratore frame = (FinestraAmministratore) SwingUtilities.getWindowAncestor(panel);

                                frame.setContentPane(frame.creaPanelMain());
                                frame.invalidate();
                                frame.validate();
                        }

                });

                panelBottoni.add(buttonIndietro);
                panelBottoni.add(buttonAvanti);
                panelBottoni.add(buttonTermina);

                panel.add(new JLabel("Stai modificando il quiz '".concat(quiz.getTitolo()).concat("'")), BorderLayout.PAGE_START);
                panel.add(panelCentrale, BorderLayout.CENTER);
                panel.add(panelBottoni, BorderLayout.PAGE_END);

                return panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

                Object src = e.getSource();

                if (src == this.buttonCancella) {
                        int rigaSelezionata = this.tabellaQuiz.getSelectedRow();
                        int colonnaSelezionata = this.tabellaQuiz.getSelectedColumn();
                        String titolo = this.tabellaQuiz.getValueAt(rigaSelezionata, colonnaSelezionata).toString();

                        Quiz quiz = Quiz.getQuiz(titolo, this.utente.getCodUtente());
                        quiz.deleteQuiz(quiz);

                        this.setContentPane(this.creaPanelMain());
                        this.invalidate();
                        this.validate();
                }

                if (src == this.buttonModifica) {
                        int rigaSelezionata = this.tabellaQuiz.getSelectedRow();
                        int colonnaSelezionata = this.tabellaQuiz.getSelectedColumn();
                        String titolo = this.tabellaQuiz.getValueAt(rigaSelezionata, colonnaSelezionata).toString();

                        Quiz quiz = Quiz.getQuiz(titolo, this.utente.getCodUtente());

                        this.setContentPane(this.creaModificaQuiz(quiz, 0));
                        this.invalidate();
                        this.validate();
                }

                if (src == this.buttonCrea) {
                        this.creaDialog(this.utente);
                }
        }

        //metodi di default
        public JTable getTabellaQuiz() {
                return tabellaQuiz;
        }

        public void setTabellaQuiz(JTable tabellaQuiz) {
                this.tabellaQuiz = tabellaQuiz;
        }

        public JButton getButtonModifica() {
                return buttonModifica;
        }

        public void setButtonModifica(JButton buttonModifica) {
                this.buttonModifica = buttonModifica;
        }

        public JButton getButtonCancella() {
                return buttonCancella;
        }

        public void setButtonCancella(JButton buttonCancella) {
                this.buttonCancella = buttonCancella;
        }

        public JButton getButtonCrea() {
                return buttonCrea;
        }

        public void setButtonCrea(JButton buttonCrea) {
                this.buttonCrea = buttonCrea;
        }

        public Utente getUtente() {
                return utente;
        }

        public void setUtente(Utente utente) {
                this.utente = utente;
        }

        public ArrayList<Quiz> getListaQuiz() {
                return listaQuiz;
        }

        public void setListaQuiz(ArrayList<Quiz> listaQuiz) {
                this.listaQuiz = listaQuiz;
        }

}

