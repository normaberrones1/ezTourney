BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS team_users;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS game_category;

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
    CONSTRAINT PK_team_users PRIMARY KEY (user_id, team_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_team_id FOREIGN KEY (team_id) REFERENCES teams(team_id)
);




COMMIT TRANSACTION;
