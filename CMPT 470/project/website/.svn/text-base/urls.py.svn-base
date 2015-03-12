from django.conf.urls import patterns, url, include

from website import views

HistoryPatterns = patterns('',
	url(r'^edit/$', 				views.TripEditView,		name='trip_edit'),
	url(r'^delete/$',			views.TripDeleteView,	name='trip_delete'),
	url(r'^addplace/$',			views.savePlaceToList,	name='saveplaces'),
	url(r'^$',					views.TripView, 		name='trip'),
)

urlpatterns = patterns('',
	url(r'^$', 					views.IndexView, 	name='index'),
	url(r'^createuser/$',		views.CreateUser, 	name='createuser'),
	url(r'^submittrip/$',		views.SubmitTrip, 	name='submittrip'),
	url(r'^history/(?P<pk>\d+)/', include(HistoryPatterns)),
	url(r'^history/$',			views.History, 		name='history'),
	url(r'^place/(?P<pk>\d+)/delete/$',	views.DeletePlace, 	name='delete_place'),
)

urlpatterns += patterns('django.contrib.auth.views',
	(r'^login/$',           'login',  {
		'template_name': 'login.html'}),
	(r'^logout/$',          'logout', {
		'template_name': 'logout.html'}),
)
