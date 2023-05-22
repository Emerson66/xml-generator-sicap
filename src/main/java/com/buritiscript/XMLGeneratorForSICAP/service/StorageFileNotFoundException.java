package com.buritiscript.XMLGeneratorForSICAP.service;

import com.buritiscript.XMLGeneratorForSICAP.exception.StorageException;

public class StorageFileNotFoundException  extends StorageException {

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
