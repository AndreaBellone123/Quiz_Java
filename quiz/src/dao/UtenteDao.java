package dao;

import java.util.ArrayList;

import dbConnection.DbConnection;
import quiz.Utente;

public class UtenteDao {

	//Restituisce l'elenco completo di utenti
	public static ArrayList<Utente> trovaTutti() {
		ArrayList<Utente> listaUtenti = new ArrayList<Utente>();

		DbConnection.connetti();

		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM utente");

		for (String[] row : risultatoQuery) {
			boolean admin = false;

			if (Integer.parseInt(row[4]) == 1) {
				admin = true;
			}

			listaUtenti.add(new Utente(row[1], row[2], row[3], admin));
		}

		return listaUtenti;
	}

	//Restituisce un utente in base al codice cercato
	public static Utente trovaUtente(String codUtente) {

		DbConnection.connetti();
		ArrayList<String[]> risultatoQuery = DbConnection.eseguiQuery("SELECT * FROM utente WHERE codice_utente='"+ codUtente +"'");

		if (risultatoQuery.size() == 1) {
			String row[] = risultatoQuery.get(0);

			boolean admin = false;

			if (Integer.parseInt(row[4]) == 1) {
				admin = true;
			}

			return new Utente(row[1], row[2], row[3], admin);
		}

		return null;
	}
	
	//Controlla se un utente è admin oppure no
	public static boolean isAdmin(String codUtente) {
		if (UtenteDao.trovaUtente(codUtente).isAdmin()) {
			return true;
		}
		return false;
	}
}
