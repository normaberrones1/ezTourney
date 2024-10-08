<template>
    <div>
        <div id="user-container">
            <h1>Users</h1>
            <table id="user-list">
                <thead>
                    <tr>
                        <th>Status</th>
                        <th>User name</th>
                        <th>Eliminated</th>
                        <th>Round eliminated</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="user in this.displayableUsers" :key="user.userId" >
                        <td v-if="isTourneyDirector && user.accepted">Accepted</td>
                        <td v-if="isTourneyDirector && !user.accepted">
                            <button class="acceptBtn" v-on:click="handleAccept(user.userId, true)">Accept</button>
                        </td>                       
                        <td>{{ user.userName }}</td>
                        <td>{{ user.isEliminated ? 'Yes': 'No'}}</td>
                        <td>{{ user.roundEliminated === 0 ?'': user.roundEliminated  }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>


<script>
import TourneyService from '../services/TourneyService'


export default {
    data() {
        return{
                users: [],
                isTourneyDirector: false
        }  
    },
    computed: {
        displayableUsers(){
            return this.isTourneyDirector ? this.users: this.users.filter(user => user.accepted);
        }
    },
    created(){
        this.setIsTourneyDirector();
        this.getTournamentUsers(this.$route.params.id);
    },
    methods:{
        getTournamentUsers(tourneyId){
            TourneyService.getTournamentUsers(tourneyId).then((response) => {
                this.users = response.data;
            });
        },
        setIsTourneyDirector() {
            TourneyService.isUserDirector(this.$route.params.id).then((response) => {
                this.isTourneyDirector = response.data;
                console.log(this.isTourneyDirector);
            });
        },
        handleAccept(userId){
            TourneyService.acceptUser(userId,this.$route.params.id).then((response) => {
                    if(response.data){
                        this.getTournamentUsers(this.$route.params.id);
                    }    
            });
        }
    }

}

</script>

<style scoped>

#user-container h1{
    text-align: center;
    color: #010708;
}
#user-container {
    color:white;
    text-align: center;
    margin-top: 2em;
    padding: auto;
    border: 1px solid rgb(124, 124, 124);
    background-color: rgba(255, 255, 255, 0.5);
}
#user-list {
    width: 100%;
    border-collapse: collapse;
    border: 1px solid rgb(124, 124, 124);
}

#user-list th {
    background-color: #94dcee93;
    color: black;
    font-weight: bold;
    font-size: 20px;
}

#user-list td {
    background-color: rgba(255, 255, 255, 0.5);
    color: black;
    font-size: 20px;
}
.acceptBtn {
    display: block;
    background-color: #58deff;
    color: black;
    border: solid 1px black;
    cursor: pointer;
    width: auto;
    font-size: 1em;
}
</style>

