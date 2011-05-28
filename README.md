Hrana
===

Hrana aims to be a simple edge cache. The major goals of Hrana are:

* to move relatively small amounts of data (like config values) close to where its needed
* to keep the aforementioned data relatively up to date
* to fan data out to thousands of machines

It provides a thrift interface and it's meant to run on the same machine as the code that will access it, for speed.