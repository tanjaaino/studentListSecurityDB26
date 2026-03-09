package fi.haagahelia.course.StudentListSecureDB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.StudentListSecureDB.domain.Department;
import fi.haagahelia.course.StudentListSecureDB.domain.DepartmentRepository;
import fi.haagahelia.course.StudentListSecureDB.domain.Student;
import fi.haagahelia.course.StudentListSecureDB.domain.StudentRepository;
import fi.haagahelia.course.StudentListSecureDB.domain.AppUser;
import fi.haagahelia.course.StudentListSecureDB.domain.AppUserRepository;

@SpringBootApplication
public class StudentListSecureDBApplication {
	
	private static final Logger log = LoggerFactory.getLogger(StudentListSecureDBApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StudentListSecureDBApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner studentDemo(StudentRepository studentRepository, 
			DepartmentRepository departmentRepository, AppUserRepository appUserRepository) {
		return (args) -> {
			log.info("save a couple of students");
			departmentRepository.save(new Department("IT"));
			departmentRepository.save(new Department("Business"));
			departmentRepository.save(new Department("Law"));
			
			studentRepository.save(new Student("John", "Johnson", "john@john.com", departmentRepository.findByName("IT").get(0)));
			studentRepository.save(new Student("Katy", "Kateson", "kate@kate.com", departmentRepository.findByName("Business").get(0)));	
			
			// Create users: admin/admin user/user
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			appUserRepository.save(user1);
			appUserRepository.save(user2);
			
			log.info("fetch all students");
			for (Student student : studentRepository.findAll()) {
				log.info(student.toString());
			}

		};
	}

}
