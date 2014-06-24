function RevMob(appId) {
	this.appId = appId;
	cordova.exec(function(successParams){}, function(errorParams){}, "RevMobPlugin", "startSession", [ appId ]);

	this.showFullscreen = function(successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "RevMobPlugin", "showFullscreen", []);
	}

	this.showPopup = function(successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "RevMobPlugin", "showPopup", []);
	}

	this.setTestingMode = function(isTesting, successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "RevMobPlugin", "setTestingMode", [isTesting]);
	}

	this.setTestingWithoutAds = function(isTesting, successCallback, errorCallback) {
		cordova.exec(successCallback, errorCallback, "RevMobPlugin", "setTestingWithoutAds", [isTesting]);
	}
}