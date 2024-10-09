<template>
    <div class="editTourneyContainer">
        <h1>Edit Tournament</h1>
        <form v-on:submit.prevent="this.handleSubmit()">
            <div class="tournamentEditGrid">
                    <label for="tournamentName">Name</label>
                    <input v-model="tournament.tourneyName" type="text" id="tourneyName" name="tournamentName" required>

                    <label for="tourneyDesc">Description</label>
                    <textarea v-model="tournament.tourneyDesc" id="tourneyDesc" name="tourneyDesc" required/>

                    <label for="startDate">Start date</label>
                    <input v-model="tournament.startDate" type="date" id="startDate" name="startDate" required>
            
                    <label for="endDate">End date</label>
                    <input v-model="tournament.endDate" type="date" id="endDate" name="endDate" required>
        
                    <label for="location">Location</label>
                    <input v-model="tournament.location" type="text" id="location" name="location" required>
        
                    <label for="entryFee">Entry fee ($)</label>
                    <input v-model="tournament.entry_fee" type="number" id="entryFee" name="entryFee" required>
        
                    <label for="prizeDesc">Prize</label>
                    <input v-model="tournament.prizeDesc" type="text" id="prizeDesc" name="prizeDesc" required>
            
                    <label for="game">Game</label>
                    <select  v-model="tournament.gameId" id="game" name="game" required>
                        <option value="" disabled selected>Select Game</option>
                        <option v-bind:value="game.gameId" v-for="game in games" 
                                :key="game.gameId">{{ game.gameName }}
                        </option>
                    </select>
                    
                    <label for="rounds">Rounds</label>
                    <input v-model="tournament.round" type="number" id="round" name="round" min="1" required>

                    <label for="isPrivate">Is Private</label>
                    <input type="checkbox" id="isPrivate" v-model="tournament.private">

                    <label for="singlesEvent">Singles Event</label>
                    <input type="checkbox" id="singlesEvent" v-model="tournament.singlesEvent">
                
                    <label for="winner">Winner</label>
                    <select v-if="!tournament.singlesEvent" v-model="tournament.winner" id="winner" name="winner">
                        <option value="" disabled selected>Select winning team</option>
                        <option v-bind:value="team.teamId" v-for="team in this.teams" 
                        :key="team.teamId">{{ team.teamName }}
                        </option>
                    </select>
                    <select v-if="tournament.singlesEvent" v-model="tournament.winningUserId"  id="winningUserId" name="winningUserId">
                        <option value="" disabled selected>Select winner</option>
                        <option v-bind:value="user.userId" v-for="user in this.users" 
                        :key="user.userId">{{ user.userName }}
                        </option>
                    </select>

            </div>
            <input class="updateBtn" type="submit" value="Save"/>  
        </form>
    </div>
    
</template>

<script>
    import GamesService from '../services/GamesService';
    import TourneyService from '../services/TourneyService';

    export default{

        data(){
            return{
                tournament: {},
                games: [],
                teams: [],
                users: []

            }
        },

        created(){
           this.getTournamentById(this.$route.params.tourneyId);
           this.getAllGames();
           this.getAllAcceptedTeams(this.$route.params.tourneyId);
           this.getAllAcceptedUsers(this.$route.params.tourneyId);
        },

        methods: {
            handleSubmit(){
                TourneyService.updateTournament(this.tournament).then((response) => {
                    if(response.status === 200){
                        this.$router.push(`/tournaments/${this.tournament.tourneyId}`)
                    }
                });
            },
            getTournamentById(tourneyId){
                TourneyService.getTournamentViewById(tourneyId).then((response) => {
                    this.tournament = response.data;
                });
            },
            acceptRejectTeamJoin(teamId, tourneyId){
                TourneyService.acceptRejectTeamJoin(teamId,tourneyId).then((response) => {
                    if(response.status === 200){
                        this.$router.push(`/tournaments/${tourneyId}`)
                    }
                });
            },
            getAllGames(){
                GamesService.getAllGames().then((response) => {
                    this.games = response.data;
                });
            },
            getAllAcceptedTeams(tourneyId){
                TourneyService.getTournamentTeams(tourneyId).then((response) => {
                    this.teams = response.data.filter(team => team.accepted);
                });
            },

            getAllAcceptedUsers(tourneyId){
                TourneyService.getTournamentUsers(tourneyId).then((response) => {
                    this.users = response.data.filter(user => user.accepted);
                });
            }
            
        }
    }
</script>

<style scoped>

.editTourneyContainer {
    display: block;
    background-color: rgba(255, 255, 255, 0.6);
    margin: 10px auto;
    width: 100%;
    height: 100%;
    border: 1px solid rgb(124, 124, 124);
    border-radius: 10px;
}

.editTourneyContainer h1{
    text-align: center;
    color: #010708;
    border: 1px solid rgb(124, 124, 124);
    background-color: rgba(255, 255, 255, 0.5);
    border-radius: 10px;
    padding: 10px;
}
.tournamentEditGrid {
    display:grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    width: 100%;
    margin: 30px;
    color: #010708;
    gap: 10px;
}
.tournamentEditGrid label{
    grid-column-start: 2;
    font-weight: bold;
    text-align: left;
    padding-left: 100px;
}

.tournamentEditGrid input, textarea{
    grid-column-start: 3;
    width: 100%
}
.tournamentEditGrid select{
    grid-column-start: 3;
    width: 100%;
    height: 20px;
}

.tournamentEditGrid input[type="checkbox"]{
    grid-column-start: 3;
    width: 20px;
    height: 20px;
}

#tourneyDesc{
    resize: none;
    height: 50px;
}

.updateBtn {
    background-color:rgba(255, 255, 255, 0.6);
    color: #b130fc;
    cursor: pointer;
    width: 150px;
    font-size: 1em;
    font-weight: bold;
    height: 2em;
    text-align: center;
    margin-bottom: 30px;
    border-radius: 10px;
}

</style>