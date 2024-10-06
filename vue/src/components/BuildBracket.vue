<template>
    <div>
        
        <div class="bracketBuilderTitle">
            <h1>Bracket Builder</h1>
        </div>
        <form @submit.prevent="calculateRounds">

            <div class="form-group">
                <label for="numTeams" class="titleCard"><strong>Number of Teams:</strong></label>
                <input type="number" id="numTeams" v-model="numTeams" min="2" max="64" required />
            </div>
            
            <div class="submitBtn">
                <button type="submit" id="createBracketBtn">Create</button>
            </div>
            
        </form>
        
        <div class="brackets-title">
            
        </div>
        <div class="flex-container">
                <div
                v-for="(numItems, round) in bracketsPerRound" 
                :key="'round-' + round" 
                class="flex-column"
            >
                <!-- Add a match div for every two teams -->
                <div v-for="matchIndex in Math.ceil(numItems / 2)" :key="'match-' + round + '-' + matchIndex" class="match" :id="'round-' + round + '-match-' + matchIndex">
                    <h2 class="matchTitle">Match</h2>
                    <div class="flex-item" v-if="(matchIndex - 1) * 2 < numItems" :id="'round-' + round + '-seat-' + ((matchIndex - 1) * 2 + 1)">
                        Team
                    </div>
                    <div class="flex-item" v-if="(matchIndex - 1) * 2 + 1 < numItems" :id="'round-' + round + '-seat-' + ((matchIndex - 1) * 2 + 2)">
                        Team
                    </div>
                </div>
        </div>
</div>    
    </div>
</template>

<script>
import TeamService from '../services/TeamService.js';

export default {
    data() {
        return {
            numTeams: 2,
            rounds: 0,
            teamName: '',
            teamCaptain: '',
            bracketsPerRound: [],
            bracketData: [],
            isWon: false,
            
        };
    },
    methods: {
        calculateRounds() {
            const rounds = Math.ceil(Math.log2(this.numTeams)); // Calculate number of rounds
            this.bracketsPerRound = [];

            
            

            for (let round = 0; round <= rounds; round++) {
                if (round === 0) {
                    this.bracketsPerRound.push(this.numTeams);  // First round is just the teams
                     
                } else {
                    const currentTeams = Math.ceil(this.numTeams / Math.pow(2, round));
                    this.bracketsPerRound.push(currentTeams);
                    
                }
            }
            for(let i = 0; i < this.bracketsPerRound.length; i++) {
                for(let j = 0; j < this.bracketsPerRound[i]; j++) {
                    let boxId = 'round-' + i + '-seat-' + j;
                    this.bracketData.push({teamName: '', isWon: false, id: boxId});
                }
            }
        },
    },
    created() {
        TeamService.getAllTeams().then((response) => {
            this.teams = response.data;
        })
    }

};    
    
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
#teamCaptain{
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
    display: flex; /* Enable Flexbox */
}

.flex-column {
    flex: 1; /* Make each column take equal space */
    margin: 10px;
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
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 10px;
    padding: 10px;
    border: 2px solid beige;
    background-color: royalblue;
    border-radius: 8px;
}
.matchTitle {
    color: lightgray;
    font-size: 20px;
    margin: 0;
}

</style>