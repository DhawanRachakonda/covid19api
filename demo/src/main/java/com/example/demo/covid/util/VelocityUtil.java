/**
 * 
 */
package com.example.demo.covid.util;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Component;

import com.example.demo.covid.dtos.SuspectionReport;

/**
 * @author explorervarun
 *
 */
@Component
public class VelocityUtil {

	public String getEmailTemplate(SuspectionReport report,String covidLocation) {

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();
		Template template = new Template();
		template = velocityEngine.getTemplate("/templates/email.vm");
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("name", report.getName());
		velocityContext.put("aadhar", report.getAadharNo());
		velocityContext.put("email", report.getEmail());
		velocityContext.put("address", report.getAddressLine1() + "," +report.getAddressLine2() + "," +
		report.getCity() + "," + report.getState() + "," + report.getPincode());
		velocityContext.put("mobile", report.getMobileNo());
		velocityContext.put("contactDate", report.getDateOfSusInf());
		velocityContext.put("contactLocation", covidLocation);
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		return stringWriter.toString();
	}
}