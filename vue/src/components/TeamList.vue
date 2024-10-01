<template>
    <h1>Current Teams</h1>
    <div id="teamContainer">
        <div id="teamCards">
            <TeamCard v-for="team in teams" :key="team.teamId" v-bind:team="team" />
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



h1 {
    text-align: center;
    color: #58deff
}

#teamContainer {
    display: grid;
    grid-template-columns: 1fr, 1fr, 1fr;
    grid-template-areas:
        "teams teams teamForm"
        "teams teams teamForm";
}

#teamForm {
    grid-area: teamForm;
}

#teamCards {
    grid-area: teams;
}
</style>