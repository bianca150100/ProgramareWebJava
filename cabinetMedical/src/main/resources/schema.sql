use cabinetmedical;


CREATE TABLE if not exists users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_type VARCHAR(50),
    loggedStatus INT,

    PRIMARY KEY (id)
);

CREATE TABLE if not exists doctor_available_time (
    id BIGINT NOT NULL AUTO_INCREMENT,
    sunday_program VARCHAR(50) NOT NULL,
    monday_program VARCHAR(50) NOT NULL,
    tuesday_program VARCHAR(50) NOT NULL,
    wednesday_program VARCHAR(50) NOT NULL,
    thursday_program VARCHAR(50) NOT NULL,
    friday_program VARCHAR(50) NOT NULL,
    saturday_program VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE if not exists doctor_details (
    id BIGINT NOT NULL AUTO_INCREMENT,
    specialization VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,
    experience INT,

    PRIMARY KEY (id)
);

CREATE TABLE if not exists doctors (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    email VARCHAR(50),

    PRIMARY KEY (id)
);

CREATE TABLE if not exists doctors_doctor_details (
    id BIGINT NOT NULL AUTO_INCREMENT,
    doctors BIGINT NOT NULL,
    doctor_details BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (doctors) REFERENCES doctors(id),
    FOREIGN KEY (doctor_details) REFERENCES doctor_details(id)
);

CREATE TABLE if not exists doctors_doctor_available_time (
    id BIGINT NOT NULL AUTO_INCREMENT,
    doctors BIGINT NOT NULL,
    doctor_available_time BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (doctors) REFERENCES doctors(id),
    FOREIGN KEY (doctor_available_time) REFERENCES doctor_available_time(id)
);

CREATE TABLE if not exists appointments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data DATE NOT NULL,
    hour VARCHAR(50) NOT NULL,
    notes VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE if not exists appointments_doctors (
    id BIGINT NOT NULL AUTO_INCREMENT,
    appointments BIGINT NOT NULL,
    doctors BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (appointments) REFERENCES appointments(id),
    FOREIGN KEY (doctors) REFERENCES doctors(id)
);

CREATE TABLE if not exists appointments_users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    appointments BIGINT NOT NULL,
    users BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (appointments) REFERENCES appointments(id),
    FOREIGN KEY (users) REFERENCES users(id)
);

CREATE TABLE if not exists donationRequests (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    hospital VARCHAR(50),
    description VARCHAR(50),
    blood_type VARCHAR(50),
    number INT,
    data DATE,

    PRIMARY KEY (id)
);

CREATE TABLE if not exists requestdonations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    hospital VARCHAR(50),
    description VARCHAR(50),
    blood_type VARCHAR(50),
    number INT,
    data DATE,

    PRIMARY KEY (id)
);

CREATE TABLE if not exists donationAppointments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data DATE NOT NULL,
    hour VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE if not exists donationAppointments_requestDonations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    appointments BIGINT NOT NULL,
    requestDonations BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (appointments) REFERENCES appointments(id),
    FOREIGN KEY (requestDonations) REFERENCES requestDonations(id)
);

CREATE TABLE if not exists donationAppointments_users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    appointments BIGINT NOT NULL,
    users BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (appointments) REFERENCES appointments(id),
    FOREIGN KEY (users) REFERENCES users(id)
);