<script lang="ts">
import Routing from './routing';

export default
{
  data()
  {
    return {
      pageIndex: 0
    }
  },
  methods: {
    async login()
    {
      const username = (this.$refs.usernameRef as HTMLInputElement).value;
      const password = (this.$refs.passwordRef as HTMLInputElement).value;
    
      try
      {
        const response = await fetch('http://localhost:8080/login', {
          method: 'POST',
          credentials: 'include',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            username: username,
            password: password
          })
        })
    
        const text = await response.text()
    
        if (!response.ok)
        {
          throw new Error(text)
        }

        // emits a changePage event to the parent, who is listening for it
        this.$emit('changePage', Routing.routes.indexOf('/home'));
      }
      catch(err)
      {
        if(err instanceof Error) 
        {
          console.log(err.message);
        }
      }
    }
  }
}
</script>

<template>
  <div class="page">
    <div class="login-card">
      <h2 class="title">Login</h2>

      <div class="input-group">
        <input
          type="text"
          placeholder="Username"
          class="input-field"
          ref="usernameRef"
        />

        <input
          type="password"
          placeholder="Password"
          class="input-field"
          ref="passwordRef"
        />
  
        <button type="submit" class="login-button" @click="login">
          Sign In
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Reset box sizing */
* {
  box-sizing: border-box;
}

/* Full page background */
.page {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: Arial, Helvetica, sans-serif;
}

/* Login card */
.login-card {
  background: white;
  padding: 40px;
  width: 360px;
  border-radius: 16px;
  box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);
}

/* Title */
.title {
  margin-bottom: 25px;
  text-align: center;
  font-weight: 600;
  color: #333;
}

/* Input group layout */
.input-group {
  display: flex;
  flex-direction: column;
  gap: 18px;
}

/* Input styling */
.input-field {
  width: 100%;
  padding: 12px 14px;
  border-radius: 10px;
  border: 1px solid #ddd;
  font-size: 14px;
  outline: none;
  transition: 0.2s ease;
}

.input-field:focus {
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.2);
}

/* Button styling */
.login-button {
  width: 100%;
  padding: 12px;
  border-radius: 10px;
  border: none;
  background: #667eea;
  color: white;
  font-size: 15px;
  font-weight: 500;
  cursor: pointer;
  transition: background 0.2s ease;
}

.login-button:hover {
  background: #5a67d8;
}
</style>
