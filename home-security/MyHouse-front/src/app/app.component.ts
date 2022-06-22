import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;
  title: any;
  constructor(private tokenStorageService: TokenStorageService, public router: Router) { }
  ngOnInit(): void {
    if (this.isLoggedIn()) {
      const user = this.tokenStorageService.getUser();
      // this.roles = user.roles;
      // this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      // this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
      this.username = user.username;
    }
  }

  isLoggedIn() : boolean {
    return !!this.tokenStorageService.getToken();
  }

  logout(): void {
    this.tokenStorageService.signOut();
    // window.location.reload();
    this.router.navigate(['/login']);
  }
}