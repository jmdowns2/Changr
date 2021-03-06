// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiBase: "http://127.0.0.1:8080",
  baselineBase: "http://127.0.0.1:8081",

  cognitoAppId: "5v6tdkij3da1arjmtbspi0tmri",
  cognitoLoginUrl: "https://testing1.auth.us-east-1.amazoncognito.com",
  gaMeasurementId: "UA-136803032-1"
};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
