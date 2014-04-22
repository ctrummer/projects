
/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id$
 * <br /> <br />
 *
 */

package com.spielwiese.trial;

import java.io.FileNotFoundException;
import java.io.IOException;



/**
 * This is the class header. The first sentence (ending with "."+SPACE) is important,
 * because it is used summary in the package overview pages.<br />
 * <br />
 *
 *
 * @author  ctr
 * @version $Revision$
 */

public class Converter {
	static String workflowCfgPath = "C:\\Users\\ctr\\Documents\\workspaces\\WAMAS-C-HEAD\\cfg\\pcs\\slotting.service.new.xml";
	static String graphmlPath = "C:\\Users\\ctr\\Documents\\tmp\\graph.graphml";

	public static void main(String[] args) {
		
		
		try {
			WorkflowConverter.convertWorkFlowToGraphml(workflowCfgPath, graphmlPath);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	

	}

}


//---------------------------- Revision History ----------------------------
//$Log$
//
