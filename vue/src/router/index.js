import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import TeamView from '../views/TeamView.vue';
import TournamentView from '../views/TournamentView.vue';
import TeamDetailView from '../views/TeamDetailView.vue';
import TournamentDetailsView from '../views/TournamentDetailsView.vue';
import EditTournamentView from '../views/EditTournamentView.vue';
import BuildBracket from '../components/BuildBracket.vue';
import MyTourneyList from '../components/MyTourneyList.vue';
// I understand that this should probably be a View in views but its just 
//easier for demo purposes to do this
import EditTeam from '../components/EditTeam.vue';
import TournamentBracketView from '../views/TournamentBracketView.vue';
import CurrentTourneyList from '../components/CurrentTourneyList.vue';




/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/teams/:teamId",
    name: 'teamDetails',
    component: TeamDetailView,
    meta: {
      requiresAuth: false
    }
  },
  //this should be changed so the component is a /views/... instead of /components/...
  {
    path: "/teams/:teamId/edit",
    name: 'editTeam',
    component: EditTeam
  },
  {
    path: "/tournaments/:tourneyId/edit",
    name: 'editTournament',
    component: EditTournamentView

  },

  {
    path: "/teams",
    name: "teams",
    component: TeamView,
  },
  {
    path: "/tournaments",
    name: "myTournament",
    component: MyTourneyList,
  },
  {
    path: "/all-tournaments",
    name: "MyTourneyList",
    component: CurrentTourneyList, 
    
  },

  {
    path: "/my-tournaments",
    name: "CurrentTourneyList",
    component: MyTourneyList, 
    
  },
  {
    path: "/tournaments/:id",
    name: "tournamentDetails",
    component: TournamentDetailsView,
    props: true

  },
  {
    path: '/tournamentBracketBuilder',
    name: "tournamentBracketBuilder",
    component: TournamentBracketView,
  },
  {
    path: '/buildBracket',
    name: 'BuildBracket',
    component: BuildBracket,
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && !store.state.token) {
    return { name: "login" };
  }
  // Otherwise, do nothing and they'll go to their next destination
});


export default router;
