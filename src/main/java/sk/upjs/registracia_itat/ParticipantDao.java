package sk.upjs.registracia_itat;

import java.util.List;

public interface ParticipantDao {

	void add(Participant participant);

	List<Participant> getAll();
	
	void save(Participant participant);

}