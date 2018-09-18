package com.balynsky.ac.user.repository.impl;

import com.balynsky.ac.backup.model.Client;
import com.balynsky.ac.user.repository.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryClientRepositoryImpl implements ClientRepository {
	private static Map<Long, Client> clients = new HashMap<>();

	static {
		clients.put(1L, new Client(1L, "Laurens Bancroft", true, 10));
		clients.put(2L, new Client(2L, "Miriam Bancroft", true, 5));
		clients.put(3L, new Client(3L, "Takeshi Kovacs", false, null));
		clients.put(4L, new Client(4L, "Kristin Ortega", false, null));
		clients.put(5L, new Client(5L, "Reileen Kawahara", true, 10));
		clients.put(6L, new Client(6L, "Vernon Elliot", false, null));
		clients.put(7L, new Client(7L, "Quellcrist Falconer", false, null));
	}

	@Override
	public Client getClientInfo(Long id) {
		return clients.get(id);
	}
}
