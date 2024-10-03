<template>
  <div class="home">
    <h1>Home</h1>
    <!-- remember to make this page only visible if authenticated -->
    <h2>Welcome to ezTourney!</h2>
    <p>At ezTourney, we make organizing and joining tournaments a breeze! Whether you're a seasoned competitor or
      just starting out, we have something for you. Our platform provides a simple and intuitive way to create, manage,
      and join exciting tournaments for your favorite games. With user-friendly features, real-time updates and a 
      supportive community, hosting and participating in tournaments has never been easier. Sign up today and start!
    </p>
    <h2>You must be authenticated to see this</h2>
    <p>And let me tell you I plan to enforce that</p>
    <div>
      <p>displayed here will be current tourneys with links to their info</p>
      <img src="/public/trophy.jpg" alt="trophy">
    </div>
    <StandingsCarousel />

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

  .home {
    text-align: center;
    color: #58deff;
  }

  img {
    width: 400px;
    height: 250px;
  }





</style>

