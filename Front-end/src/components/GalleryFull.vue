<script lang="ts">
const SCALING = 0.80;

export default {
  props: ["imageSrc", "defaultWidth", "defaultHeight"],
  emits: ["prevFull", "nextFull", "closeFull"],
  
  computed: {
    aspectRatio() {
      return this.defaultWidth / this.defaultHeight;
    }
  },

  mounted() {
    window.addEventListener("resize", this.resizeImage);
    (document.getElementById("image") as HTMLImageElement).src = this.imageSrc;
    this.resizeImage();
  },

  unmounted() {
    window.removeEventListener("resize", this.resizeImage);
  },

  methods: {
    resizeImage() {
      const image = document.getElementById("image") as HTMLImageElement
      
      // scale dimensions to fit the screen size
      image.height = window.innerHeight * SCALING;
      image.width = window.innerWidth * SCALING;

      // compute the ratio of the new dimensions
      const scaledRatio = image.width / image.height;

      // adjust dimensions to match aspect ratio
      if(scaledRatio > this.aspectRatio) {
        image.width = image.height * this.aspectRatio;
      }
      else if(scaledRatio < this.aspectRatio) {
        image.height = image.width / this.aspectRatio;
      }
    }
  }
}
</script>

<template>
  <img id="image"/>
  <button class="viewer-button viewer-left" @click="$emit('prevFull')">❮</button>
  <button class="viewer-button viewer-right" @click="$emit('nextFull')">❯</button>
  <button id="close" class="viewer-button viewer-close" @click="$emit('closeFull')">✕</button>
</template>

<style scoped>
.viewer-button {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);

  font-size: 50px;
  color: white;

  background: none;
  border: none;
  cursor: pointer;
}

.viewer-left {
  left: 30px;
}

.viewer-right {
  right: 30px;
}

.viewer-close {
  position: absolute;
  top: 25px;
  right: 30px;

  font-size: 30px;
  color: white;

  background: none;
  border: none;
  cursor: pointer;
}
</style>
