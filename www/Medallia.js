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