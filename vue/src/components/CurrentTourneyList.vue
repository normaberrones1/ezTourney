<template>
    <div class="tournament"></div>
    <h1>this page will display the current tournaments in the system</h1>
    <h2>clicking a tourney will give the actual brackets and the info</h2>
    <router-link>
    <TournamentCard />
    </router-link>
</template>

<script>
import TournamentCard from './TournamentCard.vue';
import TourneyService from '../services/TourneyService';

export default {
    data() {
        return {
            tournaments: [],
        }
    },

    components: { TournamentCard },

    created(){
        TourneyService.getCurrentTournaments().then((response) => {
            this.tournaments = response.data;
        })
    }
}
</script>

<style></style>