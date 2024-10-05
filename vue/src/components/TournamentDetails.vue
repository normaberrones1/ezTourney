<template>

    <div class="tourneyDetails">
        <h1>Tourney Details</h1>
        <div><button class="tourneyEditBtn" v-if="displayEditButton" v-on:click="this.$router.push(`/tournaments/${tournament.tourneyId}/edit`)">Edit Tournament</button></div>
        <h3>{{ tournament.tourneyName }}</h3>
        <div id="start_date">Start Date: {{ tournament.startDate }}</div>
        <div id="end_date">End Date: {{ tournament.endDate }}</div>
        <div id="location">Location: {{ tournament.location }}</div>
        <div id="entry_fee">Entry Fee: ${{ tournament.entry_fee }}</div>
        <div id="prizeDesc">Prize: ${{ tournament.prizeDesc }}</div>
        <div id="tourneyDesc">{{ tournament.tourneyDesc }}</div>
        <div id="gameId">Game ID: {{ tournament.gameId }}</div>
        <div id="round">Round: {{ tournament.round }}</div>
        <div id="winner">Winner ID: {{ tournament.winner }}</div>
    
        <div class="tourney-button" >
            <label for="teamsList">Which of Your Teams Should Join?</label>
            <input id="input-team" placeholder="Choose Team" type="list" name="teamsList" list="teamInput" required v-model="teamChoice">
            <datalist id="teamInput">
                <option v-for="team in myTeamsList" :key="team.teamID" v-bind:value="team.teamId" >{{ team.teamName }}</option>
            </datalist>
            <button id="tourney-request" v-on:click="requestTournamentJoin()">Request to Join Tournament!</button>
        </div>

        <TourneyRequestForm v-if="showModal" @close="showModal = false"/>
    </div>
</template>



<script>
import TourneyService from '../services/TourneyService';
import TourneyRequestForm from './TournamentRequestForm.vue';
import TeamService from '../services/TeamService';

export default {
    data() {
       return {
              tournament: {},
              showModal: false,
              displayEditButton: false,
              teamChoice: '',
              myTeamsList: []
         }
    },
    methods: {
        getTournament() {
            TourneyService.getTournamentById(this.$route.params.id).then((response) => {
                this.tournament = response.data;
            });
        },
        requestTournamentJoin() {
            TourneyService.requestTournamentJoin(this.$route.params.id,this.teamChoice).then((response) => {
                if(response.data) {
                    alert("Request to join tournament sent!");
                } else {
                    alert("Request to join tournament failed!");
                }
            })
        },
        setEditBtnVisible() {
            TourneyService.isUserDirector(this.$route.params.id).then((response) => {
                this.displayEditButton = response.data;
            });
        }
    },
    components: {TourneyRequestForm},

    created() {
        this.getTournament(); 
        this.setEditBtnVisible();
        TeamService.teamsImCaptain().then((response) => {
            this.myTeamsList = response.data;
        })
    },

}
</script>

<style scoped>

#tourney-button {
    display: flex;
    justify-content: center;
    align-items: center;
}

#tourney-request {
    display: block;
    background-color: rgba(255, 255, 255, 0.6);
    color: #b130fc;
    font-weight: bold;
    font-size: 30px;
    border-radius: 10px;
    margin: 10px auto;
    cursor: pointer;
    text-align: center;
    font-size: 20px;
}
    
.tourneyDetails {
    color:black;
    text-align: center;
    margin-top: 2em;
    padding: auto;
    border: 1px solid rgb(124, 124, 124);
    background-color: rgba(255, 255, 255, 0.6);
    margin-left: 20%;
    margin-right: 20%;
    border-radius: 10px;
}

.tourneyEditBtn{
    background-color: rgba(255, 255, 255, 0.6);
    color: #b130fc;
    font-weight: bold;
    font-size: 30px;
    border-radius: 10px;
    margin: 10px auto;
    cursor: pointer;
    text-align: center;
    font-size: 20px;
}

#input-team {
    width: 50%;
    margin: 10px;
    text-align: center;
    border-radius: 10px;
    border: 1px solid rgb(124, 124, 124);
    font-size: 17px;
}

</style>

