<template>
    <div class="team-form">
        <h1>Team Form</h1>
        <p> Fill out the form to create a new team!</p>
        <form>
            <label for="team-name">Team Name</label>
            <input type="text" id="team-name" name="team-name" required>
            <label for="team-captain">Team Captain</label>
            <input type="text" id="team-captain" name="team-captain" required>
            <button type="submit">Submit</button>
        </form>
    </div>
</template>

<script>
export default {
    name: 'TeamForm',
    data() {
        return {
            team: {
                name: '',
                captain: ''
            }
        }
    },
    methods: {
        submitTeam() {
            console.log(this.team);
        }
    }
}
</script>

<style>
.team-form {
    position: relative;
    left: 30%;
    margin: 0 auto;
    width: 25%;
    text-align: center;
    padding: 25px;
    border: 1px solid rgb(124, 124, 124);
    border-radius: 10px;
    background-color: rgba(255, 255, 255, 0.2);
    color: #58deff;
    font-weight: bold;
}

form {
    display: flex;
    flex-direction: column;
    align-items: center;
}

label, input {
    width: 70%;
    margin-bottom: 10px;
}
</style>