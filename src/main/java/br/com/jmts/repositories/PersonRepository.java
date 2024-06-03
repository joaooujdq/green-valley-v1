package br.com.jmts.repositories;


import br.com.jmts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PersonRepository extends JpaRepository<Person, Long>{}
