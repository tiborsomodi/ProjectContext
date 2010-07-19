package com.inepex.projectcontext;

import org.apache.tools.ant.Task;

public class SwitchContextTask extends Task{

	private String contextRelativePath;
	private String templateDir = "template";
	private String propDir = "property";
	private String propName = "";
	
	private String resourceName;
	private String contextName;
	private String outRelativePath;
		
	public void setContextRelativePath(String contextRelativePath) {
		this.contextRelativePath = contextRelativePath;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}



	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public void setOutRelativePath(String outRelativePath) {
		this.outRelativePath = outRelativePath;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public void execute() {
       SwitchContext sc = new SwitchContext(
    		   contextRelativePath, templateDir, propDir, propName, resourceName, contextName, outRelativePath
    		   , getProject().getProperty("basedir"));
       
       sc.doSwitch();
    }

}
