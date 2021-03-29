import { Route } from '@vaadin/router';
import './views/helloworld/hello-world-view';
import './views/main/main-view';
import './login-view';
import { isLoggedIn, logout } from "Frontend/auth";

export type ViewRoute = Route & { title?: string; children?: ViewRoute[] };

export const views: ViewRoute[] = [
  // for client-side, place routes below (more info https://vaadin.com/docs/v19/flow/typescript/creating-routes.html)
  {
    path: '',
    component: 'hello-world-view',
    title: '',
  },
  {
    path: 'hello',
    component: 'hello-world-view',
    title: 'Hello World',
  },
  {
    path: 'about',
    component: 'about-view',
    title: 'About',
    action: async () => {
      await import('./views/about/about-view');
    },
  },
];
export const routes: ViewRoute[] = [
  {
    path: '/login',
    component: 'login-view'
  },
  {
    path: '/logout',
    action: async (_: any, commands: any) => {
      // use the logout helper method.
      await logout();
      return commands.redirect('/');
    }
  },
  {
    path: '',
    component: 'main-view',
    action: (_: any, commands: any) => {
      if (!isLoggedIn()) {
        return commands.redirect('/login');
      }
      return undefined;
    },
    children: [...views],
  },
];
