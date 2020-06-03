# SingTranslator
>Sing translator is a mobile application develop on Android System. It its a translator for sing language. Was created for help to communicate with deaf people. This tool can translate from sing to english and english to sing language. In this readme i demonstrate how to implemnt and use by yourself.



## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)



## General info
The main purpose of creating this application was lern new techologies and create final project for postgraduate studies in WSB Universiti in Gdańsk on Mobile Application Develper.This app is two way translator. Sing to english translation was based on AI shared by Microsot on Azure platform. User simply just take photo of any sing and app will translate it. 



## Screenshots
<p align="center">
 <img src="./01.gif" data-canonical-src="./2.jpg" width="180" height="400" />
 <img src="./02.gif" data-canonical-src="./2.jpg" width="180" height="400" />
</p>



# Technologies
* Azure AI Custom Vision
* Android Studio
* Java
* Camera
* REST OkHttp3



## Setup

### Setup Azure AI 
  1. Create account on [Azure](https://azure.microsoft.com/)
  2. Login and create project on [CustomVision](https://www.customvision.ai/)
  3. Create Tags for each letter
  4. Each tag should be filled with at least 200 photos of sing. Photos should be different for better AI training
  
<p align="center"><img src="./03.png" data-canonical-src="./2.jpg" width="400"  /></p>

  5. Train yours AI 
  6. Copy and save prediction endpoint data
  
### Setup app
  1. Clone repo and change inside values/string.xml for yours prediction data
  
  ```xml
    <resources>    
        <string name="PredictionEndpoint">YOURS_ENDPOINT</string>
        <string name="PredictionKey">API_KEY</string>
        <string name="PredictionIterationName">YOURS_NAME</string>
    </resources>
```

  2. Voilà its is working :)



## Features
* English to sing translation
* Sing to english translation



## Status
Finished



## Contact
Feel free to contact Adrian Wozniak (adrianwozniak576@gmail.com)

