package ch.zli.m223.ksh19s.mw.CRM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUserImpl;
import ch.zli.m223.ksh19s.mw.CRM.model.HobbyImpl;

public interface HobbyRepository extends JpaRepository<HobbyImpl, Long> {

	default String insert(String hobbyName, AppUser appStud) {

		// cast to impl
		AppUserImpl studentImpl = (AppUserImpl) appStud;

		// update users role list
		studentImpl.setHobby(hobbyName);

		// return new Role
		return hobbyName;
	}

	Optional<HobbyImpl> findHobbyByHobby(String hobby); // Spring black magic

}
