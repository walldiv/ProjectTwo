/// <reference types="aws-sdk" />

//This function is for producing a popup window throughout the application
const popupCenter = ({ url, title, w, h }) => {
  // Fixes dual-screen position                             Most browsers      Firefox
  const dualScreenLeft = window.screenLeft !== undefined ? window.screenLeft : window.screenX;
  const dualScreenTop = window.screenTop !== undefined ? window.screenTop : window.screenY;

  const width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
  const height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

  const systemZoom = width / window.screen.availWidth;
  const left = (width - w) / 2 / systemZoom + dualScreenLeft;
  const top = (height - h) / 2 / systemZoom + dualScreenTop;
  const newWindow = window.open(
    url,
    title,
    `
  scrollbars=yes,
  width=${w / systemZoom}, 
  height=${h / systemZoom}, 
  top=${top}, 
  left=${left}
  `
  );
  if (window.focus) newWindow.focus();
};

//Closes popup windows - called from popup form buttons
function closeWindow() {
  window.open("", "_self").close();
}

// //AWS S3 SDK HELPER FUNCTS
// const albumBucketName = "thatteamproject2images";
// // Initialize the Amazon Cognito credentials provider
// function initAWS() {
//   AWS.config.region = "us-east-2"; // Region
//   AWS.config.credentials = new AWS.CognitoIdentityCredentials({
//     IdentityPoolId: "us-east-2:6e651cc5-c8ce-41f5-a60d-58696a4c6848",
//   });
// }

// // Create a new service object
// function newS3Object() {
//   var s3 = new AWS.S3({
//     apiVersion: "2006-03-01",
//     params: { Bucket: albumBucketName },
//   });
// }

// // A utility function to create HTML.
// function getHtml(template) {
//   return template.join("\n");
// }

function getCookie(name) {
  let matches = document.cookie.match(new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, "\\$1") + "=([^;]*)"));
  return matches ? decodeURIComponent(matches[1]) : undefined;
}
