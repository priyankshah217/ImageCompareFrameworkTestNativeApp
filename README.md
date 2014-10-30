ImageComparision Framework With Cucumber-jvm
====================

This project exhibits how to combine BDD(Cucumber) approach with mobile automation. 

Prerequisite
=====================
1. Android SDK
2. Appium
3. Maven (For managing dependencies)
4. Eclipse (With TestNG + Cucumber plugin)
5. ImageMagik 
(http://www.imagemagick.org/script/install-source.php, 
for debian users install using 
sudo apt-get update
sudo apt-get install imagemagick --fix-missing )
6. im4java (Maven dependencies has to be added,
		<dependency>
			<groupId>org.im4java</groupId>
			<artifactId>im4java</artifactId>
			<version>1.4.0</version>
		</dependency>
	)

Installation
=====================
1. Install from git (using git clone)
2. Run "mvn clean install" from project directory

Script Description
=====================
1. ApiDemoAppStepDefs:
	* apk Name: ApiDemos-debug.apk
	* apk Location: /src/test/resources
	* feature file Location: /src/test/resources/features
	* reference image files Location: /src/test/resources/reference-images/<device-name>/
	* Type of Application: Native
	* Gesture Simulated: Swipe/Scroll
	
Known Issues
=====================
1. Since all scripts have been tested on real device (Nexus 5), not tested on other devices)

( **Note**: This is just reference project, hence defined feature file contains just basic Gherkins sentences.)