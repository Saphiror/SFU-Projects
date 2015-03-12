from django.shortcuts import render
from django.contrib.auth import login, authenticate
from django.contrib.auth.models import User
from django.forms import ModelForm, PasswordInput, CharField
from django.http import HttpResponseRedirect, HttpResponse, Http404
from django.contrib.auth.decorators import login_required
from django.core.urlresolvers import reverse
from django import forms
from django.contrib.auth.forms import UserCreationForm

from website.models import Trip
from website.models import SavedPlaces

def IndexView(request):
	return render(request, 'index.html')

def CreateUser(request):
	if request.method == 'POST':
		form = UserCreationForm(request.POST)
		if form.is_valid():
			new_user = form.save()
			new_user = authenticate(username=request.POST['username'], password=request.POST['password1'])
			login(request, new_user)
			return HttpResponseRedirect(reverse('index'))
	else:
		form = UserCreationForm()

	return render(request, 'createuser.html', {'form': form})

class TripForm(ModelForm):
	class Meta:
		model = Trip
		fields = ('start', 'end')

def SubmitTrip(request):
	if not request.user.is_authenticated():
		return HttpResponse("No Auth")
	if request.method == 'POST':
		form = TripForm(request.POST)
		if form.is_valid():
			trip = form.save(commit=False)
			trip.user = request.user
			trip.save()
			return HttpResponse(trip.id)
	return HttpResponse("Not OK")

@login_required()
def History(request):
	if not request.user.is_authenticated():
		trips = Trip.objects.filter(share_type=2)
	else:
		trips = Trip.objects.filter(user=request.user)
	return render(request, 'history.html', {'trips': trips})

def TripView(request, pk):
	trip = Trip.objects.get(pk=pk)
	if trip.share_type == 0 and request.user != trip.user:
		raise Http404
	places = SavedPlaces.objects.filter(trip=trip)
	return render(request, 'trip.html', {'t': trip, 'places': places})

@login_required()
def TripDeleteView(request, pk):
	trip = Trip.objects.get(user=request.user, pk=pk)
	trip.delete()
	return HttpResponseRedirect(reverse('history'))

class TripEditForm(ModelForm):
	class Meta:
		model=Trip
		fields=('start', 'end', 'share_type')

@login_required()
def TripEditView(request, pk):
	trip = Trip.objects.get(user=request.user, pk=pk)
	if request.method == 'POST':
		form = TripEditForm(request.POST, instance=trip)
		if form.is_valid():
			form.save()
			return HttpResponseRedirect(reverse('trip', args=(pk,)))
	else:
		form = TripEditForm(instance=trip)
	return render(request, 'trip_edit.html', {'t': trip, 'form': form})

class PlaceForm(ModelForm):
	website = forms.CharField(required=False)
	phone = forms.CharField(required=False)
	rating = forms.CharField(required=False)
	class Meta:
		model = SavedPlaces
		fields=('name','address','website','phone','rating')

@login_required()
def savePlaceToList(request, pk):
	if not request.user.is_authenticated():
		return HttpResponse("No Auth")
	if request.method == 'POST':
		trip = Trip.objects.get(pk=pk)
		placeForm = PlaceForm(request.POST)
		if placeForm.is_valid():
			Place = placeForm.save(commit=False)
			Place.trip = trip
			objs = SavedPlaces.objects.filter(trip=trip, name=Place.name, address=Place.address)
			if len(objs) == 0:
				Place.save()
			return HttpResponse(Place.id)
	return HttpResponse("Not OK")



@login_required()
def DeletePlace(request, pk):
	place = SavedPlaces.objects.get(trip__user=request.user, pk=pk)
	place.delete()
	return HttpResponse("OK")

