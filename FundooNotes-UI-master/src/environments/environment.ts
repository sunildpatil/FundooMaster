// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  user_url: "http://localhost:8080/user/",
  note_url: "http://localhost:8080/note/",
  login_url: "http://localhost:8080/user/login",
  wrong_password: "Wrong password. Try again or click Forgot password to reset it.",
  wrong_email: "Couldn't find your Fundoo Account",
  account_not_activated: "Please Activate Your Account First",
  new_password_url: "http://localhost:8080/user/newpassword/",
  empty_field: "Field cannot be empty",
  matchError: "Those passwords didn't match. Try again.",
  link_expired: "Link has been expired, please request for a new link.",
  password_change_problem: "Some problem in changing your password, please try again after sometime.",
  registration_link: "http://localhost:8080/user/registration",
  email_already_registered: "Email already registered."

};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
