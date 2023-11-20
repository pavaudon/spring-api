CREATE TABLE IF NOT EXISTS books (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  author VARCHAR(250) NOT NULL
);
 
INSERT INTO books (title, author) VALUES
  ('1984', 'George Orwell'),
  ('Percy Jackson : Le Voleur de foudre', 'Rick Riordan'),
  ('Les Fables de La Fontaine', 'Jean de La Fontaine');