<template>
    <div class="tourneyCardContainer">
        <h2 id="tourney-title-card">{{ tourney.tourneyName }}</h2>
        <h4>Hosted By: {{tourney.directorNames}}</h4>
        <section class="tourneyNotes">
            <span>Entry Cost: ${{ tourney.entryFee }}</span>
            <p><span> Start Date: {{ tourney.startDate }}</span></p>
        </section>
        <p>{{ tourney.tourneyDesc }}</p>
        <p>--------------------------------------------</p>
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

    grid-area: tournaments;

}

#tourney-title-card {
    font-style: italic;
    margin: 1%;
    color: #7a0e88;
}
</style>