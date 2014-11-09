ImageComparision Framework With Cucumber-jvm (Native App)
====================

This project exhibits how to combine BDD(Cucumber) approach with mobile automation. 

Prerequisite
=====================
1. Android SDK
2. Appium
3. Maven (For managing dependencies)
4. Eclipse (With Junit + Cucumber plugin)
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

Configuration and Settings
=====================
1. Configuration location:/src/test/resources/config_githubsearch_mobile_web.properties
2. device-name should follow specific naming conventions like android-nexus5/android-htc_one_x

Reference Images
=====================
1. Take application snapshots from different devices
2. Save images with specific names (E.g: GoogleSearchResultScreen.png or AppMenuScreen.png)
3. Save them to src/test/resources/reference-images/<device-name>

Pending Images
=====================
1. Take application snapshots from different devices
2. Save images with specific names (E.g: GoogleSearchResultScreen.png or AppMenuScreen.png)
3. Save them to src/test/resources/pending-images/<device-name>

Mask Images
=====================
1. Take application snapshots from different devices
2. Save images with specific names (E.g: GoogleSearchResultScreen.png or AppMenuScreen.png)
3. Save them to src/test/resources/mask-images/<device-name>

Diff Images
=====================
1. Take application snapshots from different devices
2. Save images with specific names (E.g: GoogleSearchResultScreen.png or AppMenuScreen.png)
3. Save them to /target/test-classes/resources/diff-images/<device-name>
4. Diff images attached to cucumber report

Installation
=====================
1. Install from git (using git clone)

Script Description
=====================
1. Native App:
	* apk Name: ApiDemos-debug.apk
	* configuration location: /src/test/resources
	* feature file Location: /src/test/resources/features
	* reference image files Location: /src/test/resources/reference-images/<device-name>/
	* pending image files Location: /src/test/resources/pending-images/<device-name>/
	* mask image files Location: /src/test/resources/mask-images/<device-name>/
	* Type of Application: Native App
	
Procedure to run test
=====================
1. Start appium server
2. run automation from project directory using mvn clean test


Known issues and limitations 
=====================
1. Since all scripts have been tested on real device (Nexus 5), not tested on other devices)
2. This framework uses Appium and Selendroid for application instrumentation, hence it override all limitations of Appium and Selendroid.

( **Note**: This is just reference project, hence defined feature file contains just basic Gherkins sentences.)