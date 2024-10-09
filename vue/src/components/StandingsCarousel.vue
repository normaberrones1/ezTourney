<template>
  <div>
  <div class="standings-carousel">
    
    <div class="standings">



      <div v-if="currentSlide === 0" class="slides">
      <h1 id="slide-1-title">STANDINGS</h1>
      
        <ul>
          <li>
            <p>NEEDS UPDATING</p>
          </li>
        </ul>

        
      </div>



      <div  v-if="currentSlide === 1" class="slides">
      <h1 id="slide-2-title">CURRENT TOURNAMENTS</h1>
      
        <ul>
          <li v-for="tourney in Tournaments" :key="tourney.id">
            <p>{{ tourney.tourneyName }} | Ends On: {{tourney.endDate }}</p>
          </li>
        </ul>

      </div>



      <div v-if="currentSlide === 2" class="slides">
      <h1 id="slide-3-title">TEAMS COMPETING</h1>
      
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

#slide-1-title,
#slide-2-title,
#slide-3-title {
  
  color: #B130FC;
}

.slides {
  position:relative;
  width: 100%;
  height: 100%;
  margin-top: 22px;
}
  


ul {
  list-style-type: none;
  align-content: center;
  padding: 0;
  font-weight: bold;
  color: #790a79;
  font-size: 25px;
}



  .slides h1 {
    font-size: 2em;
    color: #790a79;
    display: flex;
    justify-content: center;
    align-items: center;
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
    border: 15px double #790a79;
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