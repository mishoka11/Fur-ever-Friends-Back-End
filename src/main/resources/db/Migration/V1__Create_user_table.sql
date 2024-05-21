-- V1__Create_user_and_user_types.sql

-- Create user_types table
CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

-- Insert initial user types
INSERT INTO role (type_name) VALUES ('Client');
INSERT INTO role (type_name) VALUES ('Employee');
INSERT INTO role (type_name) VALUES ('Vet');

-- Alter users table to include user_type_id column
ALTER TABLE user ADD COLUMN user_type_id BIGINT NOT NULL;

-- Add foreign key constraint to user_type_id column
ALTER TABLE user ADD CONSTRAINT fk_user_type FOREIGN KEY (user_type_id) REFERENCES role(id);

-- Create user_roles table
CREATE TABLE user_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    UNIQUE (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES user(id)
);
