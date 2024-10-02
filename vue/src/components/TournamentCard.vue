<template>
    <div class="tourneyCardContainer">
        <h2>{{ tourney.tourneyName }}</h2>
        <h4>Hosted By: {{ tourney.directorName }}</h4>
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