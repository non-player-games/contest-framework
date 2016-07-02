import { Component, AfterViewInit, ElementRef } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

declare var componentHandler: any;

@Component({
  selector: 'ai-contest-app',
  templateUrl: 'app/index.html',
  directives: [ROUTER_DIRECTIVES]
})
export class AppComponent implements AfterViewInit {
  constructor(private element: ElementRef) { }

  ngAfterViewInit() {
    var self = this;
    componentHandler
      .upgradeElements(self.element.nativeElement.querySelectorAll('.mdl-layout'));
  }
}
