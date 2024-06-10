package br.com.jmts.services;

import br.com.jmts.controllers.PersonController;
import br.com.jmts.data.vo.v1.PersonVO;
import br.com.jmts.exceptions.RequiredObjectIsNullException;
import br.com.jmts.exceptions.ResourceNotFoundException;
import br.com.jmts.mapper.Mapper;
import br.com.jmts.model.Person;
import br.com.jmts.repositories.PersonRepository;
import br.com.jmts.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServices implements UserDetailsService {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding one user by name" + username + "!");

        var user = repository.findByUsername(username);
        System.out.println("refresh token " + user.getUserName() + user.getPassword());
        if(user != null){
            System.out.println("222222222222");
            return user;
        } else {
            throw new UsernameNotFoundException("Username" + username + "not found!");
        }
    }

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }
}