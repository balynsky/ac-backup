package com.balynsky.ac.user.service.impl;

import com.balynsky.ac.backup.model.Client;
import com.balynsky.ac.user.repository.ClientRepository;
import com.balynsky.ac.user.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client getClientInfo(Long id) {
		if (id ==null){
			throw new IllegalArgumentException("You must set user id");
		}
		return clientRepository.getClientInfo(id);
	}
}
