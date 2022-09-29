import Vue from 'vue';
import { Component, Inject } from 'vue-property-decorator';
import { email, helpers, maxLength, minLength, required, sameAs } from 'vuelidate/lib/validators';
import LoginService from '@/account/login.service';
import RegisterService from '@/account/register/register.service';
import { EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE } from '@/constants';

const CURP = helpers.regex(
  'curp',
  /^[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$/
);
const loginPattern = helpers.regex('alpha', /^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$/);
const VALIDATIONS = function () {
  return {
    registerAccount: {
      curp: {
        required,
        pattern: CURP,
      },
      email: {
        required,
        minLength: minLength(5),
        email,
      },
      confirmEmail: {
        required,
        // prettier-ignore
        sameAsEmail : sameAs( function(){return this.registerAccount.email} ),
      },
    },
  };
};

@Component({
  validations: VALIDATIONS,
})
export default class Register extends Vue {
  @Inject('registerService') private registerService: () => RegisterService;
  @Inject('loginService') private loginService: () => LoginService;
  public registerAccount: any = {
    curp: undefined,
    email: undefined,
    confirmEmail: undefined,
  };
  public confirmPassword: any = null;
  public error = '';
  public errorEmailExists = '';
  public errorUserExists = '';
  public success = false;

  public register(): void {
    if (!this.$v.registerAccount.$invalid) {
      this.error = null;
      this.errorUserExists = null;
      this.errorEmailExists = null;
      this.registerAccount.langKey = this.$store.getters.currentLanguage;
      this.registerService()
        .processRegistration(this.registerAccount)
        .then(() => {
          this.success = true;
        })
        .catch(error => {
          this.success = null;
          if (error.response.status === 400 && error.response.data.type === LOGIN_ALREADY_USED_TYPE) {
            this.errorUserExists = 'ERROR';
          } else if (error.response.status === 400 && error.response.data.type === EMAIL_ALREADY_USED_TYPE) {
            this.errorEmailExists = 'ERROR';
          } else {
            this.error = 'ERROR';
          }
        });
    } else {
      this.$v.registerAccount.$touch();
    }
  }

  public openLogin(): void {
    this.loginService().openLogin((<any>this).$root);
  }
}
