<template>
    <div class="tourney-form">

        <form v-on:submit="submitTourney()">
            <h1 id="tourney-form-title">Create Tournament</h1>

            <div>
                <label for="tourney-name">Tournament Name</label>
                <input type="text" id="tourney-name" v-model="tourney.tourneyName" required>
            </div>


            <div>
                <label for="tourneyGameInput">Game of choice</label>
                <input type="list" name="tourneyGameInput" list="tourneyGameInput" required v-model="tourney.gameId">
                <datalist id="tourneyGameInput">
                    <option v-bind:value="game.gameId" v-for="game in games" :key="game.gameId">{{ game.gameName }}
                    </option>
                </datalist>
            </div>


            <div>
                <label for="start-date">Start Date</label>
                <input type="date" id="start-date" v-model="tourney.startDate" required>
            </div>

            <div>
                <label for="end-date">End Date</label>
                <input type="date" id="end-date" v-model="tourney.endDate" required>
            </div>

            <div>
                <label for="location">Location</label>
                <input type="text" id="location" v-model="tourney.location" required>
            </div>

            <div>
                <label for="entry-fee">Entry Fee</label>
                <input type="number" id="entry-fee" v-model="tourney.entry_fee" required min="1">
            </div>

            <div>
                <label for="prize-description">Prize Description</label>
                <input type="text" id="prize-description" v-model="tourney.prizeDesc" required>
            </div>

            <div>
                <label for="tourney-description">Tournament Description</label>
                <input type="text" id="tourney-description" v-model="tourney.tourneyDesc" required>
            </div>

            <button type="submit" id="form-submit">CREATE TOURNAMENT!</button>

        </form>
    </div>
</template>

<script>

import TourneyService from '../services/TourneyService';
import GamesService from '../services/GamesService';

export default {
    name: 'TourneyForm',
    data() {
        return {
            tourney: {
                tourneyName: '',
                startDate: '',
                endDate: '',
                location: '',
                entry_fee: '',
                prizeDesc: '',
                tourneyDesc: '', 
                gameId: ''
            },
            games: []
        }
    },

    methods: {
        submitTourney() {
            TourneyService.createTournament(this.tourney)
                .then(response => {
                    console.log("Tournament created successfully", response);
                })
        },
    },

    created() {
        GamesService.getAllGames().then((response) => {
            this.games = response.data;
        });
    },
}
</script>

<style>
#tourney-form-title {
    margin-bottom: 20px;
    color: #000000
}

.tourney-form {
    position: relative;
    width: 50%;
    text-align: center;
    padding: 40px;
    border: 1px solid rgb(124, 124, 124);
    border-radius: 10px;
    background-color: rgba(255, 255, 255, 0.2);
    color: #000000;
    font-weight: bold;
}

form {
    display: flex;
    flex-direction: column;
    align-items: center;
    
}

label,
input {
    width: 100%;
    margin-bottom: 10px;
}

#form-submit {
    font-size: 17px;
    color: #B130FC;
    font-weight: bold;
    cursor: pointer;
}


#tourney-name {
    margin-bottom: 20px;
}

#gameInput {
    margin-bottom: 20px;
}

#start-date {
    margin-bottom: 20px;
}

#end-date {
    margin-bottom: 20px;
}

#location {
    margin-bottom: 20px;
}

#entry-fee {
    margin-bottom: 20px;
}

#prize-description {
    margin-bottom: 20px;
}
</style>