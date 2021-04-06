package org.hbrs.se2.project.hellocar;

import org.hbrs.se2.project.hellocar.dtos.CarDTO;
import org.hbrs.se2.project.hellocar.dtos.RolleDTO;
import org.hbrs.se2.project.hellocar.dtos.UserDTO;
import org.hbrs.se2.project.hellocar.entities.Rolle;
import org.hbrs.se2.project.hellocar.entities.User;
import org.hbrs.se2.project.hellocar.repository.CarRepository;
import org.hbrs.se2.project.hellocar.repository.RoleRepository;
import org.hbrs.se2.project.hellocar.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HellocarApplicationTests {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CarRepository carRepository;

    @Test
    void testRolesOfUser() {
        Optional<User> wrapper = repository.findById(1);
        if ( wrapper.isPresent() ) {
            User user = wrapper.get();
            System.out.println("User: " + user.getLastName());
            List<Rolle> list = user.getRoles();
            assertEquals(2 , list.size() , "Anzahl der Rollen");
            Rolle rolle1 = list.get(0);
            assertEquals("admin" , rolle1.getBezeichhnung() );
        }
    }

    @Test
    void testUserDTOByAttribute() {
        UserDTO personDTO = repository.getUserByOccupation("Professor").get(0);
        System.out.println(personDTO.getFirstName());
        assertEquals("Sascha", personDTO.getFirstName());
        assertEquals(1 , personDTO.getId());
    }

    @Test
    void testUserDTOByPassword() {
        UserDTO userDTO = repository.findUserByUseridAndPassword("sascha" , "abc").get(0);
        System.out.println(userDTO.getFirstName());
        assertEquals("Sascha", userDTO.getFirstName());
    }

    @Test
    void testUserDTOAndItsRoles() {
        UserDTO userDTO = repository.findUserByUseridAndPassword("sascha" , "abc").get(0);
        System.out.println(userDTO.getFirstName());
        assertEquals("Sascha", userDTO.getFirstName());
        List<RolleDTO> list = userDTO.getRoles();
        System.out.println(list.size());
        assertEquals(2 , list.size());
    }

    @Test
    void testPersonLoad() {
        Optional<User> wrapper = repository.findById(1);
        if ( wrapper.isPresent() ) {
            User user = wrapper.get();
            assertEquals("Alda" , user.getLastName());
        }
    }

    @Test
    void testRoleRepository() {
        List<Rolle> list = roleRepository.findByBezeichhnung();
        for (Rolle r: list) {
            System.out.println("Rolle: " + r.getBezeichhnung() );
        }
    }

    @Test
    void testFindCarsAndTheirUsers() {
        List<Object[]> list = this.carRepository.findAllCarsAndTheirUsers();
        for (Object[] item: list) {
            System.out.println("Brand: " + item[0] );
            System.out.println("Model: " + item[1] );
            System.out.println("Price: " + item[2] );
            System.out.println("First Name: " + item[3] );
            System.out.println("Last Name: " + item[4] );
        }
    }

    @Test
    void testFindCarsWithMostImportantValues() {
        List<CarDTO> list = this.carRepository.findCarsByDateIsNotNull();
        for (CarDTO item: list) {
            System.out.println("Brand: " + item.getBrand() );
            System.out.println("Model: " + item.getModel() );
            System.out.println("Price: " + item.getPrice() );
            System.out.println("Phone: " + item.getPhone() );
        }
    }

}
