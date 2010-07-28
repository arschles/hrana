Hrana
===

Status
---
Hrana is still a work in progress. It currently support (but is not tested with) the local file datasource, and nothing else. It is certainly not ready for production use!

Overview
---

Hrana aims to be a simple edge cache. The major goals of Hrana are:

* to move relatively small amounts of data close to where its needed (ie: configuration data to a webserver, etc...)
* to keep the aforementioned data relatively up to date
* to fan data out to thousands of machines

Following are the intended high level features of Hrana. Almost none of these are currently implemented:

* Hrana daemons are able to talk to themselves either to push or pull data across unreliable transmission mediums
    * Hrana daemons can also load data from other sources (ie: memcache, file), and provides an easy to use plugin interface for custom data sources
    * Hrana deamons can be configured to do either or both
* for both push and pull, Hrana daemons are robust to network partitions
* Hrana has a simple thrift interface (push(map<string, string>), pull(vector<string>), get(string))
* later versions of Hrana may do data versioning

Following are the anti-features of Hrana:

* replication of massive amounts of data (ie: cross colo database replication) *per daemon* (ie: one Hrana daemon will not be designed to move GB of data)
* distributed transactions for data updates
* immediate consistency across multiple Hrana daemons