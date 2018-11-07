package sk.upjs.registracia_itat.persitent;

import java.util.List;

import sk.upjs.registracia_itat.entity.Workshop;

public interface WorkshopDao {

	List<Workshop> getAll();
	
	Workshop save(Workshop workshop);
	
	void delete(long id);

}