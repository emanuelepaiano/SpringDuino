/*
 * Copyright 2018 Emanuele Paiano.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package springduino.mvc.controllers;

import springduino.mvc.services.HelperService;
import springduino.mvc.utils.JsonResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lele
 */
@RestController
@RequestMapping("/api/extra")
public class HelperRestController {
    
    @Autowired
    HelperService helperService;
    
    @GetMapping("/echoagent")
    public ResponseEntity<JsonResponseBody> echoAgent(HttpServletRequest request){
        return new ResponseEntity<JsonResponseBody>(new JsonResponseBody(HttpStatus.OK.value(), helperService.getAgentString(request)), HttpStatus.OK);
    }
    
    @GetMapping("/echodate")
    public ResponseEntity<JsonResponseBody> echoDate(HttpServletRequest request){
        return new ResponseEntity<JsonResponseBody>(new JsonResponseBody(HttpStatus.OK.value(), (String)helperService.getCurrentDate().toString()), HttpStatus.OK);
    }

    @GetMapping("/echobrowser")
    public ResponseEntity<JsonResponseBody> echoBrowser(HttpServletRequest request){
        return new ResponseEntity<JsonResponseBody>(new JsonResponseBody(HttpStatus.OK.value(), (String)helperService.getAgentObject(request).getName().toString()), HttpStatus.OK);
    }
    
    @GetMapping("/echooperatingsystem")
    public ResponseEntity<JsonResponseBody> echoOperatingSystem(HttpServletRequest request){
       
        return new ResponseEntity<JsonResponseBody>(new JsonResponseBody(HttpStatus.OK.value(), (String)helperService.getAgentObject(request).getOperatingSystem().toString()), HttpStatus.OK);
    }
    
    
}
