<template>
  <div>
    <h1 v-text="$t('confirmation.title')" id="confirmation-code-title" class="mt-5">Código de confirmación</h1>
    <form
      id="confirmation-form"
      class="check-data-form"
      code="confirmationForm"
      role="form"
      v-on:submit.prevent="confirmationCode()"
      no-validate
    >
      <b-row>
        <b-col cols="12">
          <p><span v-text="$t('confirmation.message.enter')">Ingresa el código de confirmación que enviamos a</span> correo@dominio.com</p>
        </b-col>
        <b-col cols="12">
          <input-text
            id="id-code"
            v-model="$v.confirmation.code.$model"
            :label="$t('confirmation.code.label')"
            :readonly="false"
            :required="true"
            :valid="$v.confirmation.code.$dirty ? !$v.confirmation.code.$error : null"
            :validationsCommons="{
              requiredValue: !$v.confirmation.code.required,
              requiredMessage: $t('confirmation.code.validations.required'),
            }"
          />
        </b-col>
        <b-col cols="12">
          <div class="form-group float-right">
            <button type="button" class="button btn--primary-outline">
              <span v-text="$t('confirmation.link.back')">Volver</span>
            </button>
            <button type="submit" class="button btn--primary" data-cy="submit">
              <span v-text="$t('confirmation.form.btn')">Aceptar</span>
            </button>
          </div>
        </b-col>
        <b-col cols="12" class="text-center mt-4">
          <p><span v-text="$t('confirmation.message.notCode')">¿No te llegó ningún código?</span></p>

          <div class="form-group font-weight-bold">
            <b-link href="#">
              <span v-text="$t('confirmation.link.resend')">Reenviar código</span>
            </b-link>
          </div>
        </b-col>
      </b-row>
    </form>
  </div>
</template>
<script lang="ts" src="./confirmation-code.component.ts"></script>
