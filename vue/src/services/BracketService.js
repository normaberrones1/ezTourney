import axios from 'axios';

export default {
    getBracketData(tourneyId){
        axios.get(`/tournaments/${tourneyId}/brackets-load`);
    },

    createRoundOne(tourneyId, teamsList){
        axios.post(`/tournaments/${tourneyId}/brackets/create`, teamsList);
    },

    startNextRound(tourneyId){
        axios.get(`/tournaments/${tourneyId}/nextRound`);
    },

    saveScore(tourneyId, teamsScores){
        axios.post(`/tournaments/${tourneyId}/brackets/save-score`);
    }
}