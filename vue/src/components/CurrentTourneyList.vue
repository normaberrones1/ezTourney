<template>

    <div>
    
        <h1 id="tournaments-title">CURRENT TOURNAMENTS</h1>

        <h2 id="click-tourney">Click a tournament to view details</h2>

        
        <div id="tournament-container">
        <div class="tourneyDiv">
            <input 
                id="search-bar" type="text" 
                v-model="searchTerm" placeholder="Search Tournaments"
            />

            <div id="tourney-filter">
                <label for="select-filter"></label>
                <select id="select-filter" placeholder="Filter By" v-model="currentFilter" @change="fetchFilteredTournaments">


                    <option value="current">Current Tournaments</option>

                    <option value="upcoming">Upcoming Tournaments</option>

                    <option value="past">Past Tournaments</option>

                    <option value="all">All</option>
                </select>
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
            <router-link to="/login" id="home-login">Log in to create a tournament!</router-link>
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
            currentFilter: 'Current',
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
}


.tourneyDiv {
    grid-area: tourney;
    background-color: rgba(255, 255, 255, 0.6);
    padding: 25px;
    border: 1px solid rgb(124, 124, 124);
    border-radius: 10px;
    
}

#tournaments-title {
    position: relative;
    right: 12.3%;
    margin-top: 2%;
    margin-bottom: 2%;
    font-size: 2em;
    color: #000000;
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    margin-left: 150px;
    margin-right: 220px;

}

#click-tourney {
    position: relative;
    right: 12.3%;
    margin-top: 2%;
    margin-bottom: 2%;
    font-size: 1.5em;
    text-align: center;
    color: #000000;
    background-color: rgba(255, 255, 255, 0.6);
    border-radius: 10px;
    margin-left: 180px;
    margin-right: 250px;
    
}

#tourney-list {
    margin-bottom: 20px;
}

.tourney-link {
    text-decoration: none;
    text-align: center;
}
</style>