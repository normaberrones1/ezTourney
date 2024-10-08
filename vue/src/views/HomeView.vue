<template>
  <div class="home">
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


  <div class="trophy-image">
      <img src="/public/IMG_3569.jpg" alt="trophy">
      <img src="/public/IMG_3575.jpg" alt="trophy">
      <img src="/public/IMG_3573.jpg" alt="trophy">
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
    margin-left: 32px;
    margin-right: 30px;
    height: 85vh;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    
  }

  .trophy-image img {
    width: 400px;
    height: 250px;
    margin-top: 20px;
    border-radius: 10px;
    transition: transform 0.2s;
    
  }

  .trophy-image img:hover {
  transform: scale(1.1);
  }


  .trophy-image {
    position: absolute;
    display: flex;
    flex-wrap: wrap;
    margin-top: 20px;
    width: 90px;
    height: 90px;
    margin-left: 50px;
    margin-top: -150px;
    justify-content: flex-end;
    
    
  }



  #home-login {
    color: #58deff;
    text-decoration: none;
  }

  #ez-desc {
    width: 60%;
    margin: 0 auto;
    background-color: rgba(255, 255, 255, 0.6);
    padding: 10px;
    border: 5px double #790a79;
    border-radius: 10px;
    color: #000000;
  }







</style>

