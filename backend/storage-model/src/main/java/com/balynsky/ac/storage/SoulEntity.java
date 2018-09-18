package com.balynsky.ac.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoulEntity implements Serializable {
	private Long id;
	private Long clientId;
	private String body;
}
