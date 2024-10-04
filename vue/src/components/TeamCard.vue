<!-- 
Data for an individual team.
Team captain and team name will be visible to public
if user isnt authenticated don't show list of team members... how to do that though.
probably put a isAuthenticated on the "getMembersByTeam method"
if we even choose to do that.
-->

<template>
    <div class="teamCardContainer">
        <h2 class="teamName">{{ team.teamName }}</h2>
        <h4 class="teamCaptain">{{ teamCaptain.username }}</h4>
        <p>--------------------------------------------</p>
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
.teamName {
    font-style: italic;
    margin: 1%;
    color: #58deff;

}

.teamCaptain {
    margin: 1%;
}

.teamCardContainer {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    grid-area: teams;

}


</style>