DROP DATABASE IF NOT EXISTS transport_management_system;

CREATE DATABASE transport_management_system;

\c transport_management_system;



CREATE TABLE IF NOT EXISTS cities(
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS vehicles (
  id SERIAL PRIMARY KEY,
  registration VARCHAR(20) NOT NULL UNIQUE,
  model VARCHAR(50) NOT NULL,
  capacity INTEGER,
  speed DECIMAL(10,2),
  is_available BOOLEAN DEFAULT TRUE,
  photo VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  last_name VARCHAR(150) NOT NULL,
  first_name VARCHAR(200),
  password VARCHAR(200),
  phone_number VARCHAR(20),
  driver_license VARCHAR(20),
  email VARCHAR(100) UNIQUE,
  photo VARCHAR(255),
  place_id INTEGER REFERENCES places(id),
  role_id INTEGER REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS places (
  id SERIAL PRIMARY KEY,
  place_number INT,
  description TEXT,
  reservation_id INTEGER REFERENCES reservations(id)
);


CREATE TABLE IF NOT EXISTS reservations (
  id SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES users(id),
  reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);



CREATE TABLE IF NOT EXISTS routes (
   id SERIAL PRIMARY KEY,
   origin_city_id INTEGER REFERENCES cities(id),
   destination_city_id INTEGER REFERENCES cities(id),
   distance_km DECIMAL(10,2),
   estimated_duration_minutes INT
);

CREATE TABLE IF NOT EXISTS transports (
  id SERIAL PRIMARY KEY,
  vehicle_id INT NOT NULL,
  driver_id INT NOT NULL,
  route_id INT NOT NULL,
  customer_id INT NOT NULL,
  departure_date TIMESTAMP,
  arrival_date TIMESTAMP,
  status VARCHAR(50),
  FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
  FOREIGN KEY (driver_id) REFERENCES users(id),
  FOREIGN KEY (route_id) REFERENCES routes(id),
  FOREIGN KEY (customer_id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS expenses (
  id SERIAL PRIMARY KEY,
  description TEXT,
  amount DECIMAL(10,2),
  date TIMESTAMP,
  transport_id INTEGER REFERENCES transports(id)
);



CREATE TABLE IF NOT EXISTS payments (
  id SERIAL PRIMARY KEY,
  reservation_id INTEGER REFERENCES reservations(id),
  amount DECIMAL(10,2),
  payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  payment_method VARCHAR(100)
);

