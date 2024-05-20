-- Create user_types table
CREATE TABLE user_types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

-- Insert initial user types
INSERT INTO user_types (type_name) VALUES ('Client');
INSERT INTO user_types (type_name) VALUES ('Employee');
INSERT INTO user_types (type_name) VALUES ('Vet');

-- Alter users table to include user_type_id column
ALTER TABLE users ADD COLUMN user_type_id BIGINT NOT NULL;

-- Add foreign key constraint to user_type_id column
ALTER TABLE users ADD CONSTRAINT fk_user_type FOREIGN KEY (user_type_id) REFERENCES user_types(id);

-- Create user_roles table
CREATE TABLE user_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    UNIQUE (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES users(id)
);