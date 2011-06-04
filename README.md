# Hrana

Hrana aims to be a simple edge cache. The major goals of Hrana are:

* to move relatively small amounts of data (like config values) close to where its needed
* to keep the aforementioned data relatively up to date
* to fan data out to thousands of machines

# Building

Assuming you have sbt installed:

        sbt update && sbt compile && sbt run