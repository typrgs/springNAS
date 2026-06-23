<script setup lang="ts">
  import type { UserFile } from '@/types/userFile';
  import '@/../assets/styles/global.css';
  import '@/../assets/styles/explorer-styles.css';

  const props = defineProps<{
    userFile: UserFile
  }>()

</script>

<template>
  <div class="explorer-row" data-type="folder|file" role="button" tabindex="0" @dblclick="userFile.fileType.length === 0 ? $emit('cd') : null">
    <span class="col-icon">
      <img
        alt="File Icon"
        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 120 140'%3E%3Cpath d='M20 10 H75 L100 35 V130 H20 Z' fill='white' stroke='%23555' stroke-width='3'/%3E%3Cpath d='M75 10 V35 H100' fill='%23f0f0f0' stroke='%23555' stroke-width='3'/%3E%3Cline x1='35' y1='55' x2='85' y2='55' stroke='%23888' stroke-width='4'/%3E%3Cline x1='35' y1='70' x2='85' y2='70' stroke='%23888' stroke-width='4'/%3E%3Cline x1='35' y1='85' x2='75' y2='85' stroke='%23888' stroke-width='4'/%3E%3C/svg%3E"
        v-if="props.userFile.fileType.length > 0"
      />
      <img
        alt="Folder Icon"
        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'%3E%3Cpath fill='%23FFC107' stroke='%23B8860B' stroke-width='1.5' d='M3 6a2 2 0 0 1 2-2h4l2 2h8a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V6z'/%3E%3C/svg%3E"
        v-else
      />
    </span>
    <span class="col-name">{{ props.userFile.fileName }}</span>
    <span class="col-type">{{ props.userFile.fileType }}</span>
  </div>
</template>

<style scoped src="@/../assets/styles/global.css"></style>
<style scoped src="@/../assets/styles/explorer-styles.css"></style>
