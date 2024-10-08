<template>
    <div class="tourney-form">

        <form v-on:submit="submitTourney()">
            <h1 id="tourney-form-title">Create Tournament</h1>

            <div>
                <label for="tourney-name">Tournament Name</label>
                <input type="text" id="tourney-name" v-model="tourney.tourneyName" required>
            </div>


            <div>
            <label for="gameInput">Game of choice</label>
            <select id="gameInput" v-model="selectedGameName" @change="handleGameChange">
                <option disabled value="">Please select one</option>
                <option v-for="game in games" :key="game.gameId" :value="game.gameName">{{ game.gameName }}</option>
            </select>
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

            <div>
                <label for="isPrivate">Is Private ? </label>
                <input type="checkbox" id="isPrivate" v-model="tourney.private">
            </div>

            <div>
                <label for="singlesEvent">Is Single Event ? </label>
                <input type="checkbox" id="singlesEvent" v-model="tourney.singlesEvent">
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
                startDate: null,
                endDate: null,
                location: '',
                entry_fee: '',
                prizeDesc: '',
                tourneyDesc: '', 
                gameId: '',
                private: false,
                singlesEvent: false
            },
            games: [],
            selectedGameName: '',
        }
    },

    computed: {
        filteredGames() {
            if (!this.selectedGameName) {
                return this.games;
            }
            return this.games.filter(game => game.gameName.toLowerCase().includes(this.selectedGameName.toLowerCase()));
        }
    },

    methods: {
        submitTourney() {


            TourneyService.createTournament(this.tourney)
                .then(response => {
                    console.log("Tournament created successfully", response);
                })
        },

        handleGameChange() {
            const game = this.games.find(game => game.gameName === this.selectedGameName);
            if (game) {
                this.tourney.gameId = game.gameId;
            }
        }
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
    background-color: rgba(255, 255, 255, 0.6);
    color: #000000;
    font-weight: bold;
    margin-bottom: 20px;
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