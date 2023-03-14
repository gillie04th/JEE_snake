package models;

import java.util.ArrayList;

public class Game {
	private int id;
	private String map;
	private String depart;
	private User gagnant;
	private int tours;
	private int toursMax;
	private int speed;
	private String status;
	private ArrayList<User> joueurs;
		
	public Game() {
		super();
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) throws ModelException {
		if(id <= 0) {
			throw new ModelException("L'id ne peut pas être négatif ou nul");
		}else {			
			this.id = id;
		}
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public User getGagnant() {
		return gagnant;
	}

	public void setGagnant(User gagnant) {
		this.gagnant = gagnant;
	}

	public int getTours() {
		return tours;
	}

	public void setTours(int tours) {
		this.tours = tours;
	}

	public int getToursMax() {
		return toursMax;
	}

	public void setToursMax(int toursMax) {
		this.toursMax = toursMax;
	}

	public ArrayList<User> getJoueurs() {
		return joueurs;
	}

	public void addJoueurs(User joueur) {
		this.joueurs.add(joueur);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", map=" + map + ", depart=" + depart + ", gagnant="
				+ gagnant + ", tours=" + tours + ", toursMax" + toursMax
				+ "]";
	}
}
