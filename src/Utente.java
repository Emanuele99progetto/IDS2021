
public class Utente {

	private String email;
	private String password;
	private String username;
	private Ruolo ruolo;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public boolean setPassword(String oldpassword, String newpassword) {
		if (!oldpassword.equals( getPassword())) {
			System.out.println(oldpassword+" "+getPassword());
			System.out.println("Password attuale errata");
			return false;
		}
		this.password = newpassword;
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Utente(String email, String password, String username, Ruolo ruolo) throws IllegalArgumentException
	{ 	if (password.length() < 5 || password.length() >30 )
		{throw new IllegalArgumentException("Password errata, lunghezza consentita tra 5 e 30 caratteri");}
	
		this.email = email;
		this.password = password;
		this.username = username;
		this.ruolo = ruolo;
	}


}