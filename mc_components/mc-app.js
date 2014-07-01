'use strict';

angular
  .module('mc.sentik', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'mcDirective'
  ])

//  .directive('mcBackToTop', function () {
//    return{
//      restrict: 'EA',
//      templateUrl: 'mc_components/views/back-to-top.html',
//      link: function (scope, element, attr) {
//        /** BEGIN BACK TO TOP **/
//        element.find("#back-top").hide();
//
//        $(window).scroll(function () {
//          if ($(this).scrollTop() > 100) {
//            element.find('#back-top').fadeIn();
//          } else {
//            element.find('#back-top').fadeOut();
//          }
//        });
//        element.find('#back-top a').click(function () {
//          angular.element('body,html').animate({
//            scrollTop: 0
//          }, 800);
//          return false;
//        });
//
//        /** END BACK TO TOP **/
//      }
//    }
//  })
;
