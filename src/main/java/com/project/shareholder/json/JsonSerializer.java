/*******************************************************************************
 * CLINOVO RESERVES ALL RIGHTS TO THIS SOFTWARE, INCLUDING SOURCE AND DERIVED BINARY CODE. BY DOWNLOADING THIS SOFTWARE YOU AGREE TO THE FOLLOWING LICENSE:
 *
 * Subject to the terms and conditions of this Agreement including, Clinovo grants you a non-exclusive, non-transferable, non-sublicenseable limited license without license fees to reproduce and use internally the software complete and unmodified for the sole purpose of running Programs on one computer.
 * This license does not allow for the commercial use of this software except by IRS approved non-profit organizations; educational entities not working in joint effort with for profit business.
 * To use the license for other purposes, including for profit clinical trials, an additional paid license is required. Please contact our licensing department at http://www.clincapture.com/contact for pricing information.
 *
 * You may not modify, decompile, or reverse engineer the software.
 * Clinovo disclaims any express or implied warranty of fitness for use.
 * No right, title or interest in or to any trademark, service mark, logo or trade name of Clinovo or its licensors is granted under this Agreement.
 * THIS SOFTWARE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND. CLINOVO FURTHER DISCLAIMS ALL WARRANTIES, EXPRESS AND IMPLIED, INCLUDING WITHOUT LIMITATION, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NONINFRINGEMENT.

 * LIMITATION OF LIABILITY. IN NO EVENT SHALL CLINOVO BE LIABLE FOR ANY INDIRECT, INCIDENTAL, SPECIAL, PUNITIVE OR CONSEQUENTIAL DAMAGES, OR DAMAGES FOR LOSS OF PROFITS, REVENUE, DATA OR DATA USE, INCURRED BY YOU OR ANY THIRD PARTY, WHETHER IN AN ACTION IN CONTRACT OR TORT, EVEN IF ORACLE HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. CLINOVO'S ENTIRE LIABILITY FOR DAMAGES HEREUNDER SHALL IN NO EVENT EXCEED TWO HUNDRED DOLLARS (U.S. $200).
 *******************************************************************************/

package com.project.shareholder.json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * JsonSerializer.
 */
public class JsonSerializer extends MappingJackson2HttpMessageConverter {

    private static final Logger logger = LoggerFactory.getLogger(JsonSerializer.class);

    public static final String ACCEPT = "accept";

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        String accept = "";
        boolean proceed = true;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest();
            accept = request.getHeader(ACCEPT);
            String requestURI = request.getRequestURI();
            requestURI = requestURI.concat(requestURI.endsWith("/") ? "" : "/");
            proceed = !requestURI.equals(request.getContextPath().concat(request.getServletPath()).concat("/odm/"))
                    && !requestURI.equals(request.getContextPath().concat(request.getServletPath()).concat("/wadl/"));
        } catch (Exception ex) {
            logger.error("Error has occurred.", ex);
        }

        return (accept == null || !accept.contains(MediaType.APPLICATION_JSON_VALUE)) && proceed;
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        RestApiJsonContainer container = new RestApiJsonContainer();
        container.setVersion("1.0.0");
        if(object instanceof Error) {
            Error error = (Error) object;
            try {
                container.setErrorCode(Integer.parseInt(error.getStatus()));
            } catch (NumberFormatException ex) {
                container.setErrorCode(HttpServletResponse.SC_BAD_REQUEST);
            }

            container.setMessage(error.getMessage());
        } else {
            container.setData(object);
        }

        super.writeInternal(container, type, outputMessage);
    }
}

