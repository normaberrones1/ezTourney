<template>
    <div>
        <h1>Request to Join Tournament</h1>
        <p>Request to join tournament {{this.tournamentName}}!</p>
    
        <button @click="showModal = true" id="form-submit"> Request to Join Tournament!</button>

        <div v-if="showModal" class="modal">
            <div class="modal-content">
                <span @click="showModal = false" class="close">&times;</span>
                <form @submit.prevent="submitRequest">

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
            tournament: {
                tournamentName: 'Tournament Name'
            }
        };
    },

    methods: {
        submitRequest() {
            console.log(this.tournamentName);
            this.showModal = false;
        }
    }
}

</script>

<style>

</style>