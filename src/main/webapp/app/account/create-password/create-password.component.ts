import { maxLength, minLength, required, sameAs, helpers } from 'vuelidate/lib/validators';
import Component from 'vue-class-component';
import { Vue } from 'vue-property-decorator';

const pass = helpers.regex(
  'pass',
  /^(?=.*\d{1})(?=.*[a-z]{1})(?=.*[A-Z]{1})(?=.*[!@#$%^&*{|}?~_=+.-]{1})(?=.*[a-zA-Z0-9@$!%*?&{|}~_=+.-])(?!.*\s).{6,20}$/
);

const validations = {
  createPassword: {
    password: {
      required,
      minLength: minLength(6),
      maxLength: maxLength(20),
      pass,
    },
    confirmPassword: {
      // prettier-ignore
      required,
      sameAsPassword: sameAs(vm => {
        return vm.password;
      }),
    },
  },
};

@Component({
  validations,
})
export default class CreatePassword extends Vue {
  createPassword: any = {
    password: null,
    confirmPassword: null,
  };

  public passwordStrength;

  mounted() {
    this.passwordStrength = document.getElementById('password-strength');
  }

  public createPasswordForm() {
    if (!this.$v.createPassword.$invalid) {
      this.$bvModal
        .msgBoxConfirm(this.$t('createPassword.confirm.message').toString(), {
          title: this.$t('createPassword.confirm.title').toString(),
          size: 'md',
          buttonSize: 'md',
          okVariant: 'primary',
          cancelVariant: 'primary-outline',
          headerClass: 'p-2 border-bottom-0',
          footerClass: 'p-2 border-top-0',
          okTitle: this.$t('createPassword.form.btn-ok').toString(),
          cancelTitle: this.$t('createPassword.form.btn-cancel').toString(),
          centered: true,
        })
        .then(value => {
          if (value) {
            this.$bvModal
              .msgBoxOk(this.$t('createPassword.created.message').toString(), {
                title: this.$t('createPassword.created.title').toString(),
                size: 'md',
                buttonSize: 'md',
                okVariant: 'primary',
                okTitle: this.$t('createPassword.form.btn-ok').toString(),
                headerClass: 'p-2 border-bottom-0',
                footerClass: 'p-2 border-top-0',
                centered: true,
              })
              .then(value => {
                console.log('Cuenta creada con exito');
              })
              .catch(err => {
                // An error occurred
              });
          }
        })
        .catch(err => {
          // An error occurred
        });
    } else {
      this.$v.createPassword.$touch();
    }
  }

  public checkStrength(password) {
    let strength = 0;

    //If password contains both lower and uppercase characters
    if (password.match(/([A-Za-z])/)) {
      strength += 1;
    }
    //If it has one special character
    if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) {
      strength += 1;
    }
    //If password is greater than 7
    if (password.length > 5) {
      strength += 1;
    }

    // If value is less than 2
    if (strength == 0) {
      this.passwordStrength.classList.remove('bg-warning');
      this.passwordStrength.classList.remove('bg-success');
      this.passwordStrength.classList.add('bg-danger');
      this.passwordStrength.style = 'width: 0%';
    } else if (strength == 1) {
      this.passwordStrength.classList.remove('bg-warning');
      this.passwordStrength.classList.remove('bg-success');
      this.passwordStrength.classList.add('bg-danger');
      this.passwordStrength.style = 'width: 10%';
    } else if (strength == 2) {
      this.passwordStrength.classList.remove('bg-success');
      this.passwordStrength.classList.remove('bg-danger');
      this.passwordStrength.classList.add('bg-warning');
      this.passwordStrength.style = 'width: 60%';
    } else if (strength == 3) {
      this.passwordStrength.classList.remove('bg-warning');
      this.passwordStrength.classList.remove('bg-danger');
      this.passwordStrength.classList.add('bg-success');
      this.passwordStrength.style = 'width: 100%';
    }
  }
}
