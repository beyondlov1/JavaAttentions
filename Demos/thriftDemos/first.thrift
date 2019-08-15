namespace java com.beyond.thrift

struct User {
    1: string name
    2: i16 age
}

service MyService {
    User getUser()
}
