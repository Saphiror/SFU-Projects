from django.db import models
from django.contrib.auth.models import User
import ast

class ListField(models.TextField):
    __metaclass__ = models.SubfieldBase
    description = "Stores a python list"

    def __init__(self, *args, **kwargs):
        super(ListField, self).__init__(*args, **kwargs)

    def to_python(self, value):
        if not value:
            value = []

        if isinstance(value, list):
            return value

        return ast.literal_eval(value)

    def get_prep_value(self, value):
        if value is None:
            return value

        return str(value)

    def value_to_string(self, obj):
        value = self._get_val_from_obj(obj)
        return self.get_db_prep_value(value)

class Trip(models.Model):
	share_choices = (
		(0, 'Private'),
		(1, 'Unlisted'),
		(2, 'Public'),
	)

	user = models.ForeignKey(User)
	name = models.CharField(max_length=255)
	start = models.CharField(max_length=255)
	end = models.CharField(max_length=255)
	share_type = models.PositiveSmallIntegerField(choices=share_choices, default=0)


class SavedPlaces(models.Model):
    trip = models.ForeignKey(Trip)
    name = models.CharField(max_length=255)
    address = models.CharField(max_length=500)
    website = models.CharField(max_length=500, default="None")
    phone = models.CharField(max_length=20, default="None")
    rating = models.CharField(max_length=10, default="None")

    def __unicode__(self):
        return "%s %s %s %s %s" % (self.user, self.name, self.address, self.website, self.phone)
