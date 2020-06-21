CREATE TABLE IF NOT EXISTS comment (
    id INTEGER PRIMARY KEY,
    description TEXT NOT NULL,
    date INTEGER NOT NULL,
    service_order_id INTEGER NOT NULL,
    FOREIGN KEY(service_order_id) REFERENCES service_order(id)
);