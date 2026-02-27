import LoginPage from "./LoginPage.vue";
import HomePage from "./HomePage.vue";

export default class Routing
{
  static readonly routes = ['/', '/home'];
  static readonly pages = [LoginPage, HomePage];
}