/*
 * Copyright (c) 2012 Yodlee, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Yodlee, Inc. 
 * Use is subject to license terms.
 */
package yodlee.ysl.api.beans;


public class PublicKeyData {
	private String keyAlias;
	private String keyAsPemString;
	
	public PublicKeyData(){
		super();
	}
	
	public PublicKeyData(String keyAlias, String keyAsPemString) {
		super();
		this.keyAlias = keyAlias;
		this.keyAsPemString = keyAsPemString;
	}

	/**
	 * returns alias of the public key.
	 * @return
	 */
	public String getKeyAlias() {
		return keyAlias;
	}

	public String getKeyAsPemString() {
		return keyAsPemString;
	}

	/**
	 * @param keyAlias the keyAlias to set
	 */
	public void setKeyAlias(String keyAlias) {
		this.keyAlias = keyAlias;
	}

	/**
	 * @param keyAsPemString the keyAsPemString to set
	 */
	public void setKeyAsPemString(String keyAsPemString) {
		this.keyAsPemString = keyAsPemString;
	}
}
