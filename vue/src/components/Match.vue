<template>
    <h2 class="matchTitle">Match {{ matchNumber }}</h2>
    <TeamSelector v-if="!isFinalRound" v-bind:roundNum="roundNum"
    v-bind:teams="teams" v-for="number in numOfTeams" :key="number" ></TeamSelector>
    <TeamSelector v-if="isFinalRound" v-bind:roundNum="roundNum"></TeamSelector>
</template>

<script>
import TeamSelector from './TournamentTeamSelector.vue';

export default {
     
    props: {
        numTeams: Number,
        teams: Array,
        numOfTeams: Number,
        isFinalRound: Boolean,
        matchNumber: Number,
        roundNum: Number,
    },
   
    

    components: { TeamSelector }
}
</script>