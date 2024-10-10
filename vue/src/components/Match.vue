<template>
    <h2 class="matchTitle"></h2>
    <TeamSelector v-if="!isFinalRound" v-bind:roundNum="roundNum" v-bind:teams="teams" v-for="number in numOfTeams"
        :key="number"></TeamSelector>
    <button v-if="showSaveScoreBtn" v-on:click="saveScores">Save Scores</button>
    <TeamSelector v-if="isFinalRound" v-bind:roundNum="roundNum"></TeamSelector>
</template>

<script>
import BracketService from '../services/BracketService';
import TeamSelector from './TournamentTeamSelector.vue';

export default {
    data() {
        return {
            matchIndex: Number,
            clickedSaveScore: false,
        }
    },
    props: {
        numTeams: Number,
        teams: Array,
        numOfTeams: Number,
        isFinalRound: Boolean,
        matchNumber: Number,
        roundNum: Number,
        gotLoadedPromise: Boolean,
    },
    methods: {
        saveScores() {
            let brackets = this.$store.getters.getBracketData;
            let scoresDto = {
                team1Name: '',
                team1Score: '',
                team2Name: '',
                team2Score: '',
            }
            let teams1And2 = [];
            brackets.forEach((item) => {
                if (item.round == this.roundNum + 1 && item.seat == this.matchNumber) {
                    teams1And2.push(item);
                    console.log(item);
                }
            })
            scoresDto.team1Name = teams1And2[0].teamName;
            scoresDto.team1Score = teams1And2[0].score;
            scoresDto.team2Name = teams1And2[1].teamName;
            scoresDto.team2Score = teams1And2[1].score;
            console.log(scoresDto);
            BracketService.saveScore(this.$route.params.id, scoresDto);
            this.clickedSaveScore = true;
        }
    },
    created() {
        this.matchIndex = this.$store.getters.getTeamIndex;
        let team = {
            storeIndex: '',
            round: '',
            seat: '',
        };
        team.round = this.roundNum + 1;
        team.storeIndex = this.matchIndex;
        team.seat = this.matchNumber;
        this.$store.commit("SET_MATCH_ROUND", team);
        this.$store.commit("SET_SEAT", team);
        if (this.numOfTeams == 2) {
            team.storeIndex = this.matchIndex + 1;
            this.$store.commit("SET_MATCH_ROUND", team);
            this.$store.commit("SET_SEAT", team);
        }
    },
    computed: {
        showSaveScoreBtn(){
            if (this.numOfTeams == 1) {
            return false;
        }
        if(this.clickedSaveScore){
            return false
        }  
        return true;
        }
    },

    components: { TeamSelector }
}
</script>