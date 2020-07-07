package quiz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import dao.QuizSvoltiDao;

public class QuizUtente {
	private Quiz quiz;
	private boolean superato;
	private int punteggio;
	private long tempoImpiegato;
	private Utente utente;
	private HashMap<Domanda, Risposta> risposteDate;
	
    public QuizUtente(Utente utente, Quiz quiz) {
    	//this.utente e this.quiz dovranno prendere i valori di getUtente e getQuiz
        this.utente = utente;
        this.quiz = quiz;
        this.superato = false;
        this.punteggio = 0;
        this.tempoImpiegato = 0;
        this.risposteDate = new HashMap<Domanda, Risposta>(); //domande del quiz e risposte dell'utente
        for (Domanda domanda : this.quiz.getDomande()) {
            risposteDate.put(domanda, null);
        }
    }
    
    public void aggiungiRispostaMappa(Domanda d, Risposta r) {
    	this.risposteDate.put(d, r);
    }
    
    /*public void eseguiQuiz() {
    	
    	Instant tempoInizio = Instant.now();
    	
    	int indiceRisposta = 0;
    	Scanner myScanner = new Scanner(System.in);
    	for (Domanda domanda : this.quiz.getDomande()) {
    		System.out.println(domanda);
    		System.out.println("RISPONDI 0, 1 o 2 per rispondere o 3 per saltare la risposta");
    		String indiceRispostaStringa = myScanner.nextLine();
    		indiceRisposta = Integer.parseInt(indiceRispostaStringa);
    		
    		if (indiceRisposta <= 2) {
    			Risposta risposta = domanda.getRisposte().get(indiceRisposta);
        		this.risposteDate.put(domanda, risposta);
    		}
    	}
    	
    	Instant tempoFine = Instant.now();
    	
    	this.setTempoImpiegato(Duration.between(tempoInizio, tempoFine).getSeconds());
    }*/
    
    public void risultatoQuiz() {
    	boolean checkSuperato = false;
    	Properties properties = new Properties();
    	
    	FileInputStream ip = null;
    
		try {
			ip = new FileInputStream("C:\\Users\\Crowley\\Desktop\\Jav\\quiz11-03\\quiz\\src\\quiz\\properties.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			properties.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String punteggioMinimo = properties.getProperty("PUNTEGGIO_MINIMO");
    	
    	for (Domanda domanda : this.getQuiz().getDomande()) {
    		if(risposteDate.get(domanda) != null && risposteDate.get(domanda).isEsatto())
    			this.punteggio += 3;
    	}
    	
    	if(this.punteggio >= Integer.parseInt(punteggioMinimo))
    		checkSuperato = true;
    	
    	this.setSuperato(checkSuperato);
    }
    
    //metodi da implementare con il db
    public int aggiornaQuizDB() {    	
    	
    	if(!QuizSvoltiDao.aggiungiQuiz(this)) {
    		return 1;
    	}
    	
    	if(!QuizSvoltiDao.aggiungiRisposteDate(this)) {
    		return 2;
    	}
    	
    	return 0;
    }
	
	//metodi di default
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public boolean isSuperato() {
		return superato;
	}
	public void setSuperato(boolean superato) {
		this.superato = superato;
	}
	public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	public long getTempoImpiegato() {
		return tempoImpiegato;
	}
	public void setTempoImpiegato(long tempoImpiegato) {
		this.tempoImpiegato = tempoImpiegato;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public HashMap<Domanda, Risposta> getRisposteDate() {
		return risposteDate;
	}
	public void setRisposteDate(HashMap<Domanda, Risposta> risposteDate) {
		this.risposteDate = risposteDate;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuizUtente [quiz=").append(quiz).append(", superato=").append(superato).append(", punteggio=")
				.append(punteggio).append(", tempoImpiegato=").append(tempoImpiegato).append(", utente=").append(utente)
				.append(", risposteDate=").append(risposteDate).append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + punteggio;
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		result = prime * result + ((risposteDate == null) ? 0 : risposteDate.hashCode());
		result = prime * result + (superato ? 1231 : 1237);
		result = (int) (prime * result + tempoImpiegato);
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizUtente other = (QuizUtente) obj;
		if (punteggio != other.punteggio)
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		if (risposteDate == null) {
			if (other.risposteDate != null)
				return false;
		} else if (!risposteDate.equals(other.risposteDate))
			return false;
		if (superato != other.superato)
			return false;
		if (tempoImpiegato != other.tempoImpiegato)
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}
}
