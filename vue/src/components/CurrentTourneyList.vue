<template>

<div class="tournament">
        <h1>this page will display the current tournaments in the system</h1>
        <h2>clicking a tourney will give the actual brackets and the info</h2>
        <div>
            <router-link v-for="tourney in tournaments" :key="tourney.tourneyId"
                v-bind:to="{ name: 'tournamentDetails', params: { id: tourney.tourneyId } }">
                <TournamentCard v-bind:tourney="tourney" />
            </router-link>


        </div>

        
    <!-- <div>
    <div>

        <h1 id="tourneys-title">CURRENT TOURNEYS</h1>
        <div id="tourneyContainer">
            <div class="tourneysDiv">
                <router-link
                class="tourneyCardLink" v-for="tourney in tournaments" :key="tourney.tourneyId"
                    v-bind:to="{ name: 'tourneyDetails', params: { id: tourney.tourneyId } }">
                    <TournamentCard v-bind:tourney="tourney" />
                </router-link>
            </div>
        </div>
          


    </div> -->
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


    components: { TournamentCard, TourneyForm },

    created() {
        TourneyService.getCurrentTournaments().then((response) => {
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
    color: #B130FC;
}

#tourneyContainer {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas:
        "teams teams teams teamForm"
        "teams teams teams teamForm";
}

#tourneyForm {
    grid-area: teamForm;
    display: flex;
    justify-content: center;
}

.tourneysDiv {
    grid-area: teams;
    background-color: rgba(255, 255, 255, 0.2);
    padding: 25px;
    border: 1px solid rgb(124, 124, 124);
    border-radius: 10px;
}

#tourneys-title {
    position: relative;
    right: 12.3%;
    margin-top: 2%;
    margin-bottom: 2%;
    font-size: 2em;
    color: #B130FC;
}


</style>