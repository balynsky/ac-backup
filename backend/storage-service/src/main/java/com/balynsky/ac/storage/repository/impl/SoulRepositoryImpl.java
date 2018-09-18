package com.balynsky.ac.storage.repository.impl;

import com.balynsky.ac.storage.SoulEntity;
import com.balynsky.ac.storage.repository.SoulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class SoulRepositoryImpl implements SoulRepository {
	private final JdbcTemplate template;

	@Autowired
	public SoulRepositoryImpl(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public SoulEntity saveSoul(SoulEntity entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(connection -> {
			PreparedStatement ps = connection
					.prepareStatement("insert into soul (client_id, body) values( ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, entity.getClientId());
			ps.setString(2, entity.getBody());
			return ps;
		}, keyHolder);

		return keyHolder.getKey() == null ? null : findById(keyHolder.getKey().longValue());
	}

	@Override
	public SoulEntity findById(Long id) {
		return template.queryForObject("select * from soul where id=?", new Object[]{id},
				new BeanPropertyRowMapper<>(SoulEntity.class));

	}
}
