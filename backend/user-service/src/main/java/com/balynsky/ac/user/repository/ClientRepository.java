package com.balynsky.ac.user.repository;

import com.balynsky.ac.backup.model.Client;

/**
 */
public interface ClientRepository {
	Client getClientInfo(Long id);
}
