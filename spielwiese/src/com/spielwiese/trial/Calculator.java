
/** 
 * Copyright 2014 SSI Schaefer PEEM GmbH. All Rights reserved. 
 * <br /> <br />
 * 
 * $Id$
 * <br /> <br />
 *
 */

package com.spielwiese.trial;


/**
 * This is the class header. The first sentence (ending with "."+SPACE) is important,
 * because it is used summary in the package overview pages.<br />
 * <br />
 *
 *
 * @author  ctr
 * @version $Revision$
 */

public class Calculator {
	
	public DummyContainer add(DummyContainer augend, DummyContainer addend) {
		DummyContainer sum = new DummyContainer();
		sum.setValue(augend.getValue() + addend.getValue());
		return sum;
	}

}


//---------------------------- Revision History ----------------------------
//$Log$
//
