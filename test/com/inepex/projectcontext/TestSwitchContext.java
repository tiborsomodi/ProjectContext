package com.inepex.projectcontext;

import static org.junit.Assert.*;
import org.junit.Test;

import com.inepex.projectcontext.util.FileUtil;

public class TestSwitchContext {

	private String contextPath = "context";
	private String templateDir = "template";
	private String propDir = "property";
	private String resourceName =  "config.xml";
	private String contextName_soti = "soti";
	private String outPath = "out";
	private String expexted_soti = "Sample config\n\n" +
						"prop1: prop1Value\n" +
						"prop2: 2\n" + 
						"prop3: true\n";
	
	private String contextName_soti2 = "soti2";
	private String expexted_soti2 = "Sample config\n\n" +
	"prop1: prop1Value2\n" +
	"prop2: 3\n" + 
	"prop3: false\n";
	
	@Test public void switchTest() throws Exception {
		String content = switchHelper(contextName_soti, "");
		assertEquals(expexted_soti, content);
		
		content = switchHelper(contextName_soti2, "");
		assertEquals(expexted_soti2, content);
	}
	
	@Test public void invalidContextTest() throws Exception {
		try {
		String content = switchHelper("invalid", "");
		} catch (Exception e) {
			assertTrue(e.getMessage().startsWith(Strings.propNotFound));
		}
		
		
	}
	
	@Test public void switchWithExplicitPropNameTest() throws Exception {
		String content = switchHelper(contextName_soti, "common");
		assertEquals(expexted_soti, content);
		
		content = switchHelper(contextName_soti2, "common");
		assertEquals(expexted_soti2, content);
	}
	
	private String switchHelper(String contextName, String propName) throws Exception{
		SwitchContext sc = new SwitchContext(
				contextPath
				, templateDir
				, propDir
				, propName
				, resourceName
				, contextName
				, outPath
				, ".");
		sc.doSwitch();
		
		String newContent = FileUtil.readFile(outPath + "/" + resourceName);
		return newContent;
	}
}
