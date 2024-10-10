<template>
    <div class="tourneyCardContainer">
        <h2 id="tourney-title-card">{{ tourney.tourneyName }}</h2>
        <h4>Hosted By: {{this.directorNames}}</h4>
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
            directorNames: ''
        };
    },
    props: ["tourney"],
    created() {
        TourneyService.getCurrentTournaments().then((response) => {
            this.localTourney = response.data;
        });
        TourneyService.getTourneyDirectors(this.tourney.tourneyId).then((response) => {
            const directors  = response.data;
            this.directorNames = directors.map(director => director.username).join(', ');
        });
    },
}
</script>

<style>
.tourneyCardContainer {

    align-content: center;
}

#tourney-title-card {
    font-style: italic;
    margin: 1%;
    color: #7a0e88;
}
</style>