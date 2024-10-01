<template>
    <div class="Leader">
        <h1>{{ team.teamName }}</h1>
        <h3>{{ captain.username }}</h3>
    </div>
    <div class="Members" v-for="user in members" :key="user.userId">
        <MemberCard v-bind:user="user"/>
    </div>
</template>

<script>
import TeamService from '../services/TeamService.js';
import MemberService from '../services/MemberService.js';
import MemberCard from './MemberCard.vue';

export default {
    data() {
        return {
            teamId: -1,
            team: {},
            captain: {},
            members: [],
        }
    },

    components: {MemberCard},

    created() {
        this.teamId = this.$route.params.teamId;
        TeamService.getTeamById(this.teamId).then((response) => {
            this.team = response.data;
            MemberService.getMemberById(this.team.captainId).then((response) => {
                this.captain = response.data;
            });
        });
        MemberService.getMembersByTeam(this.teamId).then((response) => {
            this.members = response.data;
        });
    }
}
</script>

<style>

</style>