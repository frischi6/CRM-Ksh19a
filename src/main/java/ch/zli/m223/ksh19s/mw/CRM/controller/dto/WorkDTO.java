package ch.zli.m223.ksh19s.mw.CRM.controller.dto;

import ch.zli.m223.ksh19s.mw.CRM.model.Work;

public class WorkDTO {
	public String workName;

	public WorkDTO(Work work) {
		workName = work.getWork();
	}
}
