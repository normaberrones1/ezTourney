<!-- 
Data for an individual team.
Team captain and team name will be visible to public
if user isnt authenticated don't show list of team members... how to do that though.
probably put a isAuthenticated on the "getMembersByTeam method"
if we even choose to do that.
-->

<template>
    <h2>{{ team.teamName }}</h2>
    <h4>{{ teamCaptain.username }}</h4>
</template>

<script>
import MemberService from '../services/MemberService.js';

export default {
    

    data(){
        return {
            teamCaptain: {
                username: "",
                userId: ""
            },

            users: []
        }
    },

    props: ["team"],

    created(){
        MemberService.getCaptainById(team.captainId).then((response) => {
            teamCaptain = response.data;
        });
    },
}
</script>

<style>

</style>