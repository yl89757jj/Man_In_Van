from src import db
from datetime import datetime


class Job(db.Document):
    userName = db.StringField(required=True, default='')
    startTime = db.StringField(required=True, default='')
    endTime = db.StringField(required=True, default='')
    price = db.IntField(required=True, default=0)
    unitMaxprice = db.IntField(required=True, default=0)
    jobDist = db.IntField(required=True, default=0)
    roomNum = db.IntField(required=True, default=0)
    
    # content = db.StringField(required=True,default='This guy does not level anything')
    # author = db.StringField(required=True,default='Woodley')
    # date = db.DateTimeField(default=datetime.now())