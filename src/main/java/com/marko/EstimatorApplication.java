package com.marko;

import com.marko.model.Team;
import com.marko.model.User;
import com.marko.repository.TeamRepository;
import com.marko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EstimatorApplication {

	@Autowired
	private static TeamRepository teamRepository;

	@Autowired
	private static UserRepository userRepository;

	public static void main(String[] args) {
		//SpringApplication.run(EstimatorApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(EstimatorApplication.class, args);

		UserRepository userRepository = context.getBean(UserRepository.class);
		Team teamA = new Team("A");
		Team teamB = new Team("B");
		userRepository.save(new User("marko", "123", teamA, "marko@gmail.com"));
		userRepository.save(new User("teja", "123", teamB, "omaja@gmail.com"));
		userRepository.save(new User("simon", "123", teamB, "simon@gmail.com"));

	}
}
