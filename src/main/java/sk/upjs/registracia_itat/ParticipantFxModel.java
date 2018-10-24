package sk.upjs.registracia_itat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.registracia_itat.entity.Participant;

public class ParticipantFxModel {
	
	private Participant participant;
	private StringProperty name = new SimpleStringProperty();
	private StringProperty surname = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();

	public ParticipantFxModel(Participant participant) {
		this.participant = participant;
		setName(participant.getName());
		setSurname(participant.getSurname());
		setEmail(participant.getEmail());
	}
	public Participant getParticipant() {
		participant.setName(getName());
		participant.setSurname(getSurname());
		participant.setEmail(getEmail());
		return participant;
	}	
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	public StringProperty emailProperty() {
		return this.email;
	}
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty() {
		return name;
	}
	public String getSurname() {
		return surname.get();
	}
	public void setSurname(String surname) {
		this.surname.set(surname);
	}
	public StringProperty surnameProperty() {
		return surname;
	}	
}
