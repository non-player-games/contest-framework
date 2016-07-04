import { Component, AfterViewInit, ElementRef } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

declare var componentHandler: any;

@Component({
  selector: 'introduction',
  templateUrl: 'app/components/introduction/introduction.html',
  providers: [UserService],
  directives: [ROUTER_DIRECTIVES]
})
export class IntroductionComponent implements AfterViewInit {
  user: User;

  constructor(private element: ElementRef, private userService: UserService) {
    this.user = {
      Name: '',
      Email: ''
    };
  }

  ngAfterViewInit() {
    var self = this;
    componentHandler
      .upgradeElements(self.element.nativeElement.querySelectorAll('.mdl-textfield'));
  }

  signup() {
    this.userService.signup(this.user)
      .subscribe(
        res => console.log(res),
        err => console.error(err)
      );
  }
}
