package com.unizg.foi.GetInformations.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unizg.foi.GetInformations.model.app.HelloModel;

@RestController
public class HelloRestController
{
    @RequestMapping(value = "/rest/hello", method = RequestMethod.GET)
    public HelloModel helloRest()
    {
        return new HelloModel("Hello world from Rest servis");
    }
}
