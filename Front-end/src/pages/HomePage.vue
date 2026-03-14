<script lang="ts">
import ImageStubAPI from '@/api/stub/imageStub';
import Image from '@/types/image';
import GalleryFull from '@/components/GalleryFull.vue'
import GalleryThumb from '@/components/GalleryThumb.vue';

export default {
  emits: ["changePage"],
  components: {
    GalleryThumb,
    GalleryFull
  },
  
  data() {
    return {
      imageAPI: new ImageStubAPI(), // temporarily using stub API
      images: [] as Image[],
      selectImageIndex: -1
    }
  },

  mounted() {
    this.images = this.imageAPI.getImages();
  },

  methods: {
    selectImage(index: number) {
      this.selectImageIndex = index;
    },

    nextImage() {
      this.selectImageIndex = (this.selectImageIndex + 1) % this.images.length
    },

    prevImage() {
      this.selectImageIndex = (this.selectImageIndex - 1 + this.images.length) % this.images.length
    }
  }
}
</script>

<template>
  <div id="gallery">
    <GalleryThumb 
      v-for="(image, index) in images"
      v-bind:key="image.path"
      :index="index"
      :image-src="image.path"
      @click="selectImageIndex = index"
    />
  </div>
  
  <div v-if="selectImageIndex >= 0" id="selectedImageContainer">
    <GalleryFull 
      :key="selectImageIndex"
      :image-src="images[selectImageIndex]!.path"
      :default-width="images[selectImageIndex]!.width"
      :default-height="images[selectImageIndex]!.height"
      @close-full="selectImageIndex = -1"
      @prev-full="prevImage"
      @next-full="nextImage"/>
  </div>
</template>

<style scoped>
/* Gallery container (flex layout for rows) */
#gallery {
  width: 100%;
  max-width: 90vw;
  margin: auto;
  padding: 20px;

  display: flex;
  justify-content: space-between;
  flex-wrap: wrap; /* allow multiple rows */
  gap: 16px;
}

/* Each thumbnail wrapper */
#gallery > * {
  display: flex;
  justify-content: center;
  align-items: center; /* vertically center the image */
  border-radius: 8px;
  overflow: hidden;
  background: #f0f0f0;
  cursor: pointer;

  transition: transform 0.15s ease, box-shadow 0.15s ease;
}

/* Hover effect */
#gallery > *:hover {
  transform: scale(1.03);
  box-shadow: 0 6px 14px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Image styling */
#gallery img {
  height: 10vw; /* same height for all images */
  width: auto;   /* preserve aspect ratio */
  display: block;
  border-radius: 8px;
}

/* Fullscreen container */
#selectedImageContainer {
  position: fixed;
  inset: 0;

  display: flex;
  justify-content: center;
  align-items: center;

  background: rgba(0,0,0,0.92);
  z-index: 1000;
}

/* Image inside GalleryFull */
#selectedImageContainer img {
  max-width: 90%;
  max-height: 90%;
  border-radius: 8px;
}
</style>