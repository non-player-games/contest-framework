import { Component, AfterViewInit, ElementRef } from '@angular/core';

declare var componentHandler: any;

@Component({
  selector: 'homepage',
  templateUrl: 'app/components/homepage/homepage.html'
})
export class HomepageComponent implements AfterViewInit {
  constructor(private element: ElementRef) { }

  ngAfterViewInit() {
    var self = this;
    componentHandler
      .upgradeElements(self.element.nativeElement.querySelectorAll('.mdl-button'));
  }
}
