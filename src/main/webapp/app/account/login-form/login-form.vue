<template>
  <div>
    <div v-if="!hasAnyAuthority('ROLE_ADMIN') && !authenticated">
      <b-alert show data-cy="loginError" variant="danger" v-if="authenticationError" v-html="$t('login.messages.error.authentication')">
        <strong>Failed to sign in!</strong> Please check your credentials and try again.
      </b-alert>
      <h1 v-text="$t('login.title')" id="check-data-title" class="mt-5">Bienvenido a Rizoma</h1>
      <h4 v-text="$t('login.title2')">Iniciar sesión</h4>
      <b-form role="form" class="check-data-form mb-3" v-on:submit.prevent="doLogin()">
        <b-form-group v-bind:label="$t('global.form[\'username.label\']')" label-for="username">
          <b-form-input
            id="username"
            type="text"
            name="username"
            autofocus
            v-bind:placeholder="$t('global.form[\'username.placeholder\']')"
            v-model="login"
            data-cy="username"
          >
          </b-form-input>
        </b-form-group>
        <b-form-group v-bind:label="$t('login.form.password')" label-for="password">
          <b-form-input
            id="password"
            type="password"
            name="password"
            v-model.trim="name"
            v-bind:placeholder="$t('login.form[\'password.placeholder\']')"
            v-model="password"
            data-cy="password"
          >
          </b-form-input>
        </b-form-group>
        <div class="form-group float-right mb-3">
          <b-form-checkbox id="rememberMe" name="rememberMe" v-model="rememberMe" checked>
            <span v-text="$t('login.form.rememberme')">Remember me</span>
          </b-form-checkbox>

          <b-link
            :to="'/account/reset/request'"
            class="alert-link mt-3 d-block"
            v-text="$t('login.password.forgot')"
            data-cy="forgetYourPasswordSelector"
            >Did you forget your password?</b-link
          >

          <b-button data-cy="submit" type="submit" variant="primary" v-text="$t('login.form.button')" class="mt-3 float-right"
            >Sign in</b-button
          >
        </div>
      </b-form>

      <div class="clearfix"></div>
      <div class="border-top mt-4 mb-4"></div>
      <div class="mt-3 text-center">
        <span v-text="$t('global.messages.info.register.noaccount')">You don't have an account yet?</span>
        <b-link :to="'/register'" class="alert-link" v-text="$t('global.messages.info.register.link')">Register a new account</b-link>
      </div>
    </div>
    <div v-else>
      <home />
    </div>
  </div>
</template>

<script lang="ts" src="./login-form.component.ts"></script>
