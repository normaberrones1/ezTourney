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
</template>

<script>
import TourneyService from '../services/TourneyService';
export default {
    data() {
       return {
              tournament: {}
         }
    },
    methods: {
        getTournament() {
            TourneyService.getTournamentById(this.$route.params.id).then((response) => {
                this.tournament = response.data;
            });
        }
    },
    created() {
           this.getTournament();
    }
}
</script>

<style scoped>
.tourneyDetails {
    color:white;
    text-align: center;
    margin-top: 2em;
    padding: auto;
}
</style>
