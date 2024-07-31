const backendUrl = 'http://localhost:8080/';
const frontendOrigin = 'http://localhost:4200/';
const gitBranch = undefined;

export const environment = {
  production: false,
  baseUrl: backendUrl,
  apiBaseUrl: backendUrl + 'api/',
  frontendOrigin: frontendOrigin,
  gitBranch: gitBranch,
};
