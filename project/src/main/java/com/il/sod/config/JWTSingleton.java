package com.il.sod.config;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

/**
 * Created by cesaregb on 12/3/16.
 */
public enum JWTSingleton {

	INSTANCE;
	private final Key key = MacProvider.generateKey();

	public Key getKey(){
		return this.key;
	}


}
