package ch.zli.m223.ksh19s.mw.CRM.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh19s.mw.CRM.exception.InvalidArgumentException;
import ch.zli.m223.ksh19s.mw.CRM.exception.UserAllreadyExistsException;
import ch.zli.m223.ksh19s.mw.CRM.exception.UserNotFoundException;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.model.Work;
import ch.zli.m223.ksh19s.mw.CRM.repository.WorkRepository;

@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	private WorkRepository workRepository;
	private AppUser user;

	@Override
	public List<Work> getAllWorks() {
		return new ArrayList<>(workRepository.findAll());
	}

	@Override
	public Work getWork(Long id) {
		return workRepository.findById(id).orElseThrow(() -> {
			throw new UserNotFoundException("Invalid user Id " + id);
		});
	}

	@Override
	public Work insertWork(String work) {
		if (work == null)
			throw new InvalidArgumentException("Name must not be null");
		// If (user with userName exists) then throw UserAllreadyExistsException
		if (workRepository.findWorkByName(work).isPresent()) {
			throw new UserAllreadyExistsException("User with name" + work + " already exists");
		}
		return workRepository.insert(work, user);
	}

	@Override
	public void deleteWorkById(Long id) {
		if (id == null)
			throw new InvalidArgumentException("Id must not be null");
		if (workRepository.findById(id).isEmpty()) {
			return;
		}
		workRepository.deleteById(id);
	}

}
