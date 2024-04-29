CREATE TABLE conference (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    topic VARCHAR(255) NOT NULL,
    start DATETIME NOT NULL,
    end DATETIME NOT NULL,
    participants_count INT NOT NULL
);
