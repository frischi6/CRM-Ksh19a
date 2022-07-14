package ch.zli.m223.ksh19s.mw.CRM.model;

import java.util.ArrayList;
import java.util.List;

public class Schoolclass {
	public List<String> schoolclassList;

	public Schoolclass() {
		schoolclassList = new ArrayList<>();

		schoolclassList.add("I3a");
		schoolclassList.add("IM19A");
		schoolclassList.add("19CM");

	}

	public String getSchoolclass(int index) {
		return schoolclassList.get(index);
	}

}
