package com.tsf.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
   @Autowired
   private RestTemplate restTemplate;
   @Autowired
   private AsyncRestTemplate asyncRestTemplate;
   @Autowired
   private  EchoService echoService;
   @RequestMapping(value = "/echo-rest/{str}", method = RequestMethod.GET)
   public String rest(@PathVariable String str) {
       return restTemplate.getForObject("http://provider-demo/echo/" + str, String.class);
   }
   @RequestMapping(value = "/echo-async-rest/{str}", method = RequestMethod.GET)
   public String asyncRest(@PathVariable String str) throws Exception{
       ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.
               getForEntity("http://provider-demo/echo/"+str, String.class);
       return future.get().getBody();
   }
   @RequestMapping(value = "/echo-feign/{str}", method = RequestMethod.GET)
   public String feign(@PathVariable String str) {
       return echoService.echo(str);
   }
}