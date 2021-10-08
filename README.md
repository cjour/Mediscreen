# Mediscreen

REST API allowing CRUD operation of Patient on sql database.

## Technical : 
1. JAVA : 11
2. Spring boot : v2.5.5
3. Docker-compose : v1.29.2
4. Maven : v3.6.3

## Deployment :
Make sure that Docker and Maven are installed on your system. <br />

run the CLI command : ``$ ./mvnw package && java -jar target/mediscreen.jar`` <br />

run the CLI command : ``$ docker-compose up --build -d``<br />

### Code coverage (JaCoCo report)
![Screenshot 2021-10-07 at 10-24-48 mediscreen](https://user-images.githubusercontent.com/61873476/136354402-7ac44d92-da13-4f53-9779-18ecd5fbb1bf.png)
