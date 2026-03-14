<script lang="ts">
export default {
  props: ["index", "imageSrc"],
  mounted() {
    const image = document.getElementById("" + this.index) as HTMLImageElement;
    image.src = this.imageSrc;
  }
}
</script>

<template>
  <img :id="index" />
</template>
