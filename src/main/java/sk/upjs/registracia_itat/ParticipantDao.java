package sk.upjs.registracia_itat;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDao {
	
	private List<Participant> participants = new ArrayList<>();
	private long lastId = 0;
	
	public ParticipantDao() {
		//TODO pre testovacie ucely - potom zmazat
		//FIXME participant nema vsetko vyplnene
		Participant p = new Participant();
		p.setName("Andrej");
		p.setSurname("Kiska");
		p.setEmail("prezident@prezident.sk");
		this.add(p);
	}
	
	public void add(Participant participant) {
		participant.setId(++lastId);
		participants.add(participant);
	}
	
	public List<Participant> getAll() {
		return participants;
	}
}
