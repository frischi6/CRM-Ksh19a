package ch.zli.m223.ksh19s.mw.CRM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUserImpl;
import ch.zli.m223.ksh19s.mw.CRM.model.Hobby;
import ch.zli.m223.ksh19s.mw.CRM.model.HobbyImpl;

public interface HobbyRepository extends JpaRepository<HobbyImpl, Long> {

	default Hobby insert(String hobbyName, AppUser appStud) {

		// cast to impl
		AppUserImpl studentImpl = (AppUserImpl) appStud;

		// create new Hobby
		HobbyImpl newHobby = new HobbyImpl(hobbyName, studentImpl);

		// save Role to DB
		HobbyImpl savedNewHobby = save(newHobby);

		// update users role list
		studentImpl.addHobbyToList(savedNewHobby);

		// return new Role
		return savedNewHobby;
	}

	Optional<HobbyImpl> findHobbyByHobby(String hobby); // Spring black magic

}
