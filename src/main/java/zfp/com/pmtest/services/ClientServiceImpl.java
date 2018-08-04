package zfp.com.pmtest.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zfp.com.pmtest.entity.Client;
import zfp.com.pmtest.repository.ClientRepository;


@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Set<Client> getClients() {
		Set<Client> clientSet = new HashSet<>();
		clientRepository.findAll().iterator().forEachRemaining(clientSet::add);
		return clientSet;
	}

	@Override
	public Client findById(Long l) {
		 Optional<Client> clientOptional = clientRepository.findById(l);

	        if (!clientOptional.isPresent()) {
	            throw new RuntimeException("Employee Not Found!");
	        }

	        return clientOptional.get();
	}

	@Override
	public Client saveClient(Client client) {
		 return clientRepository.save(client);
	    }
	@Override
	public void deleteClient(Long id) {

		clientRepository.deleteById(id);

	}
	}

