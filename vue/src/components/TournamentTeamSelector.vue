<template>
    <div class="flex-item">
        <label for="teamSelect" v-if="roundNum === 0"></label>
        <select id="teamSelect" v-if="roundNum === 0">
            <option value="">Select a team</option>
            <option v-for="team in teams" :key="team.id" :value="team.id">{{ team.teamName }}</option>
        </select>
        Team
    </div>
</template>

<script>

export default {

    props: {
        teams: Array,
        roundNum: Number
    },
    created() {

    }
}
</script>