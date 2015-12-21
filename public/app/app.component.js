System.register(['angular2/core'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent() {
                    var self = this;
                    self.messages = [];
                    // TODO: change this hard coded ip address later
                    this.ws = new WebSocket('ws://' + window.location.host + '/wsEcho');
                    this.ws.onopen = function () {
                        // send hello message
                        self.ws.send('Hello world -- connected message');
                    };
                    this.ws.onmessage = function (evt) {
                        var message = evt.data;
                        self.messages.push(evt.data);
                    };
                }
                AppComponent.prototype.onKeyUp = function ($event) {
                    this.ws.send(this.newMessage);
                    this.newMessage = '';
                };
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        template: "\n        <h1>Non-Player Games</h1>\n        <input type=\"text\" [(ngModel)]=\"newMessage\" (keyup.enter)=\"onKeyUp($event)\" />\n\n        <ul>\n            <li *ngFor=\"#message of messages\">{{message}}</li>\n        </ul>\n    "
                    }), 
                    __metadata('design:paramtypes', [])
                ], AppComponent);
                return AppComponent;
            })();
            exports_1("AppComponent", AppComponent);
        }
    }
});
//# sourceMappingURL=app.component.js.map