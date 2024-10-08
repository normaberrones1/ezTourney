<template>
    <div class="tournament-container"></div>
    <BuildBracket />

    <button class="showBtn" @click="showBracket">Generate Bracket From Store</button>

    <BracketFromStore v-if="showBracketFromStore" :numberOfTeams="numberOfTeams" />
    
</template>

<script>
import BuildBracket from '../components/BuildBracket.vue';
import BracketFromStore from '../components/BracketFromStore.vue';


export default {
    components: {
        BuildBracket,
        BracketFromStore
    },
    data() {
        return {
            showBracketFromStore: false,
            
        }
    },
    methods: {
        showBracket() {
            this.showBracketFromStore = !this.showBracketFromStore;
        }
    },
    computed: {
        numberOfTeams() {
            return this.$store.state.bracketData.numberOfTeams;
        }
    },
}
</script>

<style scoped>
.showBtn {
    background-color: purple;
    color: white;
    width: auto;
    height: 40px;
    padding: 10px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    box-shadow: 2px 2px 5px blue;
    font-size: 16px;
    white-space: nowrap;
}
</style>