'use strict';
angular.module('adminApp')
  .controller('MainCtrl', function ($scope) {
    var notifications = [
        {
          'id': 0,
          'name': 'Thomas White',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': '1 seconds ago',
          'avatar': 'avatar-2.jpg',
          'content': 'posted on your profile page'
        },
        {
          'id': 1,
          'name': 'Doina Slaivici',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': '2 seconds ago',
          'avatar': 'avatar-3.jpg',
          'content': 'Uploaded a photo'
        },
        {
          'id': 2,
          'name': 'Hary Nichols',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': '2 hours ago',
          'avatar': 'avatar-4.jpg',
          'content': 'Commented on your post'
        },
        {
          'id': 3,
          'name': 'Mihala Cihac',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': '17 seconds ago',
          'avatar': 'avatar-5.jpg',
          'content': 'Send you a message'
        },
        {
          'id': 4,
          'name': 'Harold Chavez',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': 'Yesterday 09:00:00',
          'avatar': 'avatar-6.jpg',
          'content': 'Change his avatar'
        },
        {
          'id': 5,
          'name': 'Elizabeth Owens',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': 'Yesterday 07:00:00',
          'avatar': 'avatar-7.jpg',
          'content': 'posted on your profile page'
        },
        {
          'id': 6,
          'name': 'Ryan Ortega',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': 'Yesterday 06:00:00',
          'avatar': 'avatar-7.jpg',
          'content': 'Change her avatar'
        }
      ],
      users = [
        {
          'id': 0,
          'name': 'Thomas White',
          'avatar': 'avatar-2.jpg',
          'content': 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit',
          'status': 'online',
          'divice': 'mobile-phone'
        },
        {
          'id': 1,
          'name': 'Doina Slaivici',
          'avatar': 'avatar-3.jpg',
          'content': 'Duis autem vel eum iriure dolor in hendrerit in',
          'status': 'online',
          'divice': ''
        },
        {
          'id': 2,
          'name': 'Harry Nichols',
          'avatar': 'avatar-4.jpg',
          'content': 'I think so',
          'status': 'online',
          'divice': 'apple'
        },
        {
          'id': 3,
          'name': 'Mihaela Cihac',
          'avatar': 'avatar-5.jpg',
          'content': 'Yes, I\'ll be waiting',
          'status': 'idle',
          'time': 'Last seen yesterday',
          'divice': 'android'
        },
        {
          'id': 4,
          'name': 'Elizabeth Owens',
          'avatar': 'avatar-6.jpg',
          'content': 'Yes, I\'ll be waiting',
          'status': 'idle',
          'time': 'Last seen 2 hours ago',
          'divice': 'windows'
        },
        {
          'id': 5,
          'name': 'Frank Oliver',
          'avatar': 'avatar-6.jpg',
          'content': 'Yes, I\'ll be waiting',
          'status': 'idle',
          'time': '12 minutes',
          'divice': ''
        },
        {
          'id': 6,
          'name': 'Mya Weastell',
          'avatar': 'avatar-7.jpg',
          'content': 'Yes, I\'ll be waiting',
          'status': 'offline',
          'time': 'Last seen Feb 20.2014 05.45.50',
          'divice': 'windows'
        },
        {
          'id': 7,
          'name': 'Carl Rodriguez',
          'avatar': 'avatar-8.jpg',
          'content': 'Yes, I\'ll be waiting',
          'status': 'offline',
          'time': 'Last seen Jan 20.2014 03.45.50',
          'divice': ''
        },
        {
          'id': 8,
          'name': 'Nikita Carter',
          'avatar': 'avatar-9.jpg',
          'content': 'Yes, I\'ll be waiting',
          'status': 'offline',
          'time': '2 minutes',
          'divice': 'windows'
        }
      ],
      accountSetting = [
        {
          'id': 0,
          'setting': 'Online status',
          'done': true
        },
        {
          'id': 1,
          'setting': 'Show offline contact',
          'done': true
        },
        {
          'id': 2,
          'setting': 'Invisible mode',
          'done': false
        },
        {
          'id': 3,
          'setting': 'Show my persional status',
          'done': true
        },
        {
          'id': 4,
          'setting': 'Show my device icon',
          'done': false
        },
        {
          'id': 5,
          'setting': 'Log all message',
          'done': false
        }
      ],
      tasks = [
        {
          'id': 0,
          'avatar': 'avatar-12.jpg',
          'content': 'Drinking coffee',
          'time': '17 seconds ago',
          'deadline': '',
          'status': 'complete'
        },
        {
          'id': 1,
          'avatar': 'avatar-3.jpg',
          'content': 'Walking to nowhere',
          'time': '17 seconds ago',
          'deadline': '',
          'status': 'complete'
        },
        {
          'id': 2,
          'avatar': 'avatar-5.jpg',
          'content': 'Sleeping under bridge',
          'time': '17 seconds ago',
          'deadline': '',
          'status': 'complete'
        },
        {
          'id': 3,
          'avatar': 'avatar-1.jpg',
          'content': 'Buying some cigarettes',
          'time': '17 seconds ago',
          'deadline': '',
          'status': 'complete'
        },
        {
          'id': 4,
          'avatar': 'avatar-5.jpg',
          'content': 'Sending payment',
          'time': '2 hours ago',
          'status': 'deadline'
        },
        {
          'id': 5,
          'avatar': 'avatar-5.jpg',
          'content': 'Uploading new version',
          'time': 'Tomorrow',
          'status': 'deadline'
        }
      ],
      messages = [
        {
          'id': 0,
          'name': '',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': '17 seconds ago',
          'avatar': 'avatar-12.jpg',
          'content': 'Message 1'
        },
        {
          'id': 1,
          'name': '',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': '17 seconds ago',
          'avatar': 'avatar-5.jpg',
          'content': 'Message 2'
        },
        {
          'id': 2,
          'name': '',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': '17 seconds ago',
          'avatar': 'avatar-2.jpg',
          'content': 'Message 3'
        },
        {
          'id': 3,
          'name': '',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': '17 seconds ago',
          'avatar': 'avatar-1.jpg',
          'content': 'Message 4'
        },
        {
          'id': 4,
          'name': '',
          'email': 'hello@gmail.com',
          'phone': '123-2343-44',
          'time': '17 seconds ago',
          'avatar': 'avatar-10.jpg',
          'content': 'Message 5'
        }
      ],
      siteInfomation = [
        {
          'id': 0,
          'name': 'TODAY VISITORS',
          'number': 12254000,
          'icon': 'users'
        },
        {
          'id': 1,
          'name': 'TODAY SALES',
          'number': 521,
          'icon': 'shopping-cart'
        },
        {
          'id': 2,
          'name': 'TODAY FEEDBACK',
          'number': 124,
          'icon': 'comments'
        },
        {
          'id': 3,
          'name': 'TODAY EARNINGS',
          'number': 10241,
          'icon': 'money'
        }
      ],
      siteInfomationLast = [
        {
          'id': 0,
          'name': 'TODAY VISITORS',
          'number': 11000000
        },
        {
          'id': 1,
          'name': 'TODAY SALES',
          'number': 490
        },
        {
          'id': 2,
          'name': 'TODAY FEEDBACK',
          'number': 150
        },
        {
          'id': 3,
          'name': 'TODAY EARNINGS',
          'number': 10000
        }
      ],
      morrisChart = {
        'id': 0,
        'tilte': 'Earnings and sales chart',
        'lastyear': {
          'id': 0,
          'title': 'last year',
          'point': '805',
          'status': '-10,1',
          'percent': '11%',
          'labels': 'Earning (in 10K USD)',
          'resize': true,
          'data': [
            { y: '2000', a: 34},
            { y: '2001', a: 55},
            { y: '2002', a: 60},
            { y: '2003', a: 65},
            { y: '2004', a: 20},
            { y: '2005', a: 75},
            { y: '2006', a: 55},
            { y: '2007', a: 77},
            { y: '2008', a: 90},
            { y: '2009', a: 125},
            { y: '2010', a: 100},
            { y: '2011', a: 15},
            { y: '2012', a: 20},
            { y: '2013', a: 85}
          ],
          'xkey': 'y',
          'ykeys': 'a',
          'lineColors': '#1F91BD',
          'pointFillColors': '#fff',
          'pointStrokeColors' : '#3EAFDB',
          'gridTextColor': '#fff',
          'pointSize' :3
        },
        'earnings': {
          'id': 0,
          'title': 'Lifetime earnings',
          'point': '50024000',
          'data': [
            { y: 'Indonesia', a: 952},
            { y: 'India', a: 985},
            { y: 'United Kingdom', a: 421 },
            { y: 'United States', a: 725 },
            { y: 'Taiwan', a: 350 },
            { y: 'New Zealand', a: 120 },
            { y: 'Germany', a: 85 },
            { y: 'Thailand', a: 20 },
            { y: 'Korea', a: 65 },
            { y: 'Malaysia', a: 955},
            { y: 'China', a: 785 },
            { y: 'Philipina', a: 700 },
            { y: 'Autralia', a: 601 },
            { y: 'Japan', a: 50 },
            { y: 'Singapore', a: 124}
          ],
          'xkey': 'y',
          'ykeys': 'a',
          'labels': 'Companies',
          'resize': true,
          'barColors': '#E6563C',
          'gridTextColor': '#E6563C',
          'gridTextSize': 11,
          'grid' :false,
          'axes': false
        },
        'today': 'May 20 2014'
      },
      alertList = [
        {
          'id': 0,
          'title': 'Welcome!',
          'content': 'You probably here cause wanna know how is <a href="">Sentir UI</a> kits template, or you have purchased it. But whatever! I just wanna say thank you for viewing or purchasing my work. And let me know your feedback!'
        },
        {
          'id': 1,
          'title': 'Hello World!',
          'content': 'You probably here cause wanna know how is Sentir UI kits template, or you have purchased it. But whatever! I just wanna say thank you for viewing or purchasing my work. And let me know your feedback!'
        }
      ],
      weatherToday = {
        'id': 1,
        'country': 'YOGYAKARTA, ID',
        'temperature': 28,
        'status': 'TONIGHT',
        'prediction': 'Will rain at night'
      },
      weatherTomorow = [
        {
          'id': 1,
          'day': 'SAT',
          'temperature': 27
        },
        {
          'id': 2,
          'day': 'SUN',
          'temperature': 26
        },
        {
          'id': 3,
          'day': 'MON',
          'temperature': 15
        }
      ],
      taskslist = [
        {
          'id': 0,
          'title': 'Eating woods',
          'complete': false
        },
        {
          'id': 1,
          'title': 'Washing my pets',
          'complete': true
        },
        {
          'id': 2,
          'title': 'Uploading selfie photos',
          'complete': true
        },
        {
          'id': 3,
          'title': 'Downloading movie',
          'complete': true
        },
        {
          'id': 4,
          'title': 'Updating my alay status',
          'complete': true
        },
        {
          'id': 5,
          'title': 'Creating web template',
          'complete': false
        },
        {
          'id': 6,
          'title': 'Walking to Malioboro',
          'complete': true
        },
        {
          'id': 7,
          'title': 'Listening Alay songs',
          'complete': true
        },
        {
          'id': 8,
          'title': 'Being an elite author',
          'complete': false
        },
        {
          'id': 9,
          'title': 'Hunting cabe-cabean',
          'complete': false
        }
      ],
      followers = [
        {
          'id': 0,
          'title': 'facebook',
          'icon': 'facebook',
          'followers': '10000',
          'style': 'facebook-tile'
        },
        {
          'id': 1,
          'title': 'twitter',
          'icon': 'twitter',
          'followers': '2000',
          'style': 'twitter-tile'
        },
        {
          'id': 2,
          'title': 'dribbble',
          'icon': 'dribbble',
          'followers': '1000',
          'style': 'dribbble-tile'
        },
        {
          'id': 3,
          'title': 'linkedin',
          'icon': 'linkedin',
          'followers': '1000',
          'style': 'linkedin-tile'
        }
      ],
      weatherCountry = [
        {
          id: 1,
          "country": 'BOYOLALI, ID',
          "temperature": 27,
          "status": 'It\'s sunny',
          "opinion": 'My hometown'
        },
        {
          id: 2,
          "country": 'PARIS, FRANCE',
          "temperature": 27,
          "status": 'It\'s raining',
          "opinion": 'My dream city'
        },
        {
          id: 3,
          "country": 'MECCA, KSA',
          "temperature": 27,
          "status": 'It\'s wind',
          "opinion": 'Someday I\'ll be there'
        },
        {
          id: 4,
          "country": 'TOKYO, JAPAN',
          "temperature": 27,
          "status": 'Cloudy night',
          "opinion": 'I just wanna visit'
        }
      ],
      specicalOffers = {
        'id': 0,
        'title': 'Specical Offers',
        'image': [
          {
            'id': 1,
            'name': 'Image1',
            'url': 'images/small/img-16.jpg'
          },
          {
            'id': 2,
            'name': 'Image2',
            'url': 'images/small/img-15.jpg'
          },
          {
            'id': 3,
            'name': 'Image3',
            'url': 'images/small/img-13.jpg'
          }
        ],
        'price': 750,
        'bond': 3000,
        'bedroom': 2,
        'bathroom': 2,
        'star': 5
      },
      friendRequests = [
        {
          'id': 0,
          'name': 'Mya Weastell',
          'avatar': 'avatar-9.jpg',
          'email': 'myaweastell',
          'status': '2 murtual friends'
        },
        {
          'id': 1,
          'name': 'Craig Dixon',
          'avatar': 'avatar-6.jpg',
          'email': 'elizabethowens',
          'status': '12 murtual friends'
        },
        {
          'id': 2,
          'name': 'Mikayla King',
          'avatar': 'avatar-5.jpg',
          'email': 'haroldchaves',
          'status': '5 murtual friends'
        },
        {
          'id': 3,
          'name': 'Richard Dixon',
          'avatar': 'avatar-7.jpg',
          'email': 'elizabethowens',
          'status': '12 murtual friends'
        },
        {
          'id': 4,
          'name': 'Brenda Fuller',
          'avatar': 'avatar-8.jpg',
          'email': 'haroldchaves',
          'status': '5 murtual friends'
        },
        {
          'id': 5,
          'name': 'Ryan Ortega',
          'avatar': 'avatar-12.jpg',
          'email': 'elizabethowens',
          'status': '12 murtual friends'
        },
        {
          'id': 6,
          'name': 'Jessica Gutierrez',
          'avatar': 'avatar-10.jpg',
          'email': 'haroldchaves',
          'status': '5 murtual friends'
        }
      ],
      reminder = {
        'title': 'Next week agenda',
        'contents': [
          {
            'id': 0,
            'content': 'Eating some sand and listening alay songs in the small hole under bridge',
            'time': 'Wrote 2 days ago'
          },
          {
            'id': 1,
            'content': 'Go to school again, do homework again, meet some best friends again',
            'time': 'Wrote about a week ago'
          },
          {
            'id': 2,
            'content': 'Finishing all my works, time to vacation, spending time with family and friends',
            'time': 'Wrote about a month ago'
          }
        ]
      },
      featuredProduct = {
        'id': 0,
        'name': 'Your product name here',
        'price': 220,
        'content': 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.',
        'image': 'images/large/img-7.jpg',
        'star': 3
      },
      menu = [
        {
          'id': 0,
          'title': 'Layout template',
          'submenu': [
            {
              'id': 0,
              'title': 'Default layout',
              'link': '#fakelink'
            },
            {
              'id': 1,
              'title': 'No sidebar right',
              'link': 'layout-no-sidebar-right.html'
            },
            {
              'id': 2,
              'title': 'Profile summary left',
              'link': 'layout-profile-left.html'
            },
            {
              'id': 3,
              'title': 'No sidebar left',
              'link': 'layout-no-sidebar-left.html'
            },
            {
              'id': 4,
              'title': 'Shrink navbar',
              'link': 'layout-shrink-navbar.html'
            },
            {
              'id': 5,
              'title': 'Top navigation',
              'link': 'layout-top-navigation.html'
            },
            {
              'id': 6,
              'title': 'Tour layout',
              'link': 'layout-tour.html'
            }
          ],
          'icon': 'desktop',
          'label': 'badge'
        },
        {'id': 1,
          'title': 'Widget UI kits',
          'submenu': [
            {
              'id': 0,
              'title': 'Default',
              'link': 'widget-default.html'
            },
            {
              'id': 1,
              'title': 'Store',
              'link': 'widget-store.html'
            },
            {
              'id': 2,
              'title': 'Real estate',
              'link': 'widget-real-estate.html',
              'label': 'HOT'
            },
            {
              'id': 3,
              'title': 'Blog',
              'link': 'widget-blog.html'
            },
            {
              'id': 4,
              'title': 'Social',
              'link': 'widget-social.html',
              'label': 'HOT'
            }
          ],
          'icon': 'flask'
        },
        {'id': 2,
          'title': 'Basic elements',
          'submenu': [
            {
              'id': 0,
              'title': 'Typography',
              'link': 'element-typography.html'
            },
            {
              'id': 1,
              'title': 'Form element',
              'link': 'element-form.html'
            },
            {
              'id': 2,
              'title': 'Form example',
              'link': 'element-form-example.html'
            },
            {
              'id': 3,
              'title': 'Form WYSWYG',
              'link': 'element-wyswyg.html'
            },
            {
              'id': 4,
              'title': 'Form validation',
              'link': 'element-validation.html'
            },
            {
              'id': 5,
              'title': 'Buttonn',
              'link': 'element-button.html'
            }
          ],
          'icon': 'folder',
          'label': 'info'
        },
        {'id': 3,
          'title': 'More elements',
          'submenu': [
            {
              'id': 0,
              'title': 'Icons',
              'link': 'element-icon.html'
            },
            {
              'id': 1,
              'title': 'Box & panel',
              'link': 'element-box-panel.html'
            },
            {
              'id': 2,
              'title': 'Nav & dropdown',
              'link': 'element-nav-dropdown.html'
            },
            {
              'id': 3,
              'title': 'Breadcrumb & pagination',
              'link': 'element-breadcrumb-pagination.html'
            },
            {
              'id': 4,
              'title': 'Jumbotron & thumbnail',
              'link': 'element-thumbnail-jumbotron.html'
            },
            {
              'id': 5,
              'title': 'Alert & progress',
              'link': 'element-alert-progress-bar.html'
            },
            {
              'id': 6,
              'title': 'List group & media object',
              'link': 'element-list-media.html'
            },
            {
              'id': 7,
              'title': 'Collapse',
              'link': 'element-collapse.html'
            },
            {
              'id': 8,
              'title': 'Grid & masonry',
              'link': 'element-grid-masonry.html'
            },
            {
              'id': 9,
              'title': 'Masonry JS',
              'link': 'element-masonry-js.html'
            },
            {
              'id': 10,
              'title': 'Toastr notifications',
              'link': 'element-toastr.html',
              'label': 'NEW'
            },
            {
              'id': 11,
              'title': 'Carousel',
              'link': 'element-carousel.html',
              'label': 'NEW'
            },
            {
              'id': 12,
              'title': 'Calendar',
              'link': 'element-calendar.htm',
              'label': 'NEW'
            },
            {
              'id': 12,
              'title': 'Extra elements',
              'link': 'element-extra.html',
              'label': 'UPDATED'
            }
          ],
          'icon': 'folder-open'
        },
        {'id': 4,
          'title': 'Tables',
          'submenu': [
            {
              'id': 0,
              'title': 'Static table',
              'link': 'table-static.html'
            },
            {
              'id': 1,
              'title': 'Table color',
              'link': 'table-color.html'
            },
            {
              'id': 2,
              'title': 'Jquery datatable',
              'link': 'table-datatable.html'
            }
          ],
          'icon': 'table'
        },
        {'id': 5,
          'title': 'Chart or Graph',
          'submenu': [
            {
              'id': 0,
              'title': 'Morris chart',
              'link': 'chart-morris.html'
            },
            {
              'id': 1,
              'title': 'C3 chart',
              'link': 'chart-c3.html'
            },
            {
              'id': 2,
              'title': 'Flot chart',
              'link': 'chart-flot.html'
            },
            {
              'id': 2,
              'title': 'Easy pie chart & knob',
              'link': 'chart-easy-knob.html'
            }
          ],
          'icon': 'bar-chart-o'
        },
        {
          'id': 6,
          'title': 'Extra designs',
          'label': 'heading'
        },
        {
          'id': 7,
          'title': 'Mail apps',
          'submenu': [
            {
              'id': 0,
              'title': 'Inbox',
              'link': 'chart-morris.html'
            },
            {
              'id': 1,
              'title': 'Sent mail',
              'link': 'chart-c3.html'
            },
            {
              'id': 2,
              'title': 'New mail',
              'link': 'chart-flot.html'
            },
            {
              'id': 3,
              'title': 'Mail contact',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 4,
              'title': 'Read mail',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 5,
              'title': 'Reply mail',
              'link': 'chart-easy-knob.html'
            }
          ],
          'icon': 'envelope',
          'label': 'hot'
        },
        {'id': 8,
          'title': 'Store apps',
          'submenu': [
            {
              'id': 0,
              'title': 'Product list',
              'link': 'chart-morris.html'
            },
            {
              'id': 1,
              'title': 'Product column',
              'link': 'chart-c3.html'
            },
            {
              'id': 2,
              'title': 'Product masonry',
              'link': 'chart-flot.html'
            },
            {
              'id': 3,
              'title': 'Product detail',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 4,
              'title': 'Shopping cart',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 5,
              'title': 'Checkout',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 6,
              'title': 'Add new product',
              'link': 'chart-easy-knob.html',
              'label': 'NEW'
            },
            {
              'id': 7,
              'title': 'Order list',
              'link': 'chart-easy-knob.html'
            }
          ],
          'icon': 'shopping-cart'
        },
        {'id': 9,
          'title': 'Real estate apps',
          'submenu': [
            {
              'id': 0,
              'title': 'Property list',
              'link': 'chart-morris.html'
            },
            {
              'id': 1,
              'title': 'Property column',
              'link': 'chart-c3.html'
            },
            {
              'id': 2,
              'title': 'Property masonry',
              'link': 'chart-flot.html'
            },
            {
              'id': 3,
              'title': 'Property detail',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 4,
              'title': 'Search property',
              'link': 'chart-easy-knob.html'
            }
          ],
          'icon': 'home'
        },
        {'id': 10,
          'title': 'Blog apps',
          'submenu': [
            {
              'id': 0,
              'title': 'Blog list',
              'link': 'chart-morris.html'
            },
            {
              'id': 1,
              'title': 'Blog column',
              'link': 'chart-c3.html'
            },
            {
              'id': 2,
              'title': 'Blog masonry',
              'link': 'chart-flot.html'
            },
            {
              'id': 3,
              'title': 'Blog detaill',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 4,
              'title': 'Featured home',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 5,
              'title': 'Add new blog',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 6,
              'title': 'Comments',
              'link': 'chart-easy-knob.html'
            }
          ],
          'icon': 'comment',
          'label': 'top'
        },
        {'id': 11,
          'title': 'Social apps',
          'submenu': [
            {
              'id': 0,
              'title': 'Home activity',
              'link': 'chart-morris.html'
            },
            {
              'id': 1,
              'title': 'My profile',
              'link': 'chart-c3.html'
            },
            {
              'id': 2,
              'title': 'Friend list',
              'link': 'chart-flot.html'
            },
            {
              'id': 3,
              'title': 'Timeline',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 4,
              'title': 'Photos',
              'link': 'chart-easy-knob.html',
              'label': '5'
            }
          ],
          'icon': 'users'
        },
        {'id': 12,
          'title': 'Example pagess',
          'submenu': [
            {
              'id': 0,
              'title': 'Login',
              'link': 'chart-morris.html'
            },
            {
              'id': 1,
              'title': 'Lock screen',
              'link': 'chart-c3.html'
            },
            {
              'id': 2,
              'title': 'Forgot password',
              'link': 'chart-flot.html'
            },
            {
              'id': 3,
              'title': 'Register',
              'link': 'chart-easy-knob.html'
            },
            {
              'id': 4,
              'title': 'Pricing table',
              'link': 'chart-easy-knob.html',
              'label': ''
            },
            {
              'id': 5,
              'title': 'Invoice',
              'link': 'chart-easy-knob.html',
              'label': ''
            },
            {
              'id': 6,
              'title': 'FAQ',
              'link': 'chart-easy-knob.html',
              'label': ''
            },
            {
              'id': 7,
              'title': 'Search page',
              'link': 'chart-easy-knob.html',
              'label': ''
            },
            {
              'id': 8,
              'title': 'Media library',
              'link': 'chart-easy-knob.html',
              'label': 'NEW'
            },
            {
              'id': 9,
              'title': '404',
              'link': 'chart-easy-knob.html',
              'label': ''
            },
            {
              'id': 10,
              'title': 'Blank page',
              'link': 'chart-easy-knob.html',
              'label': ''
            }
          ],
          'icon': 'heart'
        }
      ],
      chart = [
        {
          'id': 0,
          'title': 'Kernel memory',
          'percent': 45
        },
        {
          'id': 1,
          'title': 'Physical memory',
          'percent': 85
        }
      ],
      systemStatus = {
        'id': 0,
        'title': 'System status',
        'content': [
          {
            'id': 0,
            'title': 'Handles',
            'percent': 80
          },
          {
            'id': 0,
            'title': 'Threads',
            'percent': 65
          },
          {
            'id': 0,
            'title': 'Process',
            'percent': 40
          },
          {
            'id': 0,
            'title': 'cached',
            'percent': 70
          }
        ]
      },
      lastWeekPopularItem = [
        {
          'id': 0,
          'title': 'Product name here',
          'description': 'Brand name',
          'price': 50,
          'image': 'images/shop/img-shop-1.jpg'
        },
        {
          'id': 1,
          'title': 'Product name here',
          'description': 'Brand name',
          'price': 50,
          'image': 'images/shop/img-shop-2.jpg'
        },
        {
          'id': 2,
          'title': 'Product name here',
          'description': 'Brand name',
          'price': 50,
          'image': 'images/shop/img-shop-3.jpg'
        },
        {
          'id': 3,
          'title': 'Product name here',
          'description': 'Brand name',
          'price': 50,
          'image': 'images/shop/img-shop-4.jpg'
        },
        {
          'id': 4,
          'title': 'Product name here',
          'description': 'Brand name',
          'price': 50,
          'image': 'images/shop/img-shop-5.jpg'
        },
        {
          'id': 5,
          'title': 'Product name here',
          'description': 'Brand name',
          'price': 50,
          'image': 'images/shop/img-shop-6.jpg'
        }
      ],
      settings = [
        {
          'id': 0,
          'title': 'Alert me when system down',
          'done': true
        },
        {
          'id': 1,
          'title': 'Automatic update',
          'done': true
        },
        {
          'id': 2,
          'title': 'Daily task report',
          'done': true
        }
        ,
        {
          'id': 3,
          'title': 'Remember this computer',
          'done': true
        }
      ],
      footer = {
        'id': 0,
        'author': '© 2014 <a href>Your company</a>',
        'design': 'Design by <a href>ids</a>. Purchase this item at <a href>Themeforest</a>'
      }
      ;
    $scope.logo = 'sentir-logo-primary.png';
    $scope.users = users;
    $scope.accountSetting = accountSetting;
    $scope.notifications = notifications;
    $scope.tasks = tasks;
    $scope.messages = messages;
    $scope.siteInfomation = siteInfomation;
    $scope.siteInfomationLast = siteInfomationLast;
    $scope.morrisChart = morrisChart;
    $scope.alertList = alertList;
    $scope.weatherToday = weatherToday;
    $scope.weatherTomorow = weatherTomorow;
    $scope.followers = followers;
    $scope.taskList = taskslist;
    $scope.weatherCountry = weatherCountry;
    $scope.specicalOffers = specicalOffers;
    $scope.friendRequests = friendRequests;
    $scope.featuredProduct = featuredProduct;
    $scope.reminder = reminder;
    $scope.menus = menu;
    $scope.chart = chart;
    $scope.systemStatus = systemStatus;
    $scope.lastWeekPopularItem = lastWeekPopularItem;
    $scope.settings = settings;
    $scope.footer = footer;
  })
  .controller('CollapseDemoCtrl', function ($scope) {
    var toggle = true; //Show
    var rightToggle = false; // Hide
    var pagecontent = angular.element('.page-content');
    var topnavbar = angular.element('.top-navbar');

    $scope.sidebarLeft = function () {
      var sidebarLeft = angular.element('.sidebar-left');
      var sidebarRight = angular.element('#sidebar-right');
      if (toggle) {
        sidebarLeft.addClass('sidebar-left-toggle');
        pagecontent.css('margin-left', 0);
        toggle = !toggle;
      }
      else {
        sidebarLeft.removeClass('sidebar-left-toggle');
        pagecontent.css('margin-left', 250);

        if (rightToggle) {
          topnavbar.removeClass('top-navbar-toggle');
          sidebarRight.css('right', -250);
          pagecontent.css('margin-right', 0);
          rightToggle = !rightToggle;
        }
        toggle = !toggle;
      }

    };


    $scope.sidebarRight = function () {
      var sidebarRight = angular.element('#sidebar-right');

      if (rightToggle) {
        topnavbar.removeClass('top-navbar-toggle');

        sidebarRight.css('right', -250);
        pagecontent.css('margin-right', 0);
        rightToggle = !rightToggle;
        $scope.sidebarLeft();
      } else {
        topnavbar.addClass('top-navbar-toggle');
        sidebarRight.css('right', 0);
        pagecontent.css('margin-right', 250);
        rightToggle = !rightToggle;
        toggle = true;
        $scope.sidebarLeft();
      }
    }
  })
  .controller('SkyconsCtrl', function () {

    /** BEGIN SVG WEATHER ICON **/
    if (typeof Skycons !== 'undefined') {
      var icons = new Skycons(
          {"color": "#fff"},
          {"resizeClear": true}
        ),
        list = [
          "clear-day", "clear-night", "partly-cloudy-day",
          "partly-cloudy-night", "cloudy", "rain", "sleet", "snow", "wind",
          "fog"
        ],
        i;

      for (i = list.length; i--;)
        icons.set(list[i], list[i]);
      icons.play();
    }
    ;
    /** END SVG WEATHER ICON **/
  })
  .controller('NiceScroll', function($scope, $element){
    $element.find(".sidebar-nicescroller").niceScroll({
      cursorcolor: "#121212",
      cursorborder: "0px solid #fff",
      cursorborderradius: "0px",
      cursorwidth: "0px"
    });
    $element.find(".sidebar-nicescroller").getNiceScroll().resize();
  })

;
