package be.inventj.notemanager.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Joeri Peeters
 */
@SpringBootApplication
@ComponentScan
public class NoteManagerApp {

	public static void main(String [] args){
		SpringApplication.run(NoteManagerApp.class, args);
	}
}
