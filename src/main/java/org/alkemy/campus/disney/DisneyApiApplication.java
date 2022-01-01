package org.alkemy.campus.disney;

import java.util.Iterator;
import org.alkemy.campus.disney.model.Character.FictionalCharacter;
import org.alkemy.campus.disney.repositories.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DisneyApiApplication {

	private static final Logger log = LoggerFactory.getLogger(DisneyApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DisneyApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CharacterRepository repository) {
		return (args) -> {

			long id = 1L;

			Iterator<FictionalCharacter> iterator = repository.findAll().iterator();
			// save a few characters
			if (!iterator.hasNext()) {
				log.info("Saving Characters...");
				repository.save(new FictionalCharacter("Tony"));
				repository.save(new FictionalCharacter("Strange"));
				repository.save(new FictionalCharacter("Peter"));
				repository.save(new FictionalCharacter("Thor"));
				log.info("Characters saved");
				log.info("");
			} else {
				id = iterator.next().getId();
			}

			// fetch all characters
			log.info("Characters found with findAll():");
			log.info("-------------------------------");
			for (FictionalCharacter character : repository.findAll()) {
				log.info(character.toString());
			}
			log.info("");

			// fetch an individual character by ID
			FictionalCharacter character = repository.findById(id).get();
			log.info("FictionalCharacter found with findById(" + id + "):");
			log.info("--------------------------------");
			log.info(character.toString());
			log.info("");

			// fetch characters by last name
			log.info("FictionalCharacter found with findByName('Tony'):");
			log.info("--------------------------------------------");
			repository.findByName("Tony").forEach(tony -> {
				log.info(tony.toString());
			});

			log.info("");
		};
	}

}
