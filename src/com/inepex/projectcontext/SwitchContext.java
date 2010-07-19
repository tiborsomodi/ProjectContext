package com.inepex.projectcontext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.tools.ant.BuildException;

import com.inepex.projectcontext.util.FileUtil;

public class SwitchContext {
	
	private String scriptExecutionDir;
	private String contextPath;
	private String templateDir;
	private String propDir;
	private String propName;
	
	private String resourceName;
	private String contextName;
	private String outPath;
	private Properties props;
	
	public SwitchContext(String contextPath, String templateDir,
			String propDir, String propName, String resourceName, String contextName,
			String outPath, String scriptExecutionDir) {
		super();
		this.contextPath = contextPath;
		this.templateDir = templateDir;
		this.propDir = propDir;
		this.propName = propName;
		this.resourceName = resourceName;
		this.contextName = contextName;
		this.outPath = outPath;
		this.scriptExecutionDir = scriptExecutionDir;
	}

	public void doSwitch(){
		try {
			readProps();
			generateFromTemplate();		
		} catch (ProjectContextException e){
			throw new BuildException(e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
		}
	
	}

	private void readProps() throws Exception {
		props = new Properties();
		String path;
		if (propName.equals("")){
			propName = resourceName;
		}
			
		path = scriptExecutionDir + "/" + contextPath + "/" + propDir + "/" + propName + "_" + contextName + ".props";

		try {
		props.load(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			throw new ProjectContextException(Strings.propNotFound + contextName + "Searched as: " + path);
		}
	}
	
	private void generateFromTemplate() throws Exception{
		String template = FileUtil.readFile(scriptExecutionDir + "/" + contextPath + "/" + templateDir + "/" + resourceName);
		String result = transform(template);
		FileUtil.writeFile(scriptExecutionDir + "/" + outPath + "/" + resourceName, result);
	}

	private String transform(String template) throws Exception {
		return VelocityUtil.generate(props, template);
	}

	
}
