<template>
    <div class="team"></div>
    <h1>this page will display a list of teams</h1>
    <p>clicking oon the team will show current tournaments and the captain</p>
    <div v-for="team in teams" :key="team.teamId">
        <teamCard v-bind:team="team"/>
    </div>

</template>

<script>
import TeamCard from './TeamCard.vue';
import TeamService from '../services/TeamService.js';


export default {
    data() {
        return {
            teams: [],
        }
    },

    components: [TeamCard],

    created(){

    }
}

</script>