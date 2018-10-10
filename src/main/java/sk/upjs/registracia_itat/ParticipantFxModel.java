package sk.upjs.registracia_itat;

import java.time.LocalDateTime;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParticipantFxModel {
	
	private Participant participant;
	private StringProperty email = new SimpleStringProperty();

	public ParticipantFxModel(Participant participant) {
		this.participant = participant;
		setEmail(participant.getEmail());
	}
	
	public String getEmail() {
		return email.get();
	}
	
	public void setEmail(String email) {
		this.email.set(email);
		participant.setEmail(email);
	}
	
	public StringProperty emailProperty() {
		return this.email;
	}
	
	public String getName() {
		return participant.getName();
	}

	public void setName(String name) {
		participant.setName(name);
	}

	public String getSurname() {
		return participant.getSurname();
	}

	public void setSurname(String surname) {
		participant.setSurname(surname);
	}

	public String getOrganization() {
		return participant.getOrganization();
	}

	public void setOrganization(String organization) {
		participant.setOrganization(organization);
	}

	public String getAddress() {
		return participant.getAddress();
	}

	public void setAddress(String address) {
		participant.setAddress(address);
	}

	public Long getIco() {
		return participant.getIco();
	}

	public void setIco(Long ico) {
		participant.setIco(ico);
	}

	public String getDic() {
		return participant.getDic();
	}

	public void setDic(String dic) {
		participant.setDic(dic);
	}

	public boolean isEarly() {
		return participant.isEarly();
	}

	public void setEarly(boolean early) {
		participant.setEarly(early);
	}

	public Tshirt getTshirt() {
		return participant.getTshirt();
	}

	public void setTshirt(Tshirt tshirt) {
		participant.setTshirt(tshirt);
	}

	public boolean isStudent() {
		return participant.isStudent();
	}

	public void setStudent(boolean student) {
		participant.setStudent(student);
	}

	public boolean isSingleRoom() {
		return participant.isSingleRoom();
	}

	public void setSingleRoom(boolean singleRoom) {
		participant.setSingleRoom(singleRoom);
	}

	public LocalDateTime getStart() {
		return participant.getStart();
	}

	public void setStart(LocalDateTime start) {
		participant.setStart(start);
	}

	public LocalDateTime getEnd() {
		return participant.getEnd();
	}

	public void setEnd(LocalDateTime end) {
		participant.setEnd(end);
	}

	public List<Companion> getCompanions() {
		return participant.getCompanions();
	}

	public void setCompanions(List<Companion> companions) {
		participant.setCompanions(companions);
	}

	public WorkShop getWorkshop() {
		return participant.getWorkshop();
	}

	public void setWorkshop(WorkShop workshop) {
		participant.setWorkshop(workshop);
	}

	public boolean isCash() {
		return participant.isCash();
	}

	public void setCash(boolean cash) {
		participant.setCash(cash);
	}

	public Long getId() {
		return participant.getId();
	}

	public void setId(Long id) {
		participant.setId(id);
	}

	public String toString() {
		return participant.toString();
	}
	
	
}
