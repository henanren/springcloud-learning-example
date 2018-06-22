# spring-cloud-sleuth-zipkin

## 服务列表

- **spring-cloud-eureka：** 注册中心
- **spring-cloud-zuul：**网关
- **trace-1：** 链路的第一个节点，通过restTemplate调用trace-2，并通过@LoadBalanced注解开启ribbon负载均衡
- **trace-2：** 链路的第二个节点，返回调用结果给trace-1
- **zipkin-server：** zipkin服务端



