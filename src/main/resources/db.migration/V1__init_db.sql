CREATE DATABASE IF NOT EXISTS person;
DROP TABLE IF EXISTS person.worker;
CREATE TABLE person.worker (
                               ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                               NAME VARCHAR(1000) NOT NULL,
                               BIRTHDAY DATE NOT NULL,
                               LEVEL ENUM('Trainee','Junior','Middle','Senior')NOT NULL,
                               SALARY INT NOT NULL,


                               CONSTRAINT name_check CHECK(LENGTH(NAME) >= 2),
                               CONSTRAINT birthday_check CHECK(BIRTHDAY >= '1900-01-01'),
                               CONSTRAINT salary_check CHECK(SALARY > 100 and SALARY < 100000)
);

DROP TABLE IF EXISTS person.client;
CREATE TABLE person.client(
                              ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                              NAME VARCHAR(1000) NOT NULL,

                              CONSTRAINT name_check_client CHECK(LENGTH(NAME) >= 2)
);

DROP TABLE IF EXISTS person.project ;
CREATE TABLE person.project(
                               ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                               CLIENT_ID INT,
                               START_DATE DATE NOT NULL,
                               FINISH_DATE DATE NOT NULL,

                               CONSTRAINT client_id FOREIGN KEY(CLIENT_ID) REFERENCES client(ID),
                               CONSTRAINT date_check CHECK(DATEDIFF(FINISH_DATE, START_DATE) > 30 and DATEDIFF(FINISH_DATE, START_DATE) <3000)
);

DROP TABLE IF EXISTS person.project_worker ;
CREATE TABLE person.project_worker(
                                      PROJECT_ID INT NOT NULL,
                                      WORKER_ID INT NOT NULL,
                                      PRIMARY KEY(PROJECT_ID, WORKER_ID),

                                      CONSTRAINT project_id FOREIGN KEY(PROJECT_ID)REFERENCES project(ID),
                                      CONSTRAINT worker FOREIGN KEY(WORKER_ID) REFERENCES worker(ID)
);

