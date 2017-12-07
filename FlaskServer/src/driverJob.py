from src import db
from datetime import datetime


class DriverJob(db.Document):
    userName = db.StringField(required=True, default='')
    startTime = db.StringField(required=True, default='')
    endTime = db.StringField(required=True, default='')
    unitMinprice = db.IntField(required=True, default=0)
    jobMaxDist = db.IntField(required=True, default=0)