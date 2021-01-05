var exec = require('cordova/exec');

exports.sdkInit = function (arg0, success, error) {
    exec(success, error, 'Medallia', 'sdkInit', [arg0]);
};
exports.showForm = function (arg0, success, error) {
    exec(success, error, 'Medallia', 'showForm', [arg0]);
};
exports.updateCustomLocale = function (arg0, success, error) {
    exec(success, error, 'Medallia', 'updateCustomLocale', [arg0]);
};
exports.setCustomParameters = function (arg0, success, error) {
    exec(success, error, 'Medallia', 'setCustomParameters', [arg0]);
};
exports.revertStopSDK = function (arg0, success, error) {
    exec(success, error, 'Medallia', 'revertStopSDK', [arg0]);
};