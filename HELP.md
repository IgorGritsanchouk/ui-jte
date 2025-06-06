docker compose up

simplified spring security login:
user     pwd: user_password
admin    pwd: admin_password
manager  pwd: manager_password

--- using mvn wrapper ---
PS C:\APP_DEV\ui-jte> mvn.cmd clean package
PS C:\APP_DEV\ui-jte> ./mvnw.cmd clean package    ( using maven wrapper )
PS C:\APP_DEV\ui-jte> java -jar target/ui-jte-0.0.1-SNAPSHOT.jar
PS C:\APP_DEV\ui-jte> java "-Dspring.profiles.active=prod" -jar target/ui-jte-0.0.1-SNAPSHOT.jar

-- TO USE GRAAL first download GRAAL VM jdk and use it instead also change ENV VAR JAVA_HOME=
to point to GRAALVM jdk in order to create a native image for Windows in my case!!!
--- https://www.graalvm.org/release-notes/JDK_21/
PS C:\APP_DEV\ui-jte> java -version
java version "21.0.5" 2024-10-15 LTS
Java(TM) SE Runtime Environment Oracle GraalVM 21.0.5+9.1 (build 21.0.5+9-LTS-jvmci-23.1-b48)
Java HotSpot(TM) 64-Bit Server VM Oracle GraalVM 21.0.5+9.1 (build 21.0.5+9-LTS-jvmci-23.1-b48, mixed mode, sharing)

--- GRAAL native image related ----
PS C:\APP_DEV\ui-jte> ./mvnw.cmd clean package
PS C:\APP_DEV\ui-jte> ./mvnw.cmd -Pnative native:compile
PS C:\APP_DEV\ui-jte> ./target/ui-jte "-Dspring.profiles.active=prod"
PS C:\APP_DEV\ui-jte> ./target/ui-jte "-Dspring.profiles.active=prod"
!!!!!!!!!!!
PS C:\APP_DEV\ui-jte> ./mvnw.cmd -Pnative native:compile
BASH
./mvnw -Pnative native:compile -Dspring.profiles.active=prod
WINDOWS
PS C:\APP_DEV\ui-jte> ./mvnw.cmd -Pnative native:compile "-Dspring.profiles.active=prod"

---  to run executable file with prod settings  ---
PS C:\APP_DEV\ui-jte> ./target/ui-jte "-Dspring.profiles.active=prod"

install docker and then run
PS C:\APP_DEV\java-template-engine>docker compose up
to instantiate postgres data base as a container, to check tables and stored proc use link below:
http://localhost:8081/browser/
postgress client access url:
user:admin@admin.admin
pwd: admin
postgres  user: admin   password: password

TEST with CURL -- in PowerShell
PS C:\> curl.exe -X GET http://localhost/user-name?userName=user
{"userName":"user","email":"user@gmail.com","password":"$2a$10$BgjnlVEUe8VYVF.YTSd4zuT7vAE8UxUUy0BZJxstTatdvamu.ooYm","enabled":true}

Need to add tables and data in schema29032025.XXXsql  to the database
-------   SP calls to oracle db  ---------
--- use postgress db instead of oracle ---
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres_db
spring.datasource.username=admin
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

# Spring Security JDBC Authentication  // tutorial on using database
https://www.youtube.com/watch?v=d7ZmZFbE_qY&t=1017s

// It was a requirement to use postgresDB, so we switched to postgres instead of oracle
https://www.youtube.com/watch?v=DMU-8tNaeGI
## connecting to oracle db
#spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/oracle-xe-cnr
#spring.datasource.username=system
#spring.datasource.password=Password123
#spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
Added one func and one stored proc to insert user and select from users
for given country. Just a test on how proc anf functions can be added to postgres_db
---   func and stored proceadure call  ----
CALL add_user('mark','avreliy', 'igor@gmail.com', 'England', 'Flat 7 Chappel Arches', 'Maidenhead', 'Berkshire','SL61RU');
SELECT * FROM GET_USERS_BY_COUNTRY('England');

To run the application docker desktop is required to be installed
1) docker compose up       --  to start postgres database
2) And then start spring boot application  localhost:81 will display jte Uhttp://localhost:81
3) http://localhost:81 -- with vertical menu
3) http://localhost:81/user-form-vm    -- with vertical menu
3)  http://localhost:81/home  -- without vertical menu
    http://localhost:8081/login?next=/browser/
    user:  admin@admin.admin
    pswd:  admin
    -- pg db client ---
    download native Graal jdk from :
    https://www.graalvm.org/release-notes/JDK_21/
    -- native compile  POINT to: JAVA_HOME=C:\Program Files\Java\graalvm-jdk-21.0.5+9.1
    PS C:\APP_DEV\ui-jte> ./mvnw -Pnative native:compile
4)  PS C:\APP_DEV\ui-jte> ./target/ui-jte "-Dspring.profiles.active=prod"
    PS C:\APP_DEV\ui-jte\target> start ui-jte.exe "-Dspring.profiles.active=prod" ??
4) 
---  docker related ---
PS C:\APP_DEV\ui-jte> docker build -f Dockerfile -t ui-jte-img:v0.1 .
PS C:\APP_DEV\ui-jte> docker build -f Dockerfile -t igr025/ui-jte-21-img:v0.1 .
PS C:\APP_DEV\ui-jte> docker push igr025/ui-jte-21-img:v0.1

PS C:\APP_DEV\ui-jte> docker build -f Dockerfile -t ui-jte-21-img:v0.1 .
PS C:\APP_DEV\ui-jte> docker tag ui-jte-21-img:v0.1 igr025/ui-jte-21-img:v0.1
PS C:\APP_DEV\ui-jte> docker push igr025/ui-jte-21-img:v0.1
b39f5adcc6d9: Pushed

-- running 2 containers with nginx proxy and API gatway setup --
PS C:\APP_DEV\ui-jte> docker compose up --scale ui-jte=2

    ---  USE mvn wrapper package to install node and npm ---
    PS C:\APP_DEV\java-template-engine> ./mvnw compile
    PS C:\APP_DEV\java-template-engine> ./mvnw clean package

# 1. --- Before running from a jar file in production ---
PS C:\APP_DEV\java-template-engine>
java -jar target/java-template-engine-0.0.1-SNAPSHOT.jar

# 2. --- USE production jte precompiled settings ---
gg.jte.development-mode=false
gg.jte.developmentMode=false
gg.jte.usePrecompiledTemplates=true

---- pages to access ----
http://localhost/save-user-template
http://localhost:81/to-do

-----------------------------------------
---- tailwind css added to project ----
-----------------------------------------
install node and npm
PS C:\APP_DEV\java-template-engine> node -v
v16.13.1
PS C:\APP_DEV\java-template-engine> npm -v
8.1.2
PS C:\APP_DEV\java-template-engine> mkdir src/main/frontend
PS C:\APP_DEV\java-template-engine> cd src/main/frontend
--- create a project in frontend folder ---
PS C:\APP_DEV\java-template-engine\src\main\frontend> npm init
New major version of npm available! 8.1.2 -> 11.0.0
npm notice Run npm install -g npm@11.0.0 to update!
PS C:\APP_DEV\java-template-engine\src\main\frontend> npm install -D tailwindcss
added 112 packages, and audited 113 packages in 6s
PS C:\APP_DEV\java-template-engine\src\main\frontend> npx tailwindcss init
---  adds tailwind.config.js  to frontend folder ---
PS C:\APP_DEV\java-template-engine\src\main\frontend> npm run build
PS C:\APP_DEV\java-template-engine\src\main\frontend> npm run watch

###  DOCKER related
PS C:\APP_DEV\ui-jte> docker build -f Dockerfile -t ui-jte-21-img:v0.1 .
PS C:\APP_DEV\ui-jte> docker run -p 80:80 ui-jte-21-img:v01

// docker compose logs -f ui-jte1    // to check logs of first server
// docker compose logs -f ui-jte2    // docker rm -f   //stops one of the containers
// watch -n 1 curl -s localhost:80    // not yet tried
// docker compose down && docker compose up -d    // hot refresh nginx configuration
// curl -s localhost:80
docker compose up   now, as 3 instances are specified in compose.yml
//docker compose up --scale ui-jte=2  this does not work

--- docker image for load balancing ---
docker pull nginx:stable-alpine3.20-perl
--  nginx related:   https://www.youtube.com/watch?v=9aOpRhm33oM

####  MONGO DB with DOCKER ####
image: mongo:8.0         ---- 3.9 gig
docker compose logs --tail 100 -f          ----- linux
docker compose logs -f
Clean Up Old Containers/Volumes (if configs changed)
Docker may be using cached volumes with old credentials.
docker-compose down -v
docker-compose up --build

http://localhost/api/v1/products     -- rest api for mongo

### Reference Documentation
http://127.0.0.1:8081     url to connect to ui

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.1/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.1/maven-plugin/build-image.html)
* [JTE](https://jte.gg/)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.1/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

## JTE

This project has been configured to use [JTE precompiled templates](https://jte.gg/pre-compiling/).

However, to ease development, those are not enabled out of the box.
For production deployments, you should remove

```properties
gg.jte.development-mode=true
```

from the `application.properties` file and set

```properties
gg.jte.use-precompiled-templates=true
```

instead.
For more details, please take a look at [the official documentation](https://jte.gg/spring-boot-starter-3/).

### Git related
PS C:\APP_DEV\ui-jte> git add .
PS C:\APP_DEV\ui-jte> git commit -m "added translations to orders page"
PS C:\APP_DEV\ui-jte> git push origin main

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
========   Stored Proc and Func  examples which could be called from DAO.java class ====
--------  worked ok. just an investigation  ----------
http://localhost:81/employee-orders?employeeId=1
