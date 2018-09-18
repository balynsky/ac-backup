package com.balynsky.ac.backup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
	private Long id;
	private String name;
	private boolean isMeth;
	private Integer maxVersions;
}
