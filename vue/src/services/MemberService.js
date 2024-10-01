import axios from 'axios';

export default {

    //get team captain details
    getCaptainById(id){
        return axios.get(`/teamCaptain/${id}`);
    }

}