import { Component, AfterViewInit, ElementRef } from '@angular/core';

declare var componentHandler: any;

@Component({
  selector: 'ai-contest-app',
  template: `
  <!-- Uses a header that scrolls with the text, rather than staying
  locked at the top -->
  <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header">
      <div class="mdl-layout__header-row">
        <!-- Title -->
        <span class="mdl-layout-title">AI Challenge</span>
        <!-- Add spacer, to align navigation to the right -->
        <div class="mdl-layout-spacer"></div>
        <!-- Navigation -->
        <nav class="mdl-navigation">
          <a class="mdl-navigation__link" href="">Get Started</a>
          <a class="mdl-navigation__link" href="">Join Contest</a>
          <a class="mdl-navigation__link" href="">Docs</a>
        </nav>
      </div>
    </header>
    <div class="mdl-layout__drawer">
      <span class="mdl-layout-title">AI Challenge</span>
      <nav class="mdl-navigation">
        <a class="mdl-navigation__link" href="">Get Started</a>
        <a class="mdl-navigation__link" href="">Join Contest</a>
        <a class="mdl-navigation__link" href="">Docs</a>
      </nav>
    </div>
    <main class="mdl-layout__content">
      <div class="page-content">
        <div class="mdl-grid">
          <div class="mdl-cell mdl-cell--2-offset-tablet mdl-cell--4-offset-desktop mdl-cell--4-col mdl-card mdl-shadow--2dp">
            <div class="mdl-card__title">
              <h2 class="mdl-card__title-text">Welcome</h2>
            </div>
            <div class="mdl-card__supporting-text">
              Welcome to the AI Challenge, our purpose is to provide a framework to support
              AI game environment creator and AI game bot developers to play!
            </div>
            <div class="mdl-card__actions mdl-card--border">
              <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                Get Started
              </a>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>`
})
export class AppComponent implements AfterViewInit {
  constructor(private element: ElementRef) { }

  ngAfterViewInit() {
    var self = this;
    componentHandler
      .upgradeElements(self.element.nativeElement.querySelectorAll('.mdl-layout'));
  }
}
