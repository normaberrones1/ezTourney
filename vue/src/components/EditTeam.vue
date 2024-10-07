<template>
    <div class="update-team">
        
    <form v-on:submit.prevent="updateTeam()" v-if="isTeamCaptain === true">
        <h1>Update Team</h1>
        <div class="nameInput">
            <label for="teamName">Team Name</label>
            <input class="update-team-name" v-model="team.teamName" type="text" id="teamName" name="teamName" required>
        </div>

        <div class="maxPlayersInput">
            <label for="maxPlayers">Max Players:</label>
            <input v-model="team.maxPlayers" type="number" id="maxPlayers" name="maxPlayers" min="1" required>
        </div>

        <div>
            <label for="accepting-teammates">Is your team accepting new teammates?</label>
            <div class="checkbox" required>
                <label for="is-accepting-yes">Yes</label>
                <input type="radio" id="is-accepting-yes" name="is-accepting" value="true">

                <label for="is-accepting-no">No</label>
                <input type="radio" id="is-accepting-no" name="is-accepting" value="false">
            </div>
        </div>

        <div>
            <label for="gameInput">Game of choice</label>
            <select id="gameInput" v-model="selectedGameName" @change="handleGameChange">
                <option disabled value="">Please select one</option>
                <option v-for="game in games" :key="game.gameId" :value="game.gameName">{{ game.gameName }}</option>
            </select>
            </div>
        <div>
            <input type="submit">
        </div>

    </form>
    </div>
    <div v-for="user in users" :key="user.userId" class="acceptReject">
        <h7>{{ user.username }}</h7>
        <input label="Accept" v-on:click="acceptJoin(user.userId)">
        <input label="Deny" v-on:click="rejectJoin(user.userId)">
    </div>

</template>


<script>
import TeamService from '../services/TeamService';
import GamesService from '../services/GamesService';
import MemberService from '../services/MemberService';

export default {
    data() {
        return {
            team: {},
            games: [],
            users: [],
            isTeamCaptain: false,
        }
    },

    created() {
        TeamService.getTeamById(this.$route.params.teamId).then((response) => {
            this.team = response.data;
        });
        GamesService.getAllGames().then((response) => {
            this.games = response.data;
        });
        TeamService.amITeamCaptain(this.$route.params.teamId).then((response) => {
            this.isTeamCaptain = response.data;
        });
        MemberService.getPendingInvites(this.$route.params.teamId).then((response) => {
            this.users = response.data;
        });
    },

    methods: {
        updateTeam(){
            TeamService.updateTeam(this.team).then((response) => {
                if(response.status === 200){
                    this.$router.push(`/teams/${this.team.teamId}`)
                }
            });
        },
        acceptJoin(userId){
            TeamService.acceptJoin({
                teamId: this.team.teamId,
                userId: userId
            });
        },
        rejectJoin(userId){
            TeamService.rejectJoin({
                teamId: this.team.teamId,
                userId: userId            });
        },
    }
}

</script>

<style>

.acceptReject{
    color: blue;
}

.update-team{
    display: flex;
    flex-direction: column;
    background-color: rgba(255, 255, 255, 0.6);
    margin:20px;
    align-items: center;
    padding: 20px;
    border-radius: 10px;
    

    
}

.update-team-name{
    text-align: center;
    width: 100%;
    
    
}

.nameInput,
.maxPlayersInput,
.gameInput{
    align-items: center;
    display: flex;
    flex-direction: column;
}



</style>