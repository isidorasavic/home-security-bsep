-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO USERS (first_name, last_name, username, password, role, deleted) VALUES
 ('Isidora', 'Savic', 'isidora', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'OWNER', false);
 INSERT INTO USERS (first_name, last_name, username, password, role, deleted) VALUES
 ('Isidora', 'Savic', 'isidora2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'ADMIN', false);
 INSERT INTO USERS (first_name, last_name, username, password, role, deleted) VALUES
 ('Isidora', 'Savic', 'isidora3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'TENANT', false);
 INSERT INTO USERS (first_name, last_name, username, password, role, deleted) VALUES
 ('Isidora', 'Savic', 'isidora4', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'OWNER', false);
 INSERT INTO USERS (first_name, last_name, username, password, role, deleted) VALUES
 ('Isidora5', 'Savic5', 'isidora5', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'TENANT', true);

insert into objects (name, type, owner_id) values ('kuca1', 'House', 1);
insert into objects (name, type, owner_id) values ('kuca2', 'House', 1);
insert into objects (name, type, owner_id) values ('kuca3', 'House', 1);
insert into objects (name, type, owner_id) values ('kuca4', 'House', 1);
