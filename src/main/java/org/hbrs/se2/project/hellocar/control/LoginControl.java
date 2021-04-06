package org.hbrs.se2.project.hellocar.control;

import org.hbrs.se2.project.hellocar.control.exception.DatabaseException;
import org.hbrs.se2.project.hellocar.dtos.UserDTO;
import org.hbrs.se2.project.hellocar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class LoginControl {

    @Autowired
    private UserRepository repository;

    private UserDTO userDTO = null;

    public boolean authentificate(String username, String password ) throws DatabaseException {
        List<UserDTO> list = null;
        try {
             list = repository.findUserByUseridAndPassword(username, password);
        } catch ( org.springframework.dao.DataAccessResourceFailureException e ) {
            System.out.println("A failure occured while trying to connect to database");
            throw new DatabaseException();
        }
        if ( list == null || list.isEmpty() ) {
            return false;
        }
        UserDTO userDTO = list.get(0);
        if  ( userDTO == null ) {
            return false;
        }
        this.userDTO = userDTO;
        return true;
    }

    public UserDTO getCurrentUser(){
        return this.userDTO;

    }


}
