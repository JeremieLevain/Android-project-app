---- US Daily CORONAVIRUS DATA android app project ----
    ~ Jeremie LEVAIN & Robin FERRAND ~

Android app realized as part of an educational project at Mines de Saint-Etienne ISMIN

-----------Introduction--------------

The coronavirus outbreak inspired us for the choice of this project's topic as it is really interesting to track data.
Such data also allows a lot of possibilities regarding vizualisations : it represents a chance to enrich our data analyst experience.

Motivated from a story available in this public article:
https://www.sciencealert.com/17-year-old-turned-down-8-million-to-keep-his-viral-coronavirus-tracker-ad-free


This android app is linked to a Rest API accessible at : https://DailyUScoronavirus-App-JLN-RFD.cleverapps.io itself using another (PUBLIC) API (https://api.covidtracking.com)
using https://square.github.io/retrofit/

The app contains :
- 3 fragments
	1. "DAILY LIST" listing all the US daily coronavirus data
	2. "GRAPH" offering visualizations on the coronavirus outbreak in the US with different features as of selecting a timerange (Year/Month) or displaying the moving average (3days)
	3. "INFO" containing information about the app such as its creators and its data source.

- 2 activities
	1. A Main activity to handle all of the three fragments and buttons there available
	2. A detailed activity to show more information on a daily. The idea being not to display too much information on the main activity, that would confuse the user.


Improvement proposals :
	1. Work around DateTime format and use BarCharts (more relevant?)
	2. Implement possibility to change a daily in favorite