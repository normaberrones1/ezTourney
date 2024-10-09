<template>
    <div class="tourney-details-view">
        <tournament-details />
    </div>
</template>

<script>
import TournamentDetails from '../components/TournamentDetails.vue';


export default{
    components: {
        TournamentDetails,
       
    }
}

</script>

<style scoped>
.tourney-details-view {
    height: 100%;
    width: 100%;
}
</style>