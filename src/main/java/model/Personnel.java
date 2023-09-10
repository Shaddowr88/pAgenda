package model;

public class Personnel {
	
	public String login;
	public String motDePasse;
	public boolean isAdmin=false;
	
	
	
	public Personnel(String login, String motDePasse, Boolean admin) {
		
		this.login = login;
		this.motDePasse = motDePasse;
		this.isAdmin = admin;
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	

}
