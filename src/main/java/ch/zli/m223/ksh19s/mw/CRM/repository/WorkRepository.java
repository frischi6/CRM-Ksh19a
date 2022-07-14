package ch.zli.m223.ksh19s.mw.CRM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUserImpl;
import ch.zli.m223.ksh19s.mw.CRM.model.Work;
import ch.zli.m223.ksh19s.mw.CRM.model.WorkImpl;

public interface WorkRepository extends JpaRepository<WorkImpl, Long> {

	default Work insert(String workName, AppUser appStudent) {

		// cast to impl
		AppUserImpl studentImpl = (AppUserImpl) appStudent;

		// create new Role
		WorkImpl newWork = new WorkImpl(workName, studentImpl);

		// save Role to DB
		WorkImpl savedNewWork = save(newWork);

		// update users role list
		studentImpl.addWorkToList(savedNewWork);

		// return new Role
		return savedNewWork;
	}

	Optional<WorkImpl> findWorkByName(String work); // Spring black magic

}
