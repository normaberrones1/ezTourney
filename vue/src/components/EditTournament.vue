<template>
    <form v-on:submit.prevent="updateTournament()">
        <div class="nameInput">
                <label for="tournamentName">Tournament Name</label>
                <input v-model="tournament.tourneyName" type="text" id="tourneyName" name="tournamentName" required>
            </div>
        <div class="startDateInput">
            <label for="startDate">Start Date</label>
            <input v-model="tournament.startDate" type="date" id="startDate" name="startDate" required>
         </div>
        <div class="endDateInput">
                <label for="endDate">End Date</label>
                <input v-model="tournament.endDate" type="date" id="endDate" name="endDate" required>
            </div>
        <div class="locationInput">
                <label for="location">Location</label>
                <input v-model="tournament.location" type="text" id="location" name="location" required>
            </div>
        <div class="entryFeeInput">
                <label for="entryFee">Entry Fee</label>
                <input v-model="tournament.entry_fee" type="number" id="entryFee" name="entryFee" required>
            </div>
        <div class="prizeInput">
                <label for="prizeDesc">Prize Desc</label>
                <input v-model="tournament.prizeDesc" type="text" id="prizeDesc" name="prizeDesc" required>
            </div>
        <div class="tourneyDescInput">
                <label for="tourneyDesc">Tournament Description</label>
                <input v-model="tournament.tourneyDesc" type="text" id="tourneyDesc" name="tourneyDesc" required>
            </div>
        <div class="gameIdInput">
                <label for="gameId">Game ID</label>
                <input v-model="tournament.gameId" type="number" id="gameId" name="gameId" required>
            </div>
        <div class="roundInput">
                <label for="rounds">Rounds</label>
                <input v-model="tournament.round" type="number" id="round" name="round" required>
            </div>
        <!-- <div class="winnerIdInput">
                <label for="winnerId">Winner ID</label>
                <input v-model="tournament.winner" type="number" id="winner" name="winner" required>
            </div>   -->
         <div>
            <input type="submit">
        </div>
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
            TourneyService.getTournamentById(this.$route.params.tournamentId).then((response) => {
                this.tournament = response.data;
            });
        },


        methods: {
        updateTeam(){
            TourneyService.updateTournament(this.tournament).then((response) => {
                if(response.status === 200){
                    this.$router.push(`/tournaments/${this.tournament.tourneyId}`)
                }
            });
        }
    }
}
</script>
