<template>
    <div class="tournament">
        <current-tourney-list />
    </div>
</template>

<script>    

import CurrentTourneyList from '../components/CurrentTourneyList.vue';

export default {
    components: {
        CurrentTourneyList
    }
}
</script>