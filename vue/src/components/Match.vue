<template>
    <h2 class="matchTitle">Match {{ matchNumber }}</h2>
    <TeamSelector v-if="!isFinalRound" v-bind:roundNum="roundNum" v-bind:teams="teams" v-for="number in numOfTeams"
        :key="number"></TeamSelector>
    <TeamSelector v-if="isFinalRound" v-bind:roundNum="roundNum"></TeamSelector>
</template>

<script>
import TeamSelector from './TournamentTeamSelector.vue';

export default {
    data() {
        return {
            matchIndex: Number,
        }
    },
    props: {
        numTeams: Number,
        teams: Array,
        numOfTeams: Number,
        isFinalRound: Boolean,
        matchNumber: Number,
        roundNum: Number,

    },
    methods: {

    },
    created() {
        this.matchIndex = this.$store.getters.getTeamIndex;
        let team = {
            storeIndex: '',
            round: ''
        };
        team.round=this.roundNum+1;
        team.storeIndex=this.matchIndex;
        this.$store.commit("SET_MATCH_ROUND", team);
        if(this.numOfTeams==2){
        team.storeIndex=this.matchIndex+1;
        this.$store.commit("SET_MATCH_ROUND", team);
        }
    },

    components: { TeamSelector }
}
</script>