<template>
    <div>
    
    <div id="teamContainer">    
        <div class="teamsDiv" >
            <h1 id="teams-title">CURRENT TEAMS</h1>
            
            <p v-if="isAuthenticated" id="click-team">Click a team to view details!</p>



            <div v-for="team in teams" :key="team.teamId">
                <div v-if="isAuthenticated">

                    <router-link
                    class="teamCardLink" 
                    v-bind:to="{ name: 'teamDetails', params: { teamId: team.teamId } }">
                    <TeamCard id="team-list" v-bind:team="team"/>
                    </router-link>

                </div>

                <div class="teamDisplayNA" v-else>
                
                    <p id="display-name">{{ team.teamName }}</p>
                    <p id="login-script">Log in to see team details!</p>
    
                </div>
    
            </div>
        </div>

        <div id="teamForm" v-if="isAuthenticated">
            <TeamForm></TeamForm>
        </div>



        <div v-else>
            <router-link to="/login" id="home-login-team">Log in to create a team!</router-link>
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

#home-login-team {
    display: flex;
    justify-content: center;
    font-size: 1.5em;
    color: #6ce7fd;
    text-decoration: none;
    background-color: rgba(255, 255, 255, 0.6);
    padding: 10px;
    border-radius: 10px;
    margin-left: 10%;
}



#click-team {
    margin-top: 0.1%;
    margin-bottom: 2%;
    font-size: 1.5em;
    color: #6ce7fd;
    width: 100%;

}
h1 {
    text-align: center;
    color: #B130FC;
    
}
#teamContainer {
    display: flex;
    
    gap: 10px;
    margin: 0 0px;
}
#teamForm {
    
    display: flex;
    justify-content: center;
    flex: 300px;
}
.teamsDiv{
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    background-color: rgba(255, 255, 255, 0.6);
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 20px;
    justify-content: center;
    width: 100%;    
    gap: 0px;
    margin-left: 20px;
}


#teams-title {
    text-align: center;
    margin-top: 2%;
    font-size: 2em;
    color: #000000;
    margin-left: 25%;
    margin-right: 25%;
    width: 100%;
}

#display-name {
    text-align: center;
    font-size: 1.5em;
    font-weight: bold;
    gap: 20px;
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
    margin: -70px;
    margin-right: 20px;

    border-radius: 10px;
    background-color: rgba(255, 255, 255, 0.6);
    font-weight: bold;
}

.teamCardLink {
    text-decoration: none;
    margin-bottom: 20px;

}

#team-list {
    margin-bottom: 20px;
    margin: 35px;
}

.teamDisplayNA {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 30px;
    border-radius: 10px;
}


</style>