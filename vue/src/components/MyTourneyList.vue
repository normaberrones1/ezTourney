<template>

    <div>
        <div id="tournament-container">
            

        <div class="tourneyDiv">
            <div class="navMy">
                <router-link to="/all-tournaments" id="all-tournaments"> Back to View All Tournaments</router-link>
            
                <div id="tourneyForm" v-if="isAuthenticated">
            <TourneyForm></TourneyForm>
        </div>

        <div v-else>
            <router-link to="/login" id="home-login-tourney">Log in to create a tournament!</router-link>
        </div>
            
            
            </div>

            <div v-if="isAuthenticated">
                <h1 id="tournaments-title">MY TOURNAMENTS</h1>
                <h2 id="click-tourney">Click a tournament to view details!</h2>
            
                <div class="home-image">
                    <img src="/public/trophy.jpg" alt="trophy">
                </div>

                <div class="searchMineBy">
                <input 
                    id="search-bar" type="text" 
                    v-model="searchTerm" placeholder="Search Tournaments"
                />

                <div id="tourney-filter">
                        <label for="select-filter"></label>
                        <select id="select-filter" v-model="currentFilter" @change="fetchDirectorFilteredTournaments">


                            <option value="my-current">Current Tournaments</option>

                            <option value="my-upcoming">Upcoming Tournaments</option>

                            <option value="my-past">Past Tournaments</option>

                            <option value="my-all">All</option>
                        </select>
                    </div>
                </div>



            <router-link class="tourney-link" v-for="tourney in filteredTournaments" :key="tourney.tourneyId"
                v-bind:to="{ name: 'tournamentDetails', params: { id: tourney.tourneyId } }">
                <TournamentCard id="tourney-list" v-bind:tourney="tourney" />
            </router-link>
        </div>




</div>

    </div>
</div>

</template>

<script>
import TournamentCard from './TournamentCard.vue';
import TourneyService from '../services/TourneyService';
import TourneyForm from './TourneyForm.vue';

export default  {

    data() {
        return {
            tournaments: [],
            searchTerm: '',
            dropdown: false,
            currentFilter: 'my-current',
            directorId: this.$store.state.user.id
            
        }
    },

    components: {
        TournamentCard, TourneyForm},

    props: {
        tourney: {
            type: Object,
            required: true
        }
    },

    created() {
        this.fetchDirectorFilteredTournaments();
    },

    computed: {

        filteredTournaments() {
            const today = new Date();

            return this.tournaments.filter(tourney => {
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
            this.fetchDirectorFilteredTournaments();
        },

        fetchDirectorFilteredTournaments(userId) {
            TourneyService.getDirectorFilteredTournaments(this.currentFilter, this.directorId).then((response) => {
                this.tournaments = response.data;
                
                if(this.currentFilter ==='my-current' && this.tournaments.length === 0) {
                    this.currentFilter = 'my-upcoming';
                    this.fetchDirectorFilteredTournaments();
                }

            }).catch((error) => {
                console.log("Error finding tournaments", error);
            });
        }
    },

    watch: {
        currentFilter(newFilter) {
            this.searchTerm = '';
            this.fetchDirectorFilteredTournaments();
        }
    }

}

</script>

<style>
.tourneyDiv {
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    margin-right: 20px;
    padding: 50px;
    display: grid;
    grid-template-columns: 1fr 1fr;
    flex: 1;
}

#tournament-container {
    margin-left: 20px;
    display: flex;
    align-items: flex-start;
}

#tourneyForm {
    border-radius: 10px;
    align-items: center;
    justify-content: center;
    display: flex;
    flex: 1;
}

#all-tournaments {
    text-decoration: none;
    font-weight: bold;
    display: flex;
    flex-direction: column;
    align-items: start;
    justify-content: center;
    flex: 1;
    margin-bottom: 30px;

}

.home-image {
    display: flex;
    justify-content: center;
    margin-bottom: 30px;
    height: 240px;
}

.searchMineBy {
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


</style>