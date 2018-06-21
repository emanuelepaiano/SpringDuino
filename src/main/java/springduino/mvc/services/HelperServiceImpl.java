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
package springduino.mvc.services;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author lele
 */
@Service
public class HelperServiceImpl implements HelperService{
    
    public String getAgentString(HttpServletRequest request){
        return request.getHeader("User-Agent");
    }
    
    public Date getCurrentDate(){
        return new Date();
    }
    
    
    public ReadableUserAgent getAgentObject(HttpServletRequest request){
        UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
	ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
        return agent;
    }
    
    
}
