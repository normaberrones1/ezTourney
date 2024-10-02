import axios from 'axios';

export default {
    //all teams, anyone can view this info
    getAllTeams(){
        return axios.get(`/teams/`);
    },

    //anyone can view teams and filter id
    getTeamById(id){
        return axios.get(`/teams/${id}`);
    },

    //Similar filtering.. Maybe this should be saved for a computed property
    //Getting team by captain ID
    getTeamByName(captainId){
        return axios.get(`/teams/captain/${captainId}`);
    },


    //Input params Team obj.
    //outputs a promise/
    //inserts a tean into database
    createTeam(team){
        return axios.post(`/teams`, team);
    },

    //Team input param
    //Should return the created team
    updateTeam(team){
        return axios.put(`/teams/${team.teamId}`, team);
    },

    //Accepts or rejects teammates,
    //Need to show pending requests
    acceptTeammate(teammateId){
        return axios.patch(`/teams/${teammateId}/accepting`);
    },
    
    //Allows a team to be deleted from the database
    disbandTeam(teamId){
        return axios.delete(`/teams/${teamId}`);
    },

    requestJoinTeam(teamId){
        return axios.post(`/teams/join/${teamId}`);
    }
 
};







