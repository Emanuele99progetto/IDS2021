import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Gestorespesa {

	private Set<Spesa> spese = new HashSet<Spesa>();
	private Set<Integer> codici = new HashSet<Integer>();


	
	/**
	 * Metodo aggiungere i codici alla lista
	 * @return 
	 */
	public boolean addcod(int codice) {
		if (codici.add(codice)) {
			System.out.println("La spesa di codice" + codice + " è stata inserita!");
			return true;
		} else {
			System.out.println("La spesa di codice " + codice + " è già presente del DB!");
			return false;
		}
	}

	
	/**
	 * Metodo per rimozione codici dalla lista
	 * 
	 */
	public void removecod(int codice) {

		for (Iterator<Integer> iter = this.codici.iterator(); iter.hasNext();) {
			int cod = iter.next();
			if (cod == codice) {
				iter.remove();
				System.out.println("Il codice " + codice + " è stato rimosso!");
				return;
			}

		}
		System.out.println("Il codice cercato non è presente!");
	}

	
	/**
	 * Metodo per rimozione spesa dalla lista
	 * 
	 */
	public void removeSpesa(int codice) {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codice) {
				iter.remove();
				System.out.println("La spesa " + spesa.toString() + " è stata rimossa!");
				return;
			}

		}
		System.out.println("La spesa richiesta non è presente!");
	}



	
	/**
	 * Metodo per aggiungere spese alla lista
	 * @return
	 */
	public boolean addSpesa(Spesa spesa) {
		if (spese.add(spesa)) {
			System.out.println("La spesa " + spesa.toString() + " è stata inserita!");
			return true;
		} else {
			System.out.println("La spesa " + spesa.toString() + " è già presente del DB!");
			return false;
		}
	}

	

	
	/**
	 * Metodo per la consegna della spesa 
	 * 
	 */
	public void ConsegnaMerce(int codice) {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codice) {
				spesa.setStatusconsegna(true);
				System.out.println("La spesa è stata consegnata con successo!");
			}

		}

	}

	
	/**
	 * Metodo per il ritiro della merce 
	 * @return
	 */
	public boolean RitiroMerce(int codicespesa,int codiceritiro) {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codicespesa && spesa.getCodice_ritiro()==codiceritiro) {
				spesa.setStatusritiro(true);
				return true;

			}

		}
		return false;

	}

	
	
	/**
	 * Metodo per verificare se la spesa è dentro ad un locker
	 * @return
	 */
	public boolean SpesaIsInTheLocker(int codicespesa) {
		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codicespesa && spesa.isStatusritiro() == false
					&& spesa.isStatusconsegna() == true) {
				System.out.println("La spesa è presente nel locker!");
				return true;
			}

		}

		System.out.println("La spesa non è presente nel locker!");
		return false;
	}


	
	/**
	 * Metodo per assegnare un corriere ad una spesa
	 * 
	 */
	public void ChiamataCorriere(int codice, String codicecor) {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codice) {
				spesa.setCodice_corriere(codicecor);
				System.out.println("Corriere scelto!");
				return;
			}

		}
	}

	
	
	/**
	 * Metodo per verificare se la spesa è consegnata
	 * @return
	 */
	public boolean isSpesaConsegnata(int codice)
	{	for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
		Spesa spesa = iter.next();
		if (spesa.getCodice() == codice) {
			if(spesa.isStatusconsegna()==true)
			return true;
		}

	}
	return false;}
	
	
	
	
	/**
	 * Metodo per verificare se la spesa è presente
	 * @return 
	 */
	public boolean isSpesaNotNull(int codice) {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codice) {
				return true;
			}

		}
		return false;
	}


	
	/**
	 * Metodo per verificare se il lockker è assegnato alla spesa
	 * @return 
	 */
	public boolean isNullLocker(int codicespesa) {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codicespesa) {
				if (spesa.getCodice_locker() == 0) {
				
					return true;
				}

				else
					return false;
			}

		}
		return true;

	}

	
	
	/**
	 * Metodo per verficare se la spesa ha un corriere assegnato
	 * @return 
	 */
	public boolean isNullCorriere(int codice) {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codice) {
				if (spesa.getCodice_commerciante() == "") {
					System.out.println("Corriere non assegnato");
					return true;
				} else
					return false;
			}

		}
		System.out.print("Spesa cercata non è presente");
		return true;

	}


	
	
	/**
	 * Metodo per visualizzare le spese 
	 * 
	 */
	public void showSpese() {
		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			spesa.printer();
		}
	}

	
	/**
	 * Metodo per visualizzare le spese non ritirate
	 * 
	 */
	public void showSpesenonritirate() {
		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.isStatusritiro() == false) {
				spesa.printer();
			}

		}
	}

	/**
	 * Metodo per visualizzare le spese non consegnate
	 * 
	 */
	public void showSpesenonconsegnate() {
		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.isStatusconsegna() == false) {
				spesa.printer();
			}

		}
	}
	
	

	
	
	/**
	 * Metodo per visualizzare le spese non consegnate
	 * 
	 */
	public void showSpesenonconsegnateWithout() {
		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.isStatusconsegna() == false) {
				spesa.printerWithout();
			}

		}
	}
	
	
	

	/**
	 * Metodo per assegnare un locker ad una spesa
	 * 
	 */
	public void LockerScelto(int codice, int codicelock) {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codice) {
				spesa.setCodice_locker(codicelock);
				System.out.println("Locker scelto!");
				
			}

		}
		
	}

	/**
	 * Metodo per ottenere il codice locker di una spesa
	 * @return 
	 */
	public int LockerCod(int codicespesa) {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codicespesa) {
				return spesa.getCodice_locker();
			}

		}
		System.out.println("La spesa richiesta non è presente!");
		return -1;
	}

	/**
	 * Metodo per sapere se una spesa è già presente con quel codice
	 * 
	 */
	public boolean SpesaIsIn(int codicespesa) {
		for (Iterator<Integer> iter = this.codici.iterator(); iter.hasNext();) {
			int cod = iter.next();
			if (cod == codicespesa) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Metodo per visualizzare le spese senza corriere
	 * 
	 */
	public void showSpeseSenzaCorriere() {

		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice_corriere() == null) {
				spesa.printerWithout();
			}

		}
	}

	/**
	 * Metodo per cercare una spesa 
	 * @return
	 */
	public Spesa SearchSpesa(int codspesa) {
		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codspesa) {
				return spesa;
			}

		}
		return null;
	}

	public void removeAll() {
		this.spese.clear();
		this.codici.clear();
	}


	/**
	 * Metodo per sapere se una spesa è nella lista
	 * @return 
	 */
	public boolean SpesaIsInList(int codicespesa) {
		for (Iterator<Spesa> iter = this.spese.iterator(); iter.hasNext();) {
			Spesa spesa = iter.next();
			if (spesa.getCodice() == codicespesa) {
				return true;
			}

		}
		return false;
	}

}
