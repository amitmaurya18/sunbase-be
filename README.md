# Crud Application foir customer in Spring Boot Login with Spring Security and JWT

## Dependency

    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.2</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.2</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.2</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>3.1.1</version>
		</dependency>

  ## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`

    spring.datasource.url=jdbc:mysql://localhost:3306/customer_db
    spring.datasource.username=amit18
    spring.datasource.password=Amit@1807
    server.port=9090

    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.main.allow-bean-definition-overriding=true

# JWT Configuration
    jwt.secret=gcgfcjgvcygjvgfcfgjcvhgjyvgjhcfcfgjghkvjgcftcgfxfxcifjyujhvcyrcdtrdxrxgcjygfgvyughvjntgfdgdfgfgrgrfegdgfegfregregregfvdfvdvgdsgdsgfdsgdsgdsgsdgdsgdsg

Enter your local datasource, username, password.


## Run Spring Boot application
```
mvn spring-boot:run

