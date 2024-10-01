<template>
    <div>
        <h1>{{team.teamName}}</h1>
    </div>
</template>

<script>
 import TeamService from '../services/TeamService.js'

export default {
   data(){
        return{
            teamId: -1,
            team: {},
        }
    },


    created() {
        this.teamId = this.$route.params.teamId;
        TeamService.getTeamById(this.teamId).then((response) => {
            this.team = response.data;
        })
    }
}
</script>

<style>

</style>