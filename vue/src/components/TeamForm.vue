<template>
    <div class="team-form">

        <form>        
            
            <h1 id="team-form-title">Team Enrollment Form</h1>
        
            <p> Register your team to compete!</p>

            <div class="form-filling">
            <label for="team-name">Team name</label>
            <input type="text" id="team-name" name="team-name" v-model="team.teamName" required>
            </div>

            <div>
            <label for="gameInput">Game of choice</label>
            <input type="list" name="gameInput" list="gameInput" required v-model="team.gameId">
            <datalist id="gameInput" >
                <option v-bind:value="game.gameId" v-for="game in games" :key="game.gameId">{{ game.gameName }}</option>
            </datalist>
            </div>

            <!-- <div>
            <label for="total-teams">Number of Teams</label>
            <input type="list" id="total-teams" name="total-teams" required>
            </div> -->

            <div>
            <label for="num-of-players">Max players on your team</label>
            <input placeholder="Select # of players" type="number" min="1" name="num-of-players" required>
            </div>


            <div>
            <label for="accepting-teammates">Is your team accepting new teammates?</label>
            <div class="radio">
                <label for="is-accepting-yes">True</label>
                <input type="radio" id="is-accepting-yes" name="is-accepting" value="yes">
                <label for="is-accepting-no">False</label>
                <input type="radio" id="is-accepting-no" name="is-accepting" value="no">
            </div>

            </div>

            
            <button type="submit" id="form-submit">SUBMIT TEAM!</button>
        </form>
    </div>
</template>
<script>
import axios from 'axios';
import GamesService from '../services/GamesService.js';


export default {

    name: 'TeamForm',
    data() {
        return {
            team: {
                teamName: '',
                gameId: '',
                maxPlayers: '',
                isAccepting: ''
            },
            games: []
        }
    },
    methods: {
        submitTeam() {
            axios.post('http://localhost:9000/teams', this.team).then((response) => {
                console.log("Team created successfully", response.data);
            });

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

#team-form-title {
    margin-bottom: 20px;
    color: #000000

}

.team-form {
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
label, input, select{
    width: 100%;
    margin-bottom: 10px;
}
#form-submit {
    font-size: 17px;
    color: #B130FC;
    font-weight: bold;
    cursor: pointer;
}
.checkbox {
    display: flex;
    justify-content: center;
    align-items: center;
}

.radio {
    display: flex;
    justify-content: center;
    align-items: center;
}

#team-name {
    margin-bottom: 20px;
}

#num-of-players {
    margin-bottom: 20px;
}

#accepting-teammates {
    margin-bottom: 20px;
}

#preference {
    margin-bottom: 20px;
}


</style>