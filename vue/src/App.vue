<template>
  <div id="capstone-app">
    <div id="nav">
      <div class="nav-links">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <span v-if="!this.$store.state.token != ''">&nbsp;|&nbsp;</span>
      <span v-if="this.$store.state.token != ''">&nbsp;|&nbsp;</span>


      <router-link v-bind:to="{ name: 'teams' }">Teams</router-link>
      <span v-if="!this.$store.state.token != ''">&nbsp;|&nbsp;</span>
      <span v-if="this.$store.state.token != ''">&nbsp;|&nbsp;</span>


      <router-link v-bind:to="{ name: 'tournament' }">Tournaments</router-link>
      <span v-if="!this.$store.state.token != ''">&nbsp;|&nbsp;</span>        
      <span v-if="this.$store.state.token != ''">&nbsp;|&nbsp;</span>


      <router-link v-bind:to="{ name: 'tournamentBracketBuilder' }">Brackets</router-link>
      <span v-if="this.$store.state.token != ''">&nbsp;|&nbsp;</span>

    
      
      <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>
        <!-- the span below adds a bar after logout if logout appears -->
        <span v-if="!this.$store.state.token != ''">&nbsp;|&nbsp;</span>
        <router-link v-bind:to="{ name: 'login' }" v-if="!this.$store.state.token != ''">Log In</router-link>
        <span v-if="!this.$store.state.token != ''">&nbsp;|&nbsp;</span>

        <router-link v-bind:to="{ name: 'register' }" v-if="!this.$store.state.token != ''">Sign Up</router-link>
        <span v-if="this.$store.state.token != ''">&nbsp;|&nbsp;</span>
      </div>

      <div>
        <span class="greeting" v-if="this.$store.state.token != ''">Welcome, {{ this.$store.state.user.username }}!</span>

      </div>
    </div>
    
    <router-view />
   
  </div>
</template>

<style>
#nav {
  background-color: #58deff;
  color: rgb(81, 0, 212);
  text-align: center;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin-bottom: 40px;
  font-size: 19px;
}

.nav-links {
  display: flex;
  align-items: center;
  color: rgb(81, 0, 212);
}

.greeting {
  display: flex;
  color: rgb(81, 0, 212);
  text-align: center;
  float:right;
}
body {
    background-image: url('/public/teams-background.jpg');
    background-repeat: no-repeat;
    height: 100vh;
    background-position: center;
    background-attachment: fixed;
    overflow: auto;

}

#capstone-app {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  font-family:'Arial Narrow', sans-serif;
  color: #58deff;
}
</style>
