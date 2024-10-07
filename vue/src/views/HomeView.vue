<template>
  <div class="home">
    <h1 id="home">Home</h1>
    <!-- remember to make this page only visible if authenticated -->
    <div v-if="isAuthenticated">
    <h2 >Welcome to ezTourney!</h2>
    <div id="ez-desc">
      <p>
      At ezTourney, we make organizing and joining tournaments a breeze! Whether you're a seasoned competitor or
      just starting out, we have something for you. Our platform provides a simple and intuitive way to create, manage,
      and join exciting tournaments for your favorite games. With user-friendly features, real-time updates and a 
      supportive community, hosting and participating in tournaments has never been easier. Sign up today and start!
    </p>
    </div>

  <div class="home-image">
      <img src="/public/trophy.jpg" alt="trophy">
  </div>
  <div class="carousel-container">
    <StandingsCarousel />
    </div>
</div>
    <div v-else>
      <router-link to="/login" id="home-login">Log in to unlock full experience!</router-link>
    </div>

  </div>
</template>

<script>

import StandingsCarousel from '../components/StandingsCarousel.vue';

export default {
  components: {
    StandingsCarousel
  },
  computed: {
    isAuthenticated() {
      return this.$store.state.token != '';
    }
  },


  
};
</script>

<style>
  #home {
    text-align: center;
    color: #000000;
  }

  .home {
    text-align: center;
    color: #000000;
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    padding: px;
    margin-left: 32px;
    margin-right: 30px;
    
  }

  img {
    width: 400px;
    height: 250px;
    margin-top: 20px;
    border-radius: 10px;
    
  }



  #home-login {
    color: #58deff;
    text-decoration: none;
  }

  #ez-desc {
    width: 70%;
    margin: 0 auto;
    background-color: rgba(255, 255, 255, 0.6);
    padding: 10px;
    border: 1px solid rgb(124, 124, 124);
    border-radius: 10px;
    color: #000000;
  }







</style>

