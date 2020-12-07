# football-league

http url: `http://localhost:8085/api/service/v1/team/standing?teamName=Leeds&countryName=England&leagueName=Championship`


# Deployment league on GCP

## Creating cluster on GCP
1. In the browser go to cloud.sabre.com.
2. Login with your domain email/password.
3. From the drop down menu select *gcp-project-name/ID*
4. Kubernetes Engine
5. In Clusters tab click "create cluster"
    * For cluster name use e.g. "league-cluster"
    * Location type: Zonal
    * Zone: us-central1-c
    * Master version: Static, for the version just choose default one
6. On the left hand side menu expand "default pool"
    * Set number of nodes to 1
    * Enable autoscaling and set 1 for minimum and 3 for maximum number of nodes
    * Disable autoupgrade - we don't need it for adhoc cluster
7. On the left hand side menu expand click nodes
    * For machine type select e2-highcpu-8
8. On the left hand side menu expand click networking
    * *Select public cluster*
    * Select "Access master using its external IP address" if it is not selected
    * Enter 172.16.1.0/28 in Master IP range
9. Click create at bottom of the page and wait until cluster is created

## Login to GCP & authentication
In the console login to cloud lab:

Command-line access
Configure kubectl command-line access by running the following command:

gcloud auth login your-email@gmail.com
gcloud config set project your-project ID
gcloud auth configure-docker

gcloud container clusters get-credentials cluster-name --zone us-central1-c --project your-project ID

## Now you can use docker command to upload image to gcr container registry

docker tag anonimizer-poc-mq:latest us.gcr.io/elite-pointer-296112/anonimizer-poc-mq:latest
docker push us.gcr.io/elite-pointer-296112/anonimizer-poc-mq:latest

## GCP console deployment

Go to GCP kubernates cluster , click on deploy:
Select the league image from the container registry.
Provide the environment variable if any.
Click to create a container , publish port if requried and finally container can view on workload on left hand side. 


## Auto deployment from local

kubectl apply -f deployment/league-configmap.yml
kubectl apply -f deployment/league-deployment.yml
kubectl apply -f deployment/league-service-cluster-ip.yml
