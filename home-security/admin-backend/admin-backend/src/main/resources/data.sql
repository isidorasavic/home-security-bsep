-- Lozinke su hesovane pomocu BCrypt algoritma https://www.dailycred.com/article/bcrypt-calculator
-- Lozinka za oba user-a je 123

INSERT INTO USERS (first_name, last_name, username, password, role, deleted, blocked) VALUES
 ('Isidora', 'Savic', 'isidora', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'OWNER', false, false);
 INSERT INTO USERS (first_name, last_name, username, password, role, deleted, blocked) VALUES
 ('Isidora', 'Savic', 'isidora2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'ADMIN', false, false);
 INSERT INTO USERS (first_name, last_name, username, password, role, deleted, blocked) VALUES
 ('Isidora', 'Savic', 'isidora3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'TENANT', false, false);
 INSERT INTO USERS (first_name, last_name, username, password, role, deleted, blocked) VALUES
 ('Isidora', 'Savic', 'isidora4', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'OWNER', false, false);
 INSERT INTO USERS (first_name, last_name, username, password, role, deleted, blocked) VALUES
 ('Isidora5', 'Savic5', 'isidora5', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'TENANT', true, false);

insert into objects (name, type, owner_id) values ('kuca1', 'House', 1);
insert into objects (name, type, owner_id) values ('vikendica', 'VacationHome', 1);
insert into objects (name, type, owner_id) values ('Stan', 'Apartment', 4);
insert into objects (name, type, owner_id) values ('vila', 'House', 1);

insert into devices (name, object_id, type) values ('svetlo 1', 1, 'LIGHT');
insert into devices (name, object_id, type) values ('ulazna vrata ', 1, 'DOOR');

insert into object_messages (object_id, device_id, message_type, message, date, time) values (1, 1, 'MESSAGE', ':)', '2022-05-13', '12:12:00');
insert into object_messages (object_id, device_id, message_type, message, date, time) values (1, 1, 'ALARM', ':(', '2022-05-13', '13:12:00');
insert into object_messages (object_id, device_id, message_type, message, date, time) values (1, 1, 'WARNING', ':|', '2022-05-13', '15:12:00');
insert into object_messages (object_id, device_id, message_type, message, date, time) values (1, 2, 'MESSAGE', ':)', '2022-05-14', '12:12:00');
insert into object_messages (object_id, device_id, message_type, message, date, time) values (1, 2, 'ALARM', ':(', '2022-05-14', '13:12:00');
insert into object_messages (object_id, device_id, message_type, message, date, time) values (1, 2, 'WARNING', ':|', '2022-05-14', '15:12:00');
insert into object_messages (object_id, device_id, message_type, message, date, time) values (1, 2, 'MESSAGE', ':)', '2022-05-15', '12:12:00');
insert into object_messages (object_id, device_id, message_type, message, date, time) values (1, 1, 'ALARM', ':(', '2022-05-15', '13:12:00');
insert into object_messages (object_id, device_id, message_type, message, date, time) values (1, 1, 'WARNING', ':|', '2022-05-15', '15:12:00');
