package quiz;

import java.util.ArrayList;
import java.util.List;

import dao.DomandaDao;

public class Domanda {
	private String domanda;
	private List<Risposta> risposte;

	public Domanda(String domanda) {
		this.domanda = domanda;
		this.risposte = new ArrayList<Risposta>();
	}

	public int addRisposta(Risposta r) {
		int conteggioFalse = 0;

		for (Risposta rispostaEsistente : this.getRisposte()) {
			if (rispostaEsistente.getRisposta().equals(r.getRisposta()))
				return 1;

			if (rispostaEsistente.isEsatto() && r.isEsatto())
				return 2;

			if (!rispostaEsistente.isEsatto())
				conteggioFalse++;
		}

		if (conteggioFalse == 2 && !r.isEsatto())
			return 3;

		this.getRisposte().add(r);

		//int domanda_id = DomandaDao.trovaId(this.getDomanda(), quiz_id);

		//r.addRisposta(r, domanda_id);

		return 0;
	}

	public int removeRisposta(Risposta r) {
		for (Risposta rispostaEsistente : this.getRisposte()) {
			if (rispostaEsistente.equals(r)) {
				this.getRisposte().remove(this.getRisposte().indexOf(rispostaEsistente));

				//int domanda_id = DomandaDao.trovaId(this.getDomanda(), quiz_id);

				//r.deleteRisposta(r, domanda_id);

				return 0;
			}
		}

		return 1;
	}
	public int trovaId(String testo,int quiz_id) {
		return DomandaDao.trovaId(testo, quiz_id);
	}
	//metodi che modificano o cancellano le domande dal db
	public boolean updateDomanda(Domanda domanda, int quiz_id, String nuovoTesto) {
		return DomandaDao.aggiornaDomanda(domanda, quiz_id, nuovoTesto);
	}

	public boolean deleteDomanda(Domanda domanda, int quiz_id) {
		return DomandaDao.cancellaDomanda(domanda, quiz_id);
	}

	public boolean addDomanda(Domanda domanda, int quiz_id) {
		return DomandaDao.inserisciDomanda(domanda, quiz_id);
	}

	//metodi di default
	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public List<Risposta> getRisposte() {
		return risposte;
	}

	public void setRisposte(List<Risposta> risposte) {
		this.risposte = risposte;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(domanda);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domanda == null) ? 0 : domanda.hashCode());
		result = prime * result + ((risposte == null) ? 0 : risposte.hashCode());
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
		Domanda other = (Domanda) obj;
		if (domanda == null) {
			if (other.domanda != null)
				return false;
		} else if (!domanda.equals(other.domanda))
			return false;
		if (risposte == null) {
			if (other.risposte != null)
				return false;
		} else if (!risposte.equals(other.risposte))
			return false;
		return true;
	}	
}
