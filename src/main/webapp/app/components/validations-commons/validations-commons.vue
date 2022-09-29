<template>
  <div>
    <div v-if="requiredValue">
      {{ requiredMessage }}
    </div>
    <div v-if="minValue">
      {{ minMessage }}
    </div>
    <div v-if="maxValue">
      {{ maxMessage }}
    </div>
    <div v-if="regexValue">
      {{ regexMessage }}
    </div>
  </div>
</template>

<script lang="ts">
import Component from 'vue-class-component';
import { Prop, Vue } from 'vue-property-decorator';

@Component
export default class ValidationsCommons extends Vue {
  @Prop({ default: null })
  requiredValue: boolean;

  @Prop({ default: '' })
  requiredMessage: string;

  @Prop({ default: null })
  minValue: boolean;

  @Prop({ default: '' })
  minMessage: string;

  @Prop({ default: null })
  maxValue: boolean;

  @Prop({ default: '' })
  maxMessage: string;

  @Prop({ default: null })
  regexValue: boolean;

  @Prop({ default: '' })
  regexMessage: string;
}
</script>
