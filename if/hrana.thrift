namespace cpp hrana.thrift
namespace java hrana.thrift
namespace perl Hrana.Thrift

enum ResultCode
{
	OK,
	TRY_LATER
}

service hrana
{
	ResultCode Get(1: string key);
	ResultCode Push(1: string key, 2: string value);
}
