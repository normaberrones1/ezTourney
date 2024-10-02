<template>
  <div class="home">
    <h1>Home</h1>
    <!-- remember to make this page only visible if authenticated -->
    <h2>You must be authenticated to see this</h2>
    <p>And let me tell you I plan to enforce that</p>
    <div>
      <p>displayed here will be current tourneys with links to their info</p>
    </div>
    <StandingsCarousel></StandingsCarousel>

  </div>
</template>

<script>

import StandingsCarousel from '../components/StandingsCarousel.vue';

export default {
  components: {
    StandingsCarousel
  },
  
};
</script>

<style>
  h1 {
    text-align: center;
    color: #58deff
  }


</style>

