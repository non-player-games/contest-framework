import { provideRouter, RouterConfig }  from '@angular/router';
import { HomepageComponent } from './components/homepage/homepage.component';

const routes: RouterConfig = [
  {
    path: '',
    component: HomepageComponent
  }
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(routes)
];
