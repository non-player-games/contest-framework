import { provideRouter, RouterConfig }  from '@angular/router';
import { HomepageComponent } from './components/homepage/homepage.component';
import { IntroductionComponent } from './components/introduction/introduction.component';

const routes: RouterConfig = [
  {
    path: '',
    component: HomepageComponent
  },
  {
    path: 'introduction',
    component: IntroductionComponent
  }
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(routes)
];
