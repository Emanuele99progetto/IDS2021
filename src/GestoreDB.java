import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GestoreDB {

	private Connection connessione = Connessione();

	/**
	 * 
	 * Metodo per l'autenticazione dell'utente tramite DB
	 * 
	 * @return
	 * 
	 */
	public Utente Autenticazione(String password, String email) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
			return null;
		}
		try {

			Statement stm = connessione.createStatement();
			ResultSet rs = stm.executeQuery("select email,username,password,ruolo from persone");

			while (rs.next()) {

				String emailpresente = rs.getString("email");
				String userpresente = rs.getString("username");
				String passpresente = rs.getString("password");
				Ruolo ruolo = Ruolo.valueOf(rs.getString("ruolo"));
				if (emailpresente.equals(email) && passpresente.equals(password)) {
					System.out.println("Autenticazione effettuata con successo!!!");
					Utente user = new Utente(emailpresente, passpresente, userpresente, ruolo);
					return user;
				}
			}

			System.out.println("Errore nell'autenticazione!!!");
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * Metodo per la connessione a Mysql
	 * 
	 * @return
	 * 
	 */
	public Connection Connessione() {
		String connectionString = "jdbc:mysql://localhost:3306/progetto?user=root&password=password";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionString);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	/**
	 * 
	 * Metodo per la registrazione tramite DB
	 * 
	 */
	public void Registrazione(String password, String email, String username, Ruolo ruolo) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");

		}
		try {

			Statement stm = connessione.createStatement();
			ResultSet rs = stm.executeQuery("select email from persone");

			while (rs.next()) {
				String emailpresente = rs.getString("email");
				if (emailpresente.equals(email)) {
					System.out.println("Una account collegato con l'email " + email + " è già presente nel sistema!");
					return;
				}
			}

			PreparedStatement prepared = connessione
					.prepareStatement("insert into persone (email,username,password,ruolo) values (?,?,?,?)");
			prepared.setString(1, email);
			prepared.setString(2, username);
			prepared.setString(3, password);
			prepared.setString(4, ruolo.toString());
			prepared.executeUpdate();
			System.out.println("Registrazione avvenuta con successo!!!");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * Metodo per caricamento in locale dei dati persistenti dei locker
	 * 
	 */
	public void ReadDBlocker(Gestorelocker gestore) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {

			Statement stm1 = connessione.createStatement();
			ResultSet rs1 = stm1.executeQuery("select * from locker");

			while (rs1.next()) {
				Locker locker = new Locker(rs1.getString("indirizzo"), rs1.getString("citta"), rs1.getInt("codicel"),
						rs1.getInt("nspese"), rs1.getInt("nspeseconsigliate"));
				gestore.addLocker(locker);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * Metodo per caricamento in locale dei dati persistenti delle spese
	 * 
	 */
	public void ReadDBspesa(Gestorespesa gestore, Ruolo ruolo, String email) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {

			if (ruolo == Ruolo.CLIENTE) {
				PreparedStatement prepared = connessione.prepareStatement("select * from spese where codicecliente=?");
				prepared.setString(1, email);
				ResultSet rs = prepared.executeQuery();

				while (rs.next()) {
					Spesa spesa = new Spesa(rs.getInt("codritiro"), rs.getInt("codices"), rs.getDouble("importo"),
							rs.getString("descrizione"), rs.getString("codicecliente"),
							rs.getString("codicecommerciante"), rs.getString("codicecorriere"),
							rs.getBoolean("statusconsegna"), rs.getBoolean("statusritiro"), rs.getInt("codicel"));
					gestore.addSpesa(spesa);

				}
			}

			else if (ruolo == Ruolo.COMMERCIANTE) {
				PreparedStatement prepared = connessione
						.prepareStatement("select * from spese where codicecommerciante=?");
				prepared.setString(1, email);
				ResultSet rs = prepared.executeQuery();

				while (rs.next()) {
					Spesa spesa = new Spesa(rs.getInt("codritiro"), rs.getInt("codices"), rs.getDouble("importo"),
							rs.getString("descrizione"), rs.getString("codicecliente"),
							rs.getString("codicecommerciante"), rs.getString("codicecorriere"),
							rs.getBoolean("statusconsegna"), rs.getBoolean("statusritiro"), rs.getInt("codicel"));
					gestore.addSpesa(spesa);
				}
			}

			else if (ruolo == Ruolo.CORRIERE) {
				PreparedStatement prepared = connessione.prepareStatement("select * from spese where codicecorriere=?");
				prepared.setString(1, email);
				ResultSet rs = prepared.executeQuery();

				while (rs.next()) {
					Spesa spesa = new Spesa(rs.getInt("codritiro"), rs.getInt("codices"), rs.getDouble("importo"),
							rs.getString("descrizione"), rs.getString("codicecliente"),
							rs.getString("codicecommerciante"), rs.getString("codicecorriere"),
							rs.getBoolean("statusconsegna"), rs.getBoolean("statusritiro"), rs.getInt("codicel"));
					gestore.addSpesa(spesa);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	

	/**
	 * 
	 * Metodo pe aggiornare lo status_ritiro della spesa nel DB
	 * 
	 */
	public void UpdateDBritiro(int codice) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {
			PreparedStatement prepared = connessione
					.prepareStatement("update spese set statusritiro=1 where codices=?");
			prepared.setInt(1, codice);
			prepared.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * Metodo pe aggiornare lo status_consegna della spesa nel DB
	 * 
	 */
	public void UpdateDBconsegna(int codice) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {
			PreparedStatement prepared = connessione
					.prepareStatement("update spese set statusconsegna=1 where codices=?");
			prepared.setInt(1, codice);
			prepared.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * Metodo per aggiornare il codice corriere della spesa del DB
	 * 
	 */
	public void UpdateDBcorriere(int codice, String codcorriere) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");

		}
		try {

			PreparedStatement prepared = connessione
					.prepareStatement("update spese set codicecorriere=? where codices=?");
			prepared.setString(1, codcorriere);
			prepared.setInt(2, codice);
			prepared.executeUpdate();
			System.out.println("Il corriere è stato aggiornato nel db con successo");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * Metodo per controllare se il corriere è presente nel DB
	 * 
	 * @return
	 * 
	 */
	public boolean isCorriereDB(String codicecorriere) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
			return false;
		}
		try {

			Statement stm = connessione.createStatement();
			ResultSet rs = stm.executeQuery("select email,ruolo from persone");

			while (rs.next()) {
				String emailpresente = rs.getString("email");
				Ruolo ruolo = Ruolo.valueOf(rs.getString("ruolo"));
				if (emailpresente.equals(codicecorriere) && ruolo == Ruolo.CORRIERE) {
					return true;
				}
			}
			System.out.println("Corriere non presente");
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * 
	 * Metodo per aggiunere una spesa nel DB
	 * 
	 */
	public void UpdateNewSpesa(int codritiro, int codice, String codicecli, String codicecom, String descrizione,
			double importo) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {

			PreparedStatement prepared = connessione.prepareStatement(
					"insert into spese (codices,codicecliente,codicecommerciante,descrizione,importo,codritiro) values (?,?,?,?,?,?)");
			prepared.setInt(1, codice);
			prepared.setString(2, codicecli);
			prepared.setString(3, codicecom);
			prepared.setString(4, descrizione);
			prepared.setDouble(5, importo);
			prepared.setInt(6, codritiro);

			prepared.executeUpdate();
			System.out.println("Tabella Spese aggiornata con successo!!!");

		}

		catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * Metodo per aggiungere un locker ad una spesa nel DB
	 * 
	 */
	public void UpdateDBlocker(int codices, int codicel) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");

		}
		try {
			PreparedStatement prepared = connessione.prepareStatement("update spese set codicel=? where codices=?");
			prepared.setInt(1, codicel);
			prepared.setInt(2, codices);
			prepared.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * Metodo per aggiungere una spesa in un locker nel DB
	 * 
	 */
	public void UpdateDBInlocker(int codicel) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {

			PreparedStatement prepared = connessione
					.prepareStatement("update locker set nspese=nspese+1 where codicel=?");
			prepared.setInt(1, codicel);
			prepared.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * Metodo per togliere una spesa in un locker nel DB
	 * 
	 */
	public void UpdateDBOutlocker(int codicel) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {

			PreparedStatement prepared = connessione
					.prepareStatement("update locker set nspese=nspese-1 where codicel=?");
			prepared.setInt(1, codicel);
			prepared.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * Metodo per aggiungere un locker nel DB
	 * 
	 * @return
	 * 
	 */
	public boolean UpdateNewLocker(int codicel, String via, String citta, int nspeseconsigliate) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
			return false;
		}
		try {

			PreparedStatement prepared = connessione.prepareStatement(
					"insert into locker (codicel,citta,indirizzo,nspese,nspeseconsigliate) values (?,?,?,?,?)");
			prepared.setInt(1, codicel);
			prepared.setString(2, citta);
			prepared.setString(3, via);
			prepared.setInt(4, 0);
			prepared.setInt(5, nspeseconsigliate);
			prepared.executeUpdate();
			System.out.println("Tabella Locker aggiornata con successo!!!");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * Metodo per lettura codici spese dal DB 
	 * 
	 * @return
	 */
	public void ReadDBspesaCOD(Gestorespesa gestore, Ruolo ruolo, String email) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {

			if (ruolo == Ruolo.COMMERCIANTE) {
				PreparedStatement prepared = connessione
						.prepareStatement("select codices from spese where codicecommerciante=?");
				prepared.setString(1, email);
				ResultSet rs = prepared.executeQuery();

				while (rs.next()) {
					gestore.addcod(rs.getInt("codices"));
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo per lettura codici
	 * 
	 */
	public boolean isClienteDB(String codicecliente) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
			return false;
		}
		try {

			Statement stm = connessione.createStatement();
			ResultSet rs = stm.executeQuery("select email,ruolo from persone");

			while (rs.next()) {
				String emailpresente = rs.getString("email");
				Ruolo ruolo = Ruolo.valueOf(rs.getString("ruolo"));
				if (emailpresente.equals(codicecliente) && ruolo == Ruolo.CLIENTE) {
					return true;
				}
			}
			System.out.println("Cliente non presente");
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	
	
	/**
	 * Metodo per modifica password nel DB 
	 * 
	 * 
	 */
	void changePassDB(String newpassword, String email) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {

			PreparedStatement prepared = connessione.prepareStatement("update persone set password=? where email=?");
			prepared.setString(1, newpassword);
			prepared.setString(2, email);
			prepared.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
	/**
	 * Metodo per rimozione Locker dal DB
	 * 
	 */
	void removeLockerDB(int codice_locker) {
		if (connessione == null) {
			System.out.println("Non c'è connessione con il DataBase del sistema!");
		}
		try {

			PreparedStatement prepared = connessione.prepareStatement("delete from locker where codicel=?");
			prepared.setInt(1, codice_locker);
			prepared.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
