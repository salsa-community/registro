import InputTextComponent from '@/components/input-text/input-text.vue';
import ValidationsCommonsComponent from '@/components/validations-commons/validations-commons.vue';

export function initBaseComponents(vue) {
  vue.component('input-text', InputTextComponent);
  vue.component('validations-commons', ValidationsCommonsComponent);
}
