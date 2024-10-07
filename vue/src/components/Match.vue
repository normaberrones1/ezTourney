<template>
    <h2 class="matchTitle">Match</h2>
    <TeamSelector v-if="!isFinalRound" v-bind:teams="teams" v-for="number in numOfTeams" :key="number"></TeamSelector>
    <TeamSelector v-if="isFinalRound"></TeamSelector>
</template>

<script>
import TeamSelector from './TournamentTeamSelector.vue';

export default {
    props: {
        teams: Array,
        isFinalRound: Boolean,
        numOfTeams: Number,
    },

    components: { TeamSelector }
}
</script>