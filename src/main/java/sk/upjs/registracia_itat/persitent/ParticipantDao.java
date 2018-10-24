package sk.upjs.registracia_itat.persitent;

import java.util.List;

import sk.upjs.registracia_itat.entity.Participant;

public interface ParticipantDao {

	void add(Participant participant);

	List<Participant> getAll();
	
	void save(Participant participant);

}