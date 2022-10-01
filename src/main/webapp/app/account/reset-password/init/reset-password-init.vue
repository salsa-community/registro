<template>
  <div>
    <h1 v-text="$t('reset.request.title')">Reset your password</h1>

    <form v-if="!success" class="check-data-form" name="form" role="form" v-on:submit.prevent="requestReset()">
      <div v-if="!success">
        <p v-text="$t('reset.request.messages.info')">Enter the email address you used to register.</p>
      </div>

      <div v-if="success">
        <p v-text="$t('reset.request.messages.success')">Check your emails for details on how to reset your password.</p>
      </div>

      <input-text
        id="email"
        v-model="$v.resetAccount.email.$model"
        :label="$t('global.form[\'email.label\']')"
        :placeholder="$t('global.form[\'email.placeholder\']')"
        :readonly="false"
        :required="true"
        :valid="$v.resetAccount.email.$dirty ? !$v.resetAccount.email.$error : null"
        :validationsCommons="{
          requiredValue: !$v.resetAccount.email.required,
          requiredMessage: $t('global.messages.validate.email.required'),
          minValue: !$v.resetAccount.email.minLength,
          minMessage: $t('mi-perfil.rfc.validations.minMessage', { min: '5' }),
          maxValue: !$v.resetAccount.email.maxLength,
          maxMessage: $t('mi-perfil.rfc.validations.maxMessage', { max: '100' }),
          regexValue: !$v.resetAccount.email.email,
          regexMessage: $t('global.messages.validate.email.invalid'),
        }"
      />

      <div class="form-group float-right">
        <button type="submit" class="btn btn-primary" v-text="$t('reset.request.form.button')" data-cy="submit">Reset</button>
      </div>
    </form>
  </div>
</template>

<script lang="ts" src="./reset-password-init.component.ts"></script>
