BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS team_users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE teams (
    team_id SERIAL,
    team_name varchar(50) NOT NULL,
    captain_id int NOT NULL,
    sport varchar(50) NOT NULL,
    isAccepting bit NOT NULL,
    CONSTRAINT pk_team PRIMARY KEY (team_id),
    CONSTRAINT fk_captain FOREIGN KEY (captain_id) REFERENCES users(user_id)
);

CREATE TABLE team_users (
    user_id int NOT NULL,
    team_id int NOT NULL,
    CONSTRAINT pk_team_users PRIMARY KEY (user_id, team_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_team_id FOREIGN KEY (team_id) REFERENCES teams(team_id)
);



COMMIT TRANSACTION;
