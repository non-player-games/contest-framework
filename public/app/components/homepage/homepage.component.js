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
var HomepageComponent = (function () {
    function HomepageComponent(element) {
        this.element = element;
    }
    HomepageComponent.prototype.ngAfterViewInit = function () {
        var self = this;
        componentHandler
            .upgradeElements(self.element.nativeElement.querySelectorAll('.mdl-button'));
    };
    HomepageComponent = __decorate([
        core_1.Component({
            selector: 'homepage',
            templateUrl: 'app/components/homepage/homepage.html'
        }), 
        __metadata('design:paramtypes', [core_1.ElementRef])
    ], HomepageComponent);
    return HomepageComponent;
}());
exports.HomepageComponent = HomepageComponent;
//# sourceMappingURL=homepage.component.js.map