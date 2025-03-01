<template>
    <div id="main-div">
        <div class="Leader">
            <h1>{{ team.teamName }}</h1>
        </div>

        <div id="match-title">MATCHES</div>
        <div id="match-tourney-container">
            <div class="matches-wins-losses">
                <div id="win">
                    <p>WINS</p> 
                    <p id="wins">{{matchWinLoss.wins}}</p>
                </div>
                <div id="loss">
                    <P>LOSSES</P> 
                    <p id="losses">{{matchWinLoss.losses}}</p>
                </div>
            </div>

            <div class="tournaments-wins-losses">
                <div id="win">
                    <p>WINS</p> 
                    <p id="wins">{{tourneyWinsLoss.wins}}</p>
                </div>
                <div id="loss">
                    <P>LOSSES</P> 
                    <p id="losses">{{tourneyWinsLoss.losses}}</p>
                </div>
            </div>
        </div>

        <div class="button-container">
            <button id="team-request" v-on:click="requestTeamJoin()" v-if="isTeamCaptain === false">Request to Join Team!</button>
            <button id="edit-team" v-on:click="this.$router.push(`/teams/${teamId}/edit`)" v-if="isTeamCaptain === true">Edit Team/Accept Requests to Join</button>
        </div>

        <TeamRequestForm v-if="showModal" @close="showModal = false"/>

        <div class="display-team">
            <div class="players">
                <h2 id="players-title">Team Players</h2>
                <h3 id="captain-name">{{ captain.username }}</h3>

                <div class="Members" v-for="user in members" :key="user.userId">
                    <MemberCard v-bind:user="user"/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TeamService from '../services/TeamService.js';
import MemberService from '../services/MemberService.js';
import MemberCard from './MemberCard.vue';
import TeamRequestForm from './TeamRequestForm.vue';
import TourneyService from '../services/TourneyService.js';

export default {
    data() {
        return {
            showModal: false,
            teamId: -1,
            team: {
                wins: 0,
                losses: 0,
            },
            captain: {},
            members: [],
            tourneyWinsLoss: {},
            matchWinLoss: {},
            isTeamCaptain: false,
        }
    },

    components: { MemberCard, TeamRequestForm },

    created() {
        this.teamId = this.$route.params.teamId;
        TeamService.getTeamById(this.teamId).then((response) => {
            this.team = response.data;
            MemberService.getMemberById(this.team.captainId).then((response) => {
                this.captain = response.data;
            });
        });
        MemberService.getMembersByTeam(this.teamId).then((response) => {
            this.members = response.data;
        });
        TourneyService.getTournamentWinsAndLosses(this.teamId).then((response) => {
            this.tourneyWinsLoss = response.data;
        });
        TourneyService.getMatchWinsAndLosses(this.teamId).then((response) => {
            this.matchWinLoss = response.data;
        });
        TeamService.amITeamCaptain(this.teamId).then((response) => {
            this.isTeamCaptain = response.data;
        });
    },

    methods: {
        requestTeamJoin() {
            TeamService.requestJoinTeam(this.teamId).then((response) => {
                if(response.data) {
                    alert("Request to join sent!");
                } else {
                    alert("Request to join failed!");
                }
            });
        }
    }
}
</script>

<style>
#main-div {
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    padding: 50px;
    text-align: center;
    z-index: 1;
    position: relative;
}

#button-container {
    display: flex;
    flex-direction: column; /* Make the buttons stack vertically */
    justify-content: center;
    align-items: center;
    margin-top: 20px;
}

#team-request, #edit-team {
    display: block;
    background-color: rgba(255, 255, 255, 0.6);
    color: #b130fc;
    font-weight: bold;
    font-size: 20px;
    border-radius: 10px;
    margin: 10px auto;
    cursor: pointer;
    text-align: center;
    padding: 10px 20px;
}

#match-title, #tourney-title {
    text-align: center;
    color: #000000;
    font-weight: bold;
    font-size: 30px;
    margin-top: 20px;
}

#match-tourney-container {
    display: flex;
    justify-content: space-between;
    gap: 20px; 
    margin-top: 20px;
}

.matches-wins-losses, .tournaments-wins-losses {
    display: flex;
    justify-content: space-around;
    font-size: 25px;
    font-weight: bold;
    text-align: center;
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    padding: 20px;
    width: 48%; 
    
}

#win {
    color: #14e4ff;
    font-weight: bold;
    padding: 20px;
}

#loss {
    color: #a51f6d;
    font-weight: bold;
    padding: 20px;

}

.display-team {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin-top: 20px;
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    padding: 20px;
}

.players {
    text-align: center;
}

#players-title {
    color: #000000;
}

.Members {
    color: #b130fc;
}

#captain-name {
    color: #58deff;
    text-align: center;
}
</style>
