<template>
    <form>
        <div class="nameInput">
            <label for="teamName">Team Name</label>
            <input v-model="team.teamName" type="text" id="teamName" name="teamName" required>
        </div>

        <div class="maxPlayersInput">
            <label for="maxPlayers">Max Players:</label>
            <input v-model="team.maxPlayers" type="number" id="maxPlayers" name="maxPlayers" min="1" required>
        </div>

        <div>
            <label for="accepting-teammates">Is your team accepting new teammates?</label>
            <div class="checkbox" required>
                <label for="is-accepting-yes">Yes</label>
                <input type="radio" id="is-accepting-yes" name="is-accepting" value="true">

                <label for="is-accepting-no">No</label>
                <input type="radio" id="is-accepting-no" name="is-accepting" value="false">
            </div>
        </div>

        <div>
            <label for="gameInput">Game of choice</label>
            <input type="list" name="gameInput" id="gameInput">
            <datalist id="gameInput" v-for="game in games" :key="game.gameId">
                <option v-bind:value="game.gameId"></option>
            </datalist>
        </div>
    </form>
</template>


<script>
import TeamService from '../services/TeamService';

export default {
    data() {
        return {
            team: {},
            games: []
        }
    },

    created() {
        TeamService.getTeamById(this.$route.params.teamId).then((response) => {
            this.team = response.data;
        })
    }
}

</script>