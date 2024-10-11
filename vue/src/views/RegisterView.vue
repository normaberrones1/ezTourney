<template>

<img id="signup-image" src="/public/IMG_3790.png" alt="signup-image">
  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1 id="create-title">Create an ezTourney Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button id="create-account" type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>



#register {
  background-color: rgba(255, 255, 255, 0.6);
  text-align: center;
  border-radius: 10px;
  position: absolute;
  left: 62%;
  padding: 5%;
  margin-top: 95px;
  margin-right: 70px;
}

.form-input-group {
  margin-bottom: 15px;
  text-align: center;
  
}

.form-input-group input {
  width: 100%;
  max-width: 300px;
  height: 10px;
  padding: 10px;
  border: 1px solid rgb(124, 124, 124);
  font-size: 17px;
  box-sizing: border-box;
    text-align: center;
}
label {
  margin-right: 0.5rem;
}

label, input {
  font-weight: bold;
  color: black;
}

#create-title {
  font-size: 30px;
  color: #650f8d;
  font-weight: bold;
  margin-bottom: 20px;
}

#create-account {
  
  font-size: 20px;
  color: #be34e0;
  font-weight: bold;
  cursor: pointer;
}

#signup-image {
  margin-top: 30px;
  display: block;
  margin-right: auto;
  align-content: flex-start;
  width: 59%;
  margin-left: 75px;
}

#username {

}

#password {

}

#confirmPassword {

}



</style>
