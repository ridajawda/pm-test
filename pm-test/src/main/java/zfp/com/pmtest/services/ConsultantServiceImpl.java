package zfp.com.pmtest.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zfp.com.pmtest.entity.Consultant;
import zfp.com.pmtest.repository.ConsultantRepository;


@Service
public class ConsultantServiceImpl implements ConsultantService {

	@Autowired
	private ConsultantRepository consultantRepository;
	
	@Override
	public Set<Consultant> getConsultants() {
		Set<Consultant> consultantSet = new HashSet<>();
		consultantRepository.findAll().iterator().forEachRemaining(consultantSet::add);
		return consultantSet;
	}

	@Override
	public Consultant findById(Long l) {
		 Optional<Consultant> consultantOptional = consultantRepository.findById(l);

	        if (!consultantOptional.isPresent()) {
	            throw new RuntimeException("Employee Not Found!");
	        }

	        return consultantOptional.get();
	}

	@Override
	public Consultant saveConsultant(Consultant consultant) {
		 return consultantRepository.save(consultant);
	    }

	
	}

