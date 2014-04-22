
/** 
 * Copyright 2013 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id: WorkflowConverter.java,v 1.1 2014/02/13 12:12:07 m.kegele Exp $
 * <br /> <br />
 *
 */

package com.spielwiese.trial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import peem.tools.xml.XSLTProcessor;

import com.google.common.base.Charsets;
import com.google.common.io.Files;


/**
 * This is the class header. The first sentence (ending with "."+SPACE) is important,
 * because it is used summary in the package overview pages.<br />
 * <br />
 *
 *
 * @author  m.kegele
 * @version $Revision: 1.1 $
 */

public class WorkflowConverter {
  
  private static final String WF2GRAPHML_XSLT = "java/impl/com/ssp/support/workflow/resources/wf2graphml.xslt";

  /**
   * Converts a work flow configuration into a graphml file. The used notation is BPMN 2.0.
   * 
   * @param workflowCfgPath absolute or relative path to work flow configuration
   * @param graphmlPath absolute or relative path to the target file
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void convertWorkFlowToGraphml(String workflowCfgPath, String graphmlPath) throws FileNotFoundException, IOException {
    // parse XSLT
    XSLTProcessor instance = XSLTProcessor.getInstance(new File(WF2GRAPHML_XSLT));
   
    // transform XML
    String transform = instance.transform(Files.newReader(new File(workflowCfgPath), Charsets.UTF_8));

    // write to file
    Files.write(transform, new File(graphmlPath), Charsets.UTF_8);
  }
}


//---------------------------- Revision History ----------------------------
//$Log: WorkflowConverter.java,v $
//Revision 1.1  2014/02/13 12:12:07  m.kegele
//feature: workflow transformer to graphml
//jira: RD-273
//
//
