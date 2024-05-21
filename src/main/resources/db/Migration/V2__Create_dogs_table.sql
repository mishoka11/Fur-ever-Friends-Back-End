CREATE TABLE dogs (
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
    info VARCHAR(255)
);

CREATE TABLE dog_size
(
    id int NOT NULL AUTO_INCREMENT,
    dog_id int NOT NULL,
    size varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (dog_id, size),
    FOREIGN KEY (dog_id) REFERENCES dogs (id)
);

    #size ENUM('small', 'medium', 'large') NOT NULL,