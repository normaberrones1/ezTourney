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

    //Allows a team to be deleted from the database
    disbandTeam(teamId){
        return axios.delete(`/teams/${teamId}`);
    },

    requestJoinTeam(teamId){
        return axios.post(`/teams/join/${teamId}`);
    },

    amITeamCaptain(teamId){
        return axios.get(`/am-team-captain/${teamId}`);
    },

    acceptJoin(acceptDto){
        return axios.put(`/accept-teammate/`, acceptDto);
    },

    rejectJoin(rejectDto){
        return axios.put(`/reject-teammate/`, rejectDto);
    },

    teamsImCaptain(){
        return axios.get(`/teams-im-captain/`);
    }

 
};







