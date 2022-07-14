package ch.zli.m223.ksh19s.mw.CRM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUserImpl;
import ch.zli.m223.ksh19s.mw.CRM.model.Role;
import ch.zli.m223.ksh19s.mw.CRM.model.RoleImpl;

public interface RoleRepository extends JpaRepository<RoleImpl, Long> {

	default Role insert(String roleName, AppUser appUser) {
		// cast to Impl
		AppUserImpl userImpl = (AppUserImpl) appUser;

		// Create new Role
		RoleImpl newRole = new RoleImpl(roleName, userImpl);

		// Save Role to DB
		RoleImpl savedNewRole = save(newRole);

		// Update users role list
		userImpl.addRoleToList(savedNewRole);

		// Return new Role
		return savedNewRole;
	}

	Optional<RoleImpl> findRoleByName(String name); // Spring black magic

}
