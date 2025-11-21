package model;

public class Credencial {
	
	private String nombreusuario;
	private String password;
	private String  rol;
	
	public Credencial() {}
	
	public Credencial(String username,String password,String rol) {
		this.nombreusuario = username;
		this.password = password;
		this.rol = rol;
	}

	public String getUsername() {
		return nombreusuario;
	}

	public void setUsername(String username) {
		this.nombreusuario = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
