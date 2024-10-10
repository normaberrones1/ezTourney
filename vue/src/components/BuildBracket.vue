<template>
    <div>

        <div class="bracketBuilderTitle">
            <h1>Bracket Builder</h1>
        </div>

        <div class="nextRoundBtn" v-if="!showBracketButton">
            <button @click="moveToNextRound">Next Round!</button>
        </div>

        <form v-on:submit.prevent="calculateRounds">

            <!-- <div class="form-group">
                <label for="numTeams" class="titleCard"><strong>Number of Teams:</strong></label>
                <input type="number" id="numTeams" v-model="numTeams" min="2" max="64" required />
            </div> -->

            <div class="submitBtn" v-if="showBracketButton">
                <button id="createBracketBtn">Show Bracket</button>
            </div>

        </form>

        <div class="brackets-title">

        </div>
        <div class="flex-container">
            <div v-for="(numItems, round) in bracketsPerRound" :key="'round-' + round" class="flex-column"
                v-bind:currentRound="round">

                <!-- Add a match div for every two teams -->
                <div v-for="matchIndex in Math.ceil(numItems / 2)" :key="'match-' + round + '-' + matchIndex"
                    class="match" :id="'round-' + round + '-match-' + matchIndex">
                    <Match :numTeams="numTeams" v-bind:teams="teams" v-bind:isFinalRound="matchIndex == numItems"
                        v-bind:numOfTeams="numItems % 2 === 0 ? 2 :
                matchIndex === Math.ceil(numItems / 2) ? 1 : 2" v-bind:matchNumber="matchIndex"
                        v-bind:roundNum="round"></Match>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TeamService from '../services/TeamService.js';
import Match from './Match.vue';
import { mapState, mapActions } from 'vuex';
import { createStore } from '../store/index.js';
import BracketService from '../services/BracketService.js';
const store = createStore();

export default {
    props: {
        teams: Array,
        numTeams: Number,
    },
    components: { Match },
    data() {
        return {
            rounds: 0,
            teamName: '',
            teamCaptain: '',
            bracketsPerRound: [],
            isWon: false,
            selectedTeam: [],
            showBracketButton: true,
            currentRound: 0,
        };
    },
    methods: {
        moveToNextRound() {
            let tourneyData = []
            BracketService.startNextRound(this.$route.params.id).then((response) => {
                response.data.forEach((item) => {
                    tourneyData.push(item);
                });
                console.log(tourneyData);
                let currentBrackets = this.$store.getters.getBracketData;
                let filteredBrackets = currentBrackets.filter((item) => {
                    return item.round == tourneyData[0].round;
                });
                let teamChoice = 1;
                let index = 0;
                filteredBrackets.forEach((item) => {
                    let team = {
                        storeIndex: '',
                        selectedTeam: '',
                    }
                    team.storeIndex = item.index;
                    if (teamChoice === 1) {
                        console.log(index);
                        console.log(tourneyData[index].team1Name);
                        team.selectedTeam = tourneyData[index].team1Name;
                        teamChoice = 2;
                        this.$store.commit('SET_TEAM_NAME', team)
                    } else {
                        console.log(index);
                        console.log(tourneyData[index].team2Name);
                        team.selectedTeam = tourneyData[index].team2Name;
                        teamChoice = 1;
                        this.$store.commit('SET_TEAM_NAME', team)
                        index++;
                    }
                })
            });

        },

        calculateRounds() {
            const rounds = Math.ceil(Math.log2(this.numTeams)); // Calculate number of rounds
            this.bracketsPerRound = [];
            this.selectedTeam = new Array(this.numTeams).fill('');



            for (let round = 0; round <= rounds; round++) {
                if (round === 0) {
                    this.bracketsPerRound.push(this.numTeams);  // First round is just the teams

                } else {
                    const currentTeams = Math.ceil(this.numTeams / Math.pow(2, round));
                    this.bracketsPerRound.push(currentTeams);

                }
            }
            for (let i = 0; i < this.bracketsPerRound.length; i++) {
                for (let j = 0; j < this.bracketsPerRound[i]; j++) {
                    let boxId = 'round-' + i + '-seat-' + j;
                    this.bracketData.push({ teamName: '', isWon: false, id: boxId, score: -1, round: 0, seat: 0, index: 0, });
                }
            }

            let loadedData = [];
            BracketService.getBracketData(this.$route.params.id).then((response) => {

                response.data.forEach((item) => {
                    loadedData.push(item);
                });
                console.log(loadedData);
                //more here
                let storeData = this.$store.getters.getBracketData;
                let tick = 1;
                let index = 0;

                console.log(storeData);

                storeData.forEach((bracket) => {
                    console.log(bracket.index)
                    let team = {
                        selectedTeam: '',
                        storeIndex: '',
                        score: '',
                    }
                    team.storeIndex = bracket.index;
                    console.log(team);
                    if (tick === 1) {
                        team.selectedTeam = loadedData[index].team1Name;
                        if (loadedData[index].team1Score) {
                            team.score = loadedData[index].team1Score;
                            this.$store.commit('SET_TEAM_SCORE', team);
                        }
                        console.log(team);
                        this.$store.commit('SET_TEAM_NAME', team);
                        tick = 2;
                    } else {
                        if (loadedData[index].team1Name !== loadedData[index].team2Name) {
                            team.selectedTeam = loadedData[index].team2Name;
                            if (loadedData[index].team2Score) {
                                team.score = loadedData[index].team2Score;
                                this.$store.commit('SET_TEAM_SCORE', team);
                            }
                            console.log(team);
                            this.$store.commit('SET_TEAM_NAME', team);

                            tick = 1;
                            index++;
                        }
                    }
                });
                //maybe more here
            });
            this.showBracketButton = false;

        },
        ...mapActions(['setAuthToken', 'setUser', 'setBrackets']),

        handleSaveBracket() {
            let brackets = this.$store.getters.getBracketData;
            let teamsList = []
            brackets.forEach((item) => {
                if (item.round == 1) {
                    teamsList.push(item.teamName);
                }
            })
            BracketService.createRoundOne(this.$route.params.id, teamsList);
        },

    },
    watch: {
        bracketData(newValue) {
            console.log('Bracket data in store updated:', newValue);
        },
    },
    computed: {
        ...mapState(['bracketData']),

        numberOfTeams() {
            return this.bracketData || 0;
        },

        numTeamsInRound(currentRound) {
            if (currentRound === 0) {
                return this.numTeams
            } else {
                return Math.ceil(this.numTeams / (currentRound * 2))

            }
        },

    },

}

</script>

<style>
.bracketBuilderTitle {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: cyan;
    background-color: darkmagenta;
    border-radius: 8px;
    width: 500px;
    height: 20px;
    margin: 10px auto;
    padding: 20px;
    box-shadow: 2px 2px 5px darkblue;
}

h1 {
    margin: 0;
    text-align: center;
}

#numTeams {
    width: 50px;
    height: 30px;
    border-radius: 8px;
}

#teamName {
    width: 50px;
    height: 30px;
    border-radius: 8px;
}

#teamCaptain {
    width: 50px;
    height: 30px;
    border-radius: 8px;
}

.titleCard {
    font-size: 20px;
    color: purple;
    text-align: center;
    color: cyan;
    border-radius: 8px;
    background-color: darkmagenta;
    border-radius: 8px;
    width: 450px;
    box-shadow: 2px 2px 5px darkblue;
}

#createBracketBtn {

    background-color: purple;
    color: white;
    width: auto;
    height: 40px;
    padding: 10px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    box-shadow: 2px 2px 5px blue;
    font-size: 16px;
    white-space: nowrap;
}

#saveBtn {

    background-color: purple;
    color: white;
    width: auto;
    height: 40px;
    padding: 10px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    box-shadow: 2px 2px 5px blue;
    font-size: 16px;
    white-space: nowrap;
}

.form-container {
    display: flex;
    justify-content: center;
    flex-direction: row;
}

.form-group {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 10px;
}

.flex-container {
    display: flex;
    justify-content: center;

}

.flex-column {
    display: flex;
    flex-direction: column;
    align-items: center;
    /* Center items in this column */
    position: relative;
    margin: 0 20px;
    /* Adjust spacing between rounds */
}

.matches {
    display: flex;
    flex-direction: column;
    align-items: center;
    /* Center matches */
    position: relative;
}

.flex-item {
    padding: 10px;
    background-color: rgb(208, 99, 212);
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-bottom: 5px;
    width: 80%;
}

.submitBtn {
    display: flex;
    justify-content: center;
    align-items: center;
}

.match {
    width: 150px;
    /* Set width of match boxes */
    margin: 10px 0;
    /* Space between matches */
    padding: 10px;
    border: 2px solid beige;
    background-color: royalblue;
    border-radius: 8px;
}

.flex-column:nth-child(n+2) {
    margin-top: 40px;
    /* Adjust this value as needed */
}

.flex-column:nth-child(3) {
    margin-top: 70px;
    /* Adjust this value as needed */
}

.flex-column:nth-child(4) {
    margin-top: 100px;
    /* Adjust this value as needed */
}

.flex-column:nth-child(5) {
    margin-top: 130px;
    /* Adjust this value as needed */
}

.flex-column:nth-child(6) {
    margin-top: 150px;
    /* Adjust this value as needed */
}

.flex-column:nth-child(7) {
    margin-top: 170px;
    /* Adjust this value as needed */
}

.flex-column:nth-child(8) {
    margin-top: 200px;
    /* Adjust this value as needed */
}

.matchTitle {
    color: lightgray;
    font-size: 20px;
    margin: 0;
}
</style>