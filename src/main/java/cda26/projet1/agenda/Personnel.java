package cda26.projet1.agenda;

public class Personnel {
	
	public String login;
	public String motDePasse;
	
	
	public Personnel(String login, String motDePasse) {
		
		this.login = login;
		this.motDePasse = motDePasse;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	

}
