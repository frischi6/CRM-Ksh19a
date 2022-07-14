package ch.zli.m223.ksh19s.mw.CRM.controller.dto;

import ch.zli.m223.ksh19s.mw.CRM.model.Hobby;

public class HobbyDTO {
	public String hobbyName;

	public HobbyDTO(Hobby hobby) {
		hobbyName = hobby.getHobby();
	}
}
