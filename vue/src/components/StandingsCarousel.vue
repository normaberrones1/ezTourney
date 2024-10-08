<template>
  <div>
  <div class="standings-carousel">
    
    <div class="standings">



      <div v-if="currentSlide === 0" class="slides">
      <h1 class="moveInMoveOut">Standings</h1>

        
      </div>



      <div  v-if="currentSlide === 1" class="slides">
      <h1 class="moveInMoveOut">Current Tourneys</h1>
      
        <ul>
          <li v-for="tourney in Tournaments" :key="tourney.id">
            <p>{{ tourney.tourneyName }}</p>
          </li>
        </ul>

      </div>



      <div v-if="currentSlide === 2" class="slides">
      <h1 class="moveInMoveOut">Teams Competing</h1>
      
        <ul>
          <li v-for="team in Teams" :key="team.id">
            <p>{{ team.teamName }}</p>
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
      this.currentSlide = this.currentSlide === 2 ? 0 : this.currentSlide + 1;
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

    fetchTournaments() {
      TourneyService.getCurrentFilteredTournaments().then((response) => {
        this.Tournaments = response.data;
      }).catch((error) => {
        console.error(error);
      });
    },

  },

  mounted() {
    setInterval(this.autoSlide, 7000);
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
    slideClass() {
      return (index) => (index === this.currentSlide ? 'active' : '');
    }
    

  },
    created() {
        console.log('created');
        this.fetchTeams();
        this.fetchTournaments();
      },
      updated() {
        console.log('updated');
      },



}
</script>

<style>


ul {
  list-style-type: none;
  align-content: center;
  padding: 0;
  font-weight: bold;
  color: #790a79;
}

  .slides {
    position: relative;
    width: 100%;
    
  }

  .standings {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 80vh;
    width: 80%;
    padding: 20px;
    background-color: rgba(255, 255, 255, 0.6);
    border: 5px double #790a79;
    border-radius: 10px;
  }

  .standings-carousel {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 97%;
    padding: 20px;
  }

  .moveInMoveOut {
    animation: move 9s ease-in-out infinite;
    justify-content: center;
    opacity: 0;
    z-index: 1;
    
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
    font-size: xx-large;
    padding: 20px;
    color: #790a79;
  
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