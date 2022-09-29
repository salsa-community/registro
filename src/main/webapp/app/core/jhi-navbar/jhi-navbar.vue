<template>
  <b-navbar type="dark" class="fixed-top">
    <b-navbar-brand href="#">
      <img src="../../../content/images/conacyt.svg" alt="Conacyt" />
    </b-navbar-brand>

    <b-collapse is-nav id="header-tabs">
      <b-navbar-nav>
        <b-nav-item href="#">
          <span v-text="$t('global.menu.home')">Inicio</span>
        </b-nav-item>
        <b-nav-item href="#">
          <span v-text="$t('global.menu.aboutus')">¿Quiénes somos?</span>
        </b-nav-item>
        <b-nav-item href="#">
          <span v-text="$t('global.menu.strategies')">Estrategias</span>
        </b-nav-item>
        <b-nav-item href="#">
          <span v-text="$t('global.menu.goals')">Metas</span>
        </b-nav-item>
        <b-nav-item href="#">
          <span v-text="$t('global.menu.indicators')">Indicadores</span>
        </b-nav-item>
        <b-nav-item href="#">
          <span v-text="$t('global.menu.contact')">Contacto</span>
        </b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown right id="entity-menu" v-if="authenticated" active-class="active" class="pointer" data-cy="entity">
          <span slot="button-content" class="navbar-dropdown-menu">
            <font-awesome-icon icon="th-list" />
            <span class="no-bold" v-text="$t('global.menu.entities.main')">Entities</span>
          </span>
          <entities-menu></entities-menu>
          <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
        </b-nav-item-dropdown>
        <b-nav-item-dropdown
          right
          id="admin-menu"
          v-if="hasAnyAuthority('ROLE_ADMIN') && authenticated"
          :class="{ 'router-link-active': subIsActive('/admin') }"
          active-class="active"
          class="pointer"
          data-cy="adminMenu"
        >
          <span slot="button-content" class="navbar-dropdown-menu">
            <font-awesome-icon icon="users-cog" />
            <span class="no-bold" v-text="$t('global.menu.admin.main')">Administration</span>
          </span>
          <b-dropdown-item to="/admin/user-management" active-class="active">
            <font-awesome-icon icon="users" />
            <span v-text="$t('global.menu.admin.userManagement')">User management</span>
          </b-dropdown-item>
          <b-dropdown-item to="/admin/metrics" active-class="active">
            <font-awesome-icon icon="tachometer-alt" />
            <span v-text="$t('global.menu.admin.metrics')">Metrics</span>
          </b-dropdown-item>
          <b-dropdown-item to="/admin/health" active-class="active">
            <font-awesome-icon icon="heart" />
            <span v-text="$t('global.menu.admin.health')">Health</span>
          </b-dropdown-item>
          <b-dropdown-item to="/admin/configuration" active-class="active">
            <font-awesome-icon icon="cogs" />
            <span v-text="$t('global.menu.admin.configuration')">Configuration</span>
          </b-dropdown-item>
          <b-dropdown-item to="/admin/logs" active-class="active">
            <font-awesome-icon icon="tasks" />
            <span v-text="$t('global.menu.admin.logs')">Logs</span>
          </b-dropdown-item>
          <b-dropdown-item v-if="openAPIEnabled" to="/admin/docs" active-class="active">
            <font-awesome-icon icon="book" />
            <span v-text="$t('global.menu.admin.apidocs')">API</span>
          </b-dropdown-item>
        </b-nav-item-dropdown>
        <b-nav-item-dropdown id="languagesnavBarDropdown" right v-if="languages && Object.keys(languages).length > 1">
          <span slot="button-content">
            <span class="icon-idioma"></span>
            <span class="no-bold" v-text="$t('global.menu.language')">Language</span>
          </span>
          <b-dropdown-item
            v-for="(value, key) in languages"
            :key="`lang-${key}`"
            v-on:click="changeLanguage(key)"
            :class="{ active: isActiveLanguage(key) }"
          >
            {{ value.name }}
          </b-dropdown-item>
        </b-nav-item-dropdown>
        <b-nav-item-dropdown
          right
          href="javascript:void(0);"
          id="account-menu"
          :class="{ 'router-link-active': subIsActive('/account') }"
          active-class="active"
          class="pointer"
          data-cy="accountMenu"
        >
          <span slot="button-content" class="navbar-dropdown-menu">
            <span class="icon-usuario"></span>
            <span class="no-bold" v-text="$t('global.menu.account.main')"> Account </span>
          </span>
          <b-dropdown-item data-cy="settings" to="/account/settings" tag="b-dropdown-item" v-if="authenticated" active-class="active">
            <font-awesome-icon icon="wrench" />
            <span v-text="$t('global.menu.account.settings')">Settings</span>
          </b-dropdown-item>
          <b-dropdown-item data-cy="passwordItem" to="/account/password" tag="b-dropdown-item" v-if="authenticated" active-class="active">
            <font-awesome-icon icon="lock" />
            <span v-text="$t('global.menu.account.password')">Password</span>
          </b-dropdown-item>
          <b-dropdown-item data-cy="logout" v-if="authenticated" v-on:click="logout()" id="logout" active-class="active">
            <font-awesome-icon icon="sign-out-alt" />
            <span v-text="$t('global.menu.account.logout')">Sign out</span>
          </b-dropdown-item>
          <b-dropdown-item data-cy="login" v-if="!authenticated" v-on:click="openLogin()" id="login" active-class="active">
            <font-awesome-icon icon="sign-in-alt" />
            <span v-text="$t('global.menu.account.login')">Sign in</span>
          </b-dropdown-item>
          <b-dropdown-item
            data-cy="register"
            to="/register"
            tag="b-dropdown-item"
            id="register"
            v-if="!authenticated"
            active-class="active"
          >
            <font-awesome-icon icon="user-plus" />
            <span v-text="$t('global.menu.account.register')">Register</span>
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</template>

<script lang="ts" src="./jhi-navbar.component.ts"></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
[class^='icon-'],
[class*=' icon-'] {
  font-size: 1.25rem !important;
}
</style>
