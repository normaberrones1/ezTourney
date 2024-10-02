import axios from 'axios';

export default {

    getAllGames(){
        return axios.get('/allgames')
    },
    getGameById(id){
        return axios.get(`/allgames/id=${id}`);
    },
    getGameByTeamId(teamId){
        return axios.get(`/games/team/${teamId}`);
    },
    getGameByDate(date){
        return axios.get(`/games/date/${date}`);
    },
    createGame(game){
        return axios.post(`/games`, game);
    },
    updateGame(game){
        return axios.put(`/games/${game.gameId}`, game);
    },
    deleteGame(id){
        return axios.delete(`/games/${id}`);
    },
    getGameTeams(id){
        return axios.get(`/games/${id}/teams`);
    },
    getGameMatches(id){
        return axios.get(`/games/${id}/matches`);
    },
    getGameStandings(id){
        return axios.get(`/games/${id}/standings`);
    },
    getGameByMatchId(matchId){
        return axios.get(`/games/match/${matchId}`);
    }
}