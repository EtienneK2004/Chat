package user;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4752851953420467399L;
	private String nom;
	
	public User(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return nom;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(nom, other.nom);
	}
}
