# Fagito deployment

Deployment is done using ConcourseCI

##Concourse setup

fly -t fagito login -c http://34.244.168.182:8080/

##Adding Pipeline

fly -t fagito set-pipeline -p EB-Deploy -c deploy-EB.yml --var "AWS_ACCESS_KEY_ID=<<key>>" --var "AWS_SECRET_ACCESS_KEY=<<secret>>" --var "USER=<<user>>" --var "PASS=<<passsword>>"
