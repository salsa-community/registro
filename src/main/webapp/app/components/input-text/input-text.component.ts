import Component, { mixins } from 'vue-class-component';
import { Prop } from 'vue-property-decorator';
import PropertyCommons from '../commons/property.model';

@Component
export default class InputTextComponent extends mixins(PropertyCommons) {
  @Prop({ required: true })
  public value!: string;

  get text(): string {
    return this.value;
  }

  set text(newValue: string) {
    this.$emit('input', newValue);
  }

  cambio(e) {
    this.$emit('change', e);
  }
}
