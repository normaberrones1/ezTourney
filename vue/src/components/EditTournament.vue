<template>
    <form v-on:submit.prevent="this.handleSubmit()" class="updateTourneyForm">
        <div class="tournamentEdit">
                <label for="tournamentName">Name</label>
                <input v-model="tournament.tourneyName" type="text" id="tourneyName" name="tournamentName" required>

                <label for="tourneyDesc">Description</label>
                <input v-model="tournament.tourneyDesc" type="text" id="tourneyDesc" name="tourneyDesc" required>

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
                    <option key="" value=""></option>
                    <option v-bind:value="team.teamId" v-for="team in this.teams" 
                    :key="team.teamId">{{ team.teamName }}
                    </option>
                </select>
                <select v-if="tournament.singlesEvent" v-model="tournament.winningUserId" id="winningUserId" name="winningUserId">
                    <option key="" value=""></option>
                    <option v-bind:value="user.userId" v-for="user in this.users" 
                    :key="user.userId">{{ user.userName }}
                    </option>
                </select>

        </div>
        <input class="updateBtn" type="submit" value="Save"/>
        
    </form>
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
.updateTourneyForm{
    display: block;
    background-color: rgba(255, 255, 255, 0.6);
    margin:10px;
}
.tournamentEdit {
    display:grid;
    grid-template-columns: 1fr 1fr;
    padding:5em 20em 0em 20em;
    align-items: center;
    color:white; 
    font-weight:bold;
}
.updateBtn {
    display: block;
    margin: 10px auto;
    padding: 10px;
    background-color: #58deff;
    color: black;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: auto;
}

</style>