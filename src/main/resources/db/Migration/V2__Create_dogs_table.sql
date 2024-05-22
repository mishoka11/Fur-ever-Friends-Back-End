-- V2__Create_dogs_and_dog_sizes.sql

-- Create dog_size table
CREATE TABLE dog_size (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    size ENUM('SMALL', 'MEDIUM', 'LARGE') NOT NULL
);

-- Create dogs table
CREATE TABLE dog (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    breed VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    dog_years INT GENERATED ALWAYS AS (
        CASE
            WHEN age = 1 THEN 15
            WHEN age = 2 THEN 24
            ELSE 24 + (age - 2) * 4
        END
    ) STORED,
    info VARCHAR(255),
    size_id BIGINT NOT NULL,
    FOREIGN KEY (size_id) REFERENCES dog_size(id)
);
