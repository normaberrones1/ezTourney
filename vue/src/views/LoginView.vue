<template>
      <div v-if="!isAuthenticated">

      <H1 id="login-title">LOG IN NOW TO UNLOCK THE FULL EXPERIENCE!</H1>   
      <div class="login-container">
      <img id="login-image" src="/public/IMG_3790.png" alt="login-image">
   

  <div id="login">
    
    <form v-on:submit.prevent="login">
      <h1 id="login-form-title" ></h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button id="sign-in" type="submit">Sign in</button>
      <p>
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up now!</router-link></p>
    </form>
  </div>
</div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>

#login-form-title {
  color: black;
  margin-bottom: 20px;
}

#login {
  background-color: rgba(255, 255, 255, 0.6);
  text-align: center;
  border-radius: 10px;
  position: absolute;
  left: 62%;
  padding: 5%;
}

#login-image {
  margin-top: 30px;
  display: block;
  margin-right: auto;
  align-content: flex-start;
  width: 59%;
  margin-left: 75px;
}

.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  margin-right: 20px;
  font-weight: bold;
}


.form-input-group {
  margin-bottom: 1rem;
  
}

.form-input-group input {
  width: 100%;
  max-width: 300px;
  height: 10px;
  padding: 10px;
  border: 1px solid rgb(124, 124, 124);
  font-size: 17px;
  box-sizing: border-box;
}
label {
  margin-right: 0.5rem;
}

#sign-in {
  font-size: 20px;
  color: #8522be;
  font-weight: bold;
  cursor: pointer;
}

#login-title {
  text-align: center;
  color: #ffffff;
  margin-bottom: 20px;
  background: linear-gradient(90deg, rgb(60, 185, 223) 0%, rgb(184, 61, 221) 50%, rgba(233, 233, 233, 0) 100%);
  
}

</style>