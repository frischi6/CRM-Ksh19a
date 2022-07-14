package ch.zli.m223.ksh19s.mw.CRM.service;

import java.util.List;

import ch.zli.m223.ksh19s.mw.CRM.model.Hobby;

public interface HobbyService {

	List<Hobby> getAllHobbies();

	Hobby getHobby(Long id);

	Hobby insertHobby(String hobby);

	void deleteHobbyById(Long id);

}
