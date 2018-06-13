package zfp.com.pmtest.services;

import java.util.Set;

import zfp.com.pmtest.entity.Client;
import zfp.com.pmtest.entity.Consultant;


public interface ConsultantService {

	Set<Consultant> getConsultants();

	Consultant findById(Long id);

	Consultant saveConsultant(Consultant Consultant);
	
}
