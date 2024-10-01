<!-- 
Data for an individual team.
Team captain and team name will be visible to public
if user isnt authenticated don't show list of team members... how to do that though.
probably put a isAuthenticated on the "getMembersByTeam method"
if we even choose to do that.
-->

<template>
    <div>
        <h2>{{ team.teamName }}</h2>
        <h4>{{ teamCaptain.username }}</h4>
    </div>
</template>

<script>
import MemberService from '../services/MemberService.js';

export default {


    data() {
        return {
            teamCaptain: {
                username: '',
                userId: ''
            },

            users: []
        }
    },

    props: ["team"],

    created() {
        MemberService.getMemberById(this.team.captainId).then((response) => {
            this.teamCaptain = response.data;
        });
    },
}
</script>

<style>
template {
    display: flex;
    flex-direction: row;
}

h4 {
    display: inline-block;
}
</style>