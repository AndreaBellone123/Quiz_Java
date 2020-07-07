package quiz;

import dao.RispostaDao;

public class Risposta {
	private String risposta;
	private boolean esatto;
	
	public Risposta(String risposta, boolean esatto) {
		this.risposta = risposta;
		this.esatto = esatto;
	}
	
	//metodi che modificano o cancellano le risposte dal db
	public boolean updateRisposta(Risposta risposta, int domanda_id, String nuovoTesto, boolean nuovoCorretto) {
		return RispostaDao.aggiornaRisposta(risposta, domanda_id, nuovoTesto, nuovoCorretto);
	}
	
	public boolean addRisposta(Risposta risposta, int domanda_id) {
		return RispostaDao.inserisciRisposta(risposta, domanda_id);
	}
	
	public boolean deleteRisposta(Risposta risposta, int domanda_id) {
		return RispostaDao.cancellaRisposta(risposta, domanda_id);
	}
	
	//metodi di default
	public String getRisposta() {
		return risposta;
	}
	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	public boolean isEsatto() {
		return esatto;
	}
	public void setEsatto(boolean esatto) {
		this.esatto = esatto;
	}
	@Override
	public String toString() {
		return this.getRisposta();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (esatto ? 1231 : 1237);
		result = prime * result + ((risposta == null) ? 0 : risposta.hashCode());
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
		Risposta other = (Risposta) obj;
		if (esatto != other.esatto)
			return false;
		if (risposta == null) {
			if (other.risposta != null)
				return false;
		} else if (!risposta.equals(other.risposta))
			return false;
		return true;
	}
}
