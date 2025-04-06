package org.hbrs.se2.demo.registration;

public class RegistrationControl {
	
	public RegistrationResult registerUser( UserDTO dto ) {
		RegistrationResult result = new RegistrationResult();

		String mailAddress = dto.getAddress(); // E-Mail-Adresse
		// CheckOnDB( mailAddress )  --> Check Existenz über eine DAO (z.B. UserDAO) - ToDo

		if ( dto.getPassword() == null || dto.getPassword().equals("")) {
			result.setReason(RegistrationResult.PASSWORD_MISSING);
			result.setResult(false);
		} 
		else {
			result.setReason(RegistrationResult.REGISTRATION_SUCCESSFULL);
			result.setResult(true);
		}
		return result;
	}

}
