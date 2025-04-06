package org.hbrs.se2.demo.registration;

public class UserFactory {
	
	public static UserDTO createNewUserWithNameAndPassword( String name, String password ) {
		UserDTO dto = new UserDTO();
		dto.setName(name);
		dto.setPassword(password);
		return dto;
	}
	
	public static UserDTO createDefaultUserWithNoPassword() {
		UserDTO dto = UserFactory.getDefaultUser();
		dto.setPassword("");
		return dto;
	}
	
	public static UserDTO createDefaultUserWithNoPasswordAndNoAddress() {
		UserDTO dto = UserFactory.getDefaultUser();
		dto.setPassword("");
		dto.setAddress("");
		return dto;
	}

	/**
	 * Erzeugung eines Default-Users mit vorbelegten Attributen
	 * (Template Pattern [GOF])
	 * @return
	 */
	private static UserDTO getDefaultUser() {
		UserDTO dto = new UserDTO();
		dto.setName("Stefan Meyer");
		dto.setPassword("abc99");
		dto.setAddress("Bonn");
		dto.setGebDatum("25.9.1999");
		dto.setUserID("meyer1");
		return dto;
	}

}
