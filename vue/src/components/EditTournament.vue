<template>
    <form v-on:submit.prevent="this.handleSubmit()" class="updateTourneyForm">
        <div class="tournamentEdit">
                <label for="tournamentName">Tournament Name</label>
                <input v-model="tournament.tourneyName" type="text" id="tourneyName" name="tournamentName" required>

                <label for="startDate">Start Date</label>
                <input v-model="tournament.startDate" type="date" id="startDate" name="startDate" required>
        
                <label for="endDate">End Date</label>
                <input v-model="tournament.endDate" type="date" id="endDate" name="endDate" required>
      
                <label for="location">Location</label>
                <input v-model="tournament.location" type="text" id="location" name="location" required>
     
                <label for="entryFee">Entry Fee</label>
                <input v-model="tournament.entry_fee" type="number" id="entryFee" name="entryFee" required>
       
                <label for="prizeDesc">Prize Desc</label>
                <input v-model="tournament.prizeDesc" type="text" id="prizeDesc" name="prizeDesc" required>

                <label for="tourneyDesc">Tournament Description</label>
                <input v-model="tournament.tourneyDesc" type="text" id="tourneyDesc" name="tourneyDesc" required>
           
                <label for="gameId">Game ID</label>
                <input v-model="tournament.gameId" type="number" id="gameId" name="gameId" required>
        
                <label for="rounds">Rounds</label>
                <input v-model="tournament.round" type="number" id="round" name="round" required>   
               
   
        </div>
        <input class="updateBtn" type="submit" value="Save"/>
        
    </form>
</template>




<script>
    import TourneyService from '../services/TourneyService';
    export default{

        data(){
            return{
                tournament: {}
            }
        },

        created(){
           this.getTournamentById(this.$route.params.tourneyId);
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
                TourneyService.getTournamentById(tourneyId).then((response) => {
                    this.tournament = response.data;
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
    color:black; 
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