INSERT INTO BUILDING(id,outside_temperature) VALUES(-10,10.0);
INSERT INTO ROOM(id, name, floor, current_temperature, target_temperature,building_id) VALUES(-10, 'Hall', 1, 22.3, 20.0,-10);
INSERT INTO ROOM(id, name, floor,building_id) VALUES(-9, 'Amphi A', -10,-10);

INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-10, 'ON', 'Heater1', 2000, -10);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-9, 'ON', 'Heater2', null, -10);

INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-10, 'CLOSED', 'Fixed window 1', -10);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-9, 'CLOSED', 'Sliding window 2', -10);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-8, 'OPEN', 'Sliding window 3', -9);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-7, 'CLOSED', 'Fixed window 4', -9);