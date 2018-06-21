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
package springduino.mvc.utils;

/**
 *
 * @author lele
 */
public class JsonResponseBody {
        int server;
        Object response;

        public JsonResponseBody(int server, Object response){
                this.server=server;
                this.response=response;
        }

        public int getServer() {
            return server;
        }

        public void setServer(int server) {
            this.server = server;
        }

        public Object getResponse() {
            return response;
        }

        public void setResponse(Object response) {
            this.response = response;
        }
        
        
    }
