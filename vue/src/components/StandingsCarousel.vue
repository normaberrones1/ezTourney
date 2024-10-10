<template>
  
    <div class="standings-carousel" @mouseover="stopAutoSlide" @mouseleave="startAutoSlide">
      <div class="standings">
        <div v-for="(image, index) in images" :key="index" class="slides" :class="{active: currentSlide === index}">
          <img :src="image" :alt="`slide${index}`">

</div>

  </div>
  <div class="carousel-controls">
    <button @click="previous"> &lt; </button>
    <button @click="next"> &gt; </button>
  </div>
  </div>


</template>

<script>
import GamesService from '../services/GamesService';
import TeamService from '../services/TeamService';
import TourneyService from '../services/TourneyService';

export default {

  
  methods: {
    previous() {
      this.currentSlide = (this.currentSlide === 0) ? this.images.length - 1 : this.currentSlide - 1;
      this.resetAutoSlide();
    },
    next() {
      this.currentSlide = (this.currentSlide === this.images.length - 1) ? 0 : this.currentSlide + 1;
      this.resetAutoSlide();
    },
    autoSlide() {
  
      this.next();

    },

    resetAutoSlide() {
      clearInterval(this.interval);
      this.startAutoSlide();
    },

    startAutoSlide() {
      this.interval = setInterval(this.autoSlide, 5000);
    },

    stopAutoSlide() {
      clearInterval(this.interval);
    }

  },

  mounted() {
    this.startAutoSlide();
  },

  
  
  data() {


    return {
      currentSlide: 0,
      interval: null,
      images: [
        '/public/Carousel/BG-Elev.jpg',
        '/public/Carousel/IMG_3719.WEBP',
        '/public/Carousel/IMG_3724.JPG',
        '/public/Carousel/IMG_3740.JPG',
        '/public/Carousel/IMG_3722.WEBP',
        '/public/Carousel/IMG_3725.jpg',
        '/public/Carousel/IMG_3739.JPG',
        
      ]
      
    }
  },

}

</script>

<style>

.standings {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.slides{

  animation: slide 1s;
}

.slides.active {
  display: block;
}


.slides img {
  
  width: 100%;
  height: 100%;
  object-fit: cover;
  animation-fill-mode: forwards;
  animation-delay: 0s;
  border-radius: 10px;
  box-shadow: 0 0 10px 5px #790a79;
  border: 5px solid #790a79;
  margin: 10px;
  padding: 10px;
  background-color: rgba(255, 255, 255, 0.6);
  margin-right: 20px;
}



  .carousel-controls {
    justify-content: center;
    gap: 20px;
    display: flex;
  }

  button {
    cursor: pointer;
    background-color: transparent;
    border: none;
    font-size: 50px;
    padding: 20px;
    color: #790a79;
  
  }

  

</style>