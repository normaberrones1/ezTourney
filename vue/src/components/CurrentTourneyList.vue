<template>

    <div>
        <div id="tournament-container">
            
            
        <div class="tourneyDiv">
            <div class="navMy">
                <router-link to="/my-tournaments" id="my-tournaments">My Tournaments</router-link>

            </div>
    
            <h1 id="tournaments-title">TOURNAMENTS</h1>
            <h2 id="click-tourney">Click a tournament to view details!</h2>
           
            <div class="home-image">
                <img src="/public/trophy.jpg" alt="trophy">
            </div>

            <div class="searchBy">
            <input 
                id="search-bar" type="text" 
                v-model="searchTerm" placeholder="Search Tournaments"
            />

            <div id="tourney-filter">
                <label for="select-filter"></label>
                <select id="select-filter" v-model="currentFilter" @change="fetchFilteredTournaments">


                    <option value="current">Current Tournaments</option>

                    <option value="upcoming">Upcoming Tournaments</option>

                    <option value="past">Past Tournaments</option>

                    <option value="all">All</option>
                </select>
            
            </div>
            
            
            </div>



            <router-link class="tourney-link" v-for="tourney in filteredTournaments" :key="tourney.tourneyId"
                v-bind:to="{ name: 'tournamentDetails', params: { id: tourney.tourneyId } }">
                <TournamentCard id="tourney-list" v-bind:tourney="tourney" />
            </router-link>
        


        </div>

        

        <div id="tourneyForm" v-if="isAuthenticated">
            <TourneyForm></TourneyForm>
        </div>

        <div v-else>
            <router-link to="/login" id="home-login-tourney">Log in to create a tournament!</router-link>
        </div>

</div>

    </div>


</template>

<script>
import TournamentCard from './TournamentCard.vue';
import TourneyService from '../services/TourneyService';
import TourneyForm from './TourneyForm.vue';


export default {


    data() {
        return {
            tournaments: [],
            searchTerm: '',
            dropdown: false,
            currentFilter: 'current',
            directorTournaments: []
        }
    },
    props: {
        tourney: {
            type: Object,
            required: true
        }
    },
    
    components: { TournamentCard, TourneyForm },

    created() {
        this.fetchFilteredTournaments();
    },
    computed: {
        filteredTournaments() {
            const today = new Date();

            return this.tournaments.filter((tourney) => {
                return tourney.tourneyName.toLowerCase().includes(this.searchTerm.toLowerCase());
            });


        },

        isAuthenticated() {
            return this.$store.state.token != '';
        }
    },

    methods: {
        toggleDropdown() {
            this.dropdown = !this.dropdown;
        },
        tourneyFilter(filter) {
            this.currentFilter = filter;
            this.fetchFilteredTournaments();

        },

        fetchFilteredTournaments() {
            TourneyService.getFilteredTournaments(this.currentFilter).then(response => {
                this.tournaments = response.data;

                if(this.currentFilter === 'current' && this.tournaments.length === 0) {
                    
                    this.currentFilter = 'upcoming';
                    this.fetchFilteredTournaments();
                }

            }).catch((error) => {
                console.log("Error finding tournaments", error);
            });
        },
    },


    watch: {
        currentFilter(newFilter) {
            this.searchTerm = '';
        }
    }

}
</script>

<style>

.searchBy {
    justify-content: center;
    width: 100%;    
}

#home-login-tourney {
    display: flex;
    justify-content: center;
    font-size: 1.5em;
    color: #6ce7fd;
    text-decoration: none;
    background-color: rgba(255, 255, 255, 0.6);
    padding: 10px;
    border-radius: 10px;
    margin-left: 10%;
}

.navMy {
}

#my-tournaments {
    font-size: 17px;
    color: #790a79;
    text-decoration: none;
    border-radius: 10px;
    font-weight: bold;
}



#select-filter {

}

#search-bar {

}


h1 {
    text-align: center;
    color: #58deff;
}

#tournament-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    
}

#tourneyForm {
    display: flex;
    justify-content: center;
    flex: 300px;
}


.tourneyDiv {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    background-color: rgba(255, 255, 255, 0.6);
    padding: 25px;
    border-radius: 10px;
    justify-content: center;
    
    width: 95%;
    height: auto;
    
}

#tournaments-title {
    text-align: center;
    margin-top: 2%;
    font-size: 2em;
    color: #000000;
    margin-left: 25%;
    margin-right: 25%;
    width: 100%;

}

#click-tourney {
    margin-top: 0.1%;
    margin-bottom: 2%;
    font-size: 1.5em;
    color: #0502c2;
    width: 100%;
    font-weight: bold;
    
}

#tourney-list {
    margin-bottom: 20px;
    margin:35px;
    
}

.tourney-link {
    text-decoration: none;
    margin-bottom: 20px;
}

.home-image {
    display: flex;
    justify-content: center;
    width: 100%;
    height: auto;
}

.home-image img {
    width: 250px;
    height: auto;
    border-radius: 10px;
}




</style>