<template>
  <div>
  <div class="standings-carousel">
    
    <div class="standings">

      <h1 class="moveInMoveOut">Standings</h1>
      <div v-if="currentSlide === 1">
          <ul>
            <li v-for="standing in Standings" :key="standing.id">
              {{ standing.teamName }} - {{ standing.wins }} - {{ standing.losses }}
            </li>
          </ul>
      </div>

      <h1 class="moveInMoveOut">Current Tourneys</h1>
      <div  v-if="currentSlide === 2">
          <ul>
            <li v-for="tourney in Tournaments" :key="tourney.teamName">
              {{ tourney.tourneyName }}
            </li>
          </ul>
      </div>
      
      <h1 class="moveInMoveOut">Teams</h1>
      <div v-if="currentSlide === 3">
          <ul>
            <li v-for="team in Teams" :key="team.id">
              {{ team.teamName }}
            </li>
          </ul>
      </div>



  </div>
  <div class="carousel-controls">
    <button @click="previous"> &lt; </button>
    <button @click="next"> &gt; </button>
  </div>
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
      this.currentSlide = this.currentSlide === 0 ? 2 : this.currentSlide - 1;
    },
    next() {
      this.currentSlide = this.currentSlide === 1 ? 0 : this.currentSlide + 1;
    },
    autoSlide() {
  
      this.currentSlide = this.currentSlide === 2 ? 0 : this.currentSlide + 1;

    },

    fetchTeams() {
      TeamService.getAllTeams().then((response) => {
        this.Teams = response.data;
      }).catch((error) => {
        console.error(error);
      });
    },

  },
  data() {
    return {
      Standings: [],
      Tournaments: [],
      Teams: [],
      currentSlide: 0,
    }
  },
  computed: {
    

  },
    created() {
        console.log('created');
        this.fetchTeams();
      },
      updated() {
        console.log('updated');
      },



}
</script>

<style>
.standings-carousel {
    position: relative;
    width: 100%;
    height: 100vh;
    overflow: hidden;
    text-align: center;
    display: flex;
    justify-content: center;
    
  }

  .standings {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    font-size: 30px;
    text-align: center;
  }

  button {
    background-color: rgba(255, 255, 255, 0.0);
    color: #58deff;
    border: none;
    padding: 10px;
    margin: 0 30px;
    font-size: 50px;
  
  }

  .carousel-controls {
    position: absolute;
    bottom: 20px;
    width: 100%;
    display: flex;
    justify-content: center;
  }

  .moveInMoveOut {
    position: absolute;
    
    animation: move 9s ease-in-out infinite;
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    opacity: 0;
    
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
    
    0%, 10% {
      opacity: 0;
    }
    15%, 30% {
      opacity: 1;
    }
    35%, 45% {
      opacity: 1;
    }
    50%, 100% {
      opacity: 0;
    }
    
  }

</style>