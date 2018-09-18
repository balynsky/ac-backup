package com.balynsky.ac.storage.repository;

import com.balynsky.ac.storage.SoulEntity;

/**
 */
public interface SoulRepository {

	SoulEntity saveSoul(SoulEntity entity);

	SoulEntity findById(Long id);
}
