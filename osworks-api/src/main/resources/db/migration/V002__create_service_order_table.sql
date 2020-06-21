CREATE TABLE IF NOT EXISTS service_order (
    id INTEGER PRIMARY KEY,
    description TEXT NOT NULL,
    status TEXT NOT NULL,
    opened_date INTEGER NOT NULL,
    finished_date INTEGER,
    price REAL NOT NULL,
    client_id INTEGER NOT NULL,
    FOREIGN KEY(client_id) REFERENCES client(id)
);