package zfp.com.pmtest.services;

import java.util.Set;

import zfp.com.pmtest.entity.Client;


public interface ClientService {

	Set<Client> getClients();

	Client findById(Long id);

	Client saveClient(Client client);

	void deleteClient(Long id);
	
}
