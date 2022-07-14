package ch.zli.m223.ksh19s.mw.CRM.service;

import java.util.List;

import ch.zli.m223.ksh19s.mw.CRM.model.Work;

public interface WorkService {

	List<Work> getAllWorks();

	Work getWork(Long id);

	Work insertWork(String work);

	void deleteWorkById(Long id);

}
