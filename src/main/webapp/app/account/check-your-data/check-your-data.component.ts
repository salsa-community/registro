import Vue from 'vue';
import { Component } from 'vue-property-decorator';
import { helpers, required } from 'vuelidate/lib/validators';

const CURP = helpers.regex(
  'curp',
  /^[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$/
);

const VALIDATIONS = function () {
  return {
    checkData: {
      name: {
        required,
      },
      firstName: {
        required,
      },
      lastName: {
        required,
      },
      country: {
        required,
      },
      curp: {
        required,
        pattern: CURP,
      },
    },
  };
};

@Component({
  validations: VALIDATIONS,
})
export default class CheckDataForm extends Vue {
  public checkData: any = {
    name: undefined,
    firstName: undefined,
    lastName: undefined,
    country: undefined,
    curp: undefined,
    email: undefined,
  };

  public checkDataF() {
    if (!this.$v.checkData.$invalid) {
      this.$bvModal.msgBoxOk(this.$t('checkData.confirm.message').toString(), {
        title: this.$t('checkData.confirm.title').toString(),
        size: 'md',
        buttonSize: 'md',
        okVariant: 'primary',
        headerClass: 'p-2 border-bottom-0',
        footerClass: 'p-2 border-top-0',
        okTitle: this.$t('checkData.confirm.btn').toString(),
        centered: true,
      });
    } else {
      this.$v.checkData.$touch();
    }
  }
}
