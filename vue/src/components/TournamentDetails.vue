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

           <div class="fieldLabel">Winner</div>
           <div class="fieldValue">{{ tournament.winningTeamName }}</div>

        </div>
        <div class="tourney-button" >
            <button id="tourney-request" v-on:click="requestTournamentJoin()">Request to Join Tournament!</button>
        </div>

        <TourneyRequestForm v-if="showModal" @close="showModal = false"/>
    </div>
</template>



<script>
import TourneyService from '../services/TourneyService';
import TourneyRequestForm from './TournamentRequestForm.vue';

export default {
    data() {
       return {
              tournament: {},
              showModal: false,
              displayEditButton: false
         }
    },
    methods: {
        getTournament() {
            TourneyService.getTournamentViewById(this.$route.params.id).then((response) => {
                this.tournament = response.data;
            });
        },
        requestTournamentJoin() {
            TourneyService.requestTournamentJoin(this.tournament.id).then((response) => {
                if(response.data) {
                    alert("Request to join tournament sent!");
                } else {
                    alert("Request to join tournament failed!");
                }
            })
        },
        setEditBtnVisible() {
            TourneyService.isUserDirector(this.$route.params.id).then((response) => {
                this.displayEditButton = response.data;
            });
        }
    },
    components: {TourneyRequestForm},

    created() {
        this.getTournament(); 
        this.setEditBtnVisible();
    },

}
</script>

<style scoped>

#tourney-button {
    display: flex;
    justify-content: center;
    align-items: center;
}

#tourney-request {
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
    
.tourneyDetails {
    color:black;
    text-align: center;
    margin-top: 2em;
    padding: auto;
    border: 1px solid rgb(124, 124, 124);
    background-color: rgba(255, 255, 255, 0.5);
}

.tourneyDetails h1 {
    text-align: center;
    color: #010708;
}

.tourneyEditBtn{
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

.tourneyDetailsGrid{
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-gap: 10px 50px;
    margin: 10px;
    padding: 0px 20px 20px 20px;
}

.fieldLabel{
  grid-column-start: 2;
  font-weight: bold;
}

.fieldValue{
  grid-column-end: 4;
}

.tourneyTitle{
    font-weight: bold;
}

</style>

