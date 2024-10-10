<template>

    <div class="tourneyDetails">
        <h1>Tournament Details</h1>
        <div><button class="tourneyEditBtn" v-if="displayEditButton" v-on:click="this.$router.push(`/tournaments/${tournament.tourneyId}/edit`)">Edit Tournament</button></div>
        <h2 class="tourneyTitle">{{ tournament.tourneyName }}</h2>
        <div class="tourneyDetailsGrid">
            <div class="fieldLabel">Start Date </div>
            <div class="fieldValue">{{ tournament.startDate }}</div>

            <div class="fieldLabel">End Date </div>
            <div class="fieldValue"> {{ tournament.endDate }}</div>

            <div class="fieldLabel">Location</div>
            <div class="fieldValue"> {{ tournament.location }}</div>

            <div class="fieldLabel">Entry Fee ($)</div>
            <div class="fieldValue">${{ tournament.entry_fee }}</div>

            <div class="fieldLabel">Prize</div>
            <div class="fieldValue">{{ tournament.prizeDesc }}</div>

            <div class="fieldLabel">Description </div>
            <div class="fieldValue">{{ tournament.tourneyDesc }}</div>

            <div class="fieldLabel">Game</div>
            <div class="fieldValue">{{ tournament.gameName }}</div>

            <div class="fieldLabel">Rounds</div>
            <div class="fieldValue">{{ tournament.round }}</div>

            <div class="fieldLabel">Singles Event</div>
            <div class="fieldValue">{{ tournament.singlesEvent ? 'Yes' : 'No' }}</div>

            <div class="fieldLabel">Private</div>
            <div class="fieldValue">{{ tournament.private ? 'Yes' : 'No' }}</div>

            <div class="fieldLabel">Winner</div>
            <div class="fieldValue" v-if="tournament.singlesEvent">{{ tournament.winningUserName }}</div>
            <div class="fieldValue" v-if="!tournament.singlesEvent">{{ tournament.winningTeamName }}</div>
           
        </div>
        <div class="tourneyJoinReq" >
            <div  v-if="!tournament.singlesEvent">
                <div class="choose-team-joining">
                <label  for="teamsList">Which of your teams should join this tournament?</label>
                </div>
                <br/>
                <select id="selectTeam" v-model="teamChoice" required>
                    <option value="" disabled selected>Select your team</option>
                    <option v-for="myTeam in myTeamsList" :key="myTeam.teamId" v-bind:value="myTeam.teamId" >{{ myTeam.teamName }}</option>
                </select>
            </div>
            <button id="tourney-request" v-on:click="this.requestTournamentJoin()">Join Tournament</button>
        </div>
        <tourney-team-list v-if="!tournament.singlesEvent" />
        <tourney-users-list v-if="tournament.singlesEvent" />

        <BuildBracket v-bind:teams="acceptedTeams" v-bind:numTeams="acceptedTeams.length"></BuildBracket>
    </div>
</template>



<script>
import TourneyService from '../services/TourneyService';
import TeamService from '../services/TeamService';
import TourneyTeamList from './TourneyTeamList.vue';
import TourneyUsersList from './TourneyUsersList.vue';
import BuildBracket from './BuildBracket.vue';


export default {
    data() {
       return {
              tournament: {},
              showModal: false,
              displayEditButton: false,
              teamChoice: '',
              myTeamsList: [],
              teams: [],
         }
    },
    methods: {
        getTournament() {
            TourneyService.getTournamentViewById(this.$route.params.id).then((response) => {
                this.tournament = response.data;
            });
        },
        requestTournamentJoin() {

            if(this.tournament.singlesEvent) {
                TourneyService.requestUserJoinTourney(this.$route.params.id).then((response) => {
                    if(response.data) {
                        alert("Request to join tournament sent!");
                    } else {
                        alert("Request to join tournament failed!");
                    }
                });
                return;
            }

            if(!this.tournament.singlesEvent && this.teamChoice === '') {
                alert("Please select a team to join the tournament!");
                return;
            } else {
                TourneyService.requestTeamJoinTourney(this.$route.params.id,this.teamChoice).then((response) => {
                    if(response.data) {
                        alert("Request to join tournament sent!");
                    } else {
                        alert("Request to join tournament failed!");
                    }
                });
            }
        },
        setEditBtnVisible() {
            TourneyService.isUserDirector(this.$route.params.id).then((response) => {
                this.displayEditButton = response.data;
            });
        },
        getAllMyTeams(){
            TeamService.teamsImCaptain().then((response) => {
                this.myTeamsList = response.data;
            })
        } 
    },
    components: { TourneyTeamList, TourneyUsersList, BuildBracket},
    created() {
        this.getTournament(); 
        this.setEditBtnVisible();
        this.getAllMyTeams();
        
        TourneyService.getTournamentTeams(this.$route.params.id).then((response) => {
                this.teams = response.data;
            });

    },
    computed: {
        acceptedTeams(){
            return this.teams.filter((team) => {
                return team.accepted;
            })
        }
    }

}
</script>

<style scoped>

.tourneyJoinReq {
    margin-top: 30px;
}

#tourney-request {
    display: block;
    background-color: rgba(255, 255, 255, 0.6);
    color: #b130fc;
    font-weight: bold;
    font-size: 30px;
    border-radius: 10px;
    margin: 10px auto;
    cursor: pointer;
    text-align: center;
    font-size: 20px;
}
    
.tourneyDetails {
    color:black;
    text-align: center;
    padding: auto;
    border: 1px solid rgb(124, 124, 124);
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
}

.tourneyDetails h1 {
    text-align: center;
    color: #010708;
    border: 1px solid rgb(124, 124, 124);
    background-color: rgba(255, 255, 255, 0.5);
    border-radius: 10px;
}

.tourneyEditBtn{
    background-color: rgba(255, 255, 255, 0.6);
    color: #b130fc;
    font-weight: bold;
    font-size: 30px;
    border-radius: 10px;
    margin: 15px auto;
    cursor: pointer;
    text-align: center;
    font-size: 20px;
}

.tourneyDetailsGrid{
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-gap: 10px;
    margin: 10px;
}

.fieldLabel{
  padding-left: 30px;
  grid-column-start: 2;
  font-weight: bold;
  text-align: right;
  padding-right: 20px;
}

.fieldValue{
  grid-column-start: 3;
  text-align: left;
  padding-left: 20px;
}

.tourneyTitle{
    font-weight: bold;
}

#selectTeam{
    width: 30%;
    border: 1px solid rgb(124, 124, 124);
    text-align: center;
    height: 25px;
    border-radius: 10px;
}

.choose-team-joining{
    position: reltive;
    text-align: center;
    margin-left: 30%;
    margin-right: 30%;
    
    font-weight: bold;
}


</style>

