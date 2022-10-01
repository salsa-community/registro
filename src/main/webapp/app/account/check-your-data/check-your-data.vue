<template>
  <div>
    <h1 v-text="$t('checkData.title')" id="check-data-title" class="mt-5">Verifica tus datos</h1>
    <form id="data-form" class="check-data-form" name="dataForm" role="form" v-on:submit.prevent="checkDataF()" no-validate>
      <b-row>
        <b-col cols="12">
          <input-text
            id="id-name"
            v-model="$v.checkData.name.$model"
            :label="$t('checkData.name.label')"
            :placeholder="$t('checkData.name.placeHolder')"
            :readonly="false"
            :required="true"
            :valid="$v.checkData.name.$dirty ? !$v.checkData.name.$error : null"
            :validationsCommons="{
              requiredValue: !$v.checkData.name.required,
              requiredMessage: $t('checkData.name.validations.required'),
            }"
          />
        </b-col>
        <b-col cols="6">
          <input-text
            id="id-firstName"
            v-model="$v.checkData.firstName.$model"
            :label="$t('checkData.firstName.label')"
            :placeholder="$t('checkData.firstName.placeHolder')"
            :readonly="false"
            :required="true"
            :valid="$v.checkData.firstName.$dirty ? !$v.checkData.firstName.$error : null"
            :validationsCommons="{
              requiredValue: !$v.checkData.firstName.required,
              requiredMessage: $t('checkData.firstName.validations.required'),
            }"
          />
        </b-col>
        <b-col cols="6">
          <input-text
            id="id-lastName"
            v-model="$v.checkData.lastName.$model"
            :label="$t('checkData.lastName.label')"
            :placeholder="$t('checkData.lastName.placeHolder')"
            :readonly="false"
            :required="true"
            :valid="$v.checkData.lastName.$dirty ? !$v.checkData.lastName.$error : null"
            :validationsCommons="{
              requiredValue: !$v.checkData.lastName.required,
              requiredMessage: $t('checkData.lastName.validations.required'),
            }"
          />
        </b-col>
        <b-col cols="12">
          <input-text
            id="id-country"
            v-model="$v.checkData.country.$model"
            :label="$t('checkData.country.label')"
            :placeholder="$t('checkData.country.placeHolder')"
            :readonly="false"
            :required="true"
            :valid="$v.checkData.country.$dirty ? !$v.checkData.country.$error : null"
            :validationsCommons="{
              requiredValue: !$v.checkData.country.required,
              requiredMessage: $t('checkData.country.validations.required'),
            }"
          />
        </b-col>
        <b-col cols="12">
          <input-text
            id="id-curp"
            v-model="$v.checkData.curp.$model"
            :label="$t('checkData.curp.label')"
            :placeholder="$t('checkData.curp.placeHolder')"
            :readonly="false"
            :required="true"
            :valid="$v.checkData.curp.$dirty ? !$v.checkData.curp.$error : null"
            :validationsCommons="{
              requiredValue: !$v.checkData.curp.required,
              requiredMessage: $t('checkData.curp.validations.required'),
              regexValue: !$v.checkData.curp.pattern,
              regexMessage: $t('checkData.curp.validations.regexMessage'),
            }"
          />
        </b-col>
        <b-col cols="12">
          <label v-text="$t('checkData.info')">La información que obtuvimos del RENAPO. ¿es correcta?</label>
        </b-col>
        <b-col cols="12">
          <div class="form-group float-right">
            <button type="button" class="button btn--primary-outline">
              <span v-text="$t('checkData.form.btn-no')">No</span>
            </button>
            <button type="submit" class="button btn--primary" data-cy="submit">
              <span v-text="$t('checkData.form.btn-si')">Si</span>
            </button>
          </div>
        </b-col>
      </b-row>
    </form>
  </div>
</template>
<script lang="ts" src="./check-your-data.component.ts"></script>
