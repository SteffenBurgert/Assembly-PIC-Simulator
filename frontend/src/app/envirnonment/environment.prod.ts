const backendUrl = 'https://prod.backend.assembly-pic-simulator.de/';
const frontendOrigin = 'https://assembly-pic-simulator.de/';
const gitBranch = undefined;

export const environment = {
  production: true,
  baseUrl: backendUrl,
  apiBaseUrl: backendUrl + 'api/',
  frontendOrigin: frontendOrigin,
  gitBranch: gitBranch,
};
