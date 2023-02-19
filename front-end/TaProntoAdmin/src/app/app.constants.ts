export const ACCESS_TOKEN = "ACCESS_TOKEN";
export const REFRESH_TOKEN = "REFRESH_TOKEN";

export const BASE_API = 'http://localhost:8080';
export const FIRST_VERSION_API = '/api/v1/';
export const AUTH_API = BASE_API + FIRST_VERSION_API + 'auth';
export const USER_API = BASE_API + FIRST_VERSION_API + 'users';
export const BUDGET_API = BASE_API + FIRST_VERSION_API + 'budgets';
export const PROFILES = [
  {key: "ROLE_ADMIN", name: "Admin", cod: 0},
  {key: "ROLE_ATTENDANT", name: "Attendant", cod: 1},
  {key: "ROLE_COSTUMER", name: "Costumer", cod: 2}
];
