package com.ojas.ra.service;

import com.ojas.ra.domain.AccessToken;

/**
 * 
 * @author skkhadar
 *
 */
public interface TokenService {

	/**
	 * 
	 * @param token
	 * @return
	 */
	AccessToken getAccessToken(String token);
}
