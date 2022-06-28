import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  constructor(private toastr: ToastrService, private authService: AuthService, private tokenStorage: TokenStorageService, public router: Router) {
   }
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }
  onSubmit(): void {
    const { username, password } = this.form;
    this.authService.login(username, password).subscribe({
      next: (data: any) => {
        console.log(data);
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data.username);
        this.tokenStorage.saveUserRole(data.role);
        console.log(data)
        this.isLoggedIn = true;
        this.isLoginFailed = false;
        this.router.navigate(['/home']);
      },
    error: (err: any) => {
      console.log(err.error)
      this.errorMessage = "Bad creditentials!";  //TODO: ulepsati jer je gadno hehe
      this.isLoginFailed = true;
    }
  });
  }

}