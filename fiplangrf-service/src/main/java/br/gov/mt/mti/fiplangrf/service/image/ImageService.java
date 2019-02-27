package br.gov.mt.mti.fiplangrf.service.image;


import java.io.Serializable;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ImageService implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8862003162340469158L;

	protected ImageService(){		
	}


}
