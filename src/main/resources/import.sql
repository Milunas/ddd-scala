CREATE TABLE IF NOT EXISTS patron_database_entity (
    id INTEGER IDENTITY PRIMARY KEY,
    overdue_checkouts INTEGER
);

CREATE TABLE IF NOT EXISTS book_database_entity (
    id INTEGER IDENTITY PRIMARY KEY,
    book_state VARCHAR(100) NOT NULL,
    on_hold_by_patron INTEGER IDENTITY FOREIGN KEY REFERENCES patron_database_entity,
    on_hold_till TIMESTAMP
);
