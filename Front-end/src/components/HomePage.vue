<script setup lang="ts">
</script>

<template>
   <header class="topbar">
 
    <!-- Logo: static branding, no logic needed -->
    <div class="logo">SpringNAS</div>
 
    <!-- Current path: TS should render this from the active directory state,
         e.g. an array of path segments ['/', 'Documents', 'Projects'] -->
    <nav class="breadcrumb" aria-label="Current path">
      <span class="breadcrumb-segment current">/</span>
    </nav>
 
    <!-- Logout button: TS should attach a click handler to clear session/token
         and redirect to the sign-in page -->
    <button class="btn-logout" id="logout-btn" type="button">Logout</button>
 
  </header>
 
  <!-- ============ FILE EXPLORER ============ -->
  <main class="explorer">
 
    <!-- Column headers for the detail-view list -->
    <div class="explorer-header-row">
      <span class="col col-icon" aria-hidden="true"></span>
      <span class="col col-name">Name</span>
      <span class="col col-type">Type</span>
    </div>
    <div class="explorer-row" data-type="folder|file" role="button" tabindex="0">
      <span class="col col-icon">
        <img
          alt="File Icon"
          src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 120 140'%3E%3Cpath d='M20 10 H75 L100 35 V130 H20 Z' fill='white' stroke='%23555' stroke-width='3'/%3E%3Cpath d='M75 10 V35 H100' fill='%23f0f0f0' stroke='%23555' stroke-width='3'/%3E%3Cline x1='35' y1='55' x2='85' y2='55' stroke='%23888' stroke-width='4'/%3E%3Cline x1='35' y1='70' x2='85' y2='70' stroke='%23888' stroke-width='4'/%3E%3Cline x1='35' y1='85' x2='75' y2='85' stroke='%23888' stroke-width='4'/%3E%3C/svg%3E"
        />
      </span>
      <span class="col col-name">Dummy Item</span>
      <span class="col col-type">JPG</span>
    </div>
        <div class="explorer-row" data-type="folder|file" role="button" tabindex="0">
      <span class="col col-icon">
        <img
          alt="Folder Icon"
          src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'%3E%3Cpath fill='%23FFC107' stroke='%23B8860B' stroke-width='1.5' d='M3 6a2 2 0 0 1 2-2h4l2 2h8a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V6z'/%3E%3C/svg%3E"
        />
      </span>
      <span class="col col-name">Dummy Item</span>
      <span class="col col-type">DIR</span>
    </div>
 
    <!-- File/folder list: empty by default. TS should populate this container
         at runtime by rendering one ".explorer-row" per item returned from
         the directory listing API.
 
         Each rendered row should follow this shape:
 
         <div class="explorer-row" data-type="folder|file" role="button" tabindex="0">
           <span class="col col-icon">[icon: icon-folder or icon-file]</span>
           <span class="col col-name">[item name]</span>
           <span class="col col-type">[Folder or File]</span>
         </div>
 
         - data-type="folder" rows: clicking/dblclicking should navigate into
           the folder and append a segment to the breadcrumb.
         - data-type="file" rows: clicking/dblclicking should open, preview,
           or download the file as appropriate.
    -->
    <div class="explorer-list" id="explorer-list"></div>
 
  </main>
</template>

<style lang="css">
:root {
  --black: #0A0D0B;
  --emerald: #10B981;
  --emerald-dim: #0B6E4F;
  --white: #FFFFFF;
  --off-white: #F4F6F4;
  --gray: #7C8C82;
  --line: #E2E7E4;
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
  gap: 6px;
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

/* ============ FILE EXPLORER ============ */
.explorer {
  margin-top: 64px;
  height: calc(100vh - 64px);
  overflow-y: auto;
  padding: 32px 28px;
  box-sizing: border-box;
}

.explorer-header-row,
.explorer-row {
  display: grid;
  grid-template-columns: 24px 60vw 20vw;
  align-items: center;
  width: 100%;
}

.explorer-header-row {
  padding: 0 12px 10px;
  border-bottom: 1px solid var(--line);
  font-size: 12px;
  font-weight: 600;
  letter-spacing: 0.04em;
  text-transform: uppercase;
  color: var(--gray);
}

.explorer-list {
  display: flex;
  flex-direction: column;
}

.explorer-row {
  padding: 10px 12px;
  border-bottom: 1px solid var(--line);
  font-size: 14px;
  cursor: pointer;
  transition: background 0.12s ease;
}

.explorer-row:hover {
  background: var(--white);
}

.explorer-row:focus-visible {
  outline: 2px solid var(--emerald);
  outline-offset: -2px;
}

.col-icon {
  display: flex;
  align-items: center;
  margin-right: 5px
}

.icon {
  width: 18px;
  height: 18px;
}

.icon-folder {
  color: var(--emerald-dim);
}

.icon-file {
  color: var(--gray);
}

.col-name {
  color: var(--black);
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.col-type {
  color: var(--gray);
  font-size: 13px;
}
</style>