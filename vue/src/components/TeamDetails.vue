<template>
    <div>
        <h1>{{ team.teamName }}</h1>
        <h3>{{ captain.username }}</h3>
    </div>
    <div>

    </div>
</template>

<script>
import TeamService from '../services/TeamService.js'
import MemberService from '../services/MemberService.js';

export default {
    data() {
        return {
            teamId: -1,
            team: {},
            captain: {},
            members: [],
        }
    },


    created() {
        this.teamId = this.$route.params.teamId;
        TeamService.getTeamById(this.teamId).then((response) => {
            this.team = response.data;
            MemberService.getMemberById(this.team.captainId).then((response) => {
                this.captain = response.data;
            });
        });
        MemberService.getMembersByTeam(this.teamId).then((response) => {
            
        })
    }
}
</script>

<style></style>