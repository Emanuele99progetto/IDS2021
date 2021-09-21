
public class Spesa {
	
	

	private int codice_spesa;
	private int codice_ritiro;

	private boolean statusconsegna;
	private boolean statusritiro;
	private double importo_spesa;
	private String descrizione;
	private String codice_cliente;
	private String codice_corriere;
	private String codice_commerciante;
	private int codice_locker;



	Spesa(int codice_ritiro,int codice_spesa, double importo, String descrizione, String codicecl, String codicecom)
			throws IllegalArgumentException, NullPointerException {
		if (descrizione == null)
			throw new NullPointerException("La descrizione deve essere un campo obbligatorio da inserire");
		
		if (codicecl == null)
			throw new NullPointerException("Il cod cliente deve essere un campo obbligatorio da inserire");
		if (codicecom == null)
			throw new NullPointerException("Il cod commerciante deve essere un campo obbligatorio da inserire");
		if (codice_spesa < 1)
			throw new IllegalArgumentException("Il codice spesa deve essere un numero positivo");
		if (importo <= 0)
			throw new IllegalArgumentException("L'importo deve essere un numero positivo");
		this.codice_spesa = codice_spesa;
		this.statusconsegna = false;
		this.statusritiro = false;
		importo_spesa = importo;
		this.descrizione = descrizione;

		codice_locker = 0;
		codice_cliente = codicecl;
		codice_commerciante = codicecom;
		this.codice_ritiro=codice_ritiro;
	}

	Spesa(int codice_ritiro,int codice_spesa, double importo, String descrizione, String codicecl, String codicecom,
			String codicecor, boolean consegna, boolean ritiro,int codicel) {
		this.codice_spesa = codice_spesa;
		this.statusconsegna = consegna;
		this.statusritiro = ritiro;
		importo_spesa = importo;
		this.descrizione = descrizione;

		codice_locker = codicel;
		codice_cliente = codicecl;
		codice_commerciante = codicecom;
		codice_corriere = codicecor;
		this.codice_ritiro=codice_ritiro;
	}

	
	public int getCodice_ritiro() {
		return codice_ritiro;
	}

	public void setCodice_ritiro(int codice_ritiro) {
		this.codice_ritiro = codice_ritiro;
	}
	
	public boolean isStatusconsegna() {
		return statusconsegna;
	}

	public void setStatusconsegna(boolean statusconsegna) {

		this.statusconsegna = statusconsegna;
	}

	public boolean isStatusritiro() {

		return statusritiro;
	}

	public void setStatusritiro(boolean statusritiro) {
		this.statusritiro = statusritiro;
	}

	public int getCodice_locker() {
		return codice_locker;
	}

	public void setCodice_locker(int codice_locker) {
		this.codice_locker = codice_locker;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice_spesa;
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
		Spesa other = (Spesa) obj;
		if (codice_spesa != other.codice_spesa)
			return false;
		return true;
	}

	
	//metodo per cliente 
	public void printer() {
		System.out.println(" Il codice spesa è: " + codice_spesa+", codice per ritiro "+codice_ritiro + "\n L'importo è: " + importo_spesa 
				+ "\n Lo statusconsegna: " + statusconsegna + "\n Lo statusritiro: " + statusritiro + "\n "
				+ "Il codice cliente è: " + codice_cliente + "\n Il codice commerciante è: " + codice_commerciante
				+ " \n" + " Il codice corriere è: " + codice_corriere + " \n Il codice del locker è: " + codice_locker+" \n" );

	}

	//metodo per corriere e commerciante
	public void printerWithout() {
		System.out.println(" Il codice spesa è: " + codice_spesa + "\n L'importo è: " + importo_spesa 
				+ "\n Lo statusconsegna: " + statusconsegna + "\n Lo statusritiro: " + statusritiro + "\n "
				+ "Il codice cliente è: " + codice_cliente + "\n Il codice commerciante è: " + codice_commerciante
				+ " \n" + " Il codice corriere è: " + codice_corriere + " \n Il codice del locker è: " + codice_locker+" \n" );

	}


	public double getImporto_spesa() {
		return importo_spesa;
	}

	public void setImporto_spesa(double importo_spesa) {
		this.importo_spesa = importo_spesa;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice_cliente() {
		return codice_cliente;
	}

	public void setCodice_cliente(String codice_cliente) {
		this.codice_cliente = codice_cliente;
	}

	public String getCodice_corriere() {
		return codice_corriere;
	}

	public void setCodice_corriere(String codice_corriere) {
		this.codice_corriere = codice_corriere;
	}

	public String getCodice_commerciante() {
		return codice_commerciante;
	}

	public void setCodice_commerciante(String codice_commerciante) {
		this.codice_commerciante = codice_commerciante;
	}

	

	public int getCodice() {
		return codice_spesa;
	}

	public void setCodice(int codice_spesa) {
		this.codice_spesa = codice_spesa;
	}

	public double getImporto() {
		return importo_spesa;
	}

	public void setImporto(double importo_spesa) {
		this.importo_spesa = importo_spesa;
	}

}
