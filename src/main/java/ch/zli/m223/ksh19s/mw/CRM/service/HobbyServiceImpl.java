package ch.zli.m223.ksh19s.mw.CRM.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh19s.mw.CRM.exception.InvalidArgumentException;
import ch.zli.m223.ksh19s.mw.CRM.exception.UserAllreadyExistsException;
import ch.zli.m223.ksh19s.mw.CRM.exception.UserNotFoundException;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.Hobby;
import ch.zli.m223.ksh19s.mw.CRM.repository.HobbyRepository;

@Service
public class HobbyServiceImpl implements HobbyService {

	@Autowired
	private HobbyRepository hobbyRepository;
	private AppUser user;

	@Override
	public List<Hobby> getAllHobbies() {
		return new ArrayList<>(hobbyRepository.findAll());
	}

	@Override
	public Hobby getHobby(Long id) {
		return hobbyRepository.findById(id).orElseThrow(() -> {
			throw new UserNotFoundException("Invalid user Id " + id);
		});
	}

	@Override
	public Hobby insertHobby(String hobby) {
		if (hobby == null)
			throw new InvalidArgumentException("Name must not be null");
		// If (user with userName exists) then throw UserAllreadyExistsException
		if (hobbyRepository.findHobbyByName(hobby).isPresent()) {
			throw new UserAllreadyExistsException("User with name" + hobby + " already exists");
		}
		return hobbyRepository.insert(hobby, user);
	}

	@Override
	public void deleteHobbyById(Long id) {
		if (id == null)
			throw new InvalidArgumentException("Id must not be null");
		if (hobbyRepository.findById(id).isEmpty()) {
			return;
		}
		hobbyRepository.deleteById(id);
	}

}
