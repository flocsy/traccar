/*
 * Copyright 2015 - 2016 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.helper.Log;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ResourceErrorHandler implements ExceptionMapper<Exception> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceErrorHandler.class);

    @Override
    public Response toResponse(Exception e) {
        LOGGER.error("Unhandled exception", e);
        if (e instanceof WebApplicationException) {
            WebApplicationException exception = (WebApplicationException) e;
            String message;
            if (exception.getCause() != null) {
                message = Log.exceptionStack(exception.getCause());
            } else {
                message = Log.exceptionStack(exception);
            }
            return Response.fromResponse(exception.getResponse()).entity(message).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(Log.exceptionStack(e)).build();
        }
    }

}
