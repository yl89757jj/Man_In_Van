from src import db
from datetime import datetime


class User(db.Document):
    userName = db.StringField(required=True, default='')
    password = db.StringField(required=True, default='')
    driver = db.IntField(required=True, default=0)
    #userImage = db.StringField(required=True, default='')
    userImage = db.StringField(default='')
    jobsId = db.ListField(db.StringField(), default=[])

