<template>
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

<style>

</style>