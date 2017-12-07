
'''
@author: Jw2476
'''
from flask import jsonify
from flask import render_template
from flask import Flask, request
from flask.ext.mongoengine import MongoEngine
from src import db
from src.user import User
from src.job import Job
from src.driverJob import DriverJob

from mongoengine.errors import ValidationError
from bson.objectid import ObjectId

app = Flask(__name__)
app.config.from_object('src.configue')
db.init_app(app)

@app.route('/checkExist',methods=['POST'])
def signup():
  userName = request.json['userName']
  userid = user.save()
  res = False
  for user in User.objects:
    if user.userName == userName:
      res = True
  return jsonify({'result' : res}) 


#This is just for testing
@app.route('/signup',methods=['POST'])
def signup():
  userName = request.json['userName']
  password = request.json['password']
  driver = request.json['driver']
  user = User(userName=userName,password=password, driver = driver)
  userid = user.save()
  return jsonify({'userId' : userid}) 

# POST to protect privacy
@app.route('/login',methods=['POST'])
def login():
  userName = request.json['userName']
  password = request.json['password']

  users = User.objects
  res = False
  for user in users:
    if str(user.userName) == userName and str(user.password) == password:
      res = True
      break
  # return jason format with result true as authenticate false as not authenticate
  return jsonify({'result' : res})


def matchDriver(paras):
  startTime, endTime, unitMaxprice,jobDist= paras[0],paras[1],paras[2],paras[3]
  lst = []
  jobs = DriverJob.objects
  for job in jobs:
    j_start = job.startTime
    j_end = job.endTime
    j_unitMinprice = job.unitMinprice
    j_maxDist = job.jobMaxDist
    if (j_start <= startTime) and (j_end >= endTime) and (j_unitMinprice <= unitMaxprice) and (j_maxDist >= jobDist):
      lst.append(job)
  return lst

def matchUser(paras):
  startTime, endTime, unitMinprice, jobMaxDist= paras[0],paras[1],paras[2],paras[3]
  lst = []
  jobs = Job.objects
  for job in jobs:
    j_start = job.startTime
    j_end = job.endTime
    j_unitMaxprice = job.unitMaxprice
    j_dist = job.jobDist
    if (j_start >= startTime) and (j_end <= endTime) and (j_unitMaxprice >= unitMinprice) and (jobMaxDist >=j_dist):
      lst.append(job)
  return lst

# After user post jobs, server side will do match 
@app.route('/postjob',methods=['POST'])
def postJob():
  userName = request.json['userName']
  startTime = request.json['startTime']
  endTime = request.json['endTime']
  price = request.json['price']
  unitMaxprice = request.json['unitMaxprice']
  roomNum = request.json['roomNum']
  jobDist = request.json['jobDist']
  job = Job(userName=userName, startTime=startTime,endTime=endTime,price=price,unitMaxprice = unitMaxprice, roomNum=roomNum,jobDist=jobDist)
  job.save()
  paras = [startTime, endTime, unitMaxprice,jobDist]
  lst = matchDriver(paras)
  return jsonify({'list' : lst}) 

@app.route('/postDriverjob',methods=['POST'])
def postDriverJob():
  userName = request.json['userName']
  startTime = request.json['startTime']
  endTime = request.json['endTime']
  unitMinprice = request.json['unitMinprice']
  jobMaxDist = request.json['jobMaxDist']
  driverJob = DriverJob(userName=userName, startTime=startTime,endTime=endTime, unitMinprice = unitMinprice, jobMaxDist=jobMaxDist)
  jobid = driverJob.save()
  paras = [startTime, endTime, unitMinprice, jobMaxDist]
  lst = matchUser(paras)
  return jsonify({'list' : lst}) 

# Using POST here to consist with front-end design
@app.route('/history',methods=['POST'])
def getJobHistory():
  userName = request.json['userName']
  driver = request.json['driver']
  if driver == 0:
    jobs = Job.objects
  else:
    jobs = DriverJob.objects

  lst = []
  for job in jobs:
    if job.userName == userName:
      lst.append(job)

  return jsonify({'jobHistory' : lst}) 

@app.route('/updateImage',methods=['PUT'])
def updateImage():
  userName = request.json['userName']
  userImage = request.json['userImage']
  users = User.objects
  result = "fail"
  for user in users:
    user_name = user.userName
    if user_name == userName:
      result = 'success'
      user.update(userImage = userImage)

  return jsonify({'result' : result}) 

if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0')
