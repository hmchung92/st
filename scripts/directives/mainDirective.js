'user strict';
(function () {
  angular.module('mainDirective', ['easypiechart'])
    .directive('topNavbar', function () {
      return{
        restrict: 'E',
        controller: 'CollapseDemoCtrl',
        templateUrl: 'views/top-navbar.html'
      };
    })
    .directive('topNavbarLogo', function () {
      return{
        restrict: 'E',
        templateUrl: 'views/top-navbar-logo.html',
        scope: {
        },
        link: function (scope, element, atts) {
          var logo = atts.stLogo;
          scope.logo = 'images/' + logo;
        }
      };
    })
    .directive('topNavbarNotify', function () {
      return{
        restrict: 'E',
        templateUrl: 'views/top-navbar-notify.html'
      };
    })
    .directive('topNavbarNotifyItem', function () {
      return{
        restrict: 'EA',
        scope: {
          title: '@stTitle',
          data: '=stData',
          icon: '@stIcon',
          badge: '@stBadge'
        },
        templateUrl: 'views/top-navbar-notify-item.html',
        link: function (scope, element, attr) {
        }
      };
    })
    .directive('topNavbarNotifyContent', function () {
      return{
        restrict: 'A',
        templateUrl: 'views/top-navbar-notify-content.html',
        scope: {
          data: '=stData',
          title: '@stTitle'
        },
        link: function (scope, element, attrs) {

          scope.userStatus = false;
          var status = scope.title;
          if (angular.equals(status, 'Friend requests')) {
            scope.userStatus = true;
          }

        }
      };
    })
    .directive('topNavbarUser', function () {
      return{
        restrict: 'E',
        templateUrl: 'views/top-navbar-user.html',
        scope: {
          userName: '@stUsername',
          userImage: '@stUserimage'
        },
        link: function (scope, element, attrs) {
          scope.avatar = 'images/avatar/' + scope.userImage;
        }
      };
    })
    .directive('pageContent', function () {
      return{
        restrict: 'E',
        templateUrl: 'views/page-content.html'
      };
    })
    .directive('topSidebarRightHeading', function () {
      return{
        restrict: 'AE',
        templateUrl: 'views/top-sidebar-right-heading.html'
      };
    })
    .directive('mainPageHeading', function () {
      return{
        restrict: 'E',
        scope: {
          title: '@stTitle',
          content: '@stContent'
        },
        templateUrl: 'views/main-page-heading.html'
      };
    })
    .directive('mainSiteInfomation', function () {
      return{
        restrict: 'E',
        scope: {
          infomations: '=stData',
          infomationsLast: '=stDataLast'
        },
        controller: function ($scope) {

          this.todayData = function () {
            return $scope.infomations;
          };
          this.todayLast = function () {
            return $scope.infomationsLast;
          };
        },
        templateUrl: 'views/main-site-infomation.html',
        link: function (scope, element, attrs) {
        }
      };
    })
    .directive('subInfomation', function ($window) {
      return{
        restrict: 'A',
        require: '^mainSiteInfomation',
        scope: {
          item: '=stData',
          image: '@stImage'
        },
        templateUrl: 'views/sub-infomation.html',
        link: function (scope, element, attrs, ctrl) {
          var betterStr = 'Better than yesterday',
            lessStr = 'Less than yesterday',
            siteInfomationLast = ctrl.todayLast(),
            today = scope.item.number,
            bgProgress = element.find('.progress-bar'),
            yesterday = siteInfomationLast[attrs.stIndex].number,
            number = '',
            percent = $window.Math.round(((today - yesterday) / yesterday) * 100);

          scope.number = today;

          if (today > yesterday && percent > 0) {
            scope.context = 'Better than yesterday ( ' + percent + '% )';
            if (percent < 6 && percent > 0) {
              element.addClass('bg-warning');
              bgProgress.addClass('progress-bar-warning');
            }
            else if (percent < 11 && percent > 5) {
              element.addClass('bg-success');
              bgProgress.addClass('progress-bar-success');
            }
            else {
              element.addClass('bg-primary');
              bgProgress.addClass('progress-bar-primary');
            }
          }
          else if (today >= yesterday) {
            scope.context = betterStr + ' ( ' + percent + '% )';
          } else {
            scope.context = lessStr + ' ( ' + percent + '% )';
            element.addClass('bg-danger');
            bgProgress.addClass('progress-bar-danger');
          }
        }
      };
    })
    .directive('sidebarLeft', function () {
      return{
        restrict: 'E',
        scope: {
          menus: '=stMenus',
          settings: '=stSettings'
        },
        controller: 'NiceScroll',
        templateUrl: 'views/sidebar-left.html',
        link: function(scope, element, attr){
          /** SIDEBAR FUNCTION **/

          /** END SIDEBAR FUNCTION **/
        }
      };
    })
    .directive('sidebarLeftSettings', function () {
      return{
        restrict: 'AE',
        scope: {
          data: '=stData'
        },
        templateUrl: 'views/sidebar-left-settings.html'
      };
    })
    .directive('sidebarRight', function () {
      return{
        restrict: 'E',
        templateUrl: 'views/sidebar-right.html',
        controller: 'NiceScroll',
        link: function(scope, element, attr){
        }
      };
    })
    .directive('sidebarRightItem', function () {
      return{
        restrict: 'EA',
        scope: {
          title: '@stTitle',
          data: '=stData'
        },
        templateUrl: 'views/sidebar-right-item.html',
        link: function (scope, element, attr) {
          scope.stFilter = attr.stFilter;
          scope.userStatus = false;
          scope.taskStatus = false;
          scope.notificationStatus = false;
          scope.settingStatus = false;
          var status = attr.stStatus;
          if (angular.equals(status, 'user')) {
            scope.userStatus = true;
          }
          else if (angular.equals(status, 'notification')) {
            scope.notificationStatus = true;
          }
          else if (angular.equals(status, 'task')) {
            scope.taskStatus = true;
          }
          else if (angular.equals(status, 'setting')) {
            scope.settingStatus = true;
          }
        }
      };
    })
    .directive('pageFooter', function () {
      return{
        restrict: 'E',
        scope: {
          author: '@stAuthor',
          design: '@stDesign'
        },
        templateUrl: 'views/page-footer.html'
      };
    })
    .directive('mainAlert', function () {
      return{
        restrict: 'E',
        scope: {
          alertList: '=stData'
        },
        templateUrl: 'views/main-alert.html'
      };
    })
    .directive('mainWidget', function () {
      return{
        restrict: 'E',
        templateUrl: 'views/main-widget.html'
      };
    })
    .directive('mainChartWidget', function () {
      return{
        restrict: 'E',
        scope: {
          data: '=stData'
        },
        templateUrl: 'views/main-chart-widget.html',
        link: function(scope, element, attr){
          /** BEGIN WIDGET MORRIS JS FUNCTION **/
          if (element.find('#morris-widget-1').length > 0){
            Morris.Line({
              element: 'morris-widget-1',
              data: scope.data.lastyear.data,
              xkey: scope.data.lastyear.xkey,
              ykeys: [scope.data.lastyear.ykeys],
              labels: [scope.data.lastyear.labels],
              resize: scope.data.lastyear.resize,
              lineColors: [scope.data.lastyear.lineColors],
              pointFillColors :[scope.data.lastyear.pointFillColors],
              pointStrokeColors : [scope.data.lastyear.pointStrokeColors],
              gridTextColor: [scope.data.lastyear.gridTextColor],
              pointSize: scope.data.lastyear.pointSize,
              grid: false
            });
          }

          if (element.find('#morris-widget-2').length > 0){
            //MORRIS
            Morris.Bar({
              element: 'morris-widget-2',
              data: scope.data.earnings.data,
              xkey: scope.data.earnings.xkey,
              ykeys: [scope.data.earnings.ykeys],
              labels: [scope.data.earnings.labels],
              resize: scope.data.earnings.resize,
              barColors: [scope.data.earnings.barColors],
              gridTextColor: [scope.data.earnings.gridTextColor],
              gridTextSize: scope.data.earnings.gridTextSize,
              grid :scope.data.earnings.grid,
              axes: scope.data.earnings.axes
            });
          }
          /** END WIDGET MORRIS JS FUNCTION **/


        }
      };
    })
    .directive('propertyCard', function () {
      return{
        restrict: 'E',
        scope: {
          list: '=stList',
          title: '@stTitle'
        },
        templateUrl: 'views/property-card.html',
        link: function (scope, element, attrs) {


        }
      };
    })
    .directive('owlCarouselItem', function () {
      return{
        restrict: 'AE',
        scope: {
          list: '=stData'
        },
        templateUrl: 'views/owl-carousel-item.html',
        link: function (scope, element, attrs) {


          element.find('#property-slide-8').owlCarousel({
            navigation: false, // Show next and pre buttons
            sideSpeed: 300,
            paginationSpeed: 400,
            singleItem: true,
            pagination: false,
            responsive: true
          });

        }
      };
    })
    .directive('taskList', function () {
      return{
        restrict: 'E',
        scope: {
          list: '=stList',
          title: '@stTitle'
        },
        templateUrl: 'views/task-list.html'
      };
    })
    .directive('weatherWidget', function () {
      return{
        restrict: 'E',
        scope: {
          today: '=stToday',
          tomorow: '=stTomorow'
        },
        controller: '',
        templateUrl: 'views/weather-widget.html'
      };
    })
    .directive('weatherWidgetItem', function () {
      return{
        restrict: 'E',
        templateUrl: 'views/weather-widget-item.html',
        link: function (scope, element, attr) {
          var skycon = element.find('.skycon'),
            index = attr.stIndex,
            list = [
              'clear-night',
              'snow',
              'fog'
            ];
          scope.skycon = list[index];

        }
      }
    })
    .directive('mainSocicalTitles', function () {
      return{
        restrict: 'E',
        scope: {
          data: '=stData'
        },
        templateUrl: 'views/main-socical-titles.html',
        link: function (scope, element, attrs) {
        }
      };
    })
    .directive('socicalItem', function () {
      return{
        restrict: 'E',
        scope: {
          data: '=stData',
          style: '@stStyle'
        },
        templateUrl: 'views/socical-item.html',
        link: function (scope, element, attrs) {
          //alert(attrs.socicalFollowers);
        }
      };
    })
    .directive('friendRequests', function () {
      return{
        restrict: 'E',
        scope: {
          title: '@stTitle',
          list: '=stList'
        },
        templateUrl: 'views/friend-requests.html'
      };
    })
    .directive('friendRequestsItem', function () {
      return{
        restrict: 'E',
        scope: {
          data: '=stData'
        },
        controller: 'SkyconsCtrl',
        templateUrl: 'views/friend-requests-item.html',
        link: function (scope, element, attrs) {
          //Css for first row
          var friendRequest = element.find('#st-friend-requests');

          if (attrs.stIndex == 0) {
            friendRequest.addClass('bg-success');
          }
          // Avatar
          scope.data.avatar = 'images/avatar/' + scope.data.avatar;
        }
      };
    })
    .directive('mainServerStatus', function () {
      return{
        restrict: 'E',
        templateUrl: 'views/main-server-status.html'
      };
    })
    .directive('serverStatusWidget', function(){
      return{
        restrict: 'E',
        scope: {
          title: '@stTitle',
          systemStatus: '=stSystemstatus',
          chart: '=stChart'
        },
        templateUrl: 'views/server-status-widget.html',
        link: function(scope, element, attr){
          /** BEGIN WIDGET PIE FUNCTION **/
          if ($('#realtime-chart-widget').length > 0){
            var data1 = [];
            var totalPoints = 250;
            function GetData() {
              "use strict";
              data1.shift();
              while (data1.length < totalPoints) {
                var prev = data1.length > 0 ? data1[data1.length - 1] : 50;
                var y = prev + Math.random() * 10 - 5;
                y = y < 0 ? 0 : (y > 100 ? 100 : y);
                data1.push(y);
              }
              var result = [];
              for (var i = 0; i < data1.length; ++i) {
                result.push([i, data1[i]])
              }
              return result;
            }
            var updateInterval = 500;
            var plot = $.plot($("#realtime-chart-widget #realtime-chart-container-widget"), [
              GetData()], {
              series: {
                lines: {
                  show: true,
                  fill: false
                },
                shadowSize: 0
              },
              yaxis: {
                min: 0,
                max: 100,
                ticks: 10,
                show: false
              },
              xaxis: {
                show: false
              },
              grid: {
                hoverable: true,
                clickable: true,
                tickColor: "#f9f9f9",
                borderWidth: 0,
                borderColor: "#eeeeee"
              },
              colors: ["#699B29"],
              tooltip: false,
              tooltipOpts: {
                defaultTheme: false
              }
            });
            function update() {
              "use strict";
              plot.setData([GetData()]);
              plot.draw();
              setTimeout(update, updateInterval);
            }
            update();
          }
          /** END WIDGET PIE FUNCTION **/
        }
      };
    })
    .directive('chartWidget', function(){
      return{
        restrict: 'E',
        controller: function($scope, $element){
//          $scope.percent = 65;
          $scope.anotherPercent = $scope.data.percent;
          $scope.anotherOptions = {
            easing: 'easeOutBounce',
            barColor : '#3BAFDA',
            lineWidth: 10
          };
          if($scope.data.percent > 50){
            $scope.anotherOptions = {
              easing: 'easeOutBounce',
              barColor : '#F6BA48',
              lineWidth: 10
            };
          }
        },
        scope: {
          data: '=stData'
        },
        templateUrl: 'views/chart-widget.html'
      };
    })
    .directive('widgetSystemStatus', function(){
      return{
        restrict: 'E',
        scope: {
          data: '=stData'
        },
        templateUrl: 'views/widget-system-status.html',
        link: function (scope, element, attrs) {
        }
      };
    })
    .directive('widgetProgress', function(){
      return{
        restrict: 'E',
        scope: {
          data: '=stData'
        },
        templateUrl: 'views/widget-progress.html',
        link: function (scope, element, attrs) {
          var sstatus = element.find('#sstatus'),
            percent = scope.data.percent;
          sstatus.css('width', percent+'%');
          if(percent >= 80){
            sstatus.css('background-color', '#E9573F');
          }
          else if(percent < 80 && percent >=70){
            sstatus.css('background-color', '#3BAFDA');
          }
          else if(percent < 70 && percent >=60){
            sstatus.css('background-color', '#F6BB42');
          }
          else if(percent < 60 && percent >=50){
            sstatus.css('background-color', '#F6BB42');
          }
          else{
            sstatus.css('background-color', '#8CC152');
          }
        }
      };
    })
    .directive('headlineNewTitle', function () {
      return{
        restrict: 'E',
        scope: {
        },
        controller: function ($scope, $element) {
          //       Owl Carousel
          $element.find('#tiles-slide-2').owlCarousel({
            navigation: false, // Show next and pre buttons
            sideSpeed: 500,
            paginationSpeed: 800,
            singleItem: true,
            pagination: false,
            responsive: true,
            autoPlay: true
          });
          $element.find('#tiles-slide-3').owlCarousel({
            navigation: false, // Show next and pre buttons
            sideSpeed: 500,
            paginationSpeed: 800,
            singleItem: true,
            pagination: false,
            responsive: true,
            autoPlay: true
          });
        },
        templateUrl: 'views/headline-new-title.html'
      };
    })
    .directive('mainWeatherWidget', function () {
      return{
        restrict: 'E',
        scope: {
          'list': '=stList',
          'title': '@stTitle'
        },
        templateUrl: 'views/main-weather-widget.html'
      };
    })
    .directive('mainWeatherWidgetContent', function () {
      return{
        restrict: 'E',
        scope: {
          item: '=stItem'
        },
        controller: 'SkyconsCtrl',
        templateUrl: 'views/main-weather-widget-content.html',
        link: function (scope, element, attrs) {
          var status = scope.item.status,
            bgElement = element.find('#box-weather4');

          if (angular.equals(status, 'It\'s sunny')) {
            bgElement.addClass('bg-warning');
            scope.skycon = 'clear-day';
          }
          else if (angular.equals(status, 'It\'s raining')) {
            bgElement.addClass('bg-danger');
            scope.skycon = 'rain';
          }
          else if (angular.equals(status, 'It\'s wind')) {
            bgElement.addClass('bg-success');
            scope.skycon = 'wind';
          } else {
            bgElement.addClass('bg-primary');
            scope.skycon = 'partly-cloudy-night';
          }
        }
      };
    })
    .directive('mainItemShowcase', function () {
      return{
        restrict: 'E',
        templateUrl: 'views/main-item-showcase.html'
      };
    })
    .directive('lastWeekPopularItem', function () {
      return{
        restrict: 'AE',
        controller: function ($scope, $element) {
        },
        scope: {
          data: '=stData'
        },
        templateUrl: 'views/last-week-popular-item.html',
        link: function(scope, element, attr){
          element.find('#owl-popular').owlCarousel({
            navigation: false, // Show next and pre buttons
            sideSpeed: 500,
            paginationSpeed: 800,
            singleItem: false,
            pagination: false,
            responsive: true,
            item: 4
          });

        }
      };
    })
    .directive('featuredProduct', function () {
      return{
        require: 'propertyCard',
        restrict: 'E',
        scope: {
          title: '@stTitle ',
          data: '=stData'
        },
        controller: function ($scope, $element) {
          //       Owl Carousel
          $element.find('#owl-feature').owlCarousel({
            navigation: false, // Show next and pre buttons
            sideSpeed: 500,
            paginationSpeed: 800,
            singleItem: true,
            pagination: false,
            responsive: true
          });
        },
        templateUrl: 'views/featured-product.html'
      };
    })
    .directive('reminderWidget', function () {
      return{
        restrict: 'E',
        scope: {
          title: '@stTitle',
          data: '=stData'
        },
        controller: function ($scope, $element) {
          //       Owl Carousel
          $element.find('#owl-reminder').owlCarousel({
            navigation: true, // Show next and pre buttons
            sideSpeed: 500,
            paginationSpeed: 800,
            singleItem: true,
            pagination: false,
            responsive: true,
            navigationText: ['←', '→']
          });
        },
        templateUrl: 'views/reminder-widget.html'
      };
    })
    .directive('simplifiedNumber', function(){
      return{
        restrict: 'EA',
        scope: {
          data: '@stData'
        },
        templateUrl: 'views/simplified-number.html',
        link: function(scope, element, attr){

          var number = '';
          scope.nbig = false;
          if (scope.data.length > 6) {
            scope.nbig = true;
            number = scope.data.slice(0, scope.data.length - 3);
            scope.point = number;
          }
          else{
            scope.point = scope.data;
          }
        }
      };
    })
    .directive('widgetRating', function(){
      return{
        restrict: 'EA',
        scope: {
          max: '@stMax',
          star: '@stStar'
        },
        link: function(scope, element, attr){

          for(var i=0 ; i<scope.max; i++){
            if(i < scope.star){
              element.append('<i class="fa fa-star text-warning"></i>');
            }else{
              element.append('<i class="fa fa-star"></i>');
            }
          }
        }
      };
    })
  ;
})();