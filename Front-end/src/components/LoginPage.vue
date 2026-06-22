<script setup lang="ts">
  import { useRouter } from 'vue-router'
  const router = useRouter()
  let username: string
  let password: string

  async function login(username: string, password: string) {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/api/auth/login`, {
        method: "POST",
        body: JSON.stringify({ username: username, password: password }),
        headers: new Headers({
          "Content-Type": "application/json"
        }),
        credentials: 'include'
      })

      if(response.ok) {
        router.push("/home")
      }
    }
    catch (e) {
      console.log(e)
    }
  }
</script>

<template>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign in</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@500;700&family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
  </head>
  <body>
    <section class="login-panel">
      <div class="login-card">
        <h1>SpringNAS</h1>
  
        <form @submit.prevent="login(username, password)" method="post">
          <div class="field">
            <label for="username">Username</label>
            <input v-model.trim="username" type="text" id="username" name="username" required>
          </div>
  
          <div class="field">
            <label for="password">Password</label>
            <input v-model.trim="password" type="password" id="password" name="password" required>
          </div>
  
          <button class="btn-primary">Sign in</button>
        </form>
      </div>
    </section>
  
  </body>
</template>

<style>
:root {
  --black: #0A0D0B;
  --emerald: #10B981;
  --emerald-dim: #0B6E4F;
  --white: #FFFFFF;
  --off-white: #F4F6F4;
  --gray: #7C8C82;
  --radius: 4px;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  height: 100%;
}

body {
  font-family: 'Inter', sans-serif;
  background: var(--off-white);
  color: var(--black);
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 24px;
}

/* ---------- Login panel ---------- */
.login-panel {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-card {
  width: 100%;
  max-width: 380px;
  background: var(--white);
  border: 1px solid #E2E7E4;
  border-radius: 8px;
  padding: 40px 36px;
  box-shadow: 0 4px 24px rgba(10, 13, 11, 0.06);
}

.login-card h1 {
  font-family: 'Space Grotesk', sans-serif;
  font-size: 28px;
  font-weight: 700;
  margin-bottom: 32px;
  letter-spacing: -0.01em;
}

.field {
  margin-bottom: 20px;
}

.field label {
  display: block;
  font-size: 12px;
  font-weight: 600;
  letter-spacing: 0.06em;
  text-transform: uppercase;
  color: var(--black);
  margin-bottom: 8px;
}

.field input {
  width: 100%;
  padding: 12px 14px;
  font-family: 'Inter', sans-serif;
  font-size: 14px;
  color: var(--black);
  background: var(--white);
  border: 1px solid #D6DBD8;
  border-radius: var(--radius);
  outline: none;
  transition: border-color 0.15s ease, box-shadow 0.15s ease;
}

.field input:focus {
  border-color: var(--emerald);
  box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.18);
}

.field input:focus-visible {
  outline: 2px solid var(--emerald);
  outline-offset: 1px;
}

.btn-primary {
  width: 100%;
  padding: 13px 0;
  margin-top: 8px;
  font-family: 'Inter', sans-serif;
  font-size: 14px;
  font-weight: 600;
  letter-spacing: 0.02em;
  color: var(--white);
  background: var(--black);
  border: none;
  border-radius: var(--radius);
  cursor: pointer;
  transition: background 0.15s ease;
}

.btn-primary:hover {
  background: var(--emerald-dim);
}

.btn-primary:focus-visible {
  outline: 2px solid var(--emerald);
  outline-offset: 2px;
}
</style>