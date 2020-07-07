package dao;

import java.util.ArrayList;

import dbConnection.DbConnection;
import quiz.Domanda;
import quiz.Quiz;

public class QuizDao {

	//Restituisce un quiz dal titolo
	public static Quiz trovaQuiz(String titolo, String codice_autore) {
		DbConnection.connetti();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM quiz WHERE titolo = '"+ titolo +"' AND codice_autore = '"+ codice_autore +"'");

		if (risultatoQuery.size() == 1) {

			Quiz quiz = new Quiz();

			quiz.setTitolo(risultatoQuery.get(0)[1]);
			quiz.setTempo(Integer.parseInt(risultatoQuery.get(0)[2]));
			quiz.setCodiceAutore(codice_autore);

			ArrayList<Domanda> listaDomande = DomandaDao.trovaDaQuiz(Integer.parseInt(risultatoQuery.get(0)[0]));

			for (Domanda domanda : listaDomande) {
				quiz.addDomanda(domanda);
			}

			return quiz;
		}

		return null;
	}

	public static Quiz trovaQuiz(int id) {
		DbConnection.connetti();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM quiz WHERE id = "+ id +"");

		if (risultatoQuery.size() == 1) {

			Quiz quiz = new Quiz();

			quiz.setTitolo(risultatoQuery.get(0)[1]);
			quiz.setTempo(Integer.parseInt(risultatoQuery.get(0)[2]));
			quiz.setCodiceAutore(risultatoQuery.get(0)[3]);

			ArrayList<Domanda> listaDomande = DomandaDao.trovaDaQuiz(Integer.parseInt(risultatoQuery.get(0)[0]));

			for (Domanda domanda : listaDomande) {
				quiz.addDomanda(domanda);
			}

			return quiz;
		}

		return null;
	}

	//Restituisce l'id del quiz
	public static int trovaIdQuiz(String titolo, String codice_autore) {
		DbConnection.connetti();

		int id = 0;

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM quiz WHERE titolo = '"+ titolo +"' AND codice_autore = '"+ codice_autore +"';");

		id = Integer.parseInt(risultatoQuery.get(0)[0]);

		return id;
	}

	//Restituisce tutti i quiz creati da uno stesso autore
	public static ArrayList<Quiz> trovaDaAutore(String codice_autore) {
		DbConnection.connetti();

		ArrayList<Quiz> listaQuiz = new ArrayList<Quiz>();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM quiz WHERE codice_autore = '"+ codice_autore +"'");

		for (String[] row : risultatoQuery) {
			Quiz quiz = QuizDao.trovaQuiz(row[1], row[3]);

			listaQuiz.add(quiz);
		}

		return listaQuiz;
	}

	public static ArrayList<Quiz> trovaQuizDisponibili(String codice_utente) {
		DbConnection.connetti();

		ArrayList<Quiz> listaQuiz = new ArrayList<Quiz>();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM quiz WHERE quiz.id NOT IN (SELECT quiz.id FROM quiz JOIN (SELECT * FROM quiz_svolti WHERE codice_utente = '" + codice_utente +"') AS quiz_non_disponibili ON quiz.id = quiz_non_disponibili.quiz_id);");

		for (String[] row : risultatoQuery) {
			Quiz quiz = QuizDao.trovaQuiz(row[1], row[3]);

			listaQuiz.add(quiz);
		}

		return listaQuiz;
	}

	//Inserisce un nuovo quiz
	public static boolean inserisciQuiz(Quiz quiz) {
		DbConnection.connetti();

		boolean check = false;

		if (QuizDao.trovaQuiz(quiz.getTitolo(), quiz.getCodiceAutore()) == null) {
			check = DbConnection.eseguiAggiornamento("INSERT INTO quiz (titolo, tempo, codice_autore) VALUES("
					+ " '"+ quiz.getTitolo() +"', '"+ quiz.getTempo() +"', '"+ quiz.getCodiceAutore() +"') ");
		}

		return check;
	}

	//Cancella un quiz
	public static boolean cancellaQuiz(Quiz quiz) {
		DbConnection.connetti();

		int quiz_id = QuizDao.trovaIdQuiz(quiz.getTitolo(), quiz.getCodiceAutore());

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM domanda WHERE quiz_id = "+ quiz_id +";");

		for (String[] row : risultatoQuery) {
			DbConnection.eseguiAggiornamento("DELETE FROM risposta WHERE domanda_id = "+ row[0] +";");
		}

		DbConnection.eseguiAggiornamento("DELETE FROM domanda WHERE quiz_id = "+ quiz_id +";");

		boolean check = DbConnection.eseguiAggiornamento("DELETE FROM quiz WHERE titolo = '"+ quiz.getTitolo() +"' AND codice_autore = '"+ quiz.getCodiceAutore() +"' ");

		return check;
	}
}
