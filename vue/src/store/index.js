import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      bracketData: [],
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
          id: `team-${index}`
      }));
        console.log('New state after mutation:', payload.numberOfTeams);
      },
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
    },
  });
  return store;
}
