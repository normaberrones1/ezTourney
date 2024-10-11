import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      bracketData: [],
      rounds: [],
      teamIndex: 0,
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_BRACKETS(state, payload) {
        console.log('Mutation SET_BRACKETS called with:', payload.numberOfTeams);
        state.bracketData = Array.from({ length: payload.numberOfTeams }, (_, index) => ({
          teamName: '',
          isWon: false,
          id: `team-${index}`,
          score: 0,
        }));
        console.log('New state after mutation:', payload.numberOfTeams);
      },
      SET_ROUNDS(state, rounds) {
        state.rounds = rounds;
      },
      UPDATE_TEAM_SCORE(state, { teamId, score }) {
        const team = state.bracketData.find(t => t.id === teamId);
        if (team) {
          team.score = score; // Update the score
        }
      },
      UPDATE_TEAM_NAME(state, { teamId, teamName }) {
        const team = state.bracketData.find(t => t.id === teamId);
        if (team) {
          team.teamName = teamName; // Update the team name
        }
      },
      SET_TEAM_NAME(state, team) {
        state.bracketData[team.storeIndex].teamName = team.selectedTeam;
      },
      ADD_1_TO_TEAM_INDEX(state){
        state.teamIndex++;
      },
      SET_TEAM_SCORE(state, team){
        console.log(state.bracketData[team.storeIndex].score)
        state.bracketData[team.storeIndex].score = team.score;
        console.log(state.bracketData[team.storeIndex].score)
      },
      SET_MATCH_ROUND(state, team){
        state.bracketData[team.storeIndex].round = team.round;
      },
      SET_SEAT(state, team){
        state.bracketData[team.storeIndex].seat = team.seat;
      },
      SET_INDEX(state, index){
        state.bracketData[index].index = index;
      },
      RESET_BRACKET_DATA(state){
        state.bracketData = [];
      },
      SET_SHOW_INPUT(state,team){
        state.bracketData[team.storeIndex].showInput = team.showInput;
      }
    },
    actions: {
      setAuthToken({ commit }, token) {
        commit('SET_AUTH_TOKEN', token);
      },
      setUser({ commit }, user) {
        commit('SET_USER', user);
      },
      setBrackets({ commit }, newBracket) {
        console.log('Action setBrackets called with:', newBracket.numberOfTeams);
        commit('SET_BRACKETS', newBracket);
      },
      setRounds({ commit }, rounds) {
        commit('SET_ROUNDS', rounds);
      },
      updateTeamScore({ commit }, payload) {
        commit('UPDATE_TEAM_SCORE', payload);
      },
      updateTeamName({ commit }, payload) {
        commit('UPDATE_TEAM_NAME', payload);
      },
    },
    getters: {
      getTeamIndex(state){
        let result = state.teamIndex;
        return result;
      },
      getBracketData(state){
        return state.bracketData;
      },
    },
  });
  return store;
}
