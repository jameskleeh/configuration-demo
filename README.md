Steps:

* Start a consul server accessible on port 8500
* Set `config/application/foo.bar` to "consul global" in the KV store
* Run the application
* Exercise / and verify it returns "consul global"
* Change the value in the Consul KV store
* Exercise the refresh endpoint with `curl -X POST http://localhost:8080/refresh`
* Exercise / and verify the value has not changed (incorrect behavior)
