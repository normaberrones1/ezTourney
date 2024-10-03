<template>
  <div>
  <div class="standings-carousel">
    <div class="standings">
      <h1 class="moveInMoveOut">Standings</h1>
      <h1 class="moveInMoveOut">Current Tourneys</h1>
      <h1 class="moveInMoveOut">Teams</h1>
      <div>
      </div>

    </div>
  </div>
  <div class="controls">
    <button @click="previous"> &lt; </button>

    <button @click="next"> &gt; </button>

  </div>
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
    },

  },
  data() {
    return {
      currentSlide: 0,
      slides: [
        'Standings',
        'Current Tourneys',
        'Teams'
      ]
    }
  },
  computed: {

  },
  created() {
    console.log('created');
  },
  updated() {
    console.log('updated');
  }
  

}
</script>

<style>

  button {
    background-color: rgba(255, 255, 255, 0.0);
    color: #58deff;
    border: none;
    padding: 10px;
    margin: 30px;
    font-size: 50px;
  
  }

  .moveInMoveOut {
    position: absolute;
    left: 100%;
    animation: move 9s ease-in-out infinite;
    
  }

  .moveInMoveOut:nth-child(1) {
    animation-delay: 0s;
  }

  .moveInMoveOut:nth-child(2) {
    animation-delay: 3s;
  }

  .moveInMoveOut:nth-child(3) {
    animation-delay: 6s;
  }

  .standings-carousel {
    position: relative;
    width: 100%;
    height: 100vh;
    overflow: hidden;
    text-align: center;
    display: flex;
    justify-content: center;
    
  }
  

  @keyframes move {
    10% {
      left: 100%;
      opacity: 0;
    }
    10% {
      left: 50%;
      opacity: 1;
    }
    30% {
      left: 50%;
      opacity: 1;
    }
    40% {
      left: 50%;
      opacity: 1;
    }
    50% {
      left: -100%;
      opacity: 0;
    }
    100% {
      left: -100%;
      opacity: 0;
    }
  }

</style>