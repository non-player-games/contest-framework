import {Component} from 'angular2/core';

@Component({
    selector: 'my-app',
    template: `
        <h1>Non-Player Games</h1>
        <input type="text" [(ngModel)]="newMessage" (keyup.enter)="onKeyUp($event)" />

        <ul>
            <li *ngFor="#message of messages">{{message}}</li>
        </ul>
    `
})
export class AppComponent {
    messages: String[]
    ws: WebSocket
    newMessage: String

    constructor() {
        var self: any = this;

        self.messages = [];
        // TODO: change this hard coded ip address later
        this.ws = new WebSocket('ws://' + window.location.host + '/wsEcho');

        this.ws.onopen = function() {
            // send hello message
            self.ws.send('Hello world -- connected message');
        }

        this.ws.onmessage = function(evt) {
            var message = evt.data;

            self.messages.push(evt.data);
        }
    }

    onKeyUp($event: KeyboardEvent) {
        this.ws.send(this.newMessage);
        this.newMessage = '';
    }
}
