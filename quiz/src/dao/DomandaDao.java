package dao;

import java.util.ArrayList;

import dbConnection.DbConnection;
import quiz.Domanda;
import quiz.Risposta;

public class DomandaDao {

	//Restituisce una domanda dal testo
	public static Domanda trovaDomanda(String testo, int quiz_id) {
		DbConnection.connetti();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM domanda WHERE testo = '"+ testo +"' AND quiz_id = '"+ quiz_id + "'");

		if (risultatoQuery.size() == 1) {

			Domanda domanda = new Domanda(testo);

			ArrayList<Risposta> listaRisposte = RispostaDao.trovaDaDomanda(Integer.parseInt(risultatoQuery.get(0)[0]));

			for (Risposta risposta : listaRisposte) {
				domanda.addRisposta(risposta);
			}

			return domanda;
		}

		return null;
	}

	//Restituisce l'id di una domanda
	public static int trovaId(String testo, int quiz_id) {
		DbConnection.connetti();

		int id = 0;

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM domanda WHERE testo = '"+ testo +"' AND quiz_id = '"+ quiz_id +"'");

		if (risultatoQuery.size() == 1) {

			id = Integer.parseInt(risultatoQuery.get(0)[0]);

		}

		return id;
	}

	//Restituisce tutte le risposte di una certa domanda
	public static ArrayList<Risposta> trovaRisposte(String testo, int quiz_id) {
		return (ArrayList<Risposta>) DomandaDao.trovaDomanda(testo, quiz_id).getRisposte();
	}

	//Restituisce tutte domande di un certo quiz
	public static ArrayList<Domanda> trovaDaQuiz(int quiz_id) {
		ArrayList<Domanda> listaDomande = new ArrayList<Domanda>();

		DbConnection.connetti();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM domanda WHERE quiz_id = '"+ quiz_id +"'");

		for (String[] row : risultatoQuery) {
			Domanda domanda = new Domanda(row[1]);

			ArrayList<Risposta> listaRisposte = RispostaDao.trovaDaDomanda(Integer.parseInt(risultatoQuery.get(risultatoQuery.indexOf(row))[0]));

			for (Risposta risposta : listaRisposte) {
				domanda.addRisposta(risposta);
			}

			listaDomande.add(domanda);
		}

		return listaDomande;
	}


	//Inserisce una nuova domanda
	public static boolean inserisciDomanda(Domanda domanda, int quiz_id) {
		DbConnection.connetti();

		boolean check = false;

		if (DomandaDao.trovaDomanda(domanda.getDomanda(), quiz_id) == null) {
			check = DbConnection.eseguiAggiornamento("INSERT INTO domanda (testo, quiz_id) VALUES("
					+ " '"+ domanda.getDomanda() +"', '"+ quiz_id +"') ");
		}

		return check;
	}

	//Cancella una domanda
	public static boolean cancellaDomanda(Domanda domanda, int quiz_id) {
		DbConnection.connetti();

		boolean check = DbConnection.eseguiAggiornamento("DELETE FROM domanda WHERE testo = '"+ domanda.getDomanda() +"'"
				+ " AND domanda_id = '"+ quiz_id +"'");

		return check;
	}

	//Aggiorna una domanda
	public static boolean aggiornaDomanda(Domanda domanda, int quiz_id, String nuovoTesto) {
		DbConnection.connetti();

		boolean check = DbConnection.eseguiAggiornamento("UPDATE domanda SET testo = '"+ nuovoTesto +"' WHERE testo = '"+ domanda.getDomanda() +"'"
				+ " AND domanda_id = '"+ quiz_id +"'");

		return check;
	}
}
