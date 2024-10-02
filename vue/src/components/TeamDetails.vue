<template>
    <div>
    <div class="Leader">
        <h1>{{ team.teamName }}</h1>
    </div>


    <div id="match-title">MATCHES</div>
    <div class="matches-wins-losses">
        <div id="win">
            <p>WINS</p> 
            <p id="wins">{{}}0</p>
        </div>


        <div id="loss">
            <P>LOSSES</P> 
            <p id="losses">{{}}0</p>
        </div>
    </div>



    <div id="tourney-title">TOURNAMENTS</div>
    <div class="tournaments-wins-losses">
        <div id="win">
            <p>WINS</p> 
            <p id="wins">{{}}0</p>
        </div>


        <div id="loss">
            <P>LOSSES</P> 
            <p id="losses">{{}}0</p>
        </div>
    </div>

    <div class="button-container">
        <button type="button" id="team-request" @click="showModal">Request to Join Team!</button>
    </div>

    <TeamRequestForm v-if="showModal" @close="showModal = false"/>


    <div class="players">
    <h2 id="players-title">Team Players</h2>
    <h3 id="captain-name">{{ captain.username }}</h3>

    <div class="Members" v-for="user in members" :key="user.userId">
        <MemberCard v-bind:user="user"/>
    </div>

</div>
    </div>
</template>

<script>
import TeamService from '../services/TeamService.js';
import MemberService from '../services/MemberService.js';
import MemberCard from './MemberCard.vue';
import TeamRequestForm from './TeamRequestForm.vue';

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
        }
    },

    components: {MemberCard, TeamRequestForm},

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
    }
}
</script>

<style>

#button-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

#team-request {
    display: block;
    background-color: rgba(255, 255, 255, 0.2);
    color: #b130fc;
    font-weight: bold;
    font-size: 30px;
    border-radius: 10px;
    margin: 10px auto;
    cursor: pointer;
    text-align: center;
    font-size: 20px;
}

#players-title {
    text-align: left;
    color: #c0c0c0;
    
}

.Members {
    color: #b130fc;
}

#captain-name {
    color: #58deff
}

.players {
    text-align: left;
}

#match-title {
    text-align: center;
    color: #c0c0c0;
    font-weight: bold;
    font-size: 30px;
}

#tourney-title {
    text-align: center;
    color: #c0c0c0;
    font-weight: bold;
    font-size: 30px;
}

.matches-wins-losses {
    display: flex;
    justify-content: space-around;
    font-size: 25px;
    font-weight: bold;
    text-align: center;
    background-color: rgba(255, 255, 255, 0.2);
    border-radius: 10px;
}

.tournaments-wins-losses {
    display: flex;
    justify-content: space-around;
    font-size: 25px;
    font-weight: bold;
    text-align: center;
    background-color: rgba(255, 255, 255, 0.2);
    border-radius: 10px;
}

#win {
    color: #39ff14;
    font-weight: bold;
}

#loss {
    color: #ff0033;
    font-weight: bold;
    
}

</style>