package ch.zli.m223.ksh19s.mw.CRM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUserImpl;

public interface UserRepository extends JpaRepository<AppUserImpl, Long> {

	default AppUser insert(String userName) {
		AppUserImpl user = new AppUserImpl(userName);
		return save(user);
	}

	Optional<AppUserImpl> findUserByName(String name); // Spring black magic
}
