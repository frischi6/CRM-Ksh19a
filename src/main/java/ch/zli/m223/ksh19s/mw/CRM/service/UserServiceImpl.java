package ch.zli.m223.ksh19s.mw.CRM.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh19s.mw.CRM.exception.InvalidArgumentException;
import ch.zli.m223.ksh19s.mw.CRM.exception.UserAllreadyExistsException;
import ch.zli.m223.ksh19s.mw.CRM.exception.UserNotFoundException;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<AppUser> getAllUsers() {
		return new ArrayList<>(userRepository.findAll());
	}

	@Override
	public AppUser getUser(Long id) {
		return userRepository.findById(id).orElseThrow(() -> {
			throw new UserNotFoundException("Invalid user Id " + id);
		});
	}

	@Override
	public AppUser insertUser(String name, String password) {
		if (name == null)
			throw new InvalidArgumentException("Name must not be null");
		// If (user with userName exists) then throw UserAllreadyExistsException
		if (userRepository.findUserByName(name).isPresent()) {
			throw new UserAllreadyExistsException("User with name" + name + " already exists");
		}
		return userRepository.insert(name, password);
	}

	@Override
	public void deleteUserById(Long id) {
		if (id == null)
			throw new InvalidArgumentException("Id must not be null");
		if (userRepository.findById(id).isEmpty()) {
			return;
		}
		userRepository.deleteById(id);
	}

	@Override
	public AppUser insertNewUser(String name, String password, String[] roles, String[] works, String[] courses) {
		if (name == null)
			throw new InvalidArgumentException("Name must not be null");
		// If (user with userName exists) then throw UserAllreadyExistsException
		if (userRepository.findUserByName(name).isPresent()) {
			throw new UserAllreadyExistsException("User with name" + name + " already exists");
		}
		return userRepository.insertNewUser(name, password, roles, works, courses);
	}

}
