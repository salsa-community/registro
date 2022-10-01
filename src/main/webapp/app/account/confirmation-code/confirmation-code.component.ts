import Vue from 'vue';
import { Component } from 'vue-property-decorator';
import { required } from 'vuelidate/lib/validators';

const VALIDATIONS = function () {
  return {
    confirmation: {
      code: {
        required,
      },
    },
  };
};

@Component({
  validations: VALIDATIONS,
})
export default class ConfirmationCodeForm extends Vue {
  public confirmation: any = {
    code: undefined,
  };

  public confirmationCode() {
    if (!this.$v.confirmation.$invalid) {
      //code
    } else {
      this.$v.confirmation.$touch();
    }
  }
}
