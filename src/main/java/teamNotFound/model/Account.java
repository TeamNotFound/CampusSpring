package teamNotFound.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	@NotEmpty(message = "Inserire username")
	private String username;
	
	@Column
	@NotEmpty(message="Inserire password")
	private String password;
	
	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	private Utente utente;
	
	//DA CAMBIARE 
	@ManyToOne
	@JoinColumn(name="ruolo_id")

	private Ruolo ruolo;
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Account(int id, @NotEmpty(message = "Inserire username") String username,
			@NotEmpty(message = "Inserire password") String password, Utente utente, Ruolo ruolo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.utente = utente;
		this.ruolo = ruolo;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", utente=" + utente
				+ ", ruolo=" + ruolo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
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
		Account other = (Account) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}

	public Account() {
		super();
	}

}
