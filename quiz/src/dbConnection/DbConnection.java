package dbConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DbConnection {

	private static Connection db;
	private static boolean connesso;

	//Apre la connessione con il Database
	public static boolean connetti() {
		connesso = false;

		Properties prop = new Properties();

		FileInputStream ip = null;

		try {
			ip = new FileInputStream("C:\\Users\\Crowley\\Desktop\\Jav\\quiz11-03\\quiz\\src\\dbConnection\\db.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			//Carico il driver JDBC per la connessione con il database MySQL
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Class.forName("com.mysql.jdbc.Driver");
			db = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_quiz", "root", "");
			connesso=true;

		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return connesso;
	}

	//Chiude la connessione con il Database
	public static void disconnetti() {
		try {
			db.close();
			connesso = false;
		} catch (Exception e) { e.printStackTrace(); }
	}

	//Ritorna TRUE se la connessione con il Database e' attiva
	public boolean isConnesso() {
		return connesso;
	}   

	/*Esegue una query di selezione dati sul Database
	query: una stringa che rappresenta un'istruzione SQL di tipo SELECT da eseguire
	colonne: il numero di colonne di cui sara' composta la tupla del risultato
	ritorna un Vector contenente tutte le tuple del risultato*/
	public static ArrayList<String[]> eseguiQuery(String query) {
		ArrayList<String[]> v = null;
		String [] record;
		int colonne = 0;
		try {
			Statement stmt = db.createStatement();     //Creo lo Statement per l'esecuzione della query
			ResultSet rs = stmt.executeQuery(query);   //Ottengo il ResultSet dell'esecuzione della query
			v = new ArrayList<String[]>();
			ResultSetMetaData rsmd = rs.getMetaData();
			colonne = rsmd.getColumnCount();

			while(rs.next()) {   //Creo il vettore risultato scorrendo tutto il ResultSet
				record = new String[colonne];
				for (int i=0; i<colonne; i++) record[i] = rs.getString(i+1);
				v.add( (String[]) record.clone() );
			}
			rs.close();     //Chiudo il ResultSet
			stmt.close();   //Chiudo lo Statement
		} catch (Exception e) { e.printStackTrace(); }

		return v;
	}

	/*Esegue una query di aggiornamento sul Database
	query: una stringa che rappresenta un'istuzione SQL di tipo UPDATE da eseguire
	ritorna TRUE se l'esecuzione e' adata a buon fine, FALSE se c'e' stata un'eccezione*/
	public static boolean eseguiAggiornamento(String query) {
		@SuppressWarnings("unused")
		int numero = 0;
		boolean risultato = false;
		try {
			Statement stmt = db.createStatement();
			numero = stmt.executeUpdate(query);
			risultato = true;
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			risultato = false;
		}
		return risultato;
	}
}
