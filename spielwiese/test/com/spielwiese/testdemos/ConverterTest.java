/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id$
 * <br /> <br />
 *
 */

package com.spielwiese.testdemos;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.spielwiese.trial.Converter;

/**
 * This is the class header. The first sentence (ending with "."+SPACE) is important,
 * because it is used summary in the package overview pages.<br />
 * <br />
 *
 *
 * @author  ctr
 * @version $Revision$
 */
@RunWith(MockitoJUnitRunner.class)
public class ConverterTest {
	@Mock
	private String graphmlPath;

	@Mock
	private String workflowCfgPath;
	@InjectMocks
	private Converter converter;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}

//---------------------------- Revision History ----------------------------
//$Log$
//
