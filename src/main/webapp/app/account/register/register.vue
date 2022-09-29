<template>
  <div>
    <h1 v-text="$t('register.title')" id="register-title" data-cy="registerTitle">Registration</h1>

    <div class="alert alert-success" role="alert" v-if="success" v-html="$t('register.messages.success')">
      <strong>Registration saved!</strong> Please check your email for confirmation.
    </div>

    <div class="alert alert-danger" role="alert" v-if="error" v-html="$t('register.messages.error.fail')">
      <strong>Registration failed!</strong> Please try again later.
    </div>

    <div class="alert alert-danger" role="alert" v-if="errorUserExists" v-html="$t('register.messages.error.userexists')">
      <strong>Login name already registered!</strong> Please choose another one.
    </div>

    <div class="alert alert-danger" role="alert" v-if="errorEmailExists" v-html="$t('register.messages.error.emailexists')">
      <strong>Email is already in use!</strong> Please choose another one.
    </div>
    <form
      id="register-form"
      class="login-form"
      name="registerForm"
      role="form"
      v-on:submit.prevent="register()"
      v-if="!success"
      no-validate
    >
      <input-text
        id="id-curp"
        v-model="$v.registerAccount.curp.$model"
        :label="$t('register.curp.label')"
        :placeholder="$t('register.curp.placeHolder')"
        :readonly="false"
        :required="true"
        :valid="$v.registerAccount.curp.$dirty ? !$v.registerAccount.curp.$error : null"
        :validationsCommons="{
          requiredValue: !$v.registerAccount.curp.required,
          requiredMessage: $t('register.curp.validations.required'),
          regexValue: !$v.registerAccount.email.pattern,
          regexMessage: $t('register.curp.validations.regexMessage'),
        }"
      />

      <div class="form-group float-right font-weight-bold">
        <b-link href="https://www.gob.mx/curp/" target="_blank">
          <span v-text="$t('register.no-curp')">¿No conoces tu CURP?</span>
        </b-link>
      </div>

      <div class="clearfix"></div>

      <input-text
        id="id-email"
        v-model="$v.registerAccount.email.$model"
        :label="$t('register.email.label')"
        :placeholder="$t('register.email.placeHolder')"
        :readonly="false"
        :required="true"
        :valid="$v.registerAccount.email.$dirty ? !$v.registerAccount.email.$error : null"
        :validationsCommons="{
          requiredValue: !$v.registerAccount.email.required,
          requiredMessage: $t('register.email.validations.required'),
          regexValue: !$v.registerAccount.email.email,
          regexMessage: $t('register.email.validations.regexMessage'),
        }"
      />

      <input-text
        id="id-confirm-email"
        v-model="$v.registerAccount.confirmEmail.$model"
        :label="$t('register.confirm-email.label')"
        :placeholder="$t('register.email.placeHolder')"
        :readonly="false"
        :required="true"
        :valid="$v.registerAccount.confirmEmail.$dirty ? !$v.registerAccount.confirmEmail.$error : null"
        :validationsCommons="{
          requiredValue: !$v.registerAccount.confirmEmail.required,
          requiredMessage: $t('register.confirm-email.validations.required'),
          regexValue: !$v.registerAccount.confirmEmail.email,
          regexMessage: $t('register.confirm-email.validations.regexMessage'),
        }"
      />

      <div class="clearfix"></div>
      <div class="form-group float-right">
        <button type="submit" class="button btn--primary" data-cy="submit">
          <span v-text="$t('register.form.button')">Register</span>
          <span class="icon-derecha"></span>
        </button>
      </div>
    </form>
  </div>
</template>

<script lang="ts" src="./register.component.ts"></script>
