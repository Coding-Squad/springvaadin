package com.mano.springvaadinapp1.springvaadin;
import com.mano.springvaadinapp1.springvaadin.model.Employee;
import com.mano.springvaadinapp1.springvaadin.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure
        .SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class Application {
    private static final Logger log =
            LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(EmployeeRepository
                                             repository) {
        return (args) -> {
            repository.save(new Employee("Mansur Ali",
                    "12345678","mansur@gmail.com"));
            repository.save(new Employee("Rahul Dev",
                    "987456321","rahul@gmail.com"));
            repository.save(new Employee("Rahman Manjil",
                    "963852123","manjil@fastmail.com"));
            repository.save(new Employee("Rumana Hauque",
                    "741852654","rumana@yahoo.com"));
            repository.save(new Employee("Akram Khan",
                    "321654987","akram@outlook.com"));
            // Fetch all employees
            log.info("Employees found with findAll():");
            log.info("-------------------------------");
            for (Employee e : repository.findAll()) {
                log.info(e.toString());
            }
            log.info("");
            Employee employee= repository.findById(1L).get();
            log.info("Employee found with findOne(1L):");
            log.info("--------------------------------");
            log.info(employee.toString());
            log.info("");
        };
    }
}