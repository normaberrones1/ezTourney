<template>
  <div class="standings-carousel">
    <div class="standings">
      <h1>Standings</h1>
      <p>Here are the current standings for current tournaments</p>
      <div>
      </div>

    </div>
  </div>
  <div class="controls">
    <button @click="previous"> &lt; </button>

    <button @click="next"> &gt; </button>

  </div>
    

</template>

<script>

export default {
  methods: {
    previous() {
      console.log('previous');
    },
    next() {
      console.log('next');
    }
  },
  data() {
    return {
      
    }
  },
  computed: {

  }

}
</script>

<style>

  button {
    background-color: rgba(255, 255, 255, 0.0);
    color: #58deff;
    border: none;
    padding: 10px;
    margin: 10px;
    font-size: 50px;
  
  }

</style>