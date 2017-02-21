CREATE TABLE Authors (
  id NUMBER AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(32),
  last_name VARCHAR(32)
);

CREATE TABLE Books (
  book_id NUMBER AUTO_INCREMENT PRIMARY KEY,
  book_title VARCHAR(256) NOT NULL,
  book_year VARCHAR(8),
  author_id NUMBER NOT NULL,
  FOREIGN KEY (author_id) REFERENCES Authors (id)
);

INSERT INTO Authors (first_name, last_name) VALUES ('Leo', 'Tolstoy');
INSERT INTO Authors (first_name, last_name) VALUES ('Stephen', 'King');
INSERT INTO Authors (first_name, last_name) VALUES ('Alexander', 'Pushkin');
INSERT INTO Books (book_title, book_year, author_id) VALUES ('War and Peace', '1888', 1);
INSERT INTO Books (book_title, book_year, author_id) VALUES ('It', '1988', 2);
INSERT INTO Books (book_title, book_year, author_id) VALUES ('Eugene Onegin', '1889', 3);