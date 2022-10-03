import axios from 'axios';
import Component from 'vue-class-component';
import { Inject } from 'vue-property-decorator';
import AccountService from '@/account/account.service';
import JhiNavbar from '@/core/jhi-navbar/jhi-navbar.vue';
import Home from '@/core/home/home.vue';
@Component({
  components: {
    home: Home,
  },
  watch: {
    $route() {
      (this.$root as any).$emit('bv::hide::modal', 'login-page');
    },
  },
})
export default class LoginForm extends JhiNavbar {
  @Inject('accountService')
  private accountService: () => AccountService;
  public authenticationError = null;
  public login = null;
  public password = null;
  public rememberMe: boolean = null;

  public doLogin(): void {
    const data = { username: this.login, password: this.password, rememberMe: this.rememberMe };
    axios
      .post('api/authenticate', data)
      .then(result => {
        const bearerToken = result.headers.authorization;
        if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
          const jwt = bearerToken.slice(7, bearerToken.length);
          if (this.rememberMe) {
            localStorage.setItem('jhi-authenticationToken', jwt);
            sessionStorage.removeItem('jhi-authenticationToken');
          } else {
            sessionStorage.setItem('jhi-authenticationToken', jwt);
            localStorage.removeItem('jhi-authenticationToken');
          }
        }
        this.authenticationError = false;
        this.accountService().retrieveAccount();
      })
      .catch(() => {
        this.authenticationError = true;
      });
  }
}
