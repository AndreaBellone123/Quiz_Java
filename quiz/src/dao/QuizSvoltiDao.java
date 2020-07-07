package dao;

import java.util.ArrayList;
import java.util.HashMap;
import dbConnection.DbConnection;
import quiz.Domanda;
import quiz.Quiz;
import quiz.QuizUtente;
import quiz.Risposta;

public class QuizSvoltiDao {

	public static boolean aggiungiQuiz(QuizUtente quizUtente) {
		DbConnection.connetti();

		boolean check = false;

		String codice_utente= quizUtente.getUtente().getCodUtente();
		String quiz_titolo = quizUtente.getQuiz().getTitolo();
		int quiz_id = QuizDao.trovaIdQuiz(quiz_titolo, quizUtente.getQuiz().getCodiceAutore());
		float punteggio = quizUtente.getPunteggio();
		long tempo = quizUtente.getTempoImpiegato();
		boolean superato = quizUtente.isSuperato();
		if(DbConnection.eseguiAggiornamento("INSERT INTO quiz_svolti (codice_utente, quiz_id, punteggio, tempo, superato, quiz_titolo) VALUES ('"+ codice_utente +"', "+ quiz_id +", "+ punteggio +", "+ tempo +", "+ superato +", '"+ quiz_titolo +"')")) {
			check = true;
		}

		return check;
	}

	public static boolean aggiungiRisposteDate(QuizUtente quizUtente) {
		DbConnection.connetti();

		boolean check = false;

		String codice_utente= quizUtente.getUtente().getCodUtente();
		int quiz_id = QuizDao.trovaIdQuiz(quizUtente.getQuiz().getTitolo(), quizUtente.getQuiz().getCodiceAutore());

		HashMap<Domanda, Risposta> mappa = quizUtente.getRisposteDate();

		for (HashMap.Entry<Domanda, Risposta> entry : mappa.entrySet()) {
			Domanda domanda = entry.getKey();
			Risposta risposta = entry.getValue();

			String domanda_testo = domanda.getDomanda();
			int domanda_id = DomandaDao.trovaId(domanda_testo, quiz_id);

			String risposta_testo = "";
			int risposta_id = 0;

			if(risposta != null) {
				risposta_testo = risposta.getRisposta();
				risposta_id = RispostaDao.trovaId(risposta_testo, domanda_id);
			}

			if(DbConnection.eseguiAggiornamento("INSERT INTO risposte_date (risposta_id, codice_utente, quiz_id, risposta_testo, domanda_testo) VALUES ("+ risposta_id +", '"+ codice_utente +"', "+ quiz_id +", '"+ risposta_testo +"', '"+ domanda_testo +"')")) {
				check = true;
			}
		}

		return check;
	}

	public static ArrayList<String[]> trovaQuiz(String codice_utente) {
		DbConnection.connetti();

		ArrayList<String[]> quizSvolti = new ArrayList<String[]>();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM quiz_svolti WHERE codice_utente = '"+ codice_utente +"';");

		for (String[] row : risultatoQuery) {
			String[] array = {row[6], row[3]};
			quizSvolti.add(array);
		}

		return quizSvolti;
	}


	public static ArrayList<Quiz> trovaQuizPassati(String codUtente) {
		ArrayList<Quiz> listaQuizSvolti = new ArrayList<Quiz>();
		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM quiz_svolti WHERE codice_utente = '"+codUtente+"'");
		for(int i=0;i<risultatoQuery.size();i++) {

		}
		return listaQuizSvolti;

	}
}
