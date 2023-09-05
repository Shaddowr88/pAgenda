package model;

public class Personnel {
	
	public String login;
	public String motDePasse;
	
	
	public Personnel(String login, String motDePasse) {
		
		this.login = login;
		this.motDePasse = motDePasse;
	}
	
	//Getters & Setters
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
