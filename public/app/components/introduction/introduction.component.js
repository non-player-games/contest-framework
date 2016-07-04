"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var user_service_1 = require('../../services/user.service');
var IntroductionComponent = (function () {
    function IntroductionComponent(element, userService) {
        this.element = element;
        this.userService = userService;
        this.user = {
            Name: '',
            Email: ''
        };
    }
    IntroductionComponent.prototype.ngAfterViewInit = function () {
        var self = this;
        componentHandler
            .upgradeElements(self.element.nativeElement.querySelectorAll('.mdl-textfield'));
    };
    IntroductionComponent.prototype.signup = function () {
        this.userService.signup(this.user)
            .subscribe(function (res) { return console.log(res); }, function (err) { return console.error(err); });
    };
    IntroductionComponent = __decorate([
        core_1.Component({
            selector: 'introduction',
            templateUrl: 'app/components/introduction/introduction.html',
            providers: [user_service_1.UserService],
            directives: [router_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [core_1.ElementRef, user_service_1.UserService])
    ], IntroductionComponent);
    return IntroductionComponent;
}());
exports.IntroductionComponent = IntroductionComponent;
//# sourceMappingURL=introduction.component.js.map