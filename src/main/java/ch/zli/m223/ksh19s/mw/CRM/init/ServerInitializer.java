package ch.zli.m223.ksh19s.mw.CRM.init;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ch.zli.m223.ksh19a.mj.CRM.finals.FinalsWorks;
import ch.zli.m223.ksh19s.mw.CRM.finals.FinalsCourses;
import ch.zli.m223.ksh19s.mw.CRM.model.AppUser;
import ch.zli.m223.ksh19s.mw.CRM.repository.CourseRepository;
import ch.zli.m223.ksh19s.mw.CRM.repository.HobbyRepository;
import ch.zli.m223.ksh19s.mw.CRM.repository.RoleRepository;
import ch.zli.m223.ksh19s.mw.CRM.repository.UserRepository;
import ch.zli.m223.ksh19s.mw.CRM.repository.WorkRepository;
import ch.zli.m223.ksh19s.mw.CRM.roles.AppRoles;

@Component
public class ServerInitializer implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private HobbyRepository hobbyRepository;

	@Autowired
	private WorkRepository workRepository;

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		AppUser max = userRepository.insert("Max", "max");
		roleRepository.insert(AppRoles.ADMIN, max);
		roleRepository.insert(AppRoles.USER, max);

		AppUser trax = userRepository.insert("Trax", "trax");
		roleRepository.insert(AppRoles.USER, trax);

		AppUser arbias = userRepository.insert("Arbias", "arbias");
		roleRepository.insert(AppRoles.USER, arbias);
		courseRepository.insert(FinalsCourses.I3a, arbias);
		courseRepository.insert(FinalsCourses.IM19A, arbias);
		hobbyRepository.insert("Shisha", arbias);
		workRepository.insert(FinalsWorks.VR, arbias);

		AppUser yannis = userRepository.insert("Yannis", "yannis");
		roleRepository.insert(AppRoles.USER, yannis);
		courseRepository.insert(FinalsCourses.I3a, yannis);
		courseRepository.insert(FinalsCourses.IM19A, yannis);
		hobbyRepository.insert("American Football", yannis);
		workRepository.insert(FinalsWorks.ZKB, yannis);

		AppUser sarah = userRepository.insert("Sarah", "sarah");
		roleRepository.insert(AppRoles.USER, sarah);
		courseRepository.insert(FinalsCourses.I3a, sarah);
		courseRepository.insert(FinalsCourses.IM19A, sarah);
		hobbyRepository.insert("Fussball", sarah);
		workRepository.insert(FinalsWorks.STRICHPUNKT, sarah);

		AppUser noemi = userRepository.insert("Noemi", "noemi");
		roleRepository.insert(AppRoles.USER, noemi);
		courseRepository.insert(FinalsCourses.M19C, noemi);
		hobbyRepository.insert("Fussball", noemi);
		workRepository.insert(FinalsWorks.BASLER, noemi);
		workRepository.insert(FinalsWorks.FCAARAU, noemi);

	}

}
