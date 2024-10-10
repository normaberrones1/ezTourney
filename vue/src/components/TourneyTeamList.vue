<template>
    <div>
        <div id="team-container">
            <h1>Teams</h1>
            <table id="team-table">
                <thead>
                    <tr>
                        <th v-if="isTourneyDirector">Status</th>
                        <th>Team name</th>
                        <th>Eliminated</th>
                        <th>Round eliminated</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="team in this.displayableTeams" :key="team.teamId" >
                        <td v-if="isTourneyDirector && team.accepted">Accepted</td>
                        <td v-if="isTourneyDirector && !team.accepted">
                            <button class="acceptBtn" v-on:click="handleAccept(team.teamId, true)">Accept</button>
                        </td>                       
                        <td>{{ team.teamName }}</td>
                        <td>{{ team.isEliminated ? 'Yes': 'No'}}</td>
                        <td>{{ team.roundEliminated === 0 ?'': team.roundEliminated  }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import TourneyService from '../services/TourneyService'


export default {
    data() {
        return{
                teams: [],
                isTourneyDirector: false
        }  
    },
    computed: {
        displayableTeams(){
            return this.isTourneyDirector ? this.teams: this.teams.filter(team => team.accepted);
        }
    },
    created(){
        this.setIsTourneyDirector();
        this.getTournamentTeams(this.$route.params.id);
    },
    methods:{
        getTournamentTeams(tourneyId){
            TourneyService.getTournamentTeams(tourneyId).then((response) => {
                this.teams = response.data;
            });
        },
        setIsTourneyDirector() {
            TourneyService.isUserDirector(this.$route.params.id).then((response) => {
                this.isTourneyDirector = response.data;
                console.log(this.isTourneyDirector);
            });
        },
        handleAccept(teamId){
            TourneyService.acceptTeam(teamId,this.$route.params.id).then((response) => {
                    if(response.data){
                        this.getTournamentTeams(this.$route.params.id);
                    }    
            });
        }
    }

}
</script>

<style scoped>
#team-container h1{
    text-align: center;
    color: #010708;  
}
#team-container {
    text-align: center;
    margin-top: 2em;
    padding: auto;
    background-color: rgba(255, 255, 255, 0.5);
}
#team-table {
    width: 100%;
    border-collapse: collapse;
    border: 1px solid rgb(124, 124, 124);
}

#team-list th {
    background-color: #94dcee93;
    color: black;
    border-radius: 10px;
}

#team-table th {
    background-color: rgb(255, 255, 255);
    font-weight: bold;
    font-size: 1.5em;
}

#team-table td {
    font-size: 20px;
}
.acceptBtn {
    background-color:rgba(255, 255, 255, 0.6);
    color: #b130fc;
    cursor: pointer;
    width: 150px;
    font-size: 1em;
    font-weight: bold;
    height: 2em;
    text-align: center;
    margin: 2px;
}

</style>
