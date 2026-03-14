<script lang="ts">
import Routing from './pages/routing';

export default
{
  computed: {
    currentPage()
    {
      return Routing.pages[this.index];
    }
  },

  methods: {
    updatePageIndex(pageIndex: number)
    {
      this.index = pageIndex;
    }
  },

  data()
  {
    return {
      index: 0,
    }
  },

  mounted()
  {
    window.addEventListener('hashchange', () => {
      this.index = Routing.routes.indexOf(window.location.hash.slice(1));
		})
  }
}
</script>

<template>
  <main>
     <!-- CHange page on receipt of a change-page event from child component -->
    <component :is="currentPage" @change-page="updatePageIndex($event)" />
  </main>
</template>
