package com.inepex.projectcontext;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

public class VelocityUtil {

	public static String generate(Properties props, String templateAsString) throws Exception {
		
		String res = "";
		Velocity.addProperty("resource.loader", "string");
		Velocity.addProperty("string.resource.loader.description",
				"Velocity StringResource loader");
		Velocity
				.addProperty("string.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.StringResourceLoader");
		Velocity
				.addProperty("string.resource.loader.repository.class",
						"org.apache.velocity.runtime.resource.util.StringResourceRepositoryImpl");
		Velocity.addProperty("string.resource.loader.repository.name", "repo");
		Velocity.addProperty("input.encoding", "UTF-8");

		Velocity.init();
		StringResourceRepository repo = StringResourceLoader.getRepository("repo");

		String templateName = "/templates/thetemplate.vm";
		repo.putStringResource(templateName, templateAsString);


		VelocityContext context = new VelocityContext();

		for (Object keyObj : props.keySet()){
			String key = (String) keyObj;
			context.put(key, props.get(key));
			
		}
		
		Template template = null;

		template = Velocity.getTemplate(templateName);

		StringWriter sw = new StringWriter();

		template.merge(context, sw);

		res = sw.toString();
		return res;

	}
}
