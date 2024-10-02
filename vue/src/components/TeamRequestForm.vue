<template>
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