CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255)
);

/*Insert sample data*/
INSERT INTO users (name, email) VALUES
('John', 'jgXzT@example.com'),
('Jane', '9RqQK@example.com'),
('Bob', 'Yh9QI@example.com'),
('Alice', 'U8v6A@example.com'),
('Eve', 'ZoRZ9@example.com');
