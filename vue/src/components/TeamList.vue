<template>
    <div>
    <h1 id="teams-title">CURRENT TEAMS</h1>
    <p v-if="isAuthenticated" id="click-team">Click a team to view details!</p>
    
    <div id="teamContainer">
        
        <div class="teamsDiv" >
            <div v-for="team in teams" :key="team.teamId">
                <div v-if="isAuthenticated">
                    <router-link
                    class="teamCardLink" 
                    v-bind:to="{ name: 'teamDetails', params: { teamId: team.teamId } }">
                    <TeamCard id="team-list" v-bind:team="team"/>
                    
                    </router-link>
                </div>
                <div v-else>
                
                    <p id="display-name">{{ team.teamName }}</p>
                    <p id="login-script">Log in to see team details!</p>
    
                    </div>
                    
            </div>
        </div>

        <div id="teamForm" v-if="isAuthenticated">
            <TeamForm></TeamForm>
        </div>



        <div v-else>
            <router-link to="/login" id="home-login">Log in to create a Team!</router-link>
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
    },

    computed: {
        isAuthenticated() {
            return this.$store.state.token != '';
        }
    }
}
</script>
<style>

#click-team {
    position: relative;
    right: 12.3%;
    margin-top: 2%;
    margin-bottom: 2%;
    font-size: 1.5em;
    text-align: center;
    color: #000000;
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    margin-left: 235px;
    margin-right: 250px;
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
    margin-left: 32px;
    margin-right: 15px;
}
#teamForm {
  
    display: flex;
    justify-content: center;
}
.teamsDiv{
    grid-area: teams;
    background-color: rgba(255, 255, 255, 0.6);
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
    color: #000000;
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    margin-left: 205px;
    margin-right: 220px;
}

#display-name {
    text-align: center;
    font-size: 1.5em;
    color: #000000;
    font-weight: bold;

}

#login-script {
    text-align: center;
    font-size: 1.0em;
    color: #B130FC;
}

#home-login {
    color: #000000;
    text-decoration: none;
    display: flex;
    justify-content: center;
    padding: 10px;
    margin: -60px;
    border-radius: 10px;
    background-color: rgba(255, 255, 255, 0.7);
    border: 1px solid rgb(124, 124, 124);
    font-weight: bold;
}

.teamCardLink {
    text-decoration: none;
}

#team-list {
    margin-bottom: 20px;
}
</style>