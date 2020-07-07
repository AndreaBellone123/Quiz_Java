package quiz;

import interfaccia.FinestraLogin;

public class GestisciQuiz {

	public static void main(String[] args) {

		@SuppressWarnings("unused")
		FinestraLogin login = new FinestraLogin();

		//Utente utente = new Utente("a123", "Mario", "Rossi");

		/*System.out.println("INSERISCI IL TUO CODICE UTENTE");

		Scanner myScanner = new Scanner(System.in);
		String codUtente = myScanner.nextLine();

		if ("123".equals(codUtente)) {

			System.out.println("INSERISCI LA PRIMA DOMANDA");
			Domanda domanda1 = new Domanda(myScanner.nextLine());

			String[] testoRisposta = {"", "", ""};
			int indiceRispostaEsatta;

			System.out.println("INSERISCI LA PRIMA RISPOSTA");
			testoRisposta[0] = myScanner.nextLine();
			System.out.println("INSERISCI LA SECONDA RISPOSTA");
			testoRisposta[1] = myScanner.nextLine();
			System.out.println("INSERISCI LA TERZA RISPOSTA");
			testoRisposta[2] = myScanner.nextLine();

			System.out.println("QUAL'E' LA RISPOSTA ESATTA?");
			indiceRispostaEsatta = Integer.parseInt(myScanner.nextLine());

			for(int i = 0; i < 3; i++) {
				if(i == indiceRispostaEsatta)
					domanda1.addRisposta(new Risposta(testoRisposta[i], true));
				else
					domanda1.addRisposta(new Risposta(testoRisposta[i], false));
			}

			System.out.println("INSERISCI LA SECONDA DOMANDA");
			Domanda domanda2 = new Domanda(myScanner.nextLine());

			System.out.println("INSERISCI LA PRIMA RISPOSTA");
			testoRisposta[0] = myScanner.nextLine();
			System.out.println("INSERISCI LA SECONDA RISPOSTA");
			testoRisposta[1] = myScanner.nextLine();
			System.out.println("INSERISCI LA TERZA RISPOSTA");
			testoRisposta[2] = myScanner.nextLine();

			System.out.println("QUAL'E' LA RISPOSTA ESATTA?");
			indiceRispostaEsatta = Integer.parseInt(myScanner.nextLine());

			for(int i = 0; i < 3; i++) {
				if(i == indiceRispostaEsatta)
					domanda2.addRisposta(new Risposta(testoRisposta[i], true));
				else
					domanda2.addRisposta(new Risposta(testoRisposta[i], false));
			}

			System.out.println("INSERISCI LA TERZA DOMANDA");
			Domanda domanda3 = new Domanda(myScanner.nextLine());

			System.out.println("INSERISCI LA PRIMA RISPOSTA");
			testoRisposta[0] = myScanner.nextLine();
			System.out.println("INSERISCI LA SECONDA RISPOSTA");
			testoRisposta[1] = myScanner.nextLine();
			System.out.println("INSERISCI LA TERZA RISPOSTA");
			testoRisposta[2] = myScanner.nextLine();

			System.out.println("QUAL'E' LA RISPOSTA ESATTA?");
			indiceRispostaEsatta = Integer.parseInt(myScanner.nextLine());

			for(int i = 0; i < 3; i++) {
				if(i == indiceRispostaEsatta)
					domanda3.addRisposta(new Risposta(testoRisposta[i], true));
				else
					domanda3.addRisposta(new Risposta(testoRisposta[i], false));
			}

			Quiz quiz = new Quiz();

			System.out.println("INSERISCI IL TITOLO DEL QUIZ");
			quiz.setTitolo(myScanner.nextLine());

			System.out.println("INSERISCI IL TEMPO MASSIMO DEL QUIZ");
			quiz.setTempo(Integer.parseInt(myScanner.nextLine()));

			quiz.addDomanda(domanda1);
			quiz.addDomanda(domanda2);
			quiz.addDomanda(domanda3);

			System.out.println(quiz);
			System.out.println("QUIZ INSERITO!");
		}

		else if("345".equals(codUtente)) {

			Quiz primo_quiz = new Quiz();

			Domanda domanda1 = new Domanda("Cosa e' il Sole?");

			domanda1.addRisposta(new Risposta("Stella", true));
			domanda1.addRisposta(new Risposta("Pianeta", false));
			domanda1.addRisposta(new Risposta("Satellite", false));

			Domanda domanda2 = new Domanda("Dove si trova Roma?");

			domanda2.addRisposta(new Risposta("Lazio", true));
			domanda2.addRisposta(new Risposta("Puglia", false));
			domanda2.addRisposta(new Risposta("Lombardia", false));

			Domanda domanda3 = new Domanda("Cosa e' il cane?");

			domanda3.addRisposta(new Risposta("Pesce", false));
			domanda3.addRisposta(new Risposta("Uccello", false));
			domanda3.addRisposta(new Risposta("Mammifero", true));

			primo_quiz.addDomanda(domanda1);
			primo_quiz.addDomanda(domanda2);
			primo_quiz.addDomanda(domanda3);

			primo_quiz.setTitolo("PRIMO QUIZ");
			primo_quiz.setTempo(10);

			QuizUtente quizUtente = new QuizUtente(new Utente(codUtente, "Mario", "Rossi"), primo_quiz);

			quizUtente.eseguiQuiz();
			quizUtente.risultatoQuiz();

			if(quizUtente.isSuperato())
				System.out.println("SUPERATO (Punteggio: " + quizUtente.getPunteggio() + " - Tempo Impiegato: " + quizUtente.getTempoImpiegato() + " secondi)");
			else
				System.out.println("NON SUPERATO (Punteggio: " + quizUtente.getPunteggio() + " - Tempo Impiegato: " + quizUtente.getTempoImpiegato() + " secondi)");

			System.out.println("RISPOSTE ESATTE");
			for (Domanda domanda : quizUtente.getQuiz().getDomande())
			{
				for (Risposta risposta : domanda.getRisposte()) {
					if (risposta.isEsatto()) {
						System.out.println("DOMANDA: " + domanda.getDomanda() + "\nRISPOSTA: " + risposta.getRisposta());
						break;
					}
				}
			}

			System.out.println("\n------------\n");

			System.out.println("RISPOSTE DATE");
			for (Domanda domanda : quizUtente.getQuiz().getDomande())
			{
				System.out.println("DOMANDA: " + domanda.getDomanda() + "\nRISPOSTA: " + quizUtente.getRisposteDate().get(domanda));
			}
		}

		else 
			System.out.println("UTENTE SCONOSCIUTO!");

		myScanner.close();*/
	}
}
