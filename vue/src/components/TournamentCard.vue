<template>
    <div class="tourneyCardContainer">
        <h2>{{ tourney.tourneyName }}</h2>
        <h4>Hosted By: {{}}</h4>
        <section class="tourneyNotes">
            <span>Entry Cost: ${{ tourney.entryFee }}</span>
            <span>Start Date: {{ tourney.startDate }}</span>
        </section>
        <p>{{ tourney.tourneyDesc }}</p>
    </div>
</template>

<script>
import TourneyService from '../services/TourneyService';

export default {

    data() {
        return {
            localTourney: null,
        };    
           
    },    

    props: ["tourney"],

    created() {
        TourneyService.getCurrentTournaments().then((response) => {
            this.localTourney = response.data;
        });
    },
}
</script>

<style>
.tourneyCardContainer {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    border: 1px solid rgb(124, 124, 124);
    grid-area: tournaments;
    color: #000000;
}
</style>