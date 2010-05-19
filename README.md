hrana
===

Status
---
This is a planning document only. Hrana is still a work in progress, and certainly is not ready for any use.

Uses
---

hrana aims to be a simple edge cache. The major goals of hrana are:

* to move relatively small amounts of data close to where its needed (ie: configuration data to a webserver, etc...)
* to keep the aforementioned data relatively up to date
* to fan data out to thousands of machines

Following are the intended high level features of hrana. Almost none of these are currently implemented:

* hrana daemons are able to talk to themselves either to push or pull data across unreliable transmission mediums
    * hrana daemons can also load data from other sources (ie: memcache, file), and provides an easy to use plugin interface for custom data sources
    * hrana deamons can be configured to do either or both
* for both push and pull, hrana daemons are robust to network partitions
* hrana has a simple thrift interface (push(map<string, string>), pull(vector<string>), get(string))
* later versions of hrana may do data versioning

Following are the anti-features of hrana:

* replication of massive amounts of data (ie: cross colo database replication) *per daemon* (ie: one hrana daemon will not be designed to move GB of data)
* distributed transactions for data updates
* immediate consistency across multiple hrana daemons