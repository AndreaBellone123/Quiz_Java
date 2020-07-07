package quiz;

import java.util.ArrayList;

import dao.QuizSvoltiDao;
import dao.UtenteDao;

public class Utente {
	private String codUtente;
	private String nome;
	private String cognome;
	private boolean admin;

	public Utente(String codUtente, String nome, String cognome, boolean admin) {
		this.codUtente = codUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.admin = admin;
	}

	//metodo che richiama le classi del db e trova i dati usando
	//il codice utente per eseguire una SELECT
	public static Utente getUtente(String codUtente) {
		return UtenteDao.trovaUtente(codUtente);
	}

	public ArrayList<String[]> quizSvolti() {
		return QuizSvoltiDao.trovaQuiz(this.getCodUtente());
	}

	//metodi di default
	public String getCodUtente() {
		return codUtente;
	}
	public void setCodUtente(String codUtente) {
		this.codUtente = codUtente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + ((codUtente == null) ? 0 : codUtente.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Utente other = (Utente) obj;
		if (admin != other.admin)
			return false;
		if (codUtente == null) {
			if (other.codUtente != null)
				return false;
		} else if (!codUtente.equals(other.codUtente))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Codice utente: ").append(codUtente).append(", nome: ").append(nome).append(", cognome: ")
		.append(cognome).append(", admin: ").append(admin);
		return builder.toString();
	}
}
