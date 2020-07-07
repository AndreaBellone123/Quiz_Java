package dao;

import java.util.ArrayList;

import dbConnection.DbConnection;
import quiz.Risposta;

public class RispostaDao {

	//Restituisce una risposta dal testo
	public static Risposta trovaRisposta(String testo, int domanda_id) {
		DbConnection.connetti();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM risposta WHERE testo = '"+ testo +"' AND domanda_id = '"+ domanda_id + "'");

		if (risultatoQuery.size() == 1) {
			String row[] = risultatoQuery.get(0);

			boolean esatto = false;

			if (Integer.parseInt(row[3]) == 1) {
				esatto = true;
			}

			return new Risposta(testo, esatto);
		}

		return null;
	}

	//Restituisce l'id di una risposta
	public static int trovaId(String testo, int domanda_id) {
		DbConnection.connetti();

		int id = 0;

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM risposta WHERE testo = '"+ testo +"' AND domanda_id = '"+ domanda_id + "'");

		if (risultatoQuery.size() == 1) {

			id = Integer.parseInt(risultatoQuery.get(0)[0]);

		}

		return id;
	}

	//Restituisce tutte le risposte di una certa domanda
	public static ArrayList<Risposta> trovaDaDomanda(int domanda_id) {
		ArrayList<Risposta> listaRisposte = new ArrayList<Risposta>();

		DbConnection.connetti();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM risposta WHERE domanda_id = '"+ domanda_id +"'");

		for (String[] row : risultatoQuery) {
			boolean esatto = false;

			if (Integer.parseInt(row[3]) == 1) {
				esatto = true;
			}

			listaRisposte.add(new Risposta(row[1], esatto));
		}

		return listaRisposte;
	}

	//Controlla se una risposta è corretta o meno
	public static boolean isEsatto(String testo, int domanda_id) {
		if (RispostaDao.trovaRisposta(testo, domanda_id).isEsatto()) {
			return true;
		}
		return false;
	}


	//Inserisce una nuova risposta
	public static boolean inserisciRisposta(Risposta risposta, int domanda_id) {
		DbConnection.connetti();

		boolean check = false;
		
		int esatto = 0;
		
		if (risposta.isEsatto()) {
			esatto = 1;
		}

		if (RispostaDao.trovaRisposta(risposta.getRisposta(), domanda_id) == null) {
			check = DbConnection.eseguiAggiornamento("INSERT INTO risposta (testo, domanda_id, corretto) VALUES("
					+ " '"+ risposta.getRisposta() +"', '"+ domanda_id +"', '"+ esatto +"') ");
		}

		return check;
	}

	//Cancella una risposta
	public static boolean cancellaRisposta(Risposta risposta, int domanda_id) {
		DbConnection.connetti();

		boolean check = DbConnection.eseguiAggiornamento("DELETE FROM risposta WHERE testo = '"+ risposta.getRisposta() +"'"
				+ " AND domanda_id = '"+ domanda_id +"'");

		return check;
	}

	//Aggiorna una risposta
	public static boolean aggiornaRisposta(Risposta risposta, int domanda_id, String nuovoTesto, boolean nuovoCorretto) {
		DbConnection.connetti();

		boolean check = DbConnection.eseguiAggiornamento("UPDATE risposta SET testo = '"+ nuovoTesto +"', corretto = '"+ nuovoCorretto +"' WHERE testo = '"+ risposta.getRisposta() +"'"
				+ " AND domanda_id = '"+ domanda_id +"'");

		return check;
	}
}
