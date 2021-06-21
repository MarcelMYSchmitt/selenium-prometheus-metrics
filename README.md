# Introduction

This repository contains a small spring boot application which does a selenium test and publish metrics for a (not) successfull login.

<br />


The login/application which will be tested use the Mercedes Me Login interface (CIAM). It has an own login button integrated which redirects to the login interface. There you have to set the username / userpassword. Afterwards you will get redirected to your landing page.  

<br />

You can run it by using with or without docker. Dependent on where you want to use it (with/without docker) you have to make sure that the system environment property in `SeleniumConfig` is set correctly. On a windows machine you can use the `geckodriver.exe`, in docker you will use the linux pendant. In the Dockerfile you can see that it will be installed automatically. Please make sure to set the right properties in `application.yaml` or in the `docker-compose` file.

<br />

You can see the self-defined metric `LOGIN_AVAILABILITY` via `localhost:8080/actuator/prometheus` (without docker) or via `localhost:1234/actuator/prometheus` (with docker). 

<br />

## Usecase

Why should you do this kind of selenium / prometheus stuff? With this integration and self-defined metric you could easily monitor that your application provides a running login mechanism. You can deploy this application via docker image in a Kubernetes Cluster and then scrape the provided metrics via Prometheus and integrate them in a Prometheus AlertManager or in Grafana Dashboards.