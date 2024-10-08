<template>
    <div class="flex-item">
        <label for="teamSelect"></label>
        <select id="teamSelect">
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
    },
    created(){
        
    }
}
</script>