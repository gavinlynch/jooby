# jooby-hazelcast

Exports a {{hazelcast}} instances and optionally a [session store]({{defdocs}}/hazelcast/HcastSessionStore.html) session store.

## dependency

```xml
<dependency>
  <groupId>org.jooby</groupId>
  <artifactId>jooby-hazelcast</artifactId>
  <version>{{version}}</version>
</dependency>
```

## usage

```java
{
  use(new Hcast());

  get("/", req -> {
    HazelcastInstance hcast = req.require(HazelcastInstance.class);
    ...
  });
}
```

## configuration

Any property under ```hazelcast.*``` will be automatically add it while bootstrapping a ```HazelcastInstance```.

Configuration can be done programmatically via: ```doWith(Consumer)```

```java
{
  use(new Hcast()
   .doWith(config -> {
     config.setXxx
   })
  );
}
```

## session store

```java
{
  use(new Hcast());

  session(HcastSessionStore.class);

  get("/", req -> {
   req.session().set("name", "jooby");
  });
}
```

### options

#### timeout

By default, a {{hazelcast}} session will expire after ```30 minutes```. Changing the default timeout is as simple as:

```properties
# 8 hours
session.timeout = 8h

# 15 seconds
session.timeout = 15

# 120 minutes
session.timeout = 120m

# no timeout
session.timeout = -1
```

#### name
Default session's name is ```sessions```. It's possible to change the default name by setting the property: ```hazelcast.sesssion.name```.

Happy coding!!!

{{appendix}}
