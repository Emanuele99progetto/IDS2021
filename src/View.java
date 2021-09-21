
import java.util.InputMismatchException;

import java.util.Random;
import java.util.Scanner;

public class View {

	private Utente user;
	private GestoreDB gestoredb = new GestoreDB();
	private Gestorelocker gestorelocker = new Gestorelocker();
	private Gestorespesa gestorespesa = new Gestorespesa();

	/**
	 * 
	 * Metodo di apertura dell'applicativo
	 * 
	 */
	private void hello() {
		System.out.println("*****************************************");
		System.out.println("*        Benvenuto Utente!!!!!!!!!      *");
		System.out.println("*        Servizio di ritiro spesa       *");
		System.out.println("*        nei locker appositi, si prega  *");
		System.out.println("*        di autenticarsi o registrarsi  *");
		System.out.println("*        se non si è già iscritti       *");
		System.out.println("*****************************************");
		System.out.println("*  Registrati o Autenticati!            *");
		System.out.println("*  1) Login                             *");
		System.out.println("*  2) Registrazione                     *");
		System.out.println("*  3) Esci                              *");
		try {
			int i = readConsole();
			if (i == 1)
				login();
			else if (i == 2)
				registration();
			else if (i == 3)

			{
				printGoodbye();
				close();
			}

			else {
				System.out.println("INSERIRE NUMERO CORRETTO");
				hello();

			}
		} catch (InputMismatchException e) {
			System.out.println("ERRORE INSERIMENTO VALORE");
			switcher();
		} catch (IllegalArgumentException e) {
			System.out.println("VALORE INSERITO ERRATO");
			switcher();

		}
	}

	/**
	 * 
	 * Metodo per la scelta del menu principale
	 * 
	 */
	private void switcher() {
		try {
			if (user == null) {
				hello();
			}
			switch (user.getRuolo()) {
			case CLIENTE: {

				WelcomeCliente();

				break;

			}
			case COMMERCIANTE: {
				WelcomeCommerciante();
				break;
			}
			case CORRIERE: {
				WelcomeCorriere();
				break;

			}
			case AMMINISTRATORE: {
				WelcomeAmministratore();
				break;
			}

			}
		} catch (InputMismatchException e) {
			System.out.println("ERRORE INSERIMENTO VALORE");
			switcher();
		} catch (IllegalArgumentException e) {
			System.out.println("VALORE INSERITO ERRATO");
			switcher();
		}

	}

	/**
	 * 
	 * Metodo per la registrazione dell'utente
	 * 
	 */
	private void registration() {
		System.out.println("Inserisci email!");
		String email = readConsoleString();
		System.out.println("Inserisci nome utente!");
		String username = readConsoleString();
		System.out.println("Inserisci password!");
		String password = readConsoleString();
		System.out.println("Inserisci il tuo ruolo");
		if (password.length() > 30 || password.length() < 5) {
			System.out.println("Lunghezza password errata, inserire lunghezza tra 5 e 30");
		} else {
			String role = readConsoleString();
			Ruolo ruolo = Ruolo.valueOf(role);
			gestoredb.Registrazione(password, email, username, ruolo);
		}
		hello();
	}

	/**
	 * 
	 * Metodo per l'autenticazione dell'utente
	 * 
	 */
	private void login() {
		System.out.println("Inserisci email!");
		String email = readConsoleString();
		System.out.println("Inserisci password!");
		String password = readConsoleString();
		user = gestoredb.Autenticazione( password, email);
		if (user != null) {
			gestoredb.ReadDBspesa(gestorespesa, user.getRuolo(), user.getEmail());
			gestoredb.ReadDBlocker(gestorelocker);
			gestoredb.ReadDBspesaCOD(gestorespesa, user.getRuolo(), user.getEmail());
			switch (user.getRuolo()) {
			case CLIENTE: {

				WelcomeCliente();

				break;

			}
			case COMMERCIANTE: {

				WelcomeCommerciante();
				break;
			}
			case CORRIERE: {
				WelcomeCorriere();
				break;

			}
			case AMMINISTRATORE: {
				WelcomeAmministratore();
				break;
			}
			}
		} else {
			hello();
		}

	}

	/**
	 * 
	 * Metodo di chiusura
	 * 
	 */
	private void printGoodbye() {
		System.out.println("\n A più tardi!!!!!!");
	}

	/**
	 * Metodo che legge i valori String da console
	 * 
	 * @return
	 */
	private String readConsoleString() throws InputMismatchException {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		return line;
	}

	/**
	 * Metodo che legge i valori interi da console
	 * 
	 * @return
	 */
	private int readConsole() throws InputMismatchException {
		Scanner in = new Scanner(System.in);
		int line = in.nextInt();
		return line;

	}
	
	
	/**
	 * Metodo che legge i valori interi da console
	 * 
	 * @return
	 */
	private double readConsoleDouble() throws InputMismatchException {
	
		Scanner in = new Scanner(System.in);
		double line = in.nextDouble();
		return line;

	}

	/**
	 * Metodo che genera il codice ritiro della spesa
	 * 
	 * @return
	 */
	private int generateCod() {
		Random rn = new Random();
		return rn.nextInt(999999999) + 1;
	}

	/**
	 * Metodo che mostra le varie operazioni possibili al cliente
	 * 
	 */
	private void WelcomeCliente() {
		System.out.println("*******************************");
		System.out.println("*          BENVENUTO          *");
		System.out.println("*        COSA VUOI FARE?      *");
		System.out.println("*       Clicca il numero      *");
		System.out.println("*   corrispondente all'azione *");
		System.out.println("*         desiderata!         *");
		System.out.println("*-----------------------------*");
		System.out.println("*  1) Spese effettuate        *");
		System.out.println("*  2) Spese da consegnare     *");
		System.out.println("*  3) Spese da ritirare       *");
		System.out.println("*  4) Info locker di una spesa*");
		System.out.println("*  5) Spesa ritirata          *");
		System.out.println("*  6) Cambia password         *");
		System.out.println("*  7) Logout                  *");
		System.out.println("*******************************");

		switch (readConsole()) {

		case 1: {
			System.out.println("SPESE EFFETTUATE: \n");
			gestorespesa.showSpese();
			WelcomeCliente();
			break;
		}
		case 2: {
			System.out.println("SPESE NON CONSEGNATE: \n");
			gestorespesa.showSpesenonconsegnate();
			WelcomeCliente();
			break;
		}

		case 3: {
			System.out.println("SPESE NON RITIRATE: \n");
			gestorespesa.showSpesenonritirate();
			WelcomeCliente();
			break;
		}
		case 4: {
			System.out.println("INSERIRE CODICE SPESA DESIDERATO");
			int codspesa = readConsole();
			int codloc = gestorespesa.LockerCod(codspesa);
			if (codloc <= 0) {
				System.out.println("Spesa non esistente o locker non ancora assegnato");
			} else {
				gestorelocker.InfoLocker(codloc);
			}
			WelcomeCliente();
			break;
		}
		case 5: {

			System.out.println("INSERIRE CODICE SPESA RITIRATA");
			int codicespesa = readConsole();
			System.out.println("INSERIRE CODICE RITIRO DELLA SPESA RITIRATA");
			int codiceritiro = readConsole();

			int codicelocker = gestorespesa.LockerCod(codicespesa);
			if (codicelocker <= 0) {
				System.out.println("Spesa non esistente o locker non ancora assegnato");
			}

			else if (gestorespesa.SpesaIsInTheLocker(codicespesa) == false) {
				System.out.println("Spesa non presente nel locker");
			} else if (gestorespesa.RitiroMerce(codicespesa, codiceritiro) == false) {
				System.out.println("Ritiro merce non andato a buon fine, controllare il codice ritiro inserito!");
			} else {
				gestorelocker.SpesaOut(codicelocker);
				gestoredb.UpdateDBritiro(codicespesa);
				gestoredb.UpdateDBOutlocker(codicelocker);
			}

			WelcomeCliente();
			break;
		}

		case 6: {
			System.out.println("INSERIRE PASSWORD ATTUALE");
			String oldpassword = readConsoleString();
			System.out.println("INSERIRE PASSWORD NUOVA");
			String newpassword = readConsoleString();
			if (user.setPassword(oldpassword, newpassword)) {
				gestoredb.changePassDB(newpassword, user.getEmail());
				System.out.println("Password modificata con successo");
			}
			WelcomeCliente();
			break;
		}
		case 7: {
			gestorespesa.removeAll();
			gestorelocker.removeAll();
			Logout();
			hello();
			break;
		}
		default: {
			System.out.println("INSERIRE NUMERO CORRETTO!");
			WelcomeCliente();
		}
		}

	}

	/**
	 * Metodo che mostra le varie operazioni possibili al commerciante
	 * 
	 */
	private void WelcomeCommerciante() {
		System.out.println("*******************************");
		System.out.println("*          BENVENUTO          *");
		System.out.println("*        COSA VUOI FARE?      *");
		System.out.println("*       Clicca il numero      *");
		System.out.println("*   corrispondente all'azione *");
		System.out.println("*         desiderata!         *");
		System.out.println("*-----------------------------*");
		System.out.println("*  1) Aggiungere nuova spesa  *");
		System.out.println("*  2) Modif/Aggiungi corriere *");
		System.out.println("*  3) Spese senza corriere    *");
		System.out.println("*  4) Modifica password       *");
		System.out.println("*  5) Logout                  *");
		System.out.println("*******************************");
		switch (readConsole()) {

		case 1: {
			System.out.println("Inserisci codice spesa");
			int codspesa = readConsole();
			System.out.println("Inserisci email cliente!");
			String codicecliente = readConsoleString();
			System.out.println("Inserisci descrizione");
			String desc = readConsoleString();
			System.out.println("Inserisci importo");
			double importo = readConsoleDouble();
			if (gestorespesa.SpesaIsIn(codspesa)) {
				System.out.println("SPESA GIA ESISTENTE!!!!!!!!!!");
			} else {

				// IF cliente presente nel db allora modifico spesa e database
				if (gestoredb.isClienteDB(codicecliente)) {
					int codritiro = generateCod();
					gestoredb.UpdateNewSpesa(codritiro, codspesa, codicecliente, user.getEmail(), desc, importo);
					Spesa spesa = new Spesa(codritiro, codspesa, importo, desc, codicecliente, user.getEmail());
					gestorespesa.addSpesa(spesa);
					gestorespesa.addcod(codspesa);
				}

			}
			WelcomeCommerciante();
			break;
		}
		case 2: {

			System.out.println("Inserisci email corriere!");
			String codicecorriere = readConsoleString();
			System.out.println("Inserisci codice spesa");
			int codspesa = readConsole();
			if (gestorespesa.SpesaIsInList(codspesa) != true) {
				System.out.println("SPESA NON PRESENTE!!!!!!!!!!");
			} else {
				if (gestoredb.isCorriereDB(codicecorriere)) {
					gestorespesa.ChiamataCorriere(codspesa, codicecorriere);
					gestoredb.UpdateDBcorriere(codspesa, codicecorriere);

				}
			}
			WelcomeCommerciante();

			break;
		}

		case 3: {
			System.out.println("SPESE SENZA CORRIERE ASSEGNATO: \n");
			gestorespesa.showSpeseSenzaCorriere();
			WelcomeCommerciante();

			break;
		}
		case 4: {
			System.out.println("INSERIRE PASSWORD ATTUALE");
			String oldpassword = readConsoleString();
			System.out.println("INSERIRE PASSWORD NUOVA");
			String newpassword = readConsoleString();
			if (user.setPassword(oldpassword, newpassword)) {
				gestoredb.changePassDB(newpassword, user.getEmail());
				System.out.println("Password modificata con successo");
			}
			WelcomeCommerciante();
			break ;
		}
		case 5: {
			gestorespesa.removeAll();
			gestorelocker.removeAll();
			Logout();

			hello();
			break;
		}
		default: {
			System.out.println("INSERIRE NUMERO CORRETTO!");
			WelcomeCommerciante();
		}
		}
	}

	/**
	 * Metodo che mostra le varie operazioni possibili al corriere
	 * 
	 */
	private void WelcomeCorriere() {
		System.out.println("*******************************");
		System.out.println("*          BENVENUTO          *");
		System.out.println("*        COSA VUOI FARE?      *");
		System.out.println("*       Clicca il numero      *");
		System.out.println("*   corrispondente all'azione *");
		System.out.println("*         desiderata!         *");
		System.out.println("*-----------------------------*");
		System.out.println("*  1) Visualizza Lockers      *");
		System.out.println("*  2) Consegna merce          *");
		System.out.println("*  3) Modif/Aggiungi locker   *");
		System.out.println("*  4) Spese da consegnare     *");
		System.out.println("*  5) Modifica password       *");
		System.out.println("*  6) Logout                  *");
		System.out.println("*******************************");
		switch (readConsole()) {
		case 1: {

			gestorelocker.showLockers();
			WelcomeCorriere();
			break;
		}

		case 2: {
			System.out.println("INSERIRE CODICE SPESA CONSEGNATA");
			int codicespesa = readConsole();
			if (gestorespesa.SpesaIsInList(codicespesa) != true) {
				System.out.println("SPESA NON PRESENTE!!!!!!!!!!");
			} else if (gestorespesa.isNullLocker(codicespesa) == true) {
				System.out.println("LOCKER NON ASSEGNATO!!!");
			}

			else if (gestorespesa.isSpesaConsegnata(codicespesa) == true) {
				System.out.println("SPESA GIA CONSEGNATA!!!!");
			} else {

				gestorespesa.ConsegnaMerce(codicespesa);
				gestoredb.UpdateDBconsegna(codicespesa);

			}
			WelcomeCorriere();
			break;
		}
		case 3: {

			System.out.println("Inserisci codice locker!");
			int codicelocker = readConsole();
			System.out.println("Inserisci codice spesa");
			int codicespesa = readConsole();
			if (gestorespesa.SpesaIsInList(codicespesa) != true) {
				System.out.println("SPESA NON PRESENTE!!!!!!!!!!");
			} else if (gestorelocker.isLocker(codicelocker) != true) {
				System.out.println("LOCKER NON PRESENTE!!!!!!!!!!");
			} else if (gestorespesa.isSpesaConsegnata(codicespesa)) {
				System.out.println("SPESA GIA CONSEGNATA!!!!");
			} else {
				if (gestorelocker.SpesaIn(codicelocker)) {
					if (gestorespesa.LockerCod(codicespesa) == 0) {
						gestorespesa.LockerScelto(codicespesa, codicelocker);
						gestoredb.UpdateDBlocker(codicespesa, codicelocker);
						gestoredb.UpdateDBInlocker(codicelocker);
					}

					else { // allora vuol dire che modifica il locker GIà ASSEGNATO
                        int codlockernow=gestorespesa.LockerCod(codicespesa);
						gestorelocker.SpesaOut(codlockernow);
						gestorespesa.LockerScelto(codicespesa, codicelocker);
						gestoredb.UpdateDBlocker(codicespesa, codicelocker);
						gestoredb.UpdateDBInlocker(codicelocker);
						gestoredb.UpdateDBOutlocker(codlockernow);

					}
				}
			}

			WelcomeCorriere();

			break;
		}

		case 4: {
			System.out.println("SPESE NON CONSEGNATE: \n");
			gestorespesa.showSpesenonconsegnateWithout();
			WelcomeCorriere();

			break;
		}
		case 5: {
			System.out.println("INSERIRE PASSWORD ATTUALE");
			String oldpassword = readConsoleString();
			System.out.println("INSERIRE PASSWORD NUOVA");
			String newpassword = readConsoleString();
			if (user.setPassword(oldpassword, newpassword)) {
				gestoredb.changePassDB(newpassword, user.getEmail());
				System.out.println("Password modificata con successo");
			}
			WelcomeCorriere();
			break; 
		}
		case 6: {
			gestorespesa.removeAll();
			gestorelocker.removeAll();
			Logout();

			hello();
			break;
		}
		default: {
			System.out.println("INSERIRE NUMERO CORRETTO!");
			WelcomeCorriere();
		}

		}
	}

	/**
	 * Metodo che mostra le varie operazioni possibili all'amministratore
	 * 
	 */
	private void WelcomeAmministratore() {
		System.out.println("*******************************");
		System.out.println("*          BENVENUTO          *");
		System.out.println("*        COSA VUOI FARE?      *");
		System.out.println("*       Clicca il numero      *");
		System.out.println("*   corrispondente all'azione *");
		System.out.println("*         desiderata!         *");
		System.out.println("*-----------------------------*");
		System.out.println("*  1) Aggiunge locker         *");
		System.out.println("*  2) Info Lockers            *");
		System.out.println("*  3) Rimuovi locker          *");
		System.out.println("*  4) Modifica password       *");
		System.out.println("*  5) Logout                  *");
		System.out.println("*******************************");
		switch (readConsole()) {
		case 1: {
			System.out.println("Inserisci codice locker");
			int codloc = readConsole();
			System.out.println("Inserisci citta");
			String citta = readConsoleString();
			System.out.println("Inserisci indirizzo");
			String via = readConsoleString();
			System.out.println("Inserisci numero spese consigliate");
			int nspeseconsigliate = readConsole();
			if (gestorelocker.isLocker(codloc)) {
				System.out.println("Locker già esistente");
			} else {
				Locker locker = new Locker(codloc, citta, via, nspeseconsigliate);
				gestorelocker.addLocker(locker);
				gestoredb.UpdateNewLocker(codloc, via, citta, nspeseconsigliate);
			}
			WelcomeAmministratore();

			break;
		}
		case 2: {
			gestorelocker.showLockers();
			WelcomeAmministratore();
			break;
		}
		case 3: {
			System.out.println("Inserisci codice locker");
			int codloc = readConsole();
			if (gestorelocker.getLockerAvailable(codloc) == 0) {
				gestorelocker.removeLocker(codloc);
				gestoredb.removeLockerDB(codloc);

			} else {
				System.out.println("Locker non presente o non è ancora vuoto");
			}
			WelcomeAmministratore();
			break;
		}
		case 4: {
			System.out.println("INSERIRE PASSWORD ATTUALE");
			String oldpassword = readConsoleString();
			System.out.println("INSERIRE PASSWORD NUOVA");
			String newpassword = readConsoleString();
			if (user.setPassword(oldpassword, newpassword)) {
				gestoredb.changePassDB(newpassword, user.getEmail());
				System.out.println("Password modificata con successo");
			}
			WelcomeAmministratore();
			break;
		}
		case 5: {
			gestorespesa.removeAll();
			gestorelocker.removeAll();
			Logout();

			hello();
			break;
		}
		default: {
			System.out.println("INSERIRE NUMERO CORRETTO!");
			WelcomeAmministratore();
		}

		}

	}

	/**
	 * Metodo per chiusura applicativo
	 * 
	 */
	public void close() {
		System.exit(0);
	}

	public void open() {
		hello();
		printGoodbye();
	}

	/**
	 * Metodo per il Logout
	 * 
	 */
	private void Logout() {
		user = null;
	}
}
