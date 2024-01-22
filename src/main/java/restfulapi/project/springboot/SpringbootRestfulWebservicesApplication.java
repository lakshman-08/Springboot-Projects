package restfulapi.project.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "Spring boot REST API Documentation",
				description = "Spring Boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Lakshman Sai",
						email = "lakshmansai9978@gmail.com",
						url="https://www.linkedin.com/in/muppina-lakshman-sai-996b25233/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.linkedin.com/in/muppina-lakshman-sai-996b25233/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = "https://www.linkedin.com/in/muppina-lakshman-sai-996b25233/"

		)
)
public class SpringbootRestfulWebservicesApplication {

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
