import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Gestorelocker {

	private Set<Locker> lockers = new HashSet<Locker>();

	
	/**
	 * Metodo per rimozione spesa dal locker
	 * 
	 */
	public void SpesaOut(int codice) {

		for (Iterator<Locker> iter = this.lockers.iterator(); iter.hasNext();) {
			Locker locker = iter.next();
			if (locker.getCodice() == codice) {
				    locker.SpesaOut();
					System.out.println("Il locker è stato aggiornato con successo");
					
				} 

			}

		
	}

	
	/**
	 * Metodo per inserimento spesa nel locker
	 * 
	 * @return
	 */
	public boolean SpesaIn(int codice) {

		for (Iterator<Locker> iter = this.lockers.iterator(); iter.hasNext();) {
			Locker locker = iter.next();
			if (locker.getCodice() == codice) {
				if (locker.LockerAvailable()) {
					locker.SpesaIn();
					System.out.println("Il locker è stato aggiornato con successo");
					return true;
				} else {
					System.out.println("Il locker non è stato aggiornato con successo dato che è pieno!");
					System.out.println(
							"Si prega di aspettare che qualcuno venga a ritirare la merce o di cambiare punto di ritiro!");
					return false;
				}

			}

		}
		System.out.println("Il locker cercato non è presente!");
		return false;
	}

	
	/**
	 * Metodo per ottenere informazioni del locker
	 * 
	 */
	public void InfoLocker(int codice) {

		for (Iterator<Locker> iter = this.lockers.iterator(); iter.hasNext();) {
			Locker locker = iter.next();
			if (locker.getCodice() == codice) {
				locker.printer();
				return;
			}

		}
		System.out.println("Il locker cercato non è presente!");
	}

	/**
	 * Metodo per inserimento locker 
	 * 
	 * @return
	 */
	public boolean addLocker(Locker locker) {
		if (lockers.add(locker)) {
			System.out.println("Il locker " + locker.toString() + " è stata inserito!");
			return true;
		} else {
			System.out.println("Il locker è già presente del DB!");
			return false;
		}
	}

	
	/**
	 * Metodo per cancellazioni dati locali
	 * 
	 */
	public void removeAll() {
		this.lockers.clear();
	}

	
	/**
	 * Metodo per ottenere l'oggetto locker
	 * 
	 * @return
	 */
	public Locker getLocker(int cod) {
		for (Iterator<Locker> iter = this.lockers.iterator(); iter.hasNext();) {
			Locker locker = iter.next();
			if (locker.getCodice() == cod) {
				return locker;
			}

		}
		return null;
	}

	/**
	 * Metodo per visualizzare tutti i locker 
	 * 
	 */
	public void showLockers() {
		for (Iterator<Locker> iter = this.lockers.iterator(); iter.hasNext();) {
			Locker locker = iter.next();
			locker.printer();
		}}

	
	
	/**
	 * Metodo per vedere se un locker è presente
	 * 
	 * @return
	 */
	public boolean isLocker(int codice) {
		for (Iterator<Locker> iter = this.lockers.iterator(); iter.hasNext();) {
			Locker locker = iter.next();
			if (locker.getCodice() == codice) {
				return true;
			}

		}
		return false;
		
	}
	
	/**
	 * Metodo per vedere quante spese ha un locker 
	 * 
	 * @return
	 */
	public int getLockerAvailable(int codice_locker) {
		for (Iterator<Locker> iter = this.lockers.iterator(); iter.hasNext();) {
			Locker locker = iter.next();
			if (locker.getCodice() == codice_locker)
			{return locker.getNspese();}
			
		    
	}
		return -1;}
	
	
	
	/**
	 * Metodo per rimozione locker
	 * 
	 */
	public void removeLocker(int codice_locker)
	{	for (Iterator<Locker> iter = this.lockers.iterator(); iter.hasNext();) {
		Locker locker = iter.next();
		if (locker.getCodice() == codice_locker)
			{
			System.out.println("Locker rimosso");
			iter.remove();
			}}}
	
	
}