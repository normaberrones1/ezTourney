<template>
    <div class="flex-item">
        <label for="teamSelect"></label>
        <div class="dropDown">
            <select id="teamSelect" v-model="selectedTeam" @change="onTeamChange">
                <option value="">Select a team</option>
                <option v-for="team in teams" :key="team.id" :value="team.id" >{{ team.teamName }} </option>
            </select>
        </div>
        Team
        <div>
            <label for="score" class="scoreLabel">Score: </label>
            <input type="number" id="score" class="scoreInput" v-model="score" @input="onScoreChange">
        </div>
    </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
    data() {
        return {
            score: 0,
            selectedTeam: '',
        };
    },
    props: {
        teams: Array,
        numOfTeams: Number,
        numTeams: Number,
        
    },
    methods: {
        ...mapActions(['updateTeamScore', 'updateTeamName']),

        onScoreChange() {
            if (this.selectedTeam) {
                this.updateTeamScore({ teamId: this.selectedTeam, score: this.score });
            }
        },
        onTeamChange() {
            const selectedTeamData = this.teams.find(team => team.id === this.selectedTeam);
            if (selectedTeamData) {
                // Update the team name in the store
                this.updateTeamName({ teamId: this.selectedTeam, teamName: selectedTeamData.teamName });
            }
            this.score = 0; // Reset score when team changes
        }
    },
    created(){
        
    }
}
</script>

<style>
#teamSelect {
    width: 100%;
    
}


</style>