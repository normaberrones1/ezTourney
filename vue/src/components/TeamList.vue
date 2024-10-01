<template>
    <div class="team"></div>
    <h1>Current Teams</h1>
    <div v-for="team in teams" :key="team.teamId">
        <TeamCard v-bind:team="team"/>
    </div>
    <TeamForm></TeamForm>

    


</template>

<script>
import TeamCard from './TeamCard.vue';
import TeamService from '../services/TeamService.js';
import TeamForm from './TeamForm.vue';


export default {

    data() {
        return {
            teams: [],
        }
    },

    components: {TeamCard, TeamForm},
    

    created(){
        TeamService.getAllTeams().then((response) => {
            this.teams = response.data;
        })
    }
}

</script>

<style>
body {
    background-image: url('/public/teams-background.jpg');
}

h1 {
    text-align: center;
    color:  #58deff
}

.team-form {
    display: fixed;
}



</style>