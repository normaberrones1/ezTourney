<template>
    <div class="tourneyDetails">
        <div>Tourney Details</div>
        <h1>{{ tournament.name }}</h1>
        <div id="start_date">Start Date: {{ tournament.startDate }}</div>
        <div id="end_date">End Date: {{ tournament.endDate }}</div>
        <div id="location">Location: {{ tournament.location }}</div>
        <div id="entry_fee">Entry Fee: ${{ tournament.entryFee }}</div>
        <div id="prize">Prize: ${{ tournament.prize }}</div>
        <div id="tourney_desc">{{ tournament.tourneyDesc }}</div>
        <div id="game_id">Game ID: {{ tournament.gameId }}</div>
        <div id="director_id">Director ID: {{ tournament.directorId }}</div>
        <div id="round">Round: {{ tournament.rounds }}</div>
        <div id="winner_id">Winner ID: {{ tournament.winnerId }}</div>
    </div>

    <div class="tourney-button" >
        <button id="tourney-request" v-on:click="requestTournamentJoin()">Request to Join Tournament!</button>
    </div>

    <TourneyRequestForm v-if="showModal" @close="showModal = false"/>

</template>



<script>
import TourneyService from '../services/TourneyService';
import TourneyRequestForm from './TournamentRequestForm.vue';

export default {
    data() {
       return {
              tournament: {},
              showModal: false,
         }
    },
    methods: {
        getTournament() {
            TourneyService.getTournamentById(this.$route.params.id).then((response) => {
                this.tournament = response.data;
            });

        },
        requestTournamentJoin() {
            TourneyService.requestTournamentJoin(this.tournament.id).then((response) => {
                if(response.data) {
                    alert("Request to join tournament sent!");
                } else {
                    alert("Request to join tournament failed!");
                }
            })
        }
    },
    components: {TourneyRequestForm},

    created() {
        this.getTournament();
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
    background-color: rgba(255, 255, 255, 0.2);
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
    color:white;
    text-align: center;
    margin-top: 2em;
    padding: auto;
}

</style>

