

public class Locker {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
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
		Locker other = (Locker) obj;
		if (codice != other.codice)
			return false;
		return true;
	}

	private String indirizzo;
	private String citta;
	private int codice;
	private int nspese;
	private int nspeseconsigliate;

	Locker(int codice, String citta,String indirizzo, int nspeseconsigliate) throws IllegalArgumentException,NullPointerException {
		if (indirizzo == null)
			throw new NullPointerException("L'indirizzo deve essere un campo obbligatorio da inserire");
		if (citta == null)
			throw new NullPointerException("La citta deve essere un campo obbligatorio da inserire");
		if (codice < 1)
			throw new IllegalArgumentException("Il codice deve essere un numero positivo");
		if (nspeseconsigliate < 1)
			throw new IllegalArgumentException("Il n di spese consigliate deve essere un numero positivo");
		this.citta = citta;
		this.indirizzo = indirizzo;
		this.codice = codice;
		this.nspese = 0;
		this.nspeseconsigliate = nspeseconsigliate;
	}

	Locker(String indirizzo, String citta, int codice, int nspese, int nspeseconsigliate)throws IllegalArgumentException,NullPointerException {
		this(codice,citta,indirizzo,nspeseconsigliate);
		this.nspese = nspese;
	}

	public boolean LockerAvailable() {
		if (nspese < nspeseconsigliate)
			return true;
		else
			return false;
	}

	public void printer() {
		System.out.println(" Il codice locker è: " + codice + "\n La Città è: " + citta + "\n Indirizzo: " + indirizzo
				+ "\n Il numero di spese presenti: " + nspese + " \n Il numero di spese massime :" + nspeseconsigliate+" \n");

	}

	public void SpesaIn() {
		nspese++;
	}

	public void SpesaOut() {
		nspese--;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public int getNspese() {
		return nspese;
	}

	public void setNspese(int nspese) {
		this.nspese = nspese;
	}

	public int getNspeseconsigliate() {
		return nspeseconsigliate;
	}

	public void setNspeseconsigliate(int nspeseconsigliate) {
		this.nspeseconsigliate = nspeseconsigliate;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

}
