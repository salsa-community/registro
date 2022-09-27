<template>
  <div class="form-group">
    <label :for="id">
      {{ label }} <span v-if="required">*</span>
      <span v-if="tooltip">
        <b-icon :id="id + 'tooltip'" icon="question-circle"></b-icon>
        <b-tooltip :target="id + 'tooltip'" :title="tooltip" placement="right"></b-tooltip>
      </span>
    </label>
    <b-form-input
      :placeholder="placeholder"
      :id="id"
      v-model="text"
      :state="valid"
      trim
      :readonly="readonly"
      @change="cambio($event)"
    ></b-form-input>
    <b-form-invalid-feedback>
      <validations-commons
        :requiredValue="validationsCommons.requiredValue"
        :requiredMessage="validationsCommons.requiredMessage"
        :minValue="validationsCommons.minValue"
        :minMessage="validationsCommons.minMessage"
        :maxValue="validationsCommons.maxValue"
        :maxMessage="validationsCommons.maxMessage"
        :regexMessage="validationsCommons.regexMessage"
        :regexValue="validationsCommons.regexValue"
      ></validations-commons>
    </b-form-invalid-feedback>
    <small v-if="description" class="form-text text-muted">{{ description }}</small>
  </div>
</template>

<script lang="ts" src="./input-text.component.ts"></script>
