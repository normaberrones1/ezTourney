import axios from 'axios';

export default {
    getCurrentTournaments() {
        return axios.get(`/tournaments`);
    },

    getTournamentsHistory() {
        return axios.get(`/tournaments/history`)
    },

    getTournamentById(id) {
        return axios.get(`/tournaments/${id}`);
    },

    getTournamentByTeamId(id) {
        return axios.get(``);
    },

    getTournamentByDate(date) {
        return axios.get(``);
    },

    createTournament(tournament) {
        return axios.post(`/create-tournament`, tournament);
    },

    updateTournament(tournament) {
        return axios.put(`/tournaments/${tournament.tourneyId}`, tournament);
    },

    deleteTournament(id) {
        return axios.delete(``);
    },

    getTournamentTeams(id) {
        return axios.get(`/tournaments/${id}/teams`);
    },

    getTournamentMatches(id) {
        return axios.get(``);
    },

    getTournamentStandings(id) {
        return axios.get(``);
    },

    getTournamentWinsAndLosses(id) {
        return axios.get(`/tournaments/${id}/wl`);
    },

    getMatchWinsAndLosses(id) {
        return axios.get(`/match-info/${id}`);
    },

    isUserDirector(tourneyId) {
        return axios.get(`/is-director/${tourneyId}`);
    },
    acceptTeam(teamId, tourneyId) {
        return axios.patch(`/tournaments/${tourneyId}/accept-team/${teamId}`);
    },

    getTournamentViewById(id) {
        return axios.get(`/tournaments/${id}/view`);
    },
}