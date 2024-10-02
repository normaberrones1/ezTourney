<template>
<<<<<<< HEAD
    <div>
        <h1>Team Request Form</h1>
        <form @submit.prevent="submitForm">
            <div class="form-group">
                <label for="teamName">Team Name</label>
                <input type="text" id="teamName" v-model="teamName" class="form-control">
            </div>
            <div class="form-group">
                <label for="teamDescription">Team Description</label>
                <textarea id="teamDescription" v-model="teamDescription" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label for="teamMembers">Team Members</label>
                <input type="text" id="teamMembers" v-model="teamMembers" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</template>
=======
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

<style>

</style>
>>>>>>> 3e8c56ac3fa65101a66bc6b99b7524dc1fe3655e
