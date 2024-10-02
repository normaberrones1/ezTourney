<template>
  <div>
    <h1>Team Request Form</h1>
    <p>Request to join {{this.teamName}}!</p>

        <button @click="showModal = true" id="form-submit"> Request to Join Team!</button>


        <div v-if="showModal" class="modal">
            <div class="modal-content">
                <span @click="showModal = false" class="close">&times;</span>
                <form @submit.prevent="submitRequest">
                    <div class="form-filling">
                        <label for="player-name">Player Name</label>
                        <input type="text" id="player-name" name="player-name" required>
                    </div>


                    <div>
                        <label for="player-email">Player Email</label>
                        <input type="email" id="player-email" name="player-email" required>
                    </div>


                    <button type="submit" id="form-submit">SUBMIT REQUEST!</button>
                    <button type="button" @click="showModal = false">Cancel</button>

                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {

    data() {
        return {
            showModal: false,
            playerName: '',
            playerEmail: '',
            team: {
                teamName: 'Team Name'
            }
        };
    },
    methods: {
        submitRequest() {
            console.log(this.playerName);
            console.log(this.playerEmail);
            this.showModal = false;
        }
    }
}
</script>

<style></style>

