BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS team_users;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS game_category;
DROP TABLE IF EXISTS tournament;
DROP TABLE IF EXISTS team_tourney;
DROP TABLE IF EXISTS tourney_matches;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE game_category(
    category_id SERIAL,
    category_name varchar(50) NOT NULL,
    CONSTRAINT PK_category PRIMARY KEY (category_id)
);

CREATE TABLE games (
    game_id SERIAL,
    game_name varchar(50) NOT NULL,
    category_id int NOT NULL,
    CONSTRAINT PK_games PRIMARY KEY (game_id),
    CONSTRAINT FK_category FOREIGN KEY (category_id) REFERENCES game_category(category_id)
);

CREATE TABLE teams (
    team_id SERIAL,
    team_name varchar(50) NOT NULL,
    captain_id int NOT NULL,
    game_id int NOT NULL,
    isAccepting boolean NOT NULL,
    max_players int NOT NULL,
    CONSTRAINT PK_team PRIMARY KEY (team_id),
    CONSTRAINT FK_captain FOREIGN KEY (captain_id) REFERENCES users(user_id),
    CONSTRAINT FK_game FOREIGN KEY (game_id) REFERENCES games(game_id)
);

CREATE TABLE team_users (
    user_id int NOT NULL,
    team_id int NOT NULL,
    accepted boolean NOT NULL,
    CONSTRAINT PK_team_users PRIMARY KEY (user_id, team_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_team_id FOREIGN KEY (team_id) REFERENCES teams(team_id)
);

CREATE TABLE tournament (
    tourney_id SERIAL,
    tourney_name varchar(50) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    location varchar(50) NOT NULL,
    entry_fee int NOT NULL,
    prize_desc varchar(100) NOT NULL,
    tourney_desc varchar(500) NOT NULL,
    game_id int NOT NULL,
    director_id int NOT NULL,
    is_complete boolean NOT NULL,
    round int NOT NULL,
    CONSTRAINT PK_tourney PRIMARY KEY (tourney_id),
    CONSTRAINT FK_game FOREIGN KEY (game_id) REFERENCES games(game_id),
    CONSTRAINT FK_director FOREIGN KEY (director_id) REFERENCES users(user_id)
);

CREATE TABLE team_tourney (
    team_id int NOT NULL,
    tourney_id int NOT NULL,
    isAccepted boolean NOT NULL,
    eliminated boolean NOT NULL,
    round_eliminated int,
    CONSTRAINT PK_team_tourney PRIMARY KEY (team_id, tourney_id),
    CONSTRAINT FK_team FOREIGN KEY (team_id) REFERENCES teams(team_id),
    CONSTRAINT FK_tourney FOREIGN KEY (tourney_id) REFERENCES tournament(tourney_id)
);

CREATE TABLE tourney_matches (
    tourney_id int NOT NULL,
    team_1_id int NOT NULL,
    team_2_id int NOT NULL,
    set_winner int,
    team_1_points int NOT NULL,
    team_2_points int NOT NULL,
    CONSTRAINT PK_tourney_matches PRIMARY KEY (tourney_id, team_1_id, team_2_id),
    CONSTRAINT FK_team_1 FOREIGN KEY (team_1_id) REFERENCES teams(team_id),
    CONSTRAINT FK_team_2 FOREIGN KEY (team_2_id) REFERENCES teams(team_id),
    CONSTRAINT FK_tourney FOREIGN KEY (tourney_id) REFERENCES tournament(tourney_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

COMMIT TRANSACTION;
