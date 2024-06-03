package br.com.jmts;

import br.com.jmts.model.Person;
import br.com.jmts.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Arrays;



@EnableAutoConfiguration
@SpringBootApplication
public class GreenValleyV1Application implements CommandLineRunner{



	//@Autowired
	//private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(GreenValleyV1Application.class, args);
	}


	@Override
	public void run(String... args) throws  Exception{
		/*
		Person p1 =  new Person( "firstName1", "lastName1", "address1", "gender1");
		Person p2 =  new Person( "firstName2", "lastName2", "address2", "gender2");
		Person p3 =  new Person( "firstName3", "lastName3", "address3", "gender3");
		Person p4 =  new Person( "firstName4", "lastName4", "address4", "gender4");
		Person p5 =  new Person( "firstName5", "lastName5", "address5", "gender5");
		Person p6 =  new Person( "firstName6", "lastName6", "address6", "gender6");
		repository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6));
		 */
	}


}