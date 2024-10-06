<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'teams' }">Teams</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'tournament' }">Tournaments</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link>
        <!-- the span below adds a bar after logout if logout appears -->
        <span v-if="this.$store.state.token != ''">&nbsp;|&nbsp;</span>
      <router-link v-bind:to="{ name: 'login' }">Log In</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'register' }">Sign Up</router-link> &nbsp; &nbsp;
      <span class="greeting" v-if="this.$store.state.token != ''">Welcome, {{ this.$store.state.user.username }}!</span>
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
}

.greeting {
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
</style>
