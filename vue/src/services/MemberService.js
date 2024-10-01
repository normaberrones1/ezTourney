import axios from 'axios';

export default {

    //get team captain details
    getMemberById(id){
        return axios.get(`/member/${id}`);
    },

    getMembersByTeam(teamId){
        return axios.get(`/team/${teamId}/members`);
    },

}