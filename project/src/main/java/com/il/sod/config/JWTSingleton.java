package com.il.sod.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;

/**
 * Created by cesaregb on 12/3/16.
 */
public enum JWTSingleton {

	INSTANCE;

	private final Key key;
	SignatureAlgorithm sigAlg;

	private JWTSingleton(){
		sigAlg = SignatureAlgorithm.HS256; // or HS384 or HS512

//		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
//		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		key = MacProvider.generateKey(sigAlg);
	}


	public Key getKey(){
		return this.key;
	}

	public SignatureAlgorithm getSigAlg(){
		return sigAlg;
	}


	//Sample method to construct a JWT
	public String createJWT(String id, String subject, int days) {

		String issuer = "InteractiveLabs";

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id)
				.setIssuedAt(now)
				.setSubject(subject)
				.setIssuer(issuer)
				.signWith(sigAlg, key);

		//if it has been specified, let's add the expiration
		long ttlMillis = days * 24 * 60 * 60 * 1000;
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	public boolean isValidToken(String token){

		//This line will throw an exception if it is not a signed JWS (as expected)
//		Claims claims = Jwts.parser()
//				.setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
//				.parseClaimsJws(jwt).getBody();

		try {
			return  Jwts.parser().setSigningKey(key)
					.parseClaimsJws(token)
					.getBody()
					.getSubject().equals(Constants.BASIC_AUTH);

		} catch (SignatureException e) {
			return false;
		}



	}


}
