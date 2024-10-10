<template>

    <div>
        <div id="tournament-container">
            
            
        <div class="tourneyDiv">
            <div class="navMy" v-if="isAuthenticated">
                <router-link to="/my-tournaments" id="my-tournaments">Back to My Tournaments</router-link>
            
                <div id="tourneyForm" v-if="isAuthenticated">
            <TourneyForm></TourneyForm>
        </div>

        <div v-else>
            <router-link to="/login" id="home-login-tourney">Log in to create a tournament!</router-link>
        </div>
            
            
            </div>
    
        <div>
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


        <div class="tournament-list">
            <router-link class="tourney-link" v-for="tourney in filteredTournaments" :key="tourney.tourneyId"
                v-bind:to="{ name: 'tournamentDetails', params: { id: tourney.tourneyId } }">
                <TournamentCard id="tourney-list" v-bind:tourney="tourney" />
            </router-link>
        </div>


        </div>

        


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

.tourneyDiv {
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    margin-right: 20px;
    display: grid;

}

#tournament-container {
    margin-left: 20px;
    display: flex;
    align-items: flex-start;
    grid-template-columns: 1fr 1fr;
}



#tourneyForm {
    border-radius: 10px;
    align-items: center;
    justify-content: center;
    display: flex;
    flex: 1;
}

#my-tournaments {
    text-decoration: none;
    font-weight: bold;
    display: flex;
    flex-direction: column;
    align-items: start;
    justify-content: center;
    flex: 1;
    margin-bottom: 30px;
    
}

.searchBy {
    display: flex;
    flex-direction: column;
    align-items: center;
    box-sizing: border-box;
    margin-bottom: 30px;
}


#search-bar {
    width: 300px;
    text-align: center;
    
}

.home-image {
    display: flex;
    justify-content: center;
    margin-bottom: 30px;
    height: 240px;
}

.tournament-list {
    
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;

}






</style>