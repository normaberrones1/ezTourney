BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('James Howard','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Rodger Remes','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Romulus','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO game_category(category_name) VALUES ('Traditional Sports');
INSERT INTO games(game_name, category_id) VALUES ('American Football', 1);
INSERT INTO games(game_name, category_id) VALUES ('Soccer (Football)', 1);
INSERT INTO games(game_name, category_id) VALUES ('Basketball', 1);
INSERT INTO games(game_name, category_id) VALUES ('Baseball', 1);
INSERT INTO games(game_name, category_id) VALUES ('Volleyball', 1);
INSERT INTO games(game_name, category_id) VALUES ('Cricket', 1);
INSERT INTO games(game_name, category_id) VALUES ('Rugby', 1);
INSERT INTO games(game_name, category_id) VALUES ('Field Hockey', 1);
INSERT INTO games(game_name, category_id) VALUES ('Ice Hockey', 1);

INSERT INTO game_category(category_name) VALUES ('Combat Sports');
INSERT INTO games(game_name, category_id) VALUES ('Boxing', 2);
INSERT INTO games(game_name, category_id) VALUES ('MMA', 2);
INSERT INTO games(game_name, category_id) VALUES ('Wrestling', 2);
INSERT INTO games(game_name, category_id) VALUES ('Judo', 2);
INSERT INTO games(game_name, category_id) VALUES ('Taekwondo', 2);
INSERT INTO games(game_name, category_id) VALUES ('Karate', 2);
INSERT INTO games(game_name, category_id) VALUES ('Kendo', 2);
INSERT INTO games(game_name, category_id) VALUES ('Aikido', 2);

INSERT INTO game_category(category_name) VALUES ('Board Games');
INSERT INTO games(game_name, category_id) VALUES ('Chess', 3);
INSERT INTO games(game_name, category_id) VALUES ('Checkers', 3);
INSERT INTO games(game_name, category_id) VALUES ('Go', 3);
INSERT INTO games(game_name, category_id) VALUES ('Scrabble', 3);
INSERT INTO games(game_name, category_id) VALUES ('Magic: The Gathering', 3);

INSERT INTO game_category(category_name) VALUES ('Video Games');
INSERT INTO games(game_name, category_id) VALUES ('League of Legends', 4);
INSERT INTO games(game_name, category_id) VALUES ('Dota 2', 4);
INSERT INTO games(game_name, category_id) VALUES ('Counter-Strike 2', 4);
INSERT INTO games(game_name, category_id) VALUES ('Fortnite', 4);
INSERT INTO games(game_name, category_id) VALUES ('Overwatch 2', 4);
INSERT INTO games(game_name, category_id) VALUES ('Valorant', 4);
INSERT INTO games(game_name, category_id) VALUES ('Apex Legends', 4);
INSERT INTO games(game_name, category_id) VALUES ('Call of Duty', 4);
INSERT INTO games(game_name, category_id) VALUES ('FIFA', 4);
INSERT INTO games(game_name, category_id) VALUES ('Rocket League',4 );
INSERT INTO games(game_name, category_id) VALUES ('StarCraft II', 4);
INSERT INTO games(game_name, category_id) VALUES ('Super Smash Bros', 4);
INSERT INTO games(game_name, category_id) VALUES ('Street fighter', 4);
INSERT INTO games(game_name, category_id) VALUES ('Tekken', 4);
INSERT INTO games(game_name, category_id) VALUES ('Hearthstone', 4);

INSERT INTO game_category(category_name) VALUES ('Racquet Sports');
INSERT INTO games(game_name, category_id) VALUES ('Squash',5 );
INSERT INTO games(game_name, category_id) VALUES ('Racquetball',5 );
INSERT INTO games(game_name, category_id) VALUES ('Tennis', 5);
INSERT INTO games(game_name, category_id) VALUES ('Badminton', 5);

INSERT INTO game_category(category_name) VALUES ('Outdoor Games');
INSERT INTO games(game_name, category_id) VALUES ('Ultimate Frisbee', 6);
INSERT INTO games(game_name, category_id) VALUES ('Cornhole', 6);
INSERT INTO games(game_name, category_id) VALUES ('Spikeball', 6);

INSERT INTO game_category(category_name) VALUES ('Motorsports');
INSERT INTO games(game_name, category_id) VALUES ('Formula 1', 7);
INSERT INTO games(game_name, category_id) VALUES ('NASCAR', 7);
INSERT INTO games(game_name, category_id) VALUES ('Motocross', 7);
INSERT INTO games(game_name, category_id) VALUES ('Drag racing', 7);

INSERT INTO game_category(category_name) VALUES ('Extreme Sports');
INSERT INTO games(game_name, category_id) VALUES ('Skateboarding', 8);
INSERT INTO games(game_name, category_id) VALUES ('Snowboarding', 8);
INSERT INTO games(game_name, category_id) VALUES ('Surfing', 8);
INSERT INTO games(game_name, category_id) VALUES ('Rock climbing', 8);

INSERT INTO game_category(category_name) VALUES ('Other Sports');
INSERT INTO games(game_name, category_id) VALUES ('Golf', 9);
INSERT INTO games(game_name, category_id) VALUES ('Billiards',9);
INSERT INTO games(game_name, category_id) VALUES ('Bowling', 9);
INSERT INTO games(game_name, category_id) VALUES ('Cycling', 9);

INSERT INTO teams(team_name, captain_id, game_id, isaccepting, max_players)
VALUES ('League of Cornholers', 4, 43, true, 16);

INSERT INTO teams(team_name, captain_id, game_id, isaccepting, max_players)
VALUES ('Champs of the Underground', 5, 22, true, 8);

INSERT INTO teams(team_name, captain_id, game_id, isaccepting, max_players)
VALUES ('Freaked Out', 3, 13, true, 12);

COMMIT TRANSACTION;
