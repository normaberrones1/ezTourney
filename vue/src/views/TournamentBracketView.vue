<template>
    <div class="tournament-container">
        <BracketBuilder />
    </div>
    
    
</template>

<script>
import BracketBuilder from '../components/BracketBuilder.vue';


export default {
    components: {
        BracketBuilder
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