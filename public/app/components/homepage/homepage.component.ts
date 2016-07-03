import { Component, AfterViewInit, ElementRef } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

declare var componentHandler: any;

@Component({
  selector: 'homepage',
  templateUrl: 'app/components/homepage/homepage.html',
  directives: [ROUTER_DIRECTIVES]
})
export class HomepageComponent implements AfterViewInit {
  constructor(private element: ElementRef) { }

  ngAfterViewInit() {
    var self = this;
    componentHandler
      .upgradeElements(self.element.nativeElement.querySelectorAll('.mdl-button'));
  }
}
