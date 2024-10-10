<template>

    <div>
        <div id="tournament-container">
            

        <div class="tourneyDiv">
            <div class="navMy">
                <router-link to="/tournaments" id="my-tournaments">View Tournaments</router-link>
            </div>
    
            <h1 v-if="isAuthenticated" id="tournaments-title">MY TOURNAMENTS</h1>
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


.searchMineBy {
    justify-content: center;
    width: 100%;    
}

#my-tournaments {
    display: flex;
    font-size: 1.5em;
    color: #B130FC;
    text-decoration: none;
    background-color: rgb(243, 243, 243);
    padding: 1px;
    border-radius: 5px;
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




#select-filter {
    position: relative;
    width: 40%;
    height: 15%;
    border-radius: 5px;
    border: 1px solid rgb(124, 124, 124);
    text-align: center;
    cursor: pointer;
}

#search-bar {
    position: relative;
    justify-content: center;
    cursor: pointer;
    margin-top: 2%;
    margin-bottom: 2%;
    width: 39%;
    height: 15px;
    border-radius: 5px;
    border: 1px solid rgb(124, 124, 124);
    text-align: center;
}


h1 {
    text-align: center;
    color: #58deff;
}

#tournament-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas:
        "tourney tourney tourney tourneyForm"
        "tourney tourney tourney tourneyForm";
    margin-left: 32px;
    margin-right: 15px;
    
}

#tourneyForm {
    display: flex;
    justify-content: center;
    margin-top: 5px;

}


.tourneyDiv {
    grid-area: tourney;
    background-color: rgba(255, 255, 255, 0.6);
    padding: 25px;
    border-radius: 10px;
    margin-bottom: 20px;
    justify-content: center;
    height: 100%;
    margin-top: 80px;
    
}

#tournaments-title {
    margin-top: 2%;
    font-size: 2em;
    color: #000000;
    border-radius: 10px;

}

#click-tourney {
    margin-top: 0.1%;
    margin-bottom: 2%;
    font-size: 1.5em;
    text-align: center;

    color: #0502c2;
    
}

#tourney-list {
    margin-bottom: 20px;
}

.tourney-link {
    text-decoration: none;
    text-align: center;
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