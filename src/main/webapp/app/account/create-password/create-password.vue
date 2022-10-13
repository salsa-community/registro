<template>
  <div class="login-form">
    <h1 v-text="$t('createPassword.title')" id="check-data-title" class="mt-5">Crea tu contraseña</h1>
    <p class="mt-4" v-text="$t('createPassword.info')">tu clave</p>
    <form name="form" role="form" id="password-form" v-on:submit.prevent="createPasswordForm()" no-validate>
      <input-text
        id="password"
        v-model="$v.createPassword.password.$model"
        :label="$t('createPassword.password.label')"
        :readonly="false"
        :required="true"
        type="password"
        :valid="$v.createPassword.password.$dirty ? !$v.createPassword.password.$error : null"
        @keyup.native="checkStrength($v.createPassword.password.$model)"
        :validationsCommons="{
          requiredValue: !$v.createPassword.password.required,
          requiredMessage: $t('createPassword.password.validations.required'),
        }"
      />
      <div id="popover-password">
        <div class="progress">
          <div
            id="password-strength"
            class="bg bg-success"
            role="progressbar"
            aria-valuenow="40"
            aria-valuemin="0"
            aria-valuemax="100"
            style="width: 0%"
          ></div>
        </div>
        <p class="float-right text-muted" v-text="$t('createPassword.form.strong-password')">Contraseña segura<span id="result"> </span></p>
      </div>

      <input-text
        id="confirmPassword"
        v-model="$v.createPassword.confirmPassword.$model"
        :label="$t('createPassword.confirm-password.label')"
        :readonly="false"
        :required="true"
        type="password"
        class="mt-5"
        :valid="$v.createPassword.confirmPassword.$dirty ? !$v.createPassword.confirmPassword.$error : null"
        :validationsCommons="{
          requiredValue: !$v.createPassword.confirmPassword.required,
          requiredMessage: $t('createPassword.confirm-password.validations.required'),
          regexValue: !$v.createPassword.confirmPassword.sameAsPassword,
          regexMessage: $t('global.messages.error.dontmatch'),
        }"
      />

      <div class="clearfix"></div>
      <div class="form-group float-right mt-5">
        <button type="submit" class="button btn--primary" data-cy="submit">
          <span v-text="$t('createPassword.form.continue')">continue</span>
          <span class="icon-derecha"></span>
        </button>
      </div>
    </form>
  </div>
</template>

<script lang="ts" src="./create-password.component.ts"></script>
