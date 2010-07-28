namespace cpp hrana.thrift
namespace java hrana.thrift
namespace perl Hrana.Thrift
namespace py hrana.thrift

exception HranaException
{
    1: i64 error_code,
    2: string error_string
}

service Hrana
{
    //get whether or not key exists
    bool Has(1: string key);
    
    //get a given key. throws if key doesn't exist
	string Get(1: string key) throws(1: HranaException e),

	//get all key-value-pairs that this hrana server has stored
	map<string, string> GetAll(),

	//push all a key-value pair to this hrana server. optionally choose not to overwrite existing keys, and throw if the key already exists
	bool Push(1: string key, 2: string value, 3: bool overwrite) throws(1: HranaException e),

	//push many key-value-pairs to Hrana. overwrites any keys that already existed
	oneway void PushBulkAsync(1: map<string, string> vals)

	//clear all key-value-pairs from this Hrana server
	bool Clear();
}
