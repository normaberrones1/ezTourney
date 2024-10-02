BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('James Howard','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Rodger Remes','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Romulus','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Frank Ocean','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Charli XcX','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Dorian Electra','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Saul Goodman','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Ryan Gosling','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Howls Castle','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');



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

INSERT INTO team_users (user_id, team_id, accepted) VALUES (4,1,true);
INSERT INTO team_users (user_id, team_id, accepted) VALUES (5,2,true);
INSERT INTO team_users (user_id, team_id, accepted) VALUES (3,3,true);
INSERT INTO team_users (user_id, team_id, accepted) VALUES (6,3,true);
INSERT INTO team_users (user_id, team_id, accepted) VALUES (7,3,false);
INSERT INTO team_users (user_id, team_id, accepted) VALUES (8,2,true);
INSERT INTO team_users (user_id, team_id, accepted) VALUES (9,2,true);
INSERT INTO team_users (user_id, team_id, accepted) VALUES (10,1,true);
INSERT INTO team_users (user_id, team_id, accepted) VALUES (11,1,true);

INSERT INTO tournament (tourney_name, start_date, end_date, location, entry_fee,
prize_desc, tourney_desc, game_id, director_id, round)
VALUES ('Tournament TITLE WOOOO', '2024-10-4', '2024-10-6', 'Columbus OH', 200,
'500 V-Bucks', 'Win v-bucks in this exciting matchup, coming to you over the first weekend of october',
18, 9, 1);

INSERT INTO tournament (tourney_name, start_date, end_date, location, entry_fee,
prize_desc, tourney_desc, game_id, director_id, round, winner_id)
VALUES ('Ultimate Face off', '2024-10-18', '2024-10-18', 'Columbus OH', 200,
'My Catalytic Converter', 'I will rip my catalytic converter out of my car for whomever wins this tournament',
13, 2, 1, 3);

INSERT INTO tournament (tourney_name, start_date, end_date, location, entry_fee,
prize_desc, tourney_desc, game_id, director_id, round)
VALUES ('Tournament Past due', '2024-7-12', '2024-7-15', 'Columbus OH', 200,
'200 Fleas', 'This tournament has gained so much dust that its grown fleas',
25, 4, 2);

INSERT INTO team_tourney (team_id, tourney_id, isAccepted, eliminated)
VALUES (2,1, false, false);

INSERT INTO team_tourney (team_id, tourney_id, isAccepted, eliminated)
VALUES (3,1, true, false);

INSERT INTO team_tourney (team_id, tourney_id, isAccepted, eliminated)
VALUES (1,1, true, true);

INSERT INTO team_tourney (team_id, tourney_id, isAccepted, eliminated)
VALUES (1, 2, true, false);

COMMIT TRANSACTION;
