<template>
    <div>
        <tournament-details />
        <tourney-team-list />
    </div>
</template>

<script>
import TournamentDetails from '../components/TournamentDetails.vue';
import TourneyTeamList from '../components/TourneyTeamList.vue';

export default{
    components: {
        TournamentDetails,
        TourneyTeamList
    }
}

</script>