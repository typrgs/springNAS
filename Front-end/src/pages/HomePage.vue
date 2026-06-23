<script setup lang="ts">
  import { useRouter } from 'vue-router'
  import { API_URL, getJson } from '@/util.ts'
  import { ref, onBeforeMount, onMounted } from 'vue'
  import FileComponent from '@/components/FileComponent.vue'
  import { type UserFile } from '@/types/userFile'
  
  const router = useRouter()

  const rootDir = ref<string>("")
  const files = ref<UserFile[]>([])
  const breadcrumbs = ref([""])

  onBeforeMount(async () => {
    await loadFiles()
  })

  onMounted(() => {
    // stop user selection after double click
    document.addEventListener('mousedown', function(event) {
      if (event.detail === 2) {
        event.preventDefault();
      }
    });
  })

  async function logout() {
    try {
      const response = await fetch(`${API_URL}/api/auth/logout`, {
        method: 'DELETE',
        credentials: 'include'
      })

      if(response.ok) {
        router.push('/')
      }
    }
    catch (e) { console.log(e) }
  }

  async function loadFiles() {
    rootDir.value = (await getJson(`${API_URL}/api/file/showRoot`))['root']
    files.value = await loadDir(rootDir.value)
  }

  async function loadDir(dir: string): Promise<UserFile[]> {
    return await getJson(`${API_URL}/api/file/dir?root=${encodeURIComponent(dir)}`)
  }

  async function navigateDown(dir: string) {
    breadcrumbs.value.push(dir)
    files.value = await loadDir(rootDir.value + '/' + dir)
  }

  async function navigateBreadcrumb(index: number) {
    if(index != breadcrumbs.value.length - 1) {
      breadcrumbs.value = breadcrumbs.value.slice(0, index+1)
      files.value = await loadDir(rootDir.value + '/' + breadcrumbs.value[index])
    }
  }
</script>

<template>
   <header class="topbar">
    <div class="logo">SpringNAS</div>
 
    <!-- Current path: TS should render this from the active directory state,
         e.g. an array of path segments ['/', 'Documents', 'Projects'] -->
    <nav class="breadcrumb" aria-label="Current path">
      <span 
        class="breadcrumb-segment"
        v-for="(crumb, index) in breadcrumbs"
        :key="index"
        @click="navigateBreadcrumb(index)">
        {{ crumb.length > 0 ? crumb : 'root' }} /&nbsp;
      </span>
    </nav>
 
    <!-- Logout button: TS should attach a click handler to clear session/token
         and redirect to the sign-in page -->
    <button class="btn-logout" id="logout-btn" type="button" @click="logout">Logout</button>
 
  </header>
 
  <!-- ============ FILE EXPLORER ============ -->
  <main class="explorer">
 
    <!-- Column headers for the detail-view list -->
    <div class="explorer-header-row">
      <span class="col col-icon" aria-hidden="true"></span>
      <span class="col col-name">Name</span>
      <span class="col col-type">Type</span>
    </div>

    <div class="explorer-list" id="explorer-list">
      <FileComponent
        v-for="file in files"
        :key="file.fileName"
        :userFile="file"
        @cd="navigateDown(file.fileName)"/>
    </div>
  </main>
</template>

<style scoped src="@/../assets/styles/global.css"></style>
<style scoped src="@/../assets/styles/explorer-styles.css"></style>
<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* ============ HEADER / NAV BAR ============ */
.topbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  z-index: 100;
  display: flex;
  align-items: center;
  gap: 24px;
  height: 64px;
  padding: 0 28px;
  background: var(--black);
  border-bottom: 1px solid rgba(255, 255, 255, 0.08);
  box-sizing: border-box;
}

.logo {
  font-family: 'Space Grotesk', sans-serif;
  font-size: 16px;
  font-weight: 700;
  letter-spacing: 0.04em;
  color: var(--white);
  white-space: nowrap;
}

/* Breadcrumb grows to fill available space between logo and logout button */
.breadcrumb {
  flex: 1;
  display: flex;
  align-items: center;
  font-size: 13px;
  color: var(--gray);
  overflow-x: auto;
  white-space: nowrap;
}

.breadcrumb-segment {
  color: var(--gray);
  cursor: pointer;
}

.breadcrumb-segment:hover {
  color: var(--emerald);
}

.breadcrumb-segment.current {
  color: var(--white);
  font-weight: 500;
  cursor: default;
}

.breadcrumb-separator {
  color: var(--gray);
  opacity: 0.5;
}

.btn-logout {
  flex-shrink: 0;
  padding: 8px 16px;
  font-family: 'Inter', sans-serif;
  font-size: 13px;
  font-weight: 600;
  color: var(--white);
  background: transparent;
  border: 1px solid rgba(255, 255, 255, 0.18);
  border-radius: var(--radius);
  cursor: pointer;
  transition: border-color 0.15s ease, color 0.15s ease;
}

.btn-logout:hover {
  border-color: var(--emerald);
  color: var(--emerald);
}

.btn-logout:focus-visible {
  outline: 2px solid var(--emerald);
  outline-offset: 2px;
}
</style>