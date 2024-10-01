<template>
    <h1>Current Teams</h1>
    <div id="teamContainer">
        <section class="teamCardHeader"><span>Team</span><span>Captain</span></section>
        <div class="teamCards" v-for="team in teams" :key="team.teamId">
            <router-link v-bind:to="{name: 'teamDetails', params: {teamId:team.teamId}}">
            <TeamCard v-bind:team="team" />
            </router-link>
        </div>
        <div id="teamForm">
            <TeamForm></TeamForm>
        </div>
    </div>
    

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

    components: { TeamCard, TeamForm },


    created() {
        TeamService.getAllTeams().then((response) => {
            this.teams = response.data;
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

#teamContainer {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas:
        "header header header teamForm"
        "teams teams teams teamForm"
        "teams teams teams teamForm";
}

#teamForm{
    grid-area: teamForm;
    display: flex;
    justify-content: center;
}

#teamCards {
    grid-area: teams;
    color: #58deff;
}

.teamCardHeader {
    grid-area: header;
    display: flex;
    justify-content: space-between;
    color: #58deff;
}


</style>