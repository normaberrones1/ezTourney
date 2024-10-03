<template>

    <div>
    
        <h1 id="tournaments-title">CURRENT TOURNAMENTS</h1>
        <h2 id="click-tourney">Click a tournament to view details</h2>
        <div id="tournament-container">
        <div class="tourneyDiv">
            <router-link v-for="tourney in tournaments" :key="tourney.tourneyId"
                v-bind:to="{ name: 'tournamentDetails', params: { id: tourney.tourneyId } }">
                <TournamentCard v-bind:tourney="tourney" />
            </router-link>
        </div>

        <div>
            <TourneyForm></TourneyForm>
        </div>

</div>

    </div>


</template>

<script>
import TournamentCard from './TournamentCard.vue';
import TourneyService from '../services/TourneyService';
import TourneyForm from './TourneyForm.vue';

export default {
    data() {
        return {
            tournaments: [],
        }
    },
    props: {
        tourney: {
            type: Object,
            required: true
        }
    },
    
    components: { TournamentCard, TourneyForm },

    created() {
        TourneyService.getCurrentTournaments().then((response) => {
            console.log(response.data);
            this.tournaments = response.data;
        })
    }

}
</script>

<style>
body {
    background-image: url('/public/teams-background.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    
}

h1 {
    text-align: center;
    color: #58deff;
}

#tournament-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas:
        "tourney tourney tourney tourneyForm"
        "tourney tourney tourney tourneyForm";
}

#tourneyForm {
    display: flex;
    justify-content: center;
}


.tourneyDiv {
    grid-area: tourney;
    background-color: rgba(255, 255, 255, 0.2);
    padding: 25px;
    border: 1px solid rgb(124, 124, 124);
    border-radius: 10px;
}

#tournaments-title {
    position: relative;
    right: 12.3%;
    margin-top: 2%;
    margin-bottom: 2%;
    font-size: 2em;
    color: #B130FC;
}

#click-tourney {
    position: relative;
    right: 12.3%;
    margin-top: 2%;
    margin-bottom: 2%;
    font-size: 1.5em;
    text-align: center;
    color: #58deff;
}
</style>