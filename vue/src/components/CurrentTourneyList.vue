<template>
    <div class="tournament"></div>
    <h1>this page will display the current tournaments in the system</h1>
    <h2>clicking a tourney will give the actual brackets and the info</h2>
    <div>
        <router-link v-for="tourney in tournaments" :key="tourney.tourneyId"
            v-bind:to="{ name: 'tourneyDetails', params: { id: tourney.tourneyId } }">
            <TournamentCard v-bind:tourney="tourney" />
        </router-link>
    </div>
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

    created() {
        TourneyService.getCurrentTournaments().then((response) => {
            this.tournaments = response.data;
        })
    }
}
</script>

<style></style>