import axios from 'axios';

export default {

    getAllGames(){
        return axios.get('/allgames')
    }
}