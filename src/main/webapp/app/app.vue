<template>
  <div id="app">
    <ribbon></ribbon>
    <jhi-navbar></jhi-navbar>
    <div v-if="!hasAnyAuthority('ROLE_ADMIN') && !authenticated" class="conacyt-login-container app-container">
      <div class="column bg-alt" style="background-image: url('../content/images/loginbg.svg')">
        <div class="welcome-panel">
          <div class="welcome-panel__title">
            <img src="../content/images/logotipo.svg" alt="Rizoma" />
          </div>
        </div>
      </div>
      <div class="column login-container">
        <div class="login-wrapper box-container">
          <router-view></router-view>
        </div>
      </div>
    </div>
    <div v-else class="container-fluid admin-container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script lang="ts" src="./app.component.ts"></script>
