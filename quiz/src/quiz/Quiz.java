package quiz;

import java.util.ArrayList;
import java.util.List;

import dao.QuizDao;

public class Quiz {
	private String titolo;
	private int tempo;
	private List<Domanda> domande;
	private String codiceAutore;

	public Quiz() {
		this.domande = new ArrayList<Domanda>();
	}

	public int addDomanda(Domanda d) {
		for (Domanda domandaEsistente : this.getDomande()) {
			if (domandaEsistente.equals(d))
				return 1;
		}

		this.getDomande().add(d);

		//int quiz_id = QuizDao.trovaIdQuiz(this.getTitolo(), this.getCodiceAutore());

		//d.addDomanda(d, quiz_id);

		return 0;
	}

	public int removeDomanda(Domanda d) {
		for (Domanda domandaEsistente : this.getDomande()) {
			if (domandaEsistente.equals(d)) {
				this.getDomande().remove(this.getDomande().indexOf(domandaEsistente));

				//int quiz_id = QuizDao.trovaIdQuiz(this.getTitolo(), this.getCodiceAutore());

				//d.deleteDomanda(d, quiz_id);

				return 0;
			}
		}

		return 1;
	}

	public String[] elencoRisposteEsatte() {
		String[] elenco = new String[this.getDomande().size()];

		for (int i = 0; i < this.getDomande().size(); i++) {
			for (Risposta risposta : this.getDomande().get(i).getRisposte()) {
				if (risposta.isEsatto()) {
					elenco[i] = risposta.getRisposta();
					break;
				}
			}
		}

		return elenco;
	}
	public static ArrayList<Quiz> quizDaAutore(String codiceautore){
		
		ArrayList<Quiz>listaQuizAutore = new ArrayList<Quiz>();
		listaQuizAutore = QuizDao.trovaDaAutore(codiceautore);
		return listaQuizAutore;
		
	}
	
	public int trovaIdQuiz(String titolo,String codiceAutore) {
		return QuizDao.trovaIdQuiz(titolo, codiceAutore);
	}

	//metodi che modificano o cancellano i quiz dal db
	public static Quiz getQuiz(String titolo, String codice_autore) {
		return QuizDao.trovaQuiz(titolo, codice_autore);
	}

	public static ArrayList<Quiz> quizDisponibili(String codice_utente) {
		return QuizDao.trovaQuizDisponibili(codice_utente);
	}

	public boolean addQuiz(Quiz quiz) {
		return QuizDao.inserisciQuiz(quiz);
	}

	public boolean deleteQuiz(Quiz quiz) {
		return QuizDao.cancellaQuiz(quiz);
	}

	//metodi di default
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public List<Domanda> getDomande() {
		return domande;
	}
	public void setDomande(List<Domanda> domande) {
		this.domande = domande;
	}

	public String getCodiceAutore() {
		return codiceAutore;
	}

	public void setCodiceAutore(String codiceAutore) {
		this.codiceAutore = codiceAutore;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quiz [titolo=").append(titolo).append(", tempo=").append(tempo).append(", domande=")
		.append(domande).append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domande == null) ? 0 : domande.hashCode());
		result = prime * result + tempo;
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
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
		Quiz other = (Quiz) obj;
		if (domande == null) {
			if (other.domande != null)
				return false;
		} else if (!domande.equals(other.domande))
			return false;
		if (tempo != other.tempo)
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}
}
