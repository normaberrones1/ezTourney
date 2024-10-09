import axios from 'axios';

export default {
    getBracketData(tourneyId){
       return axios.get(`/tournaments/${tourneyId}/brackets-load`);
    },

    createRoundOne(tourneyId, teamsList){
       return axios.post(`/tournaments/${tourneyId}/brackets/create`, teamsList);
    },

    startNextRound(tourneyId){
        return axios.get(`/tournaments/${tourneyId}/nextRound`);
    },

    saveScore(tourneyId, teamsScores){
        return axios.post(`/tournaments/${tourneyId}/brackets/save-score`, teamsScores);
    }
}