<template>
    <div>
    <h1 id="teams-title">CURRENT TEAMS</h1>
    <div id="teamContainer">
        <div class="teamsDiv" >
            <router-link
            class="teamCardLink" v-for="team in teams" :key="team.teamId"
            v-bind:to="{ name: 'teamDetails', params: { teamId: team.teamId } }">
                <TeamCard v-bind:team="team" />
            </router-link>
        </div>
        <div id="teamForm">
            <TeamForm></TeamForm>
        </div>
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
    color: #B130FC;
}
#teamContainer {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas:
        "teams teams teams teamForm"
        "teams teams teams teamForm";
}
#teamForm {
    grid-area: teamForm;
    display: flex;
    justify-content: center;
}
.teamsDiv{
    grid-area: teams;
    background-color: rgba(255, 255, 255, 0.2);
    padding: 25px;
    border: 1px solid rgb(124, 124, 124);
    border-radius: 10px;
}
#teams-title {
    position: relative;
    right: 12.3%;
    margin-top: 2%;
    margin-bottom: 2%;
    font-size: 2em;
    color: #B130FC;
}
</style>